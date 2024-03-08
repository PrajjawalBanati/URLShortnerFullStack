package com.springboot.URLShortner.Service;

import com.google.common.hash.Hashing;
import com.springboot.URLShortner.Model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class UrlServiceImp implements UrlService{

    @Autowired
    private RedisTemplate<String, Url> redisTemplate;
    @Override
    public Url getUrlByKey(String key) {
        Url url = redisTemplate.opsForValue().get(key);
        return url;
    }

    @Override
    public Url shorternUrl(String url) {
        String key = Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
        Url ShortUrl = Url.builder().key(key).createdAt(LocalDateTime.now()).url(url).build();
        redisTemplate.opsForValue().set(key, ShortUrl, 36000L, TimeUnit.SECONDS);
        return ShortUrl;
    }
}
