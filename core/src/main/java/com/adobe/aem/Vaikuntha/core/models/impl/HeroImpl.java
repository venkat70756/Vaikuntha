package com.adobe.aem.Vaikuntha.core.models.impl;

import com.adobe.aem.Vaikuntha.core.models.Hero;
import com.adobe.aem.Vaikuntha.core.models.HeroMultiModelImpl;
import com.adobe.aem.Vaikuntha.core.services.DemoTwoService;
import com.adobe.aem.Vaikuntha.core.services.HeroService;
import com.adobe.aem.Vaikuntha.core.services.TestingService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Activate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Model(adaptables = Resource.class,
        adapters = Hero.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeroImpl implements Hero {

    private static final Logger log = LoggerFactory.getLogger(HeroImpl.class);


    @ValueMapValue
    private String heroHeading;


    @ValueMapValue
    private String category;

    @OSGiService
    DemoTwoService demoTwoService;


    @ChildResource
    private List<HeroMultiModelImpl> store;


    @OSGiService
    HeroService heroService;

    @OSGiService
    TestingService testingService;





    @Override
    public String getHeroHeading() {

       int value = heroService.getAddition(2,5);
       log.info("The addition of two values is: {}", value);
        return heroHeading;
    }

    @Override
    public String getCategory() {
        log.debug("Category");
        return category;
    }

    @Override
    public List<HeroMultiModelImpl> getStore() {
        return store;
    }

    @Override
    public String getMyName(){
        return heroService.getServiceValue();
    }

    @Override
    public String getName() {
        return demoTwoService.getMessage();
    }

    @Override
    public int getAge() {
        return testingService.getStudentAge();
    }
}
