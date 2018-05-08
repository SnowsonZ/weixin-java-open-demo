package com.github.binarywang.demo.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@SpringBootApplication
public class WxOpenApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WxOpenApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WxOpenApplication.class);
    }
}
