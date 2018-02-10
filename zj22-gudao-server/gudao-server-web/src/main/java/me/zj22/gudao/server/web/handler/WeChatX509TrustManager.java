package me.zj22.gudao.server.web.handler;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public class WeChatX509TrustManager implements X509TrustManager {
    public WeChatX509TrustManager() {
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
