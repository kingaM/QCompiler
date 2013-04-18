package ast;

import java.util.ArrayList;

public class VariableDecl extends Decl {
	
	private String id;
	private String type;
	private ArrayList<Expr> init;
	
	public VariableDecl(String id, String type, ArrayList<Expr> seq) {
		super();
		this.id = id;
		this.type = type;
		this.init = seq;
	}

	@Override
	public String toString() {
		return "VariableDecl [id=" + id + ", type=" + type + ", init=" + init
				+ "]";
	}

}
