package ast;

import java.util.ArrayList;

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

	@Override
	public String toString() {
		return "TypeDecl [" + (id != null ? "id=" + id + ", " : "")
				+ (fields != null ? "fields=" + fields : "") + "]";
	}

}
