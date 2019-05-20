# LEXICAL ANALYZER

## Overview
We need to create a program in C which checks whether certain coding conventions have been adhered to in a given source code file as explained below. The lexical analysis of the input C code should be performed by a program generated via lex tool. For this you need to write a suitable specification (definitions, rules etc.) file which will be input to lex tool for generating the lexical analyser.
- for more info [assignment_ques.pdf](https://github.com/cynicphoenix/Pragmatics-Assignments/blob/master/lexical_analyzer/CSL202-Assignment-1.pdf)
## Directories

```bash
|-lexical_analyzer
    |-2017csb1189.c
    |-2017csb1189.h
    |-2017csb1189.l
    |-test.c
```

## To Execute

To RUN THE PROGRAM, enter:
(test file is test.c)

```bash
lex 2017csb1189.l
gcc 2017csb1189.c lex.yy.c -o r
./r test.c
```
