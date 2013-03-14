import java_cup.runtime.*;

%%

%debug
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
  
  public int getLineNumber() { return yyline + 1; }
%}

lineTerminator = \r|\n|\r\n
digit = [0-9]
identifier = [a-z][:jletterdigit:]*
int = 0 | -?[1-9]{digit}*
boolean = true|false
void = VOID
float = -?{digit}+.{digit}+
whitespace = [ \t\n\r\f]
myType = int|float|list|tuple|bool|string|char
char = \'[a-z0-9A-Z]\'

%state STRING ENDOFLINECOMMENT TRADITIONALCOMMENT

%%

<YYINITIAL> {
	{myType}		{	return symbol(sym.TYPE);	}
	";"				{	return symbol(sym.SEMI);	}
	":"				{	return symbol(sym.COLON);	}
	"="				{	return symbol(sym.EQ);	}
	"=="             {   return symbol(sym.EQCOMP);    }
	"!="             {   return symbol(sym.NOTEQ);    }
	"<"             {   return symbol(sym.LESS);    }
	"<="             {   return symbol(sym.LESSOREQ);    }
	">"             {   return symbol(sym.GREATER);    }
	">="             {   return symbol(sym.GREATEROREQ);    }
	"+"             {   return symbol(sym.PLUS);    }
	"-"             {   return symbol(sym.MINUS);    }
	"*"             {   return symbol(sym.TIMES);    }
	"/"             {   return symbol(sym.DIVIDE);    }
	"^"             {   return symbol(sym.POWER);    }
	"!"             {   return symbol(sym.NOT);    }
	"||"             {   return symbol(sym.OR);    }
	"&&"             {   return symbol(sym.AND);    }
	"["				{	return symbol(sym.LSQPAREN);	}
	"]"				{	return symbol(sym.RSQPAREN);	}
	"("				{	return symbol(sym.LPAREN);	}
	")"				{	return symbol(sym.RPAREN);	}
	"{"				{	return symbol(sym.LBRACE);	}
	"}"				{	return symbol(sym.RBRACE);	}
	","				{	return symbol(sym.COMMA);	}
	"|"				{ 	return symbol(sym.PIPE);	}
	\"		        { 	string.setLength(0); yybegin(STRING); }
	"return"        {	return symbol(sym.RETURN);	}
	"if"            {	return symbol(sym.IF);	}
	"else"          {	return symbol(sym.ELSE);	}
	"do"            {	return symbol(sym.DO);	}
	"while"         {	return symbol(sym.WHILE);	}
	"repeat"        {	return symbol(sym.REPEAT);	}
	"until"        {	return symbol(sym.UNTIL);	}
	"testexpr"		{	return symbol(sym.TEST);	}
	"tdef"			{	return symbol(sym.TDEF);	}
	"fdef"			{	return symbol(sym.FDEF);	}
	{char}			{ 	return symbol(sym.CHAR, yytext());	}
	{boolean}		{	return symbol(sym.BOOL, new Boolean(yytext()));}
	{identifier}	{	return symbol(sym.ID);	}
	{int}			{	return symbol(sym.INT, new Integer(yytext())); }
	{float} 		{	return symbol(sym.FLOAT, new Float(yytext())); }
	{whitespace} 	{} 
	{void}			{   return symbol(sym.VOID); }
	.				{ System.err.println("Illegal character <"+
                                                    yytext()+">"); }

}

<STRING> {
  \"                             { yybegin(YYINITIAL); 
                                   return symbol(sym.STRING_LITERAL, 
                                   string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }

  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
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

