package by.solbegsoft.shortener.demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class WhiteList implements InitializingBean {
    @Value("${api.path}")
    private String apiPath;

    private Set<String> whiteList;

    @Override
    public void afterPropertiesSet(){
        whiteList = Set.of(
                "/swagger-ui.html",
                "/v3/api-docs/**",
                "/configuration/ui",
                "/swagger-ui/**",
                apiPath + "/url/redirect/**"
        );
    }

    public String[] get(){
        return whiteList.toArray(new String[0]);
    }
}