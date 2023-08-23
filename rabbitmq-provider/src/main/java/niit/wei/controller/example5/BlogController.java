package niit.wei.controller.example5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author WeiJinLong
 * @Date 2023-08-23 11:07
 * @Version 1.0
 */
@Controller
@Slf4j
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * TODO  今日头条中，有个关注栏目。
     *      当用户关注了多个类型的博客主时，对应的博客主发送了相应的文章，会推送给用户。
     *      例如： 关注了Java相关技术，新文章时，会推送
     *            关注了 Python相关技术，新文章时，会推送
     */
    @RequestMapping("info")
    @ResponseBody
    public String sendBlogInfo(HttpServletRequest request) {
//        文章内容
        String msg = request.getParameter("msg");
//        栏目key
        String key = request.getParameter("key");
        log.info("博客文章发送成功--->{}===>{}", msg, key);
        amqpTemplate.convertAndSend("blogTopicExchange", key, msg);
        return "博客文章发送成功" + msg + "\t" + key;
    }
}
