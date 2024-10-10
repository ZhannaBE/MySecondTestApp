package ru.bikmurzina.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    public String uid;
    public String operationUid;
    private String systemTime;
    public String code;
    private String errorCode;
    public String errorMessage;

}
