package com.quyet.be_quan_ao.model.viewModel;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MessErro{
    private String mess;
    private int status;
}
