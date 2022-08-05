package com.mck.task01.resources.exceptions;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String msg;
    private String path;
}
