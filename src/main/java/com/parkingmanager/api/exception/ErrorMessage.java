package com.parkingmanager.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private int status;
    private String type;
    private String title;
    private String detail;
}
