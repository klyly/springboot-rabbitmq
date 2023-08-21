package niit.wei.service.impl.example1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author WeiJinLong
 * @Date 2023-08-21 15:08
 * @Version 1.0
 */
@Service
@Slf4j
public class EmailServiceImpl {
    @RabbitListener(queues = {"emailQueue1"})
    public void sendEmail(String cardId){
        log.info("向卡号{}发送工资", cardId);
    }
}
