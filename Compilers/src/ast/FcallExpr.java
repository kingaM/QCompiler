package ast;

import java.util.ArrayList;

import visitor.Visitor;

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
	
	public void accept(Visitor v){
		v.visit(this);
	}


	@Override
	public String toString() {
		return "FcallExpr [id=" + id + ", parameters=" + parameters + "]";
	}
	
	

}
