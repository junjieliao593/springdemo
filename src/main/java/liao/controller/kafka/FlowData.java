package liao.controller.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ljj
 */
@Data
@NoArgsConstructor
public class FlowData implements Serializable {

    private static final long serialVersionUID = -8046529408356722997L;

    private String entityId;

    private String entityName;

    private String scheduleId;

    private String batchNo;

    private boolean boundary;

    private String receiveTime;

    private String operateType;

    private String sourceId;

    private String esIndex;

    private String command;

    private Map<String, Object> content;
}
