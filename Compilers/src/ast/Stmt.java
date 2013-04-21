package ast;

import visitor.Visitor;

public class Stmt {
	 public Object accept(Visitor v){
			return v.visit(this);
		}
}
