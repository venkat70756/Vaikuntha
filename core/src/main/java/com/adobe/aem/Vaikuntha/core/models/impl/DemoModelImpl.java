package com.adobe.aem.Vaikuntha.core.models.impl;

import com.adobe.aem.Vaikuntha.core.models.DemoModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class,
        adapters = DemoModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class DemoModelImpl implements  DemoModel {

    @ValueMapValue
    private boolean isRegular;

    @ValueMapValue
    private String country;


    @ChildResource
    private List<DemoMultiModel> socialMedia;

    @Override
    public boolean getIsRegular() {
        return isRegular;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public List<DemoMultiModel> getSocialMedia() {
        return socialMedia;
    }
}

