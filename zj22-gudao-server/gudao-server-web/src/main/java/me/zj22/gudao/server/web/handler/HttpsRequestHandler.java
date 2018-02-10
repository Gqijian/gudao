package me.zj22.gudao.server.web.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.zj22.gudao.server.web.enums.RequestMethodType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public class HttpsRequestHandler {
    private static Logger log = LoggerFactory.getLogger(HttpsRequestHandler.class);

    public HttpsRequestHandler() {
    }

    public static JSONObject httpsRequest(String requestUrl, RequestMethodType requestMethod) {
        return httpsRequest(requestUrl, requestMethod, (String)null);
    }

    public static JSONObject httpsRequest(String requestUrl, RequestMethodType requestMethod, String outputStr) {
        HttpsURLConnection httpUrlConn = null;
        BufferedReader bufferedReader = null;

        try {
            TrustManager[] e = new TrustManager[]{new WeChatX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init((KeyManager[])null, e, new SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            httpUrlConn = (HttpsURLConnection)url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod(requestMethod.name());
            if(RequestMethodType.GET.equals(requestMethod)) {
                httpUrlConn.connect();
            }

            if(null != outputStr) {
                OutputStream inputStream = httpUrlConn.getOutputStream();
                inputStream.write(outputStr.getBytes("UTF-8"));
                inputStream.close();
            }

            InputStream inputStream1 = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream1, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder buffer = new StringBuilder();
            String str = null;

            while((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            log.error("\n\n\n请求用户数据返回的数据:\n" + buffer.toString() + "\n\n\n");
            JSONObject var13 = JSON.parseObject(buffer.toString());
            return var13;
        } catch (ConnectException var25) {
            log.error("Weixin server connection timed out.");
        } catch (Exception var26) {
            log.error("https request error:{}", var26);
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                    bufferedReader = null;
                } catch (Exception var24) {
                    log.error(var24.getMessage(), var24);
                }
            }

            if(httpUrlConn != null) {
                httpUrlConn.disconnect();
            }

        }

        return null;
    }

    public static InputStream httpsRequestToInputStream(String requestUrl, RequestMethodType requestMethod, String outputStr) {
        try {
            TrustManager[] e = new TrustManager[]{new WeChatX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init((KeyManager[])null, e, new SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod(requestMethod.name());
            if(RequestMethodType.GET.equals(requestMethod)) {
                httpUrlConn.connect();
            }

            if(null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            httpUrlConn.disconnect();
            return httpUrlConn.getInputStream();
        } catch (ConnectException var9) {
            log.error("Weixin server connection timed out.");
            var9.printStackTrace();
        } catch (Exception var10) {
            log.error("https request error:{}", var10);
            var10.printStackTrace();
        }

        return null;
    }
}

