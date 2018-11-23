import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * Searches based on library and chant ID.
 * Used to populate manuscript tables on both search pages.
 * @author Caitlin Tumulty
 */
public class ManuscriptSearch {
	
	public static ResultSet getManuscripts(String library, String city, String country, Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(!city.equals("Any") && !library.equals("Any")) {
			try {
				return stmt.executeQuery("SELECT DISTINCT * FROM (Manuscript NATURAL JOIN Library) NATURAL JOIN Country "
						+ "WHERE library =  " + "'"+library+"'" + " AND city = " +"'"+city+"'"+ ";" );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(city.equals("Any") && !library.equals("Any")) {
			try {
				return stmt.executeQuery("SELECT DISTINCT * FROM (Manuscript NATURAL JOIN Library) NATURAL JOIN Country "
						+ "WHERE library =  " + "'"+library+"'" + ";" );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(!city.equals("Any") && library.equals("Any")) {
			try {
				return stmt.executeQuery("SELECT DISTINCT * FROM (Manuscript NATURAL JOIN Library) NATURAL JOIN Country "
						+ "WHERE city = " +"'"+city+"'"+ ";" );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(city.equals("Any") && library.equals("Any")) {
			try {
				return stmt.executeQuery("SELECT DISTINCT * FROM (Manuscript NATURAL JOIN Library) NATURAL JOIN Country "
						+ "WHERE countryName =" + "'"+country+"'" + ";" );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static ResultSet getManuscriptsRefined(String libSiglum, String msSiglum, Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			return stmt.executeQuery("SELECT DISTINCT chantID, genreID, feastName, incipit FROM ((Manuscript NATURAL JOIN Chant) "
					+ "NATURAL JOIN MasterChant) NATURAL JOIN Feast WHERE libSiglum =  " + "'"+libSiglum+"' "
							+ "AND msSiglum = " + "'"+msSiglum+"'"+ " limit 500;" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet getManuscriptFromChant(String chantID, Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			return stmt.executeQuery("SELECT DISTINCT chantID, msSiglum, libSiglum, msIncipit, imageLink "
					+ "FROM ((Leaf NATURAL JOIN Chant) NATURAL JOIN Manuscript) NATURAL JOIN Feast "
					+ "WHERE chantID = " +"'"+chantID+"'"+ ";" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
