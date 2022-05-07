package com.prgrms.restapiclone.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    private String message;
    private int status;
    private String code;
    private List<FieldError> errors;

    private ErrorResponse(ErrorCode code, List<FieldError> errors) {
        this.message = code.getMessage();
        this.errors = errors;
        this.code = code.getCode();
        this.status = code.getStatus();
    }

    private ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.code = code.getCode();
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(ErrorCode code, String message) {
        this.message = message;
        this.code = code.getCode();
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
    }

    public static ErrorResponse of(ErrorCode code, String message) {
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    private static class FieldError {
        private String field;
        private String value;
        private String reason;

        public static List<FieldError> of(String field, String value, String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

}
