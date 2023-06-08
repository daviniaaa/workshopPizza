package workshopPizza.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PizzaRepo {
    @Autowired
    RedisTemplate<String, Object> template;

    
    
}
