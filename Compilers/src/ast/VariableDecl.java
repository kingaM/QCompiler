package ast;

import java.util.ArrayList;

import visitor.Visitor;

public class VariableDecl extends Decl {
	
	private String id;
	private String type;
	private ArrayList<Expr> init;
	
	public VariableDecl(String id, String type, ArrayList<Expr> seq) {
		super();
		this.id = id;
		this.type = type;
		this.init = seq;
	}
	
    public Object accept(Visitor v){
		return v.visit(this);
	}
    
	@Override
	public String toString() {
		String ifs = "";
		for(int i = 0; i < init.size() - 1; i++){
			ifs = ifs + init.get(i).toString() + ",";
		}
		ifs = ifs + init.get(init.size()-1).toString();
		return id + ":" + type + " = " + ifs + ";\n";
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public ArrayList<Expr> getInit() {
		return init;
	}

}
