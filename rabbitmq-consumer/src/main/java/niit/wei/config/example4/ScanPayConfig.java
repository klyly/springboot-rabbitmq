package niit.wei.config.example4;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WeiJinLong
 * @Date 2023-08-23 10:14
 * @Version 1.0
 */
@Configuration
public class ScanPayConfig {
    /**
     * 1.定义交换机
     * 2.分别编写队列
     * 3.绑定
     */
    @Bean
    public DirectExchange scanPayDirectExchange(){
        return new DirectExchange("scanPayDirectExchange");
    }

    /**
     * 定义队列
     * 队列名称：编码规范：全部英文（尽量不用下划线）
     */
    @Bean
    public Queue weiXinScanPayQueue(){
        return new Queue("weiXinScanPayQueue");
    }
    @Bean
    public Queue zhiFuScanPayQueue(){
        return new Queue("zhiFuScanPayQueue");
    }
    @Bean
    public Binding bindScanWeiXinToDirectExchange(Queue weiXinScanPayQueue,DirectExchange scanPayDirectExchange){
        return BindingBuilder.bind(weiXinScanPayQueue).to(scanPayDirectExchange).with("weixin");
    }
    @Bean
    public Binding bindScanZhiFuToDirectExchange(Queue zhiFuScanPayQueue,DirectExchange scanPayDirectExchange){
        return BindingBuilder.bind(zhiFuScanPayQueue).to(scanPayDirectExchange).with("zhifubao");
    }
}
