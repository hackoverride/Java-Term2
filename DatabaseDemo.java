import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDemo {
	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:sqlite:demo.db");
		Statement statement = con.createStatement();
		statement.executeUpdate("drop table if exists person");
		statement.executeUpdate("create table person (id integer, name string)");
		statement.executeUpdate("insert into person values(1, 'john')");
		statement.executeUpdate("insert into person values(2, 'claire')");
		ResultSet rs = statement.executeQuery("select * from person");
		while (rs.next()) {
			// read the result set
			System.out.println("name = " + rs.getString("name"));
			System.out.println("id = " + rs.getInt("id"));
			}
		con.close();
		System.out.println("Success!");
		}
	}