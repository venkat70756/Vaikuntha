package com.adobe.aem.Vaikuntha.core.models;

import com.adobe.aem.Vaikuntha.core.models.impl.DemoMultiModel;

import java.util.List;

public interface DemoModel {

    public boolean getIsRegular();

    public String getCountry();

    public List<DemoMultiModel> getSocialMedia();
}
