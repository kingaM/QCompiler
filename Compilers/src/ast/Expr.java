package ast;

import visitor.Visitor;

public class Expr {
	 public Object accept(Visitor v){
			return v.visit(this);
		}
}
