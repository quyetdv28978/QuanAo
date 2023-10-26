package com.quyet.be_quan_ao.handleException;

import com.quyet.be_quan_ao.model.viewModel.MessErro;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class handleFile {
    @ExceptionHandler(SizeLimitExceededException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public MessErro handleFileErro() {
        return MessErro.builder().mess("file < 10MB").build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MessErro HandleLogin() {
        return MessErro.builder().mess("Người dùng không hợp lệ").build();
    }
}
