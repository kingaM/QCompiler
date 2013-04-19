import ast.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;


public class SymbolTable {

	private Hashtable<Object, Object> symbolTable;

	public SymbolTable() {
		symbolTable = new Hashtable<Object,Object>();
	}
}

/* Ok, we can do this in 2 ways:
 * a - have one class and one hashmap to fill with that class alone 
 * b - have one class for functions and one for variables
 * I honestly prefer b... looks neater 
 */

// a
class Entry{
	int kind; //int or whatever, indicates variable, type or function
	String id;
	String type;
	Entry next; //or parent?
	
	int level; //for variables, 0 global, 1 local
}

//b 
class Variable{
	String id;
	String type;

	public Variable(String id, String type) {
		this.id = id;
		this.type = type;
	}

	public String id() { 
		return id; 
	}

	public String type() {
		return type; 
	}

}

class Function{
	String id;
	String type;
	ArrayList<Variable> parameters;
	HashMap<Object, Variable> variables;

	//TODO
	
}