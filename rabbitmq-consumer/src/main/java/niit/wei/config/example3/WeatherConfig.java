package niit.wei.config.example3;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WeiJinLong
 * @Date 2023-08-21 16:46
 * @Version 1.0
 */
@Configuration
public class WeatherConfig {
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("weatherFanoutExchange");
    }

    @Bean
    public Queue fanoutTbQueue(){
        return new Queue("weatherFanoutTbQueue",false);
    }
    @Bean
    public Queue fanoutJdQueue(){
        return new Queue("weatherFanoutJdQueue",false);
    }
    @Bean
    public Binding bindFanoutTb(Queue fanoutTbQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutTbQueue).to(fanoutExchange);
    }
    @Bean
    public Binding bindFanoutJd(Queue fanoutJdQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutJdQueue).to(fanoutExchange);
    }
}
