package com.prgrms.restapiclone.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
@ConfigurationProperties(prefix = "spring.datasource")
@ConstructorBinding
public class DbConfigProperties {

    private final String url;
    private final String username;
    private final String password;

    public DbConfigProperties(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

