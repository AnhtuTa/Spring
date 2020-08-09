package hello.common;

import lombok.Getter;

@Getter
public enum StatusType {

    // 200
    SUCCESS(200000, "Success"),
    NO_DATA(200001, "No data"),

    // 400
    FAIL(400000, "FAIL"),

    // 401
    UNAUTHORIZED(401001, "Oops! Unauthorized!!!"),

    // 404
    BOOK_NOT_FOUND(404001, "Book not found"),

    // 500
    INTERNAL_SERVER_ERROR(404001, "INTERNAL SERVER ERROR!");

    private int code;
    private String message;

    private StatusType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
