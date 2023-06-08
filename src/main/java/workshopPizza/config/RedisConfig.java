package workshopPizza.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${REDISHOST}")
    private String redisHost;
    
    @Value("${REDISPORT}")
    private int redisPort;

    @Value("${REDISUSERNAME}")
    private String username;

    @Value("${REDISPASSWORD}")
    private String password;

    @Bean
    @Scope("singleton")
     public RedisTemplate<String, Object> getRedisTemplate() {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
    
        if(!username.isEmpty() && !password.isEmpty()){
            config.setUsername(username);
            config.setPassword(password);
        }
       config.setDatabase(0);
    
       final JedisClientConfiguration client = JedisClientConfiguration.builder().build();
       final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, client);
       jedisFac.afterPropertiesSet();
    
       final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
       redisTemplate.setConnectionFactory(jedisFac);
       redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    
       RedisSerializer<Object> objSerializer = new JdkSerializationRedisSerializer(getClass()
    .getClassLoader());
        redisTemplate.setHashValueSerializer(objSerializer);
        return redisTemplate; 
    }    
}
