import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * Searches based on country, city, and library.
 * Used to populate "Search by Library" page.
 * @author Caitlin Tumulty
 */
public class LibrarySearch {

	public ResultSet getCountries(Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
			return stmt.executeQuery("SELECT DISTINCT countryName FROM Country" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ResultSet getCityFromCountry(String country, Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
			return stmt.executeQuery("SELECT DISTINCT city FROM Library a NATURAL JOIN Country b WHERE b.countryName =  " 
					+ "'"+country+"'" + ";" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} 

	public ResultSet getLibraries(String city, String country, Connection conn) {
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
			if(city.equals("Any")) {
				return stmt.executeQuery("SELECT library FROM Library NATURAL JOIN Country WHERE countryName = " 
						+"'"+country+"'");
			}
			return stmt.executeQuery("SELECT library FROM Library WHERE city = " +"'"+city+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

