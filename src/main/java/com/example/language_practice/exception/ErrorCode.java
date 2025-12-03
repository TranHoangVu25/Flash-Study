package com.example.language_practice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTED(1002,"User is existed",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005,"User is not existed",HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006,"Token expired",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1007,"Token is not valid",HttpStatus.BAD_REQUEST),
    EMAIL_NOT_CONFIRMED(1008,"Email has not confirmed yet",HttpStatus.BAD_REQUEST),
    ACCOUNT_PASSWORD_NOT_CORRECT(1009,"Email or password is incorrect",HttpStatus.BAD_REQUEST),
    REPORT_NOT_EXISTED(1010,"Report is not existed",HttpStatus.BAD_REQUEST),
    STUDY_SET_NOT_EXISTED(1005,"Study Set is not existed",HttpStatus.NOT_FOUND),
    ;
    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
