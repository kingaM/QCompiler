package visitor;

public class SymbolEntry {

	private String id;
	private String varType;
	private SymbolType type;
	private String retType;
	
	public SymbolEntry(String id, String varType, SymbolType type,
			String retType) {
		this.id = id;
		this.varType = varType;
		this.type = type;
		this.retType = retType;
	}

	public SymbolEntry(String id, String varType, SymbolType type) {
		this.id = id;
		this.varType = varType;
		this.type = type;
	}

	public SymbolType getType() {
		return type;
	}

	public String getVarType() {
		return varType;
	}

	public String getId() {
		return id;
	}

	public String getRetType() {
		return retType;
	}

	@Override
	public String toString() {
		return "SymbolEntry [" + (id != null ? "id=" + id + ", " : "")
				+ (varType != null ? "varType=" + varType + ", " : "")
				+ (type != null ? "type=" + type + ", " : "")
				+ (retType != null ? "retType=" + retType : "") + "]";
	}

}
