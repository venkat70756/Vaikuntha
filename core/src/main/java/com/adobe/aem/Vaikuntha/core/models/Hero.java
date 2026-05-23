package com.adobe.aem.Vaikuntha.core.models;

import java.util.List;

public interface Hero {

    public String getHeroHeading();

    public String getCategory();

    public List<HeroMultiModelImpl> getStore();

    public String getMyName();


    public String getName();

    public int getAge();
}
