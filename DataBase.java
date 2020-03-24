import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

	private Connection con;
	private Statement statement;
	private String db = "";
	
	public DataBase(String db) {
		this.db = db;
	}
	
	public boolean sjekker() {
		boolean eksisterer = true;
		try {
			this.con = DriverManager.getConnection(db);
			this.statement = con.createStatement();
			String sqlTabellen = "CREATE TABLE IF NOT EXISTS todo("
								+ "nr INTEGER PRIMARY KEY, "
								+ "todo VARCHAR(200) NOT NULL, "
								+ "aktiv BOOLEAN NOT NULL); ";
			
			int antallEndret = statement.executeUpdate(sqlTabellen);
			if (antallEndret > 0) {
				// databasen eksisterer ikke
				eksisterer = false;
			} else {
				// databasen eksiterer allerede
			}
			
			con.close();
		} catch ( SQLException e ) {
			System.err.print(e);
		}
		return eksisterer;
	}
	
	
	public String getQuery(String s) throws SQLException{
		String tempResult = "";
		try {
			this.con = DriverManager.getConnection(db);
			this.statement = con.createStatement();
			ResultSet rs = statement.executeQuery(s);
		while (rs.next()) {
			// read the result set
			System.out.println("name = " + rs.getString("name"));
			System.out.println("id = " + rs.getInt("id"));
			}
		
		con.close();
		
		} catch ( SQLException e ) {
			System.err.print("SQL Feil: " + e);
		} catch ( Exception e ) {
			System.err.print(e);
		}
		return tempResult;
	}
	
	public void lagNy(String s) {
		try {
			this.con = DriverManager.getConnection(db);
			this.statement = con.createStatement();
			statement.executeUpdate(s);
			con.close();
		} catch (SQLException e) {
			System.err.print(e);
		}		
	}
	/*
	public int getNr() {
		System.out.println("getting a number from DB");
		int temp = 0;
		try {
			this.con = DriverManager.getConnection(db);
			this.statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT count(*) AS nr FROM todo");
			System.out.print(rs);
			temp = rs.getInt("nr");
			System.out.println(temp);
			con.close();
			
		} catch (SQLException e) {
			System.err.print(e);
		} catch (Exception e) {
			System.out.print("Feil ved DB getNr metoden: " + e);
		}
		
		return temp;
		}
		*/
	}
