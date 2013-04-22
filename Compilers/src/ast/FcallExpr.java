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
	
	public Object accept(Visitor v){
		return v.visit(this);
	}


	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < parameters.size() - 1; i++){
			s = s + parameters.get(i).toString() + ",";
		}
		s = s + parameters.get(parameters.size()-1).toString();
		return id + "(" + s + ")";
	}
	
	

}
