package niit.wei.service.impl.example6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author WeiJinLong
 * @Date 2023-08-23 14:52
 * @Version 1.0
 */
@Service
@Slf4j
public class UserRegisterServiceImpl {
    @RabbitListener(queues = {"delayed.queue.user.register"})
    public void sendEmail(String uname) {
        log.info("用户发送邮件成功--》" + uname + "\t" + new Date().toString());
    }
}
