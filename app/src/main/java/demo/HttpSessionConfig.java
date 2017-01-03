package demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Bean(name = "org.springframework.autoconfigure.redis.RedisProperties")
    @ConditionalOnMissingBean
    public RedisProperties redisProperties() {
        return new RedisProperties();
    }
    
    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisProperties().getHost());
        factory.setPort(redisProperties().getPort());
        factory.setPassword(redisProperties().getPassword());
        return factory;
    }
}