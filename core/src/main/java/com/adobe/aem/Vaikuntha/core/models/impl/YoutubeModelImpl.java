package com.adobe.aem.Vaikuntha.core.models.impl;


import com.adobe.aem.Vaikuntha.core.models.YoutubeModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        adapters = YoutubeModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
) // this annotation can converts the java class as a sling model
public class YoutubeModelImpl implements YoutubeModel {

    @ValueMapValue
    private String videoId;

    @ValueMapValue
    private int videoNumber;

    @ValueMapValue
    private String videoDescription;


    @Override
    public String getVideoId() {
        return videoId;
    }

    @Override
    public int getVideoNumber() {

        return videoNumber;
    }

    @Override
    public String getVideoDescription() {
        return videoDescription;
    }
}
