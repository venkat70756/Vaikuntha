package com.adobe.aem.Vaikuntha.core.models.impl;

import com.adobe.aem.Vaikuntha.core.models.MyTitleModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(
        adaptables = Resource.class,
        adapters = MyTitleModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class MyTitleModelImpl implements MyTitleModel {

    private String title = "Hi this value is coming from the sling model";


    private static final Logger log = LoggerFactory.getLogger(MyTitleModelImpl.class);

    @Override
    public String getMyTitle() {
        log.trace("Trace Log");
        log.debug("Debug log");
        log.info("Info log");
        log.warn("Warn log");
        log.error("Error log");
        return title;
    }
}
