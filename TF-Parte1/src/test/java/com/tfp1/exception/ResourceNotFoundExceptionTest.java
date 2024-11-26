package com.tfp1.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import main.java.com.tfp1.exception.ResourceNotFoundException;

/**
 * Testes para a classe ResourceNotFoundException.
 */
public class ResourceNotFoundExceptionTest {

    @Test
    public void testResourceNotFoundExceptionWithMessage() {
        String message = "Recurso não encontrado";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testResourceNotFoundExceptionWithMessageAndCause() {
        String message = "Recurso não encontrado";
        Throwable cause = new IllegalArgumentException("Causa");
        ResourceNotFoundException exception = new ResourceNotFoundException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
