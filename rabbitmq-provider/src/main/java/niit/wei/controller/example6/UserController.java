package niit.wei.controller.example6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author WeiJinLong
 * @Date 2023-08-23 14:46
 * @Version 1.0
 */
@Controller
@RequestMapping("user")
@Slf4j
public class UserController {
    /**
     * 消息队列
     */
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * todo 用户注册成功后，生成一个延迟消息，3天没登录短信提醒
     */
    @GetMapping("register")
    @ResponseBody
    public String register(HttpServletRequest request) {
        String uname = request.getParameter("uname");
//        延迟时间毫秒
        int time = Integer.parseInt(request.getParameter("time"));
        int delayTime = time;
        log.info("用户业务逻辑注册成功--》" + uname + "\t" + new Date().toString() + "\t" + delayTime + "毫秒");
        //队列发送 邮件  延迟 delayTime
//        amqpTemplate.convertAndSend("delayed.exchange.user.register",
//                "delayed.key.user.register", uname, msg -> {
//                    msg.getMessageProperties().setDelay(delayTime);
//                    return msg;
//                });
        amqpTemplate.convertAndSend("delayed.exchange.user.register",
                "delayed.key.user.register", uname, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setDelay(delayTime);
                        return message;
                    }
                });
        return "用户注册成功";
    }
}
