package niit.wei.controller.example4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author WeiJinLong
 * @Date 2023-08-23 10:06
 * @Version 1.0
 * 扫码支付：当不同用户请求同一个二维码时，根据用户的扫描方式（微信/支付宝/银联）转发执行相应的服务器。
 */

@Controller
@RequestMapping("scan")
@Slf4j
public class ScanPayController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * TODO 扫码支付
     *
     * @return
     */
    @GetMapping("info")
    @ResponseBody
    public String info(HttpServletRequest request) {
//        支付金额
        String balance = request.getParameter("balance");
//        flag 微信 支付宝 银联
        String flag = request.getParameter("flag");
        log.info("进行扫码支付" + balance + "\t" + flag);
        amqpTemplate.convertAndSend("scanPayDirectExchange", flag, balance);
        return "账户支付成功" + flag + "\t" + balance;
    }
}
