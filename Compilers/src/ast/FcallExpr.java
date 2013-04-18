package ast;

import java.util.ArrayList;

public class FcallExpr extends Expr {
	
	private String id;
	private ArrayList<Expr> parameters;
	
	public FcallExpr(String id, ArrayList<Expr> seq) {
		this.id = id;
		this.parameters = seq;
	}

	public String getId() {
		return id;
	}

	public ArrayList<Expr> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		return "FcallExpr [id=" + id + ", parameters=" + parameters + "]";
	}
	
	

}
