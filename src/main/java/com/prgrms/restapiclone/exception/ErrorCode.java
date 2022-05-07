package com.prgrms.restapiclone.exception;

public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "C001", "입력 값이 잘못되었습니다."),
    INTERNAL_SERVER_ERROR(500, "C002", "서버에 문제가 발생하였습니다."),
    METHOD_NOT_SUPPORTED(406, "C003", "지원하지 않는 HTTP Method입니다.");

    private int status;
    private String code;
    private String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
