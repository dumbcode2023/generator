package com.greedystar.generator.exceptions;

import java.io.IOException;

public class BusinessException extends RuntimeException{
    public BusinessException(IOException e) {
        super(e);
    }
}
