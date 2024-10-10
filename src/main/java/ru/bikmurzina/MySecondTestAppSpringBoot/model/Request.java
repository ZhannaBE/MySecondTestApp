package ru.bikmurzina.MySecondTestAppSpringBoot.model;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

    public class Request {

    @NotBlank
    @Size(max=32)
    private String uid;

    @Size(max=32)
    private String operationUid;

    private String systemName;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String systemTime;

    private String source;

    @Min(1)
    @Max(100000)
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;




}
