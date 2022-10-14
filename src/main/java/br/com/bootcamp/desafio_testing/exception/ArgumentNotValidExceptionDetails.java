package br.com.bootcamp.desafio_testing.exception;

import br.com.bootcamp.desafio_testing.model.FiledError;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArgumentNotValidExceptionDetails {
    private int status;
    private String title;
    private String message;
    private List<FiledError> errors;
}
