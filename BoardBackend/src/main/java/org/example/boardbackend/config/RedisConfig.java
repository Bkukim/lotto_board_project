package org.example.boardbackend.config;





import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * packageName : com.example.simpledms.config
 * fileName : RedisConfig
 * author : kangtaegyung
 * date : 2022/06/14
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/14         kangtaegyung          최초 생성
 */
@Configuration
public class RedisConfig {
    //  Redis IP
    @Value("${redis.host}")
    private String redisHost;

    //  Redis Port Number
    @Value("${redis.port}")
    private int redisPort;

    //  레디스 연결
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);

        return new LettuceConnectionFactory(configuration);
    }

    //  레디스 관리  :  1) 생성 dept          : ttl - 1 minute
//
    @Bean
    public RedisCacheManager cacheManager() {
//    Redis 캐시 설정 함수를 호출해서 래디스 설정
        RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10))
                .disableCachingNullValues(); // 캐싱할 때 null 값을 허용하지 않음

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfig)
                .withCacheConfiguration("notices", myDefaultCacheConfig(Duration.ofMinutes(5)))  // depts 캐쉬 생성
                .withCacheConfiguration("notice", myDefaultCacheConfig(Duration.ofMinutes(3)))   // dept 캐쉬 생성
                .build();
    }

    //  Redis 캐시 설정 함수 : 1) 설정 default config 2) 설정 ttl 3) 설정 serialize
    private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(duration)         // ttl(만료시간) 설정
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); // Value 를 직렬화할 때 사용하는 규칙. Jackson2 를 많이 사용함
    }

    // redisTemplate 사용 설정
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());    // key
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class)); //Java Obj <-> JSON -> String Value
        return redisTemplate;
    }


    // 리스너 어댑터 설정
    @Bean
    public RedisMessageListenerContainer redisMessageListener() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());
        return container;
    }

}

