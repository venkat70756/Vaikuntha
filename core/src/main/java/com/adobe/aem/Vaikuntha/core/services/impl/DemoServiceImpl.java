package com.adobe.aem.Vaikuntha.core.services.impl;

import com.adobe.aem.Vaikuntha.core.services.DemoService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = DemoService.class, immediate = true)
@Designate(ocd = DemoServiceImpl.DemoConfiguration.class)
public class DemoServiceImpl implements DemoService {

    private  static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

    private  String message;


    @ObjectClassDefinition(
            name = "Simple Service Configuration",
            description = "For Testing"
    )
    public @interface DemoConfiguration{
        @AttributeDefinition(
                name = "Sample Message",
                description = "Sample Message from the OSGI service"
        )
        String serviceMessage() default "hello";


            @AttributeDefinition(
                    name = "API  URL",
                    description = "API URL"
            )
            String apiURL();
    }

//    @Activate
//    @Modified
//    protected void activate(DemoConfiguration demoConfiguration){
//        message = demoConfiguration.serviceMessage();
//        log.info(message);
//    }
//
//
//    @Deactivate
//    protected void deactivate(){
//        message = "Bundle got Deactivated";
//        log.info(message);
//    }

//    @Modified
//    protected void modified(){
//        message = "Bundle got Modified";
//        log.info(message);
//    }


    @Override
    public String getMessage(){
        return message;
    }

}
