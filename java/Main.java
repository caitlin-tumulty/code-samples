
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Main entry point to the application.
 * Creates the SQL connection and starts the GUI.
 * @author Caitlin Tumulty.
 */
public class Main {

	public static void main(String[] args) {
		
		Interface gui = new Interface();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();//taken from class notes
		
		}
		catch(Exception e){
			System.out.println("Connection Failure");//taken from class notes
		}
		
	try {
			conn = DriverManager.getConnection("jdbc:mysql://mysql.cs.jmu.edu/Manuscript2018", "tumultcm", "cs474");
		}
		catch(Exception e) {
			System.out.println("Connection Failure");
			System.exit(0);
		}
		
		gui.setConnection(conn);
		gui.run();

	}

}
