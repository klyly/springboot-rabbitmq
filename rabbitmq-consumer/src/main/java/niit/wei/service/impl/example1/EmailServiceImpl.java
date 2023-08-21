package niit.wei.service.impl.example1;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @Author WeiJinLong
 * @Date 2023-08-21 15:08
 * @Version 1.0
 */
@Service
@Slf4j
@ConfigurationProperties(prefix = "server")
@Data
public class EmailServiceImpl {
    private String port;

    @RabbitListener(queues = {"emailQueue1"})
    public void sendEmail(String cardId) {
        log.info("向卡号{}发送工资,邮件发送成功！", cardId + "\t===>" + port);
    }
}
