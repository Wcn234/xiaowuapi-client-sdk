package com.example.xiaowuapiclientsdk;


import com.example.xiaowuapiclientsdk.client.XiaowuApiClient;
import com.example.xiaowuapiclientsdk.model.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("xiaowuapi.client")
@Data
@ComponentScan
public class XiaowuApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public XiaowuApiClient xiaowuApiClient() {
       return new XiaowuApiClient(accessKey, secretKey);
    }
}
