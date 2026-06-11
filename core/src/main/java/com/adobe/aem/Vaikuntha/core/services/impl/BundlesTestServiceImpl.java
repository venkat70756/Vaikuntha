package com.adobe.aem.Vaikuntha.core.services.impl;

import com.adobe.aem.Vaikuntha.core.services.BundlesTestService;
import com.adobe.aem.Vaikuntha.core.services.DemoService;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = BundlesTestService.class, immediate = true)
@Designate(ocd = BundleTestConfiguration.class)
public class BundlesTestServiceImpl implements BundlesTestService {

    private static final Logger log = LoggerFactory.getLogger(BundlesTestServiceImpl.class);

    @Reference
    DemoService demoService;

    @Activate
    public void activate(){
        log.info("Another Service data: {}", demoService.getVenkat());
        log.info("activate");
    }

    @Modified
    public void modified(){
        log.info("modified");
    }

    @Deactivate
    public void deactivate(){
        log.info("deactivate");
    }
}
