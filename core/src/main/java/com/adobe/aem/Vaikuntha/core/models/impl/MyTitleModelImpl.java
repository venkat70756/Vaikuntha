package com.adobe.aem.Vaikuntha.core.models.impl;

import com.adobe.aem.Vaikuntha.core.models.MyTitleModel;
import com.adobe.aem.Vaikuntha.core.services.DemoService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(
        adaptables = Resource.class,
        adapters = MyTitleModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class MyTitleModelImpl implements MyTitleModel {



    @OSGiService
    DemoService demoService;


    private static final Logger log = LoggerFactory.getLogger(MyTitleModelImpl.class);

    @Override
    public String getMyTitle() {
    log.info("getMyTitle Method Calling from sling model");
        return demoService.getVenkat();
    }
}
