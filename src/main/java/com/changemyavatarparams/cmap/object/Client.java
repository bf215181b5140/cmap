package com.changemyavatarparams.cmap.object;

import com.changemyavatarparams.cmap.view.ChangeMyAvatarParams;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.WebBrowser;

import java.util.HashMap;

public class Client {
    private UI ui;
    private ChangeMyAvatarParams view;

    private int clientId;
    private String ip;
    private String userAgent;
    private String locale;
    private String cookie;
    private HashMap<String, String> headers;

    public Client(UI ui, ChangeMyAvatarParams view, WebBrowser webBrowser) {
        this.ui = ui;
        this.view = view;
        this.ip = webBrowser.getAddress();
        this.userAgent = webBrowser.getBrowserApplication();
        this.locale = webBrowser.getLocale().toString();
        this.cookie = java.util.UUID.randomUUID().toString();
        this.headers = new HashMap<>();
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public ChangeMyAvatarParams getView() {
        return view;
    }

    public void setView(ChangeMyAvatarParams view) {
        this.view = view;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String toString() {
        return "UI: " + ui.getUIId() + " View: " + view.getClassName() + " Cookie: " + cookie;
    }
}
