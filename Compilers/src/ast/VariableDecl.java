package ast;

import java.util.ArrayList;

public class VariableDecl extends Decl {
	
	private String id;
	private String type;
	private ArrayList<Expr> init;
	
	public VariableDecl(String id, String type, ArrayList<Expr> init) {
		super();
		this.id = id;
		this.type = type;
		this.init = init;
	}

	@Override
	public String toString() {
		return "VariableDecl [id=" + id + ", type=" + type + ", init=" + init
				+ "]";
	}

}
