package com.bianbian.common.conf.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author bianbian
 * @date 2018/11/9
 */
@Slf4j
@Configuration
public class HttpClientPool {
    @Value("${httpClient.maxTotal:500}")
    public int httpclientMaxTotal;

    @Value("${httpClient.defaultMaxPerRoute:500}")
    public int httpclientDefaultMaxPerRoute;

    @Value("${httpClient.linkLimit:500}")
    public int httpclientLinkLimit;

    @Value("${httpClient.connectTimeout:600000}")
    public int httpclientConnectTimeout;

    @Value("${httpClient.connectionRequestTimeout:600000}")
    public int httpclientConnectionRequestTimeout;

    @Value("${httpClient.readTimeout:600000}")
    public int httpclientReadTimeout;

    @Value("${keepAlive.timeout:300000}")
    public int keepaliveTimeout;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(CloseableHttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        factory.setConnectTimeout(httpclientConnectTimeout);
        factory.setConnectionRequestTimeout(httpclientConnectionRequestTimeout);
        factory.setReadTimeout(httpclientReadTimeout);

        return factory;
    }

    @Bean
    public CloseableHttpClient createHttpClient() {

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectTimeout(httpclientConnectTimeout)
                .setConnectionRequestTimeout(httpclientConnectionRequestTimeout)
                .setSocketTimeout(httpclientReadTimeout)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        cm.setMaxTotal(httpclientMaxTotal);
        cm.setDefaultMaxPerRoute(httpclientMaxTotal);
        return HttpClients
                .custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(cm)
                .build();

    }
}
