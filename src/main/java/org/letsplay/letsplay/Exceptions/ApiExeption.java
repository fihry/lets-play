package org.letsplay.letsplay.Exceptions;

import lombok.Getter;
import org.letsplay.letsplay.dto.userRresponse;
@Getter
public class ApiExeption extends RuntimeException {
    private userRresponse userRresponse;
    public ApiExeption(String message) {
        super(message);
    }
}
