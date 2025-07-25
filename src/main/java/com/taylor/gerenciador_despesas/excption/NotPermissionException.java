package com.taylor.gerenciador_despesas.excption;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotPermissionException extends ResponseStatusException {
    public NotPermissionException(String message) {
        super(HttpStatus.UNAUTHORIZED,message);
    }
}
