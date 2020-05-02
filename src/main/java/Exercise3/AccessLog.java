package Exercise3;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AccessLog implements Serializable {
    private String tenantName;

    private LocalDateTime dateTime;

    private String operation;

    private DoorStatus doorStatus;

    private String errorMessage;

    public AccessLog(String tenantName, LocalDateTime dateTime, String operation, DoorStatus doorStatus, String errorMessage){
        this.tenantName=tenantName;
        this.dateTime=dateTime;
        this.operation=operation;
        this.doorStatus=doorStatus;
        this.errorMessage=errorMessage;
    }
    @Override
    public String toString() {
        return "\nAccessLog{" +
                "tenantName='" + tenantName + '\'' +
                ", dateTime=" + dateTime +
                ", operation='" + operation + '\'' +
                ", doorStatus=" + doorStatus +
                ", errorMessage='" + errorMessage + '\'' +
                "}";
    }
}
