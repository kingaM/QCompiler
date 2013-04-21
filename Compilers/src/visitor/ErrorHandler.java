package visitor;

public class ErrorHandler {

	public enum ErrorType {
		TYPE, SCOPE_DECL, CUSTOM, SCOPE_NOTDECL
	}

	private int syntaxErrors = 0;
	private int scopeErrors = 0;
	private int typeErrors = 0;
	private int otherErrors = 0;

	public ErrorHandler(int syntaxErrors) {
		this.syntaxErrors = syntaxErrors;
	}

	public void printErrorMessage(String s, String section, ErrorType t) {
		switch (t) {
		case TYPE:
			typeErrors++;
			printErrorMessage("Incorrect type " + s + " in " + section);
			break;
		case SCOPE_NOTDECL:
			scopeErrors++;
			printErrorMessage("Variable " + s
					+ " was not found in the enclosing scope" + " in section "
					+ section);
			break;
		case SCOPE_DECL:
			scopeErrors++;
			printErrorMessage("Variable " + s
					+ " was already declared");
			break;
		case CUSTOM:
			otherErrors++;
			printErrorMessage(s);
		}
	}

	public void printErrorMessage(String s, ErrorType t) {
		switch (t) {
		case TYPE:
			typeErrors++;
			printErrorMessage("Incorrect type " + s);
			break;
		case SCOPE_NOTDECL:
			scopeErrors++;
			printErrorMessage("Variable " + s
					+ " was not found in the enclosing scope");
			break;
		case SCOPE_DECL:
			scopeErrors++;
			printErrorMessage("Variable " + s
					+ " was already declared");
			break;
		case CUSTOM:
			otherErrors++;
			printErrorMessage(s);
		}
	}

	private void printErrorMessage(String s) {
		System.err.println(s);
	}

	public void printSummary() {
		if (syntaxErrors == 0 && typeErrors == 0 && scopeErrors == 0)
			System.out
					.println("\n\nProgram compiled successfully, no errors found.");
		else {
			System.out.println("\n\nYou had :");
			System.out.println("\t" + syntaxErrors + " syntax"
					+ (syntaxErrors == 1 ? " error" : " errors"));
			System.out.println("\t" + typeErrors + " type"
					+ (typeErrors == 1 ? " error" : "errors"));
			System.out.println("\t" + scopeErrors + " scope"
					+ (scopeErrors == 1 ? " error" : " errors"));
			if(otherErrors > 0)
			System.out.println("\t" + otherErrors + " other"
					+ (otherErrors == 1 ? " error" : " errors"));
		}
	}

}
