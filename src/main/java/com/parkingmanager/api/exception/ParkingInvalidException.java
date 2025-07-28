package com.parkingmanager.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingInvalidException extends RuntimeException {

  private String message = "Parking Invalid";
  private String description = "Some field is not valid";

  public ParkingInvalidException(String description) {
    this.description = description;
  }
}
