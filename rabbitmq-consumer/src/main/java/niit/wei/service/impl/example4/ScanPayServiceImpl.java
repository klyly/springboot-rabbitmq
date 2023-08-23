package niit.wei.service.impl.example4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author WeiJinLong
 * @Date 2023-08-23 10:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class ScanPayServiceImpl {
    /**
     * TODO 微信服务器
     */
    @RabbitListener(queues = {"weiXinScanPayQueue"})
    public void weiXinPayInfo(String balance){
        log.info("微信服务器进行金额操作--->{}", balance);
    }
    /**
     * TODO 支出宝服务器
     */
    @RabbitListener(queues = {"zhiFuScanPayQueue"})
    public void zhiFuPayInfo(String balance){
        log.info("支付宝服务器进行金额操作--->{}", balance);
    }
}
