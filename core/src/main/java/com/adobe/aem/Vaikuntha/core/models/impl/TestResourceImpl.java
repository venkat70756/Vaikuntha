package com.adobe.aem.Vaikuntha.core.models.impl;

import com.adobe.aem.Vaikuntha.core.models.TestResource;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(
        adaptables = Resource.class,
         adapters = TestResource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TestResourceImpl implements TestResource {

    private static final Logger log = LoggerFactory.getLogger(TestResourceImpl.class);


    @Self
    ResourceResolver resourceResolver;

    private List<PageInfo> childPages;


    @Override
    public List<PageInfo> getChildPages() {

        childPages = new ArrayList<>();

        Resource resource = resourceResolver.getResource("/content/Vaikuntha/us/en/my-account");

        PageManager pageManager = resource.adaptTo(PageManager.class);

//        String cr = resource.getName();
//        log.info("Current Resource is: {}", cr);
//
//        PageManager pageManager = resource.getResourceResolver().adaptTo(PageManager.class);


        if (pageManager == null){  // null  == null
            log.error("PageManger not adapted properly");
            return  null;
        }

        Page currentPage = pageManager.getContainingPage(resource);


        if (currentPage != null){
            log.info("Current Page is not null and the page is: {}", currentPage.getPath());
            Iterator<Page> children = currentPage.listChildren();

            while (children.hasNext()){
                Page child = children.next();
                PageInfo info = new PageInfo();

                info.setPath(child.getPath());
                info.setTitle(child.getTitle());
                childPages.add(info);
            }
        }else {
            log.error("Current Page have the null value");
            return null;
        }
        return childPages;
    }
}






/// interface  --> class   // implements


// class --> class  // extends


//