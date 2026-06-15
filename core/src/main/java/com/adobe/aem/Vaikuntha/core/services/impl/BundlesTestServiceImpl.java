package com.adobe.aem.Vaikuntha.core.services.impl;

import com.adobe.aem.Vaikuntha.core.services.BundlesTestService;
import com.adobe.aem.Vaikuntha.core.services.DemoService;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

@Component(service = BundlesTestService.class, immediate = true)
@Designate(ocd = BundleTestConfiguration.class)
public class BundlesTestServiceImpl implements BundlesTestService {

    private static final Logger log = LoggerFactory.getLogger(BundlesTestServiceImpl.class);

    @Reference
    DemoService demoService;

    private String studentName;

    private int studentAge;

    private boolean isRegular;

    private String[] studentSubjects;

    private String studentAdhar;

    private String studentClass;


    public BundlesTestServiceImpl() {}

    @Activate
    @Modified
    public void activate(BundleTestConfiguration bundleTestConfiguration){
        studentName = bundleTestConfiguration.getStudentName();
        studentAge = bundleTestConfiguration.getStudentAge();
        isRegular = bundleTestConfiguration.getRegularStudent();
        studentSubjects = bundleTestConfiguration.getStudentSubjects();
        studentAdhar = bundleTestConfiguration.getStudentAdharCardNumber();
        studentClass = bundleTestConfiguration.getStudentClass();


        log.info("Student Name: {} , Student Age: {},  Is regular: {}, Student Subjects: {}, Student Adhar: {}, Student Class: {}", studentName,  studentAge,  isRegular, Arrays.toString(studentSubjects), studentAdhar, studentClass);
    }


    @Deactivate
    public void deactivate(){
        log.info("deactivate");
    }
}
