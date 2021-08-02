package com.paxees.tcc.network.retrofitBuilder;

public final class Builder {

    private String consumerKey;
    private String consumerSecret;
    private int type;

    public Builder consumerKey(String consumerKey) {
        if (consumerKey == null) throw new NullPointerException("consumerKey = null");
        this.consumerKey = consumerKey;
        return this;
    }

    public Builder consumerSecret(String consumerSecret) {
        if (consumerSecret == null) throw new NullPointerException("consumerSecret = null");
        this.consumerSecret = consumerSecret;
        return this;
    }



    public OAuthInterceptor build() {

        if (consumerKey == null) throw new IllegalStateException("consumerKey not set");
        if (consumerSecret == null) throw new IllegalStateException("consumerSecret not set");

        return new OAuthInterceptor(consumerKey, consumerSecret);
    }
}