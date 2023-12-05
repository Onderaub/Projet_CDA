package com.CDA.PLanning.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Tool Not Found")
public class ToolNotFoundException extends RuntimeException {
}
