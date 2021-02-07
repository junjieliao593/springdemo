package liao.controller.kafka;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ljj
 */
@Data
public class DataModel implements Serializable {

    private static final long serialVersionUID = -4574043842681498749L;

    private String topic;
    private FlowData flowData;


}
