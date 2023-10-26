package com.quyet.be_quan_ao.Utility;

import com.quyet.be_quan_ao.model.viewModel.MessErro;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class Const {

    public final static ResponseEntity MESS_ERROR = ResponseEntity.badRequest()
            .body(MessErro
                    .builder().mess("Have error")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build());

    public final static Optional MESS_ID_INVALID = Optional.of(MessErro
            .builder().mess("Id invalid")
            .status(400).build());

    public final static Optional MESS_DUPLICATE_NAME = Optional.of(MessErro
            .builder().mess("Duplicate name")
            .status(400)
            .build()
    );

    public final static Optional MESS_NOT_NULL = Optional.of(MessErro
            .builder().mess("String ís not null").status(601).build());

    public final static Optional MESS_QUALITY_Greater_than_0 = Optional.of(MessErro
            .builder().mess("quality > 0").status(600).build());

    public final static Optional MESS_ADD_SUSSCESS = Optional.of(MessErro.builder()
            .mess("add susscess").status(200).build());

    public static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources");

    public static final Optional MESS_FILE = Optional.of(MessErro.builder()
            .mess("file have jpg, png").status(700).build());
}
