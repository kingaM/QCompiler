package ast;

import java.util.ArrayList;
import visitor.Visitor;

public class FunctionDecl extends Decl {

	private String id;
	private String returnType;
	private ArrayList<Field> fieldDecl;
	private ArrayList<Stmt> body;

	public FunctionDecl(String id, String returnType,
			ArrayList<Field> fieldDecl, ArrayList<Stmt> body) {
		this.id = id;
		this.returnType = returnType;
		this.fieldDecl = fieldDecl;
		this.body = body;
	}

	public String getId() {
		return id;
	}

	public String getReturnType() {
		return returnType;
	}

	public ArrayList<Field> getFieldDecl() {
		return fieldDecl;
	}

	public ArrayList<Stmt> getBody() {
		return body;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}

	@Override
	public String toString() {
		String bodys = "";
		if (body != null) {

			for (int i = 0; i < body.size(); i++) {
				bodys = bodys + body.get(i).toString();
			}
		}
		String s = "";
		if (fieldDecl != null) {

			for (int i = 0; i < fieldDecl.size() - 1; i++) {
				s = s + fieldDecl.get(i).toString() + ",";
			}
			s = s + fieldDecl.get(fieldDecl.size() - 1).toString();
		}
		return "fdef " + id + "(" + s + ") : " + returnType + " {\n" + bodys
				+ "\n}\n";
	}

}
