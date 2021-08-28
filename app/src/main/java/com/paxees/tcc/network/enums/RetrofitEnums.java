package com.paxees.tcc.network.enums;

import com.paxees.tcc.utils.GlobalClass;
public enum RetrofitEnums {
    URL_HBL(GlobalClass.BASE_URL_HBL),
    URL_STRAIN_REQUEST_FROM(GlobalClass.BASE_URL_STRAIN_REQUEST_FROM),
    URL_ZOHO(GlobalClass.BASE_URL_ZOHO);
    RetrofitEnums(String url) {
        this.url = url;
    }
    String url;
    public String getUrl() {
        return url;
    }
}
