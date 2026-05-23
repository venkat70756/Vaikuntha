package com.adobe.aem.Vaikuntha.core.services.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Demo Two OSGI Configuration")
public @interface DemoTwoServiceConfig {
    @AttributeDefinition(name = "ID", type = AttributeType.INTEGER)
    public int getId();

    @AttributeDefinition(name = "Bike Name", type = AttributeType.STRING)
    public String getBikeName();

    @AttributeDefinition(name = "Bike Price", type = AttributeType.INTEGER)
    public int getBikePrice();

    @AttributeDefinition(name = "Active or not", type = AttributeType.BOOLEAN)
    public boolean getActiveOrNot();

    @AttributeDefinition(name = "Chais number", type = AttributeType.PASSWORD)
    public String getChaisNumber();

    @AttributeDefinition(name = "Specifications", type = AttributeType.STRING)
    public String[] getSpecifications();

    @AttributeDefinition(name = "Bike company", type = AttributeType.STRING,
            options = {
            @Option(value = "classic350", label = "Classic 350"),
                    @Option(value = "Crux", label = "Yamaha"),
                    @Option(value = "Shine", label = "Honda"),

    })
    public String getBikeCompany();
}
