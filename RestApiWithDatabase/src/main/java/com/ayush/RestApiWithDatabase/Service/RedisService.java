package com.ayush.RestApiWithDatabase.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    private RedisTemplate<String , String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <T> T get(String key , Class<T> entityClass){
        try{
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(o.toString() , entityClass);
        }
        catch (Exception e){
            log.error("exception" , e);
            return null;
        }
    }

    public void set(String key , Object o , Long ttl){

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key , jsonValue , ttl ,  TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            log.error("exception e");
        }
    }
}
