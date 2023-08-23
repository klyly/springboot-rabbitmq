package niit.wei.config.example6;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author WeiJinLong
 * @Date 2023-08-23 14:54
 * @Version 1.0
 */
@Configuration
public class UserDelayedQueueConfig {
    /**
     * TODO 1、定义交换机   delayed.exchange.user.register
     *      2、定义队列     delayed.queue.user.register
     *      3、路由KEY      delayed.key.user.register
     *      4、队列绑定交换机路由
     */
    /**
     * TODO 定义交换机
     */
    @Bean
    public CustomExchange delayedExchangeUserRegister() {
        /**
         * String name, 交换机名称
         * String type,  交换机消息类型（x-delayed-message)
         * boolean durable, 是否持久化
         * boolean autoDelete,是否删除
         * Map<String, Object> arguments  // 队列中的消息什么时候会自动被删除？
         *   arguments.put("x-message-ttl",10000); //设置过期时间
         *   arguments.put("x-expires", 10000); //x-expires用于当多长时间没有消费者访问该队列的时候，该队列会自动删除，
         *   arguments.put("x-max-length", 4); //x-max-length:用于指定队列的长度，如果不指定，可以认为是无限长，例如指定队列的长度是4，当超过4条消息，前面的消息将被删除，给后面的消息腾位
         */
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange("delayed.exchange.user.register", "x-delayed-message",
                true, false, arguments);
    }

    /**
     * TODO 定义队列 delayed.queue.user.register
     */
    @Bean
    public Queue delayedQueueUserRegister() {
        return new Queue("delayed.queue.user.register");
    }

    /**
     * TODO 3 绑定 交换机 队列 路由KEY
     *
     * @return
     */
    @Bean
    public Binding bindDelayedQueueDelayedExchangeUserRegister(
            Queue delayedQueueUserRegister,
            CustomExchange delayedExchangeUserRegister) {
        return BindingBuilder.bind(delayedQueueUserRegister).to(delayedExchangeUserRegister).with("delayed.key.user.register").noargs();

    }
}
