import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * Searches based on genre and feast.
 * Used to populate "Search by Chant" page.
 * @author Caitlin Tumulty
 */
public class ChantSearch {

	public ResultSet getFeasts(Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
			return stmt.executeQuery("SELECT DISTINCT feastName FROM Feast" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getGenre(Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
			return stmt.executeQuery("SELECT DISTINCT genreName FROM Genre" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ResultSet getChants(String genreName, String feastName, Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			return stmt.executeQuery("SELECT DISTINCT chantID,incipit,genreName, feastName FROM "
					+ "(MasterChant NATURAL JOIN Genre) NATURAL JOIN Feast WHERE genreName =  " 
					+ "'"+genreName+"' and feastName = " +"'"+feastName+"'" + ";" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
