package com.du1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//@RestControllerAdvice

public class ExceptionHandler {
        /**
         * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
         */
        @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
        @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
        public ErrorMessage handleAllException(Exception ex, WebRequest request) {
            // quá trình kiểm soat lỗi diễn ra ở đây
            return new ErrorMessage(10000, ex.getLocalizedMessage());
        }

        /**
         * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
         */
        @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
        @ResponseStatus(value = HttpStatus.BAD_REQUEST)
        public ErrorMessage Nullpointer(Exception ex, WebRequest request) {
            return new ErrorMessage(404, "Đối tượng không tồn tại null");

        }

        @org.springframework.web.bind.annotation.ExceptionHandler(IndexOutOfBoundsException.class)
        @ResponseStatus(value = HttpStatus.BAD_REQUEST)
        public ErrorMessage TodoException(Exception ex, WebRequest request) {
            return new ErrorMessage(404, "Đối tượng không tồn tại");

    }
}
