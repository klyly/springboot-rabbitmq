package niit.wei.config.example5;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WeiJinLong
 * @Date 2023-08-23 11:15
 * @Version 1.0
 */
@Configuration
public class BlogTopicConfig {
    @Bean
    public TopicExchange bolgTopicExchange(){
        return new TopicExchange("blogTopicExchange");
    }
    @Bean
    public Queue blogJavaQueue(){
        return new Queue("blogJavaQueue");
    }
    @Bean
    public Queue blogPythoneQueue(){
        return new Queue("blogPythonQueue");
    }
    @Bean
    public Queue blogAllQueue(){
        return new Queue("blogAllQueue");
    }
    @Bean
    public Binding bindJavaToTopicExchange(Queue blogJavaQueue,TopicExchange bolgTopicExchange){
        /**
         * 规则：建议*或#
         */
        return BindingBuilder.bind(blogJavaQueue).to(bolgTopicExchange).with("blog.java");
    }

    @Bean
    public Binding bindPythonToTopicExchange( Queue blogPythoneQueue,TopicExchange bolgTopicExchange){
        /**
         * 规则：建议*或#
         */
        return BindingBuilder.bind(blogPythoneQueue).to(bolgTopicExchange).with("blog.python");
    }

    @Bean
    public Binding bindAllToTopicExchange( Queue blogAllQueue,TopicExchange bolgTopicExchange){
        /**
         * 规则：建议*或#
         */
        return BindingBuilder.bind(blogAllQueue).to(bolgTopicExchange).with("blog.#");
    }

}
