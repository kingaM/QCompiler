package ast;

import java.util.ArrayList;

import visitor.Visitor;

public class TypeDecl extends Decl {
	
	private String id;
	private ArrayList<Field> fields;
	
	public TypeDecl(String id, ArrayList<Field> fields) {
		this.id = id;
		this.fields = fields;
	}

	public String getId() {
		return id;
	}

	public ArrayList<Field> getFields() {
		return fields;
	}
	
    public Object accept(Visitor v){
		return v.visit(this);
	}
    
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < fields.size() - 1; i++){
			s = s + fields.get(i).toString() + ", ";
		}
		s = s + fields.get(fields.size()-1).toString();
		return id + " : " + s + ";";
	}

}
