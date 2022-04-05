package com.pbl.dsl;

import com.pbl.dsl.lexer.Token;

public class RuntimeError extends Throwable {
    public final Token token;
    public RuntimeError(Token token, String message) {
        super(message);
        this.token = token;
    }
}
