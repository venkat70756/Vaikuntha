package com.adobe.aem.Vaikuntha.core.services.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "YL First Test OSGI Configuration", description = "Testing configuration for Yash and Leela" )
public @interface BundleTestConfiguration {

    @AttributeDefinition(name = "Student Name", type = AttributeType.STRING, description = "Enter the Student Name")
    public String getStudentName();

    @AttributeDefinition(name = "Student Age", type = AttributeType.INTEGER, description = "Enter the Student  Age")
    public int getStudentAge() default 25;

    @AttributeDefinition(name = "Regular Student",  type=AttributeType.BOOLEAN, description = "Is student Regular or not")
    public boolean getRegularStudent() default true;

    @AttributeDefinition(name = "Student Subjects", type = AttributeType.STRING, description = "Provide the student subjects")
    public  String[] getStudentSubjects() default {"Telugu","Computers"};

    @AttributeDefinition(name = "Student Adhar", type = AttributeType.PASSWORD, description = "Enter the Student Adhar Card Number")
    public String getStudentAdharCardNumber();

    @AttributeDefinition(name = "Select Student class", type = AttributeType.STRING, description = "Select the Student class", options = {
            @Option(label = "10 th Class", value = "10"),
            @Option(label = "9th class", value = "9")
    })
    public String getStudentClass();



}

/*
 *  Byte
 *  short
 *  int
 *  long
 *
 * float
 *  double
 *
 * char
 * boolean
 *
 *
 *
 *
 * String
 * arrays
 */
