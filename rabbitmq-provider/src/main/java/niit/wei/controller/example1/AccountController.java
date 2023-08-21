package niit.wei.controller.example1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author WeiJinLong
 * @Date 2023-08-21 15:03
 * @Version 1.0
 */
@Controller
@Slf4j
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("sendWage")
    @ResponseBody
    public String sendWage(@RequestParam("cardId") String cardId) {
        log.info("向卡号{}发送工资", cardId);
        amqpTemplate.convertAndSend("emailQueue1", cardId);
        return "发送工资成功！" + cardId;
    }
}
