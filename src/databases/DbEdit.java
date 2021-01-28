package databases;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbEdit {

// TODO az egészet újracsinálni hogy változtatható legyen az adatbázis	
// csak néhány adatbázis lesz fix oszlopokkal, szal itt helyben lehet választani köztük egy listából

// 	elso feltoltes kesobb kell add/remove/edit
	private static String[] telepulesek = { "Telepulesek", "IRSZ", "NAME" };
	private static Connection con;

	private void getConnection() throws ClassNotFoundException, SQLException { // csatlakozik az adatbázishoz,

		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:src/databases/Telepulesek.db");
	}

	private ResultSet displayDb(String[] data) throws ClassNotFoundException, SQLException {
		if (con == null) {
			getConnection();
		}
		String stQquery = "SELECT @ROW1, @ROW2 FROM @TABLE";

		String[] queryToReplace = { "@TABLE", "@ROW1", "@ROW2" };
		String query = stQquery;
		for (int i = 0; i < telepulesek.length; i++) {
			query = query.replace(queryToReplace[i], data[i]);
		}

		Statement state = con.createStatement();
		ResultSet res = state.executeQuery(query); // dinamikusan fog változni
													// databasetől
													// függően, kell egy metódus rá //kb kész
		return res;
	}

	public void addData(String[] data, int IRSZ, String NAME) throws ClassNotFoundException, SQLException {
		if (con == null) {
			getConnection();
		}
		String stQquery = "INSERT INTO @TABLE(@ROW, @ROW2) values(?,?);	";
		String[] queryToReplace = { "@TABLE", "@ROW1", "@ROW2" };
		String query = stQquery;
		PreparedStatement prep = con.prepareStatement(query);

		prep.setInt(1, IRSZ);
		prep.setString(2, NAME);
		prep.execute();
	}

	public static void main(String[] args) {
			
		DbEdit dbed = new DbEdit();
		ResultSet rs;
	
////feltolt
//	
//		try {
//			BufferedReader fill2 = new BufferedReader(new InputStreamReader(
//					new FileInputStream("src/databases/Telepulesek.txt"), StandardCharsets.UTF_8));
//
//			String line = "";
//			String[] linearray = new String[2];
//			int lineCount = 1;
//			while ((line = fill2.readLine()) != null) {
//				linearray = line.split("\t", 0);
//				dbed.addData("Telepulesek", Integer.parseInt(linearray[0]), linearray[1]);
//				System.out.println("line " + lineCount + " added");
//				lineCount++;
//			}
//			fill2.close();
//		} catch (IOException | NumberFormatException | ClassNotFoundException | SQLException e1) {
//			
//			e1.printStackTrace();
//		}
//
//display konzolra

		try {
			rs = dbed.displayDb(telepulesek);
		
			while (rs.next()) {
				System.out.println(rs.getString("IRSZ") + " " + rs.getString("NAME"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
