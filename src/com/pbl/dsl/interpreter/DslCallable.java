package com.pbl.dsl.interpreter;

import java.util.List;

public interface DslCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
