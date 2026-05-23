package com.adobe.aem.Vaikuntha.core.services.impl;

import com.adobe.aem.Vaikuntha.core.services.TestingService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Component(service = TestingService.class, immediate = true)
@Designate(ocd = TestConfiguration.class)
public class TestingServiceImpl implements TestingService {


    private static final Logger log = LoggerFactory.getLogger(TestingServiceImpl.class);

    private String name;

    private int age;

    private boolean isRegualr;

    private String[] sub;

    private String country;


    @Activate
    @Modified
    public void getActivate(TestConfiguration testConfiguration){
        name = testConfiguration.getName();
        age = testConfiguration.getAge();
        isRegualr = testConfiguration.getRegular();
        sub = testConfiguration.getSubjects();
        country = testConfiguration.getCountries();

        log.info("The Student Name is: {}", name);
        log.info("The Student  Age is {}", age);
        log.info("Regular Student: {}", isRegualr);
        log.info("Subjects: {}", Arrays.toString(sub));
        log.info("The Student Country is: {}", country);
    }


//    @Modified
//    public void getModified(){
//        log.info("Modified method is executed");
//
//    }


    @Deactivate
    public void getDeactivated(){
        log.info("Deactivated method is executed");
    }


    @Override
    public String getStudentName() {
        log.info("The Student Name is:{}", name);
        return name;
    }


    @Override
    public int getStudentAge(){
        return age;
    }

    @Override
    public boolean getIsRegular() {
        return isRegualr;
    }

    @Override
    public String[] getAllSubjects() {
        return sub;
    }

    @Override
    public String getStudentCountry() {
        return country;
    }
}
