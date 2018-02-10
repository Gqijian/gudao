package me.zj22.gudao.server.web.ssl;

import javax.net.ssl.TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public interface X509TrustManager extends TrustManager {
    void checkClientTrusted(X509Certificate[] var1, String var2) throws CertificateException;

    void checkServerTrusted(X509Certificate[] var1, String var2) throws CertificateException;

    X509Certificate[] getAcceptedIssuers();
}