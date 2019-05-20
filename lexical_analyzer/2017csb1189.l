%{
#include "2017csb1189.h" 
%}
%%

[\n]					yylineno++;
"/*"					return START_COMMENT;
"*/"					return END_COMMENT;
[0-9]*					return INTEGER;

["unsigned"]?"int"|"char"|"float"|"double"|"struct"|"long"|"void"|"long"[ ]+"int"|"long"[ ]+"long"[ ]+"int"		return DATA_TYPE;

[_a-z]*+[ ]*"("			return CORRECT_FUNCTION;
[_A-Za-z]+[ ]*"("		return INCORRECT_FUNCTION_CAPITAL;
[_a-z0-9A-Z]+[ ]*"("	return INCORRECT_FUNCTION_DIGIT;

[)}]					return RIGHT_PARANTHESIS;
";"						return SEMICOLON;
","						return COMMA;
["]						return INVERTED_COMMA;
[']						return SINGLE_INVERTED_COMMA;

[a-z][a-z0-9]*			return CORRECT_VARIABLE;
[a-zA-Z][a-zA-Z0-9]*	return INCORRECT_VARIABLE_CAPITAL;
[0-9][a-z0-9A-Z]*		return INCORRECT_VARIABLE_STARTDIGIT;
[_a-zA-Z0-9]*			return INCORRECT_VARIABLE_UNDERSCORE;
[ \t]					;
.						;
%%
int yywrap(void) {
return 1;
}
