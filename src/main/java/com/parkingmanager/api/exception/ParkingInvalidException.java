package com.parkingmanager.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@NoArgsConstructor
public class ParkingInvalidException extends RuntimeException {

  private final String title = "Parking Invalid";

  public ParkingInvalidException(String message) {
    super(message);
  }

}
