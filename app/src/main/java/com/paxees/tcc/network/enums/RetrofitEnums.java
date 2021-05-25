package com.paxees.tcc.network.enums;

import com.paxees.tcc.utils.GlobalClass;
public enum RetrofitEnums {
    URL_HBL(GlobalClass.BASE_URL_HBL);
    RetrofitEnums(String url) {
        this.url = url;
    }
    String url;
    public String getUrl() {
        return url;
    }
}
