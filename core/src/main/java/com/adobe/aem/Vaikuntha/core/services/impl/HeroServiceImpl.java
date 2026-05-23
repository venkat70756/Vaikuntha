package com.adobe.aem.Vaikuntha.core.services.impl;

import com.adobe.aem.Vaikuntha.core.services.HeroService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = HeroService.class, immediate = true)
public class HeroServiceImpl implements HeroService {


    private static final Logger LOGGER = LoggerFactory.getLogger(HeroServiceImpl.class);

    private String serviceValue = "This is coming from Service";



    @Override
    public String getServiceValue() {

        return serviceValue;
    }

    @Override
    public int getAddition(int a, int b) {

        return a+b;
    }
}
