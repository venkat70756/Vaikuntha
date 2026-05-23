package com.adobe.aem.Vaikuntha.core.services.impl;

import com.adobe.aem.Vaikuntha.core.services.DemoTwoService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Component(service = DemoTwoService.class, immediate = true)
@Designate(ocd = DemoTwoServiceConfig.class)
public class DemoTwoServiceImpl implements DemoTwoService {

    private String message = "This is message from DemoTwoServiceImpl";

    private static final Logger log = LoggerFactory.getLogger(DemoTwoServiceImpl.class);

    private String name;

    private int price;

    private boolean active;

    private String number;

    private String[] specification;

    private String company;


    @Activate
    @Modified
    public void activate(DemoTwoServiceConfig config) {
        name = config.getBikeName();
        price = config.getBikePrice();
        active = config.getActiveOrNot();
        number = config.getChaisNumber();
        specification = config.getSpecifications();
        company = config.getBikeCompany();


        log.info("name :{}, price: {}. active : {} , number : {}, specification:{}, company: {}", name, price, active, number, Arrays.toString(specification) );
    }





    @Deactivate
    public void deactivate(){
        log.info("Deactivate method triggerd");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
