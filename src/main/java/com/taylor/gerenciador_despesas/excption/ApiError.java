package com.taylor.gerenciador_despesas.excption;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record ApiError(
        String titile,
        int status,
        String detail,
        String instance,
        OffsetDateTime timestamp,
        Object message
) {
    public ApiError from(HttpServletRequest request, MethodArgumentNotValidException e){
        Map<String, List<String>> erros = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList()
                        )
                ));

        var title = e.getBody().getTitle();
        var status = e.getBody().getStatus();
        var detail = e.getBody().getDetail();
        var instance = request.getRequestURI();
        var timestamp = OffsetDateTime.now();
        var message = Map.copyOf(erros);

        return new ApiError(title, status, detail, instance, timestamp, message);
    }
}
