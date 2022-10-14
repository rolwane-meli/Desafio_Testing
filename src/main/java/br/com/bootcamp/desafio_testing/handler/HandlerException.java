package br.com.bootcamp.desafio_testing.handler;

import br.com.bootcamp.desafio_testing.exception.ArgumentNotValidExceptionDetails;
import br.com.bootcamp.desafio_testing.exception.ExceptionDetails;
import br.com.bootcamp.desafio_testing.exception.NotFoundException;
import br.com.bootcamp.desafio_testing.model.FiledError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleNotFoundException(NotFoundException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .status(400)
                .title("BAD REQUEST")
                .message(ex.getMessage())
                .timestamps(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentNotValidExceptionDetails> handleTeste(MethodArgumentNotValidException ex) {

        List<FiledError> errors = ex.getFieldErrors().stream()
                .map((erro) -> new FiledError(erro.getField(), erro.getDefaultMessage(), Objects.requireNonNull(erro.getRejectedValue())
                        .toString())).collect(Collectors.toList());

        ArgumentNotValidExceptionDetails exceptionDetails = ArgumentNotValidExceptionDetails.builder()
                .status(400)
                .title("BAD REQUEST")
                .message("Argumentos inválidos.")
                .errors(errors)
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
