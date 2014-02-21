package clueGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BadConfigFormatException extends Exception {
	// Vars
	private String message;
	private static final String COLUMNCOUNTMESSAGE = "Column count mismatch.";
	private static final String ROWCOUNTMESSAGE = "Row count mismatch.";
	private static final String BADCELLMESSAGE = "Bad cell symbol.";
	private static final String BADLEGENDMESSAGE = "Legend line does not consist of key/value pair.";
	private static final String UNKNOWNMESSAGE = "Unknown error in configuration files.";
	private static final String LOGFILE = "logfile.txt";
	// Constructors
	public BadConfigFormatException(char errorcode) {
		super();
		switch(errorcode) {
		case 'c':
			message = COLUMNCOUNTMESSAGE;
			break;
		case 'r':
			message = ROWCOUNTMESSAGE;
			break;
		case 's':
			message = BADCELLMESSAGE;
			break;
		case 'l':
			message = BADLEGENDMESSAGE;
			break;
		}
		writeLog(LOGFILE);
	}
	public BadConfigFormatException() {
		super();
		message = UNKNOWNMESSAGE;
		writeLog(LOGFILE);
	}
	private void writeLog(String fname) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(fname), true)));
			out.println(toString());
			out.close();
		}
		catch(IOException e) {
			System.out.println(String.format("Could not write to \"%s\".", fname));
		}
	}
	// ToString Method
	public String toString() {
		return message;
	}
	// GetMessage Should use The New Message as Well
	@Override
	public String getMessage() {
		return toString();
	}
}