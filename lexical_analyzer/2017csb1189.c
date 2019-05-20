#include <stdio.h>
#include "2017csb1189.h" 

extern int yylex();
extern int yylineno;
extern FILE* yyin;

int main(int argc, char** argv){
	int current_token, prev_token, next_token;
	int data_type_line_number;
	int flag1=0,flag2=0;

	if(argc>=2){
		char* str=argv[1];
		yyin=fopen(str,"r");
	}
	else
		printf("Give input file as command line arguement.");

	current_token=yylex();
	next_token=yylex();

	while(current_token){
		prev_token=current_token;
		current_token=next_token;
		next_token=yylex();
		//To ignore the inside of COMMENTS
		if(current_token==START_COMMENT){
			prev_token=current_token;
			current_token=next_token;
			next_token=yylex();
			while(current_token!=END_COMMENT || current_token==EOF){
				prev_token=current_token;
				current_token=next_token;
				next_token=yylex();
			}
		}
		//To ignore inside of INVERTED COMMAS
		if(current_token==INVERTED_COMMA){
			prev_token=current_token;
			current_token=next_token;
			next_token=yylex();
			while(current_token!=INVERTED_COMMA){
				prev_token=current_token;
				current_token=next_token;
				next_token=yylex();
			}
		}
		//To ignore inside of SINGLE INVERTED COMMA
		if(current_token==SINGLE_INVERTED_COMMA){
			prev_token=current_token;
			current_token=next_token;
			next_token=yylex();
			while(current_token!=SINGLE_INVERTED_COMMA){
				prev_token=current_token;
				current_token=next_token;
				next_token=yylex();
			}
		}
		//FUNCTION ARGUEMENTS are also variables
		if(current_token==DATA_TYPE && (next_token==CORRECT_FUNCTION||next_token==INCORRECT_FUNCTION_CAPITAL||next_token==INCORRECT_FUNCTION_DIGIT))
			flag1=1;
		if(current_token==RIGHT_PARANTHESIS)
			flag1=0;
		//COMMA seperated variables
		if(current_token==SEMICOLON||current_token==RIGHT_PARANTHESIS)
			flag2=0;
		//DOCUMENTATION CHECK
		if(current_token==DATA_TYPE && prev_token!=END_COMMENT && flag1==0)
			if(next_token==CORRECT_VARIABLE||next_token==INCORRECT_VARIABLE_CAPITAL||next_token==INCORRECT_VARIABLE_STARTDIGIT||next_token==INCORRECT_VARIABLE_UNDERSCORE)
				printf("Line %d: Variable declarations should be documented.\n", yylineno);
		if(current_token==DATA_TYPE && prev_token!=END_COMMENT)
			if(next_token==CORRECT_FUNCTION||next_token==INCORRECT_FUNCTION_CAPITAL||next_token==INCORRECT_FUNCTION_DIGIT)
				printf("Line %d: Function declarations should be documented.\n", yylineno);
		//IDENTIFIER CHECK having previous DATA TYPE
		if(current_token==DATA_TYPE){
			flag2=1;
			if(next_token!=CORRECT_FUNCTION && next_token!=CORRECT_VARIABLE){
				if(next_token==INCORRECT_FUNCTION_CAPITAL)
					printf("Line %d: Function name should be lower case.\n", yylineno);
				else if(next_token==INCORRECT_FUNCTION_DIGIT)
					printf("Line %d: Function name should not have digits.\n", yylineno);
				else if(next_token==INCORRECT_VARIABLE_CAPITAL)
					printf("Line %d: Variable name should be lower case.\n", yylineno);
				else if(next_token==INCORRECT_VARIABLE_STARTDIGIT)
					printf("Line %d: Variable name should not start with digit.\n", yylineno);
				else if(next_token==INCORRECT_VARIABLE_UNDERSCORE)
					printf("Line %d: Variable name should not have underscore.\n", yylineno);
				else if(current_token==DATA_TYPE)
					printf("Line %d: Invalid variable name or token.\n", yylineno);
			}		
		}
		//IDENTIFIER CHECK defined after comma
		if(flag2==1 && prev_token==COMMA){
				if(current_token==INCORRECT_VARIABLE_CAPITAL)
					printf("Line %d: Variable name should be lower case.\n", yylineno);
				else if(current_token==INCORRECT_VARIABLE_STARTDIGIT)
					printf("Line %d: Variable name should not start with digit.\n", yylineno);
				else if(current_token==INCORRECT_VARIABLE_UNDERSCORE)
					printf("Line %d: Variable name should not have underscore.\n", yylineno);
		}
	}
	return 0;
}
