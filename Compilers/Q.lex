import java_cup.runtime.*;

%%

%cup
%line
%column

%{
  StringBuffer string = new StringBuffer();
  
  private Symbol symbol(int type) { 
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}  

lineTerminator = \r|\n|\r\n
digit = [0-9]
identifier = [a-z][:jletterdigit:]*
int = 0 | -?[1-9]{digit}*
boolean = true|false
float = -?{digit}+\.{digit}+
whitespace = [ \t\n\r\f]
myType = int|float|list|tuple|bool|string|char
char = \'[a-z0-9A-Z]\'

%state STRING ENDOFLINECOMMENT TRADITIONALCOMMENT
 
%%

<YYINITIAL> {
	{myType}		{	return symbol(sym.TYPE, yytext());		}
	"::"			{	return symbol(sym.CONCAT);		}
	";"				{	return symbol(sym.SEMI);		}
	":"				{	return symbol(sym.COLON);		}
	"="				{	return symbol(sym.EQ);			}
	"=="            {   return symbol(sym.EQCOMP);    	}
	"!="            {   return symbol(sym.NOTEQ);    	}
	"<"             {   return symbol(sym.LESS);    	}
	"<="            {   return symbol(sym.LESSOREQ);    }
	">"             {   return symbol(sym.GREATER);    	}
	">="            {   return symbol(sym.GREATEROREQ); }
	"+"             {   return symbol(sym.PLUS);    	}
	"-"             {   return symbol(sym.MINUS);    	}
	"*"             {   return symbol(sym.TIMES);    	}
	"/"             {   return symbol(sym.DIVIDE);    	}
	"^"             {   return symbol(sym.POWER);    	} 
	"!"             {   return symbol(sym.NOT);    		}
	"[|"			{   return symbol(sym.LTUPLE);   	}
	"|]"			{   return symbol(sym.RTUPLE);   	}
	"||"            {   return symbol(sym.OR);    		}
	"&&"            {   return symbol(sym.AND);    		}
	"in"            {   return symbol(sym.IN);   		}
	"len"           {   return symbol(sym.LEN);   		}
	"["				{	return symbol(sym.LSQPAREN);	}
	"]"				{	return symbol(sym.RSQPAREN);	}
	"("				{	return symbol(sym.LPAREN);		}
	")"				{	return symbol(sym.RPAREN);		}
	"{"				{	return symbol(sym.LBRACE);		}
	"}"				{	return symbol(sym.RBRACE);		} 
	","				{	return symbol(sym.COMMA);		}
	\"		        { 	string.setLength(0); yybegin(STRING); }
	\.              {   return symbol(sym.DOT);    		}
	"return"        {	return symbol(sym.RETURN);		}
	"if"            {	return symbol(sym.IF);			}
	"else"          {	return symbol(sym.ELSE);		}
	"do"            {	return symbol(sym.DO);			}
	"while"         {	return symbol(sym.WHILE);		}
	"repeat"        {	return symbol(sym.REPEAT);		}
	"until"         {	return symbol(sym.UNTIL);		}
	"tdef"			{	QCup.section = "Type Declaration"; 
						return symbol(sym.TDEF);		}
	"fdef"			{	QCup.section = "Function Declaration"; 
						return symbol(sym.FDEF);		}
	"void"			{   return symbol(sym.VOID); 		}
	"//"			{ 	yybegin(ENDOFLINECOMMENT); 		}
	"/*"			{ 	yybegin(TRADITIONALCOMMENT);	}
	{char}			{ 	return symbol(sym.CHAR, yytext());	}
	{boolean}		{	return symbol(sym.BOOL, new Boolean(yytext()));}
	{identifier}	{	return symbol(sym.ID, yytext());			}
	{int}			{	return symbol(sym.INT, new Integer(yytext())); }
	{float} 		{	return symbol(sym.FLOAT, new Float(yytext())); }
	{whitespace} 	{									} 
	.				{ QCup.numOfErrors++;  System.err.println("Illegal character <"+
                                                    yytext()+"> at line " + (yyline + 1) + ", column " + (yycolumn + 1) + " at section " + QCup.section); }

}

<STRING> {
  \"					{ yybegin(YYINITIAL); 
                          return symbol(sym.STRING_LITERAL, 
                          string.toString()); }
  [^\n\r\"\\]+ 			{ string.append( yytext() ); }
  \\t       			{ string.append('\t'); }
  \\n     				{ string.append('\n'); }

  \\r   				{ string.append('\r'); }
  \\\"   				{ string.append('\"'); }
  \\    				{ string.append('\\'); }
}

<ENDOFLINECOMMENT> {
	{lineTerminator}	{	yybegin(YYINITIAL);	}
	  .					{	}
}

<TRADITIONALCOMMENT> {
	"*/"           		{	yybegin(YYINITIAL);	}
	{lineTerminator}	{	}
	.					{	}
}
