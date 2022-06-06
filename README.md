
# *Domain Specific Language for Children's Maze Game*

## Table of Contents

* [Goal](#goal)
* [Objectives](#objectives)
* [Grammar](#grammar)
* [Input and Output](#input-and-output)

## Goal

This DSL is developed upon a Problem-Based Learning university project, having the aim to facilitate the process of studying programming through interactive games.

## Objectives

- [x] Develop the **Grammar** of the DSL;
- [x] Implement the Lexical Analyzer (**Lexer**);
- [x] Implement the Syntax Analyzer (**Parser**);
- [x] Implement the Semantic Analyzer (**Interpreter**).

## Grammar

```
S = {<source code>}

VN = {<source code>, <set of affirmations>, <affirmation>, <function declaration>, <player>, <variable declaration>,
      <identifier>, <letter>, <digit>, <calculator>, <steps>, <call function>, <conditions>, <number>, <equal to>,
      <operation>, <direction>, <condition>, <variable>, <map>, <non-zero number>, <break>, <link>, <characters>}

VT = {0, …, 9, a, …, z, A, …, Z, Start, End, Player, Hunter, var, for, while, break, if, else, case, Step, Right,
      Left, Up, Down, Function, width, length, labyrinth-layout, “_”, “&&”, “||”, “:”, “=”, “==”, “;”, “{”, “}”,
      “<”, “>”, “<=”, “>=”, “!=”, “(”, “)”, “+”, “-”, “*”, “/”, “””, ““”, “,”, “.”}

P = {
    <source code> → <map> <source code>
    <source code> → <function declaration>* Start <player> {<set of affirmations>} End
    <function declaration> → Function <identifier> (<identifier>+) {<set of affirmations>}
    <identifier> → <letter> | {<letter> | <digit> | _} *
    <set of affirmations> → < affirmation> | < affirmation><set of affirmations>
    <affirmation> → <variable declaration> |
                    <call function> |
                    <calculator> |
                    <steps> |
                    <break> |
                    if <conditions> {<set of affirmations>} |
                    if <conditions> {<set of affirmations>} else {<set of affirmations>} |
                    while (<variable> | <number >) {<set of affirmations>} |
                    for (<variable> | <number >) {<set of affirmations>} |
                    case { <conditions> : {{}<set of affirmations> {}} }
    <variable declaration> → var <identifier>;
    <call function> → <identifier> (<identifier>+);
    <calculator> → <identifier> = <equal to>;
    <equal to> → <identifier> |
                 <identifier> <operation> <equal to> |
                 <number> |
                 <number> <operation> <equal to>
    <operation> → + | - | * | /
    <steps> → Step <direction>;
    <direction> → Right | Left | Up | Down
    <break> → break;
    <conditions> → <variable> <condition> <variable>
    <variable> → <identifier> | <letter>
    <condition> → && | || | == | < | > | <= | >= | !=
    <number> → <digit> | <digit> <number>
    <non-zero number> → <no 0 digit> | < no 0 digit > <number>
    <letter> → a | b | c | … | A | B | C | … | Z
    <no 0 digit> → 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
    <digit> → 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
    <player> → Player | Hunter
    <map> → import <link> |
            {width: <non-zero number>, length: <non-zero number>, labyrinth-layout: “<number>”}
    <link> → “<characters>*”
    <characters> → 0 | … | 9 | a | … | z | A | … | Z | _ | @| # | $ | % | : | = | ; | { | } | < | > | ! | ( | ) | + | - | * | / | .
}
```

## Input and Output

### Example 1:

```
> step right;
> step up;
> var a = 0; while (a < 5) {a = a + 1; step right;}
```

![maze-game](https://user-images.githubusercontent.com/78982185/172125276-68235152-45f7-42df-b5f2-c6740edf891b.gif)

### Example 2:

```
> step right;
> step down;
> var a = 0; while (a < 2) {a = a + 1; step right;}
> var b = 0; while (b < 2) {b = b + 1; step up;}
> var c = 0; while (c < 3) {c = c + 1; step right;}

```

![maze-game-2](https://user-images.githubusercontent.com/78982185/172127982-7603ad79-1e7e-41ae-9b49-853c2308e27f.gif)

### Example 3:

```
> step right;
> step down;
> var a = 0; while (a < 4) {a = a + 1; step right;}
> step left;
> step down;
> step up;
> step left;
> var b = 0; while (b < 2) {b = b + 1; step up;}
> var c = 0; while (c < 3) {c = c + 1; step right;}

```

![maze-game-3](https://user-images.githubusercontent.com/78982185/172131223-08723ca6-22de-41b3-a68a-6fb5d4fd0dee.gif)





