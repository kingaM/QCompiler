package visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SymbolTable {
	
	private ArrayList<SymbolTable> children;
	private SymbolTable parent; 
	private Iterator<SymbolTable> it; 
	private HashMap<String, SymbolEntry> entries = new HashMap<String, SymbolEntry>();

	public SymbolTable() {
		children = new ArrayList<SymbolTable>(); 
	}
	
	public SymbolTable(SymbolTable symbolTable) {
		this(); 
		parent = symbolTable; 
	}
	
	public SymbolTable getNextScope(){
		if(it == null)
			it = children.iterator(); 
			
		if(!it.hasNext())
		{
			System.out.println("A fatal error has occured, exiting..."); 
			System.exit(1); 
		}
		
		return (SymbolTable) it.next(); 
	}

	public SymbolEntry getEntry(String k, SymbolTable t) {
		if(t.entries.containsKey(k))
		{
			return t.entries.get(k); 
		}
		else if(t.parent != null)
		{
			return getEntry(k, t.parent); 
		}
		
		return null;
	}
	
	public SymbolEntry get(String k){
		return getEntry(k, this); 
	}
	
	public int put(String key, SymbolType t, String type){
		SymbolEntry e = new SymbolEntry(key, type, t);
		if(!entries.containsKey(key)) {
			entries.put(key, e);
			return 0;
		}
		System.out.println("\nERROR: " + t + " " + key + " already declared\n");
		return -1;
	}
	
	public int put(String key, SymbolType t, String type, String returnType){
		SymbolEntry e = new SymbolEntry(key, type, t, returnType);
		if(!entries.containsKey(key)) {
			entries.put(key, e);
			return 0;
		}
		System.out.println("\nERROR: " + t + " " + key + " already declared\n");
		return -1;
	}
	
	public SymbolTable enterScope()
	{
		SymbolTable t = new SymbolTable(this);
		children.add(t); 
		return t; 
	}
	
	public SymbolTable exitScope()
	{
		return parent; 
	}

	public ArrayList<SymbolTable> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return "SymbolTable ["
				+ (parent != null ? "parent=" + parent + ", " : "")
				+ (entries != null ? "entries=" + entries : "") + "]";
	}

}
