package com.pbl.dsl.lexer;

import com.pbl.dsl.DSL_Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1; // tracks what source line 'current' is on
    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("start",    TokenType.START);
        keywords.put("end",      TokenType.END);
        keywords.put("player",   TokenType.PLAYER);
        keywords.put("hunter",   TokenType.HUNTER);
        keywords.put("var",      TokenType.VAR);
        keywords.put("for",      TokenType.FOR);
        keywords.put("and",      TokenType.AND);
        keywords.put("class",    TokenType.CLASS);
        keywords.put("else",     TokenType.ELSE);
        keywords.put("false",    TokenType.FALSE);
        keywords.put("if",       TokenType.IF);
        keywords.put("nil",      TokenType.NIL);
        keywords.put("or",       TokenType.OR);
        keywords.put("print",    TokenType.PRINT);
        keywords.put("return",   TokenType.RETURN);
        keywords.put("super",    TokenType.SUPER);
        keywords.put("this",     TokenType.THIS);
        keywords.put("true",     TokenType.TRUE);
        keywords.put("while",    TokenType.WHILE);
        keywords.put("break",    TokenType.BREAK);
        keywords.put("case",     TokenType.CASE);
        keywords.put("step",     TokenType.STEP);
        keywords.put("right",    TokenType.RIGHT);
        keywords.put("left",     TokenType.LEFT);
        keywords.put("up",       TokenType.UP);
        keywords.put("down",     TokenType.DOWN);
        keywords.put("width",    TokenType.WIDTH);
        keywords.put("length",   TokenType.LENGTH);
        keywords.put("function", TokenType.FUNCTION);
        keywords.put("labyrinth-layout",  TokenType.LABYRINTH_LAYOUT);
    }

    public Scanner(String source) {
        this.source = source;
    }

    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(': addToken(TokenType.LEFT_PAREN); break;
            case ')': addToken(TokenType.RIGHT_PAREN); break;
            case '{': addToken(TokenType.LEFT_BRACE); break;
            case '}': addToken(TokenType.RIGHT_BRACE); break;
            case '_': addToken(TokenType.UNDERSCORE); break;
            case ',': addToken(TokenType.COMMA); break;
            case '.': addToken(TokenType.DOT); break;
            case '-': addToken(TokenType.MINUS); break;
            case '+': addToken(TokenType.PLUS); break;
            case '*': addToken(TokenType.STAR); break;
            case ':': addToken(TokenType.COLON); break;
            case ';': addToken(TokenType.SEMICOLON); break;
            case '&':
                addToken(match('&') ? TokenType.AND_AND : null);
                break;
            case '|':
                addToken(match('|') ? TokenType.DOUBLE_BAR : null);
                break;
            case '!':
                addToken(match('=') ? TokenType.NOT_EQUAL : TokenType.NOT);
                break;
            case '=':
                addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
                break;
            case '<':
                addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
                break;
            case '>':
                addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
                break;
            case '/':
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(TokenType.SLASH);
                }
                break;

            case ' ':
            case '\r':
            case '\t':
                break;

            case '\n':
                line++;
                break;

            case '"': string(); break;

            default:
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                } else {
                    DSL_Application.error(line, "Unexpected character.");
                }
                break;
        }
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) advance();

        String text = source.substring(start, current);
        TokenType type = keywords.get(text);
        if (type == null)
            type = TokenType.IDENTIFIER;
        addToken(type);
    }

    private void number() {
        while (isDigit(peek())) advance();

        // Look for a fractional part
        if (peek() == '.' && isDigit(peekNext())) {
            // Consume the "."
            advance();

            while (isDigit(peek())) advance();
        }

        addToken(TokenType.NUMBER,
                Double.parseDouble(source.substring(start, current)));
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }

        if (isAtEnd()) {
            DSL_Application.error(line, "Unterminated string.");
            return;
        }

        // The closing "
        advance();

        // Trim the surrounding quotes
        String value = source.substring(start + 1, current - 1);
        addToken(TokenType.STRING, value);
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    // peek() is sort of like advance(), but doesn't consume the character, it's called 'lookahead'
    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}
