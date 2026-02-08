package com.adobe.aem.Vaikuntha.core.models.impl;

import com.adobe.aem.Vaikuntha.core.models.Demo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(
        adaptables = Resource.class,
        adapters = Demo.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class DemoImpl implements Demo {


    @ValueMapValue
    private  String authorName;

    @ValueMapValue
    private  String bookName;

    @ValueMapValue
    private String publishedDate;

    @ValueMapValue
    private  String bookAvialableAddress;

    @ValueMapValue
    private String bookImage;

    @ValueMapValue
    private int bookPrice;


    @Override
    public String getAuthorName() {
        return authorName;
    }

    @Override
    public String getBookName() {
        return bookName;
    }

    @Override
    public String getPublishedDate() {
        return publishedDate;
    }

    @Override
    public String getBookAvialableAddress() {
        return bookAvialableAddress;
    }

    @Override
    public String getBookImage() {
        return bookImage;
    }

    @Override
    public int getBookPrice() {
        return bookPrice;
    }

    @Override
    public String getExtraName(){
        return "AEM";
    }
}
