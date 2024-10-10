package ru.bikmurzina.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bikmurzina.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.bikmurzina.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.bikmurzina.MySecondTestAppSpringBoot.model.Response;
import ru.bikmurzina.MySecondTestAppSpringBoot.service.ValidationService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Data

@RestController
public class MyController {

    private final ValidationService validationService;

    @Autowired
    public MyController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @RequestMapping(value = "/feedback", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<Response> feedback (@Valid @RequestBody Response request, BindingResult bindingResult) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T 'HH: mm:ss. SSS'z");
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(simpleDateFormat.format(new Date()))
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("ValidationExectption");
            response.setErrorMessage("ошибка валидации");
        } catch (Exception e) {
            response.setCode("failed");
            response.setErrorCode("unknownExectption");
            response.setErrorMessage("ошибка непредвиденная");

        }

        try {
            UnsupportedCodeException.isValid(bindingResult);
        }catch (UnsupportedClassVersionError e){
            response.setCode("failed");
            response.setErrorCode("ValidationExectption");
            response.setErrorMessage("ошибка валидации");
        } catch (Exception e) {
            response.setCode("failed");
            response.setErrorCode("unknownExectption");
            response.setErrorMessage("ошибка непредвиденная");


        }
        return new ResponseEntity<>(response, HttpStatus.OK);


    }

}