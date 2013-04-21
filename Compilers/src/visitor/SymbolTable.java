package visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SymbolTable {
	
	private ArrayList<SymbolTable> children = new ArrayList<SymbolTable>(); 
	private SymbolTable parent; 
	private Iterator<SymbolTable> it; 
	private HashMap<String, SymbolEntry> entries = new HashMap<String, SymbolEntry>();
	private ErrorHandler eh;

	public SymbolTable(SymbolTable symbolTable, ErrorHandler eh) {
		parent = symbolTable; 
		this.eh = eh;
	}
	
	public SymbolTable(ErrorHandler eh) {
		this.eh = eh;
		parent = null;
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
		System.out.println("PUT ENTRY 1");
		SymbolEntry e = new SymbolEntry(key, type, t);
		if(!entries.containsKey(key)) {
			
			entries.put(key, e);
			return 0;
		}
		eh.printErrorMessage(key, ErrorHandler.ErrorType.SCOPE_DECL);
		return -1;
	}
	
	public int put(String key, SymbolType t, String type, String returnType){
		System.out.println("PUT ENTRY 2");
		SymbolEntry e = new SymbolEntry(key, type, t, returnType);
		if(!entries.containsKey(key)) {
			
			entries.put(key, e);
			return 0;
		}
		eh.printErrorMessage(key, ErrorHandler.ErrorType.SCOPE_DECL);
		return -1;
	}
	
	public SymbolTable enterScope()
	{
		SymbolTable t = new SymbolTable(this, eh);
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
