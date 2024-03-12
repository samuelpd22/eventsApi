package com.dsfgentarefas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class handlerException {

    @ExceptionHandler(EventoNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> EventNaoEncontrado(EventoNaoEncontradoException e, WebRequest request){
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                e.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);

    }
}
