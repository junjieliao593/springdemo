package liao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljj
 */
@RequestMapping("/test")
@RestController
public class KafkaTestController {
    private static Logger logger = LoggerFactory.getLogger(KafkaTestController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * kafka测试发送
     *
     * @return
     */
    @RequestMapping("/push/{num}")
    public Object pushTest(@PathVariable Integer num) {
        num = num == null ? 1 : num;
        Map<String, Object> listMap = new HashMap<>(num);
        for (int i = 0; i < num; i++) {
            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format0.format(System.currentTimeMillis());
            listMap.put("key-" + i + ":" + time, "value-" + i + ":" + time);
        }
        for (Map.Entry<String, Object> listMap1 : listMap.entrySet()) {
            if (listMap1.getValue() != null) {
                kafkaTemplate.send("kafka-1", listMap1.getKey(), listMap1.getValue().toString());
            }
        }
        System.out.println("kafka测试发送完成，共" + num + "条数据");
        return null;
    }
}