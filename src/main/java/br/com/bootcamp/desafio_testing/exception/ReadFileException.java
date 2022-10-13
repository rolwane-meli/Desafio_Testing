package br.com.bootcamp.desafio_testing.exception;

public class ReadFileException extends RuntimeException {
    public ReadFileException(String message) {
        super(message);
    }
}
