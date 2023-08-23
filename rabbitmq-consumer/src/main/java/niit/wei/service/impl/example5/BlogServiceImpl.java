package niit.wei.service.impl.example5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author WeiJinLong
 * @Date 2023-08-23 11:13
 * @Version 1.0
 */
@Service
@Slf4j
public class BlogServiceImpl {
    @RabbitListener(queues = {"blogJavaQueue"})
    public void sendBlogJava(String msg){
        log.info("java文章博客进行推送消息--->{}",msg);
    }

    @RabbitListener(queues = {"blogPythonQueue"})
    public void sendBlogPython(String msg){
        log.info("python文章博客进行推送消息--->{}",msg);
    }

    @RabbitListener(queues = {"blogAllQueue"})
    public void sendBlogAll(String msg){
        log.info("任意类型文章博客进行推送消息--->{}",msg);
    }
}
