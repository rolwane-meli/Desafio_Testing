package br.com.bootcamp.desafio_testing.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionDetails {
    private int status;
    private String title;
    private String message;
    private LocalDateTime timestamps;
}
