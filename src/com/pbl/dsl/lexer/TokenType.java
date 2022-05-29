package com.pbl.dsl.lexer;

public enum TokenType {
    // single-character tokens
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE,
    RIGHT_BRACE, COMMA, DOT, MINUS, PLUS,
    STAR, COLON, SEMICOLON, SLASH, UNDERSCORE,

    // one or two character tokens
    NOT, NOT_EQUAL,       // !, !=
    EQUAL, EQUAL_EQUAL,     // =, ==
    GREATER, GREATER_EQUAL, // >, >=
    LESS, LESS_EQUAL,       // <, <=
    AND_AND, DOUBLE_BAR,    // &&, ||

    // literals
    IDENTIFIER, STRING, NUMBER,

    // keywords
    START, END, PLAYER, HUNTER, VAR, FOR,
    AND, CLASS, ELSE, FALSE, IF, NIL, OR,
    PRINT, RETURN, SUPER, THIS, TRUE, WHILE,
    BREAK, CASE, STEP, RIGHT, LEFT, UP, DOWN,
    FUNCTION, WIDTH, LENGTH, LABYRINTH_LAYOUT,

    EOF
}
