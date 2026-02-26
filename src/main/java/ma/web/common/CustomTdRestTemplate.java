package ma.web.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class CustomTdRestTemplate {

    @Autowired
    public RestTemplate customTemplate;

    @Autowired
    public Environment env;

    public <T> ResponseEntity<T> tdConnectionForEntity(String urlPath, Class<T> responseType, String methodGetParams){
        String url = env.getProperty("td.url.base.api") + urlPath;
        String authValue = "Bearer " + env.getProperty("td.id.access");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authValue);
        headers.setAccept(MediaType.parseMediaTypes("application/json;charset=UTF-8"));

        HttpEntity<String> httpEntityForObject = new HttpEntity<>(headers);

        if(methodGetParams != null){
            url += methodGetParams;
        }

        return customTemplate.exchange(url, HttpMethod.GET, httpEntityForObject, responseType);
    }
}
