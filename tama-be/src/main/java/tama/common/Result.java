package tama.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {
    private String code;
    private String message;
    private T value;

    public void setSuccessResult(T value) {
        this.code = Constants.Code.SUCCESS;
        this.message = Constants.Message.SUCCESS;
        this.value = value;
    }

    public void setFailResult(String message) {
        this.code = Constants.Code.FAIL;
        this.message = message;
        this.value = null;
    }
}
