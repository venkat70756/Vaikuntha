package com.adobe.aem.Vaikuntha.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
@Designate(ocd = SnehalOSGIConfiguration.Config.class)
public class SnehalOSGIConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SnehalOSGIConfiguration.class);

    private String myName;

    @ObjectClassDefinition(name = "Snehal OSGI Test Conf")
    public @interface Config{
        @AttributeDefinition(name = "MY Name", type = AttributeType.STRING)
        public String getMyName();
    }

    @Activate
    @Modified
    public void testing(Config config){
        myName = config.getMyName();
        log.info("Name is:{}", myName);
    }

}
