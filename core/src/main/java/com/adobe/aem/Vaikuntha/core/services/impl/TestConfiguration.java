package com.adobe.aem.Vaikuntha.core.services.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "TestConfiguration", description = "For testing the OSGI Configuration")
public @interface TestConfiguration {
    @AttributeDefinition(name = "Student Name", type = AttributeType.STRING, description = "Enter the Student Name")
    public String getName();


    @AttributeDefinition(name = "Student Age", type = AttributeType.INTEGER, description = "Enter the Student Age")
    public int getAge();


    @AttributeDefinition(name = "Regular Student", type = AttributeType.BOOLEAN, description = "Is Student is regular")
    public boolean getRegular() default true;

    @AttributeDefinition(name = "Subjects", type = AttributeType.STRING, description = "See your subjects")
    public String[] getSubjects() default {"English", "Maths", "Science"};

    @AttributeDefinition(name = "Countries", type = AttributeType.STRING, description = "Select your country",
            options = {
                @Option(label = "India", value = "india"),
                    @Option(label = "Russia", value = "russia")
            }
    )
    public String getCountries() default "India";
}


//https://app.beeceptor.com/mock-server/dummy-json
