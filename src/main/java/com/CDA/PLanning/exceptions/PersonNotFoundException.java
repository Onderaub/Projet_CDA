package com.CDA.PLanning.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The type Dvd not found exception.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Person Not Found")

public class PersonNotFoundException extends RuntimeException {

}



