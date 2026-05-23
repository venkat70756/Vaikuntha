package com.adobe.aem.Vaikuntha.core.schedulers;

import com.adobe.aem.Vaikuntha.core.services.impl.TestConfiguration;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = TestSchedulers.Config.class)
public class TestSchedulers implements Runnable{

    private String JOB_NAME;

    private String cornExpression;

    @ObjectClassDefinition(name = "Test Scheduler Config" )
    public @interface Config{
        @AttributeDefinition(name ="Cron Expression", type = AttributeType.STRING)
        public String getCornExpression() default "*/5 * * * * ?";


        @AttributeDefinition(name = "JOB Name", type = AttributeType.STRING)
        public String getJobName() default "Test JOB";
    }

    @Reference
    private Scheduler scheduler;

    private static final Logger log = LoggerFactory.getLogger(TestSchedulers.class);



    @Activate
    protected void activate(Config config){

        JOB_NAME = config.getJobName();
        cornExpression = config.getCornExpression();

        log.info("Scheduler Activate method ");


        scheduler.schedule(this, scheduler.EXPR(cornExpression).name(JOB_NAME).canRunConcurrently(false));
    }

    @Override
    public void run() {
        log.info("Scheduler is working");
    }
}
