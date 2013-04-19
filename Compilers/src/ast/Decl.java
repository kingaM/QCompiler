package ast;

import visitor.Visitor;

public class Decl extends Node{
	public void accept(Visitor v){
		v.visit(this);
	}
}
