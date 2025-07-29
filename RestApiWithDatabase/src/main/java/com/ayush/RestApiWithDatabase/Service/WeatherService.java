package com.ayush.RestApiWithDatabase.Service;

import com.ayush.RestApiWithDatabase.cache.AppCache;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private String apikey;

    private RestTemplate restTemplate;

    private AppCache appCache;
    private RedisService redisService;
}
