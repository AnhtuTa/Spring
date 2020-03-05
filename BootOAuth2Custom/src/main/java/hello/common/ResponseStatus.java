package hello.common;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    
    UNAUTHORIZED(401001, "Oops! Unauthorized!!!"),

    BOOK_NOT_FOUND(404001, "Book not found!"),
    USER_XYZ_NOT_FOUND(404002, "User XYZ not found!"),
    USER_NOT_FOUND(404003, "User not found!"),
    SCHOOL_NOT_FOUND(404004, "Schoo; not found!");
    
    private int code;
    private String message;

    private ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

}