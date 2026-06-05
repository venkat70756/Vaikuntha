package com.adobe.aem.Vaikuntha.core.models.impl;

import com.adobe.aem.Vaikuntha.core.models.MyTitleModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(
        adaptables = Resource.class,
        adapters = MyTitleModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class MyTitleModelImpl implements MyTitleModel {

    private String title = "Hi this value is coming from the sling model";


    @Override
    public String getMyTitle() {
        return title;
    }
}
