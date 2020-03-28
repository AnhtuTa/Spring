package hello.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

    private int code;

    private String message;

    private Object data;

    private Object meta;

    public void setStatus(StatusType statusType) {
        this.code = statusType.getCode();
        this.message = statusType.getMessage();
    }

    public void setStatus(StatusType statusType, String message) {
        this.code = statusType.getCode();
        this.message = message;
    }
}
