package com.adobe.aem.Vaikuntha.core.schedulers;

import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = AccountStatementScheduler.AccountStatementConfiguration.class )
public class AccountStatementScheduler implements Runnable {

    @Reference
    private Scheduler scheduler;

    private static final Logger log = LoggerFactory.getLogger(AccountStatementScheduler.class);

    String CORN_EXPRESSION;

    String JOB_NAME;

    @ObjectClassDefinition(name = "Account Statement Configuration")
    public @interface AccountStatementConfiguration{

        @AttributeDefinition(name = "Cron Expression",type = AttributeType.STRING)
        public String getCronExpression();

        @AttributeDefinition(name = "JOB Name",type = AttributeType.STRING)
        public String getJobName();
    }


    @Activate
    @Modified
    protected void activate(AccountStatementConfiguration accountStatementConfiguration){
        CORN_EXPRESSION = accountStatementConfiguration.getCronExpression();
        JOB_NAME = accountStatementConfiguration.getJobName();

        log.info("AccountStatementScheduler Activate method triggered");
        scheduler.schedule(this,scheduler.EXPR(CORN_EXPRESSION).name(JOB_NAME).canRunConcurrently(false));
    }

    @Override
    public void run() {
        log.info("Monthly Account statement successfully sent to customer");
    }
}



/*
* @Component(service=interface.class, immediate=true)
*
* @Component(service=Servlet.class)
*
* @Component(service=Runnable.class, immediate=true)
*
*
* 1) if you want call the any services or OSGIServices into another services(OSGI service, Schedulers, Servlets)
* we can use the @Reference Annotation.
*
* 2) if you want call the any service or OSGi service into sling model we have to use @OSGIService annotation.
*
* 0/30 * * * * ?
 */