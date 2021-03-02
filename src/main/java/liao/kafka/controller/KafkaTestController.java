package liao.kafka.controller;

import com.alibaba.fastjson.JSON;
import liao.kafka.model.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        //使用LinkedHashMap保证有序消费
        Map<String, Object> listMap = new LinkedHashMap<>(num);
        for (int i = 0; i < num; i++) {
            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format0.format(System.currentTimeMillis());
            listMap.put("key-" + i + ":" + time, "value-" + i + ":" + time);
        }
        String topic = "kafka-1";
        for (Map.Entry<String, Object> listMap1 : listMap.entrySet()) {
            if (listMap1.getValue() != null) {
                kafkaTemplate.send(topic, listMap1.getKey(), listMap1.getValue().toString())
                        .addCallback(successCallback -> {
                            logger.info("发送kafka成功！");
                        }, FailureCallback -> {
                            logger.error(String.format("发送数据到Kafka出现错误，Topic: %s，数据: %s", topic, JSON.toJSONString(listMap1.getValue())));
                });
            }
        }
        System.out.println("kafka测试发送完成，共" + num + "条数据");
        return "kafka测试发送完成，共" + num + "条数据";
    }


    /**
     * 数据发送到指定topic
     *
     * @return
     */
    @PostMapping("/push")
    public Object push(@RequestBody DataModel dataModel) {
        if (dataModel != null) {
            kafkaTemplate.send(dataModel.getTopic(), dataModel.getFlowData().getEntityId(), JSON.toJSONString(dataModel.getFlowData()));
        }
        return dataModel;
    }
}