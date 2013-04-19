package ast;

import visitor.Visitor;

public class Expr {
	 public void accept(Visitor v){
			v.visit(this);
		}
}
