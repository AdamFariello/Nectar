package databaseCode;

@SuppressWarnings("serial") 
public abstract class databaseErrors extends Exception {
	public databaseErrors() {}
	/*
	public databaseErrors(String message, String string) {
		super(String.format(
			"\n" + message + "\n" + "-".repeat(message.length() + 5), string
		));
	}
	public databaseErrors(String message, int borderLength, String string) {
		super(String.format(
			"\n" + message + "\n" + "-".repeat(borderLength), string
		));
	}
	*/
	public databaseErrors(String message, String string) {
		super(String.format(
			"\n" + message, string
		));
	}
}

@SuppressWarnings("serial") 
class errorUnequalArrayListLengths extends databaseErrors { 
	private static String message = "The given input ArrayList for, "
						 		  + "and the amount of columns from %s, "
						 		  + "are of different lengths";
	public errorUnequalArrayListLengths(String string) {
		super(message, string);
	}
}


/*
@SuppressWarnings("serial") 
class errorUnequalObjectTypes extends databaseErrors {
	//TODO: Create functions to catch bad data types 
	//For when inserting into the database
	public errorUnequalObjectTypes(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}
}
*/

@SuppressWarnings("serial")
class errorNullConnection extends databaseErrors {
	errorNullConnection() { super(); }
}

@SuppressWarnings("serial")
class errorUnknownDatabase extends databaseErrors {
	private static String message = "The database %s is not a real database\n"
								  + "Use one of the following databases:"
								  + "\n\t1) nectarDB_administration"
								  + "\n\t2) nectarDB_products"
								  + "\n\t3) nectarDB_user";
	/*
	private static int borderLength = "The database %s is not a real database\n"
									  .length() + 5;
	public errorUnknownDatabase(String string) {
		super (message, borderLength, string);
	}
	*/
	
	public errorUnknownDatabase(String string) {
		super (message, string);
	}
}