import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * Searches based on century.
 * To be used in date-based search.
 * @author Caitlin Tumulty
 */
public class ChantCenturySearch {
	public static ResultSet getChants(String centuryName, Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			return stmt.executeQuery("SELECT DISTINCT chantID,msIncipit,genreName, "
					+ "feastName, liturgicalOccasion, centuryName FROM ((((Chant NATURAL JOIN Genre) "
					+ "NATURAL JOIN Feast) NATURAL JOIN Leaf) Natural Join Section) NATURAL JOIN	"
					+ "Century WHERE centuryName =  " + "'"+centuryName+"'" + "LIMIT 150;" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
