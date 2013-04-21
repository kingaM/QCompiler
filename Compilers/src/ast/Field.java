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
	
	
	public Object accept(Visitor v){
		return v.visit(this);
	}

	@Override
	public String toString() {
		return "Field [" + (id != null ? "id=" + id + ", " : "")
				+ (type != null ? "type=" + type : "") + "]";
	}



}
