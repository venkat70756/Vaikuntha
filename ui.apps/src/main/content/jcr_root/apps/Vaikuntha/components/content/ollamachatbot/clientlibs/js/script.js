(function (document) {

    document.addEventListener('DOMContentLoaded', function () {

        const chatbot = document.getElementById('voollama-chatbot');

        if (!chatbot) {
            console.warn('Ollama chatbot markup not found on this page');
            return;
        }

        const chatWindow = chatbot.querySelector('.chat-window');
        const chatIcon = chatbot.querySelector('#voollama-chat-icon');

        const messagesContainer = chatWindow.querySelector('.messages');
        const input = chatWindow.querySelector('.chat-input');
        const sendBtn = chatWindow.querySelector('.send-btn');
        const closeBtn = chatWindow.querySelector('.close-btn');
        const minimizeBtn = chatbot.querySelector('.minimize-btn');

        /* ---------------- UI Actions ---------------- */

        closeBtn.addEventListener('click', () => {
            chatWindow.style.display = 'none';
            chatIcon.style.display = 'none';
        });

        minimizeBtn.addEventListener('click', () => {
            chatWindow.style.display = 'none';
            chatIcon.style.display = 'flex';
        });

        chatIcon.addEventListener('click', () => {
            chatWindow.style.display = 'flex';
            chatIcon.style.display = 'none';
        });

        /* ---------------- CSRF Token ---------------- */

        function getCsrfToken() {
            return fetch('/libs/granite/csrf/token.json', {
                credentials: 'same-origin'
            })
                .then(res => {
                    if (!res.ok) throw new Error('Failed to fetch CSRF token');
                    return res.json();
                })
                .then(data => data.token);
        }

        /* ---------------- Chat Logic ---------------- */

        let busy = false; // <-- flag to prevent multiple requests

        const sendMessage = () => {
            if (busy) return; // prevent new request if one is in progress

            const text = input.value.trim();
            if (!text) return;

            addMessage('user', text);
            input.value = '';
            busy = true;
            sendBtn.disabled = true;
            input.disabled = true;

            addMessage('loading', 'Ollama is typing...');

            getCsrfToken()
                .then(token => {
                    return fetch('/bin/ollama/chat', {
                        method: 'POST',
                        credentials: 'same-origin',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                            'CSRF-Token': token
                        },
                        body: `prompt=${encodeURIComponent(text)}`
                    });
                })
                .then(res => {
                    if (!res.ok) throw new Error('Chat request failed');
                    return res.json();
                })
                .then(data => {
                    removeLoading();
                    const formatted = formatAIResponse(data.response || 'Sorry, I could not understand.');
                    addMessage('bot', formatted, true);
                })
                .catch(err => {
                    removeLoading();
                    addMessage('bot', 'Error: Unable to connect to Ollama.');
                    console.error('Ollama error:', err);
                })
                .finally(() => {
                    busy = false;
                    sendBtn.disabled = false;
                    input.disabled = false;
                    input.focus();
                });
        };

        sendBtn.addEventListener('click', sendMessage);
        input.addEventListener('keypress', e => {
            if (e.key === 'Enter') sendMessage();
        });

        /* ---------------- Helpers ---------------- */

        function addMessage(sender, text, isHtml = false) {
            const div = document.createElement('div');
            div.className = `message ${sender}`;
            if (isHtml) {
                div.innerHTML = text; // allow HTML formatting
            } else {
                div.textContent = text;
            }
            messagesContainer.appendChild(div);
            messagesContainer.scrollTop = messagesContainer.scrollHeight;
        }

        function removeLoading() {
            const loading = messagesContainer.querySelector('.message.loading');
            if (loading) loading.remove();
        }

        /* ---------------- AI Text Formatter ---------------- */

        function formatAIResponse(text) {
            if (!text) return '';

            let formatted = text;

            // 1. Convert numbered lists: 1. xxx 2. xxx => <ul><li>xxx</li></ul>
            const listRegex = /(?:\d+\.)\s*([^\d]+)/g;
            if (listRegex.test(formatted)) {
                formatted = formatted.replace(listRegex, '<li>$1</li>');
                formatted = '<ul>' + formatted + '</ul>';
            }

            // 2. Bold headings (lines ending with :)
            formatted = formatted.replace(/^(.+?):/gm, '<b>$1:</b>');

            // 3. Preserve line breaks
            formatted = formatted.replace(/\n/g, '<br/>');

            return formatted;
        }

    });

})(document);
