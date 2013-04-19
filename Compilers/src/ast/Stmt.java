package ast;

import visitor.Visitor;

public class Stmt {
	 public void accept(Visitor v){
			v.visit(this);
		}
}
