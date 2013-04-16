package ast;

import java.util.ArrayList;

public class FunctionDecl extends Decl {
	
	private String id;
	private ArrayList<Field> fieldDecl;
	private ArrayList<Stmt> body;

}
