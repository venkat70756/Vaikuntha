package com.adobe.aem.Vaikuntha.core.services.impl;

import com.adobe.aem.Vaikuntha.core.services.DemoService;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = DemoService.class)
public class DemoServiceImpl implements DemoService {

    private String text = "This text is coming from the Osgi service";

    private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public String getVenkat() {
        log.info("getVenkat Mehtod triggered");
        return text;
    }
}
