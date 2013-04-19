package ast;

import visitor.Visitor;

public class Field {
	
	private String id;
	private String type;
	
	public Field(String id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}
	
	
	public void accept(Visitor v){
		v.visit(this);
	}



}
