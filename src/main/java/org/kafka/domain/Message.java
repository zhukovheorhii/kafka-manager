package org.kafka.domain;

import java.util.Map;

public class Message {
    private String key;
    private String vale;
    private Map<String, String> headers;

    public void setKey(String key) {
        this.key = key;
    }

    public void setVale(String vale) {
        this.vale = vale;
    }

    public String getVale() {
        return vale;
    }

    public String getKey() {
        return key;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
