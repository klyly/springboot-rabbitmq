package niit.wei.config.example1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WeiJinLong
 * @Date 2023-08-21 15:06
 * @Version 1.0
 */
@Configuration
public class EmailConfig {
    @Bean
    public Queue emailQueue(){
        return new Queue("emailQueue1");
    }
}
