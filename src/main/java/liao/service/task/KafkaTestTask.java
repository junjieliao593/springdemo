package liao.service.task;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ljj
 */
@Component
public class KafkaTestTask {

    private static Logger logger = LoggerFactory.getLogger(KafkaTestTask.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 测试数据接收
     *
     * @return
     */
    @KafkaListener(topics = "kafka-1")
    public void listenPassVehicle(ConsumerRecord<?, ?> record) {
        logger.info("消费kafka数据:" + record.value().toString());
    }
}
