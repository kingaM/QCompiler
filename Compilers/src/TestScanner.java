import java.io.*;

public class TestScanner {

	public static void main(String args[]) throws Exception {

		System.setIn(new FileInputStream("testfile.txt"));
		Yylex myScanner = new Yylex(System.in);
		QCup myParser = new QCup(myScanner);

		myParser.parse();
	}
}