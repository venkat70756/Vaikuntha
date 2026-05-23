package com.adobe.aem.Vaikuntha.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeroMultiModelImpl {

    @ValueMapValue
    private String storeImg;

    @ValueMapValue
    private String storePath;

    @ValueMapValue
    private String altText;


    public String getStoreImg(){
        return storeImg;
    }

    public String getStorePath(){
        return storePath;
    }

    public String getAltText(){
        return altText;
    }
}
