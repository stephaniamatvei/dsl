package com.pbl.dsl.parser;

import com.pbl.dsl.lexer.Token;

import java.util.List;

public class StmtPrinter implements Stmt.Visitor<String> {

    public String print(List<Stmt> stmts) {
        StringBuilder output = new StringBuilder();
        for (Stmt stmt : stmts) {
            output.append(stmt.accept(this)).append(" ");
        }
        return output.toString();
    }

    @Override
    public String visitBlockStmt(Stmt.Block stmt) {
        return " { " + print(stmt.statements) + " } ";
    }

    @Override
    public String visitExpressionStmt(Stmt.Expression stmt) {
        return new ExprPrinter().print(stmt.expression);
    }

    @Override
    public String visitFunctionStmt(Stmt.Function stmt) {
        return stmt.name.lexeme + parenthesizeTokens(stmt.params) + print(stmt.body);
    }

    @Override
    public String visitIfStmt(Stmt.If stmt) {
        String output = " If (" + new ExprPrinter().print(stmt.condition) + ") "
                + "{ " + stmt.thenBranch.accept(this) + " }";

        if (stmt.elseBranch != null)
            output += " else { " + stmt.elseBranch.accept(this) + " }";
        return output;
    }

    @Override
    public String visitVarStmt(Stmt.Var stmt) {
        return "var:" + stmt.name.lexeme + "(" + new ExprPrinter().print(stmt.initializer) + ")";
    }

    @Override
    public String visitStepStmt(Stmt.Step stmt) {
        return stmt.keyword.lexeme + " " + stmt.direction.lexeme;
    }

    @Override
    public String visitReturnStmt(Stmt.Return stmt) {
        return stmt.keyword.lexeme + "(" + new ExprPrinter().print(stmt.value) + ")";
    }

    @Override
    public String visitWhileStmt(Stmt.While stmt) {
        return "While(" + new ExprPrinter().print(stmt.condition) + ")"
                + "{" + stmt.body.accept(this) + "}";
    }

    private String parenthesizeTokens(List<Token> tl) {
        StringBuilder output = new StringBuilder(" ");
        for (Token t : tl) {
            output.append(t.lexeme).append(" ");
        }
        return output.toString();
    }
}