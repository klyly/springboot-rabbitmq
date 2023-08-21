package niit.wei.controller.example3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author WeiJinLong
 * @Date 2023-08-21 16:20
 * @Version 1.0
 */
@Controller
@RequestMapping("weather")
@Slf4j
public class WeatherController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("info")
    @ResponseBody
    public String info(HttpServletRequest request) {
        String city = request.getParameter("city");
        String msg = "最新天气情况：天气晴朗20-30," + city;
        log.info("最新天气情况：天气晴朗20-30,{}", city);
        amqpTemplate.convertAndSend("weatherFanoutExchange", null, msg);
        return "天气预报发送成功," + city;
    }
}
