package visitor;

public class ErrorHandler {
	
	public enum ErrorType {
		TYPE, SCOPE, CUSTOM
	}

	private int syntaxErrors = 0;
	private int scopeErrors = 0;
	private int typeErrors = 0;
	private int otherErrors = 0;

	public ErrorHandler(int syntaxErrors) {
		this.syntaxErrors = syntaxErrors;
	}
	
	public void printErrorMessage(String s, String section, ErrorType t){
		switch(t){
		case TYPE:
			typeErrors++;
			printErrorMessage("Incorrect type " + s + " in " + section);
			break;
		case SCOPE:
			scopeErrors++;
			printErrorMessage("Variable " + s + " was not found in the enclosing scope" + " in section "+ section);
			break;
		case CUSTOM:
			otherErrors++;
			printErrorMessage(s);
		}
	}
	
	public void printErrorMessage(String s, ErrorType t){
		switch(t){
		case TYPE:
			typeErrors++;
			printErrorMessage("Incorrect type " + s);
			break;
		case SCOPE:
			scopeErrors++;
			printErrorMessage("Variable " + s + " was not found in the enclosing scope");
			break;
		case CUSTOM:
			otherErrors++;
			printErrorMessage(s);
		}
	}
	
	private void printErrorMessage(String s) {
		System.err.println(s);
	}

}
