package ast;

import visitor.Visitor;

public class Decl extends Node{
	public Object accept(Visitor v){
		return v.visit(this);
	}
}
