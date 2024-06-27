package com.system.fridges.service.utils;

import com.system.fridges.models.transferObjects.orderObjects.AutoOrderRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class Delivety {

    private final static String DELIVETY_API_URL = "https://smart2.delivety.com/hooks/catch/bx3sl8kl43";

    private final static String ACCESS_TOKEN = "yUk2EzNE3MLzYuyTnEK3N4zc";

    public void sendAutoOrdering(AutoOrderRequest autoOrderRequest) {
            autoOrderRequest.access_token = ACCESS_TOKEN;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AutoOrderRequest> request = new HttpEntity<>(autoOrderRequest, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(DELIVETY_API_URL, request, String.class);
    }
}
