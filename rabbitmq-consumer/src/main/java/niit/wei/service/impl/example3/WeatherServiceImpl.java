package niit.wei.service.impl.example3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author WeiJinLong
 * @Date 2023-08-21 16:40
 * @Version 1.0
 */
@Service
@Slf4j
public class WeatherServiceImpl {

    @RabbitListener(queues = {"weatherFanoutJdQueue"})
    public void weatherFanoutJd(String msg){
        log.info("京东商城天气预报提示：{}",msg);
    }

    @RabbitListener(queues = {"weatherFanoutTbQueue"})
    public void weatherFanoutTb(String msg){
        log.info("淘宝商城天气预报提示：{}",msg);
    }
}
