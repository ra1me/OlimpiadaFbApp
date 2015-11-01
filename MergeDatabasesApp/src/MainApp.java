import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MainApp {

	
	
	public static void main(String[] args) {
		Scanner  in = new Scanner(System.in);
		String databaseToMerge;
		System.out.print("Nazwa bazy: ");
		databaseToMerge = in.nextLine();
		System.out.println("Opening main database...");
		Connection c=null;
		Statement stmt=null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:OlimpiadaMainDatabase");
			stmt = c.createStatement();
		} catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
		System.out.println("Main database openeded succesfully.");
		System.out.println("Opening database to add...");
		File addDatabase=null;
		addDatabase = new File(databaseToMerge);
		if(!addDatabase.exists()){
			System.err.println("Database to add not found.");
			System.exit(0);
		}
		System.out.println("Database to add opened successfully.");
		System.out.println("Adding to main database...");
		try {
		    BufferedReader inFile = new BufferedReader(new FileReader(addDatabase));
		    String line;
		    while ((line = inFile.readLine()) != null){
		    	System.out.println(line);
		        String cutLine[] = line.split("\\|");
		        try{
		        	cutLine[2]=cutLine[2];
		        }catch(Exception e){
		        	line=line + inFile.readLine();
		        }
		        cutLine = line.split("\\|");
		        System.out.println(line);
		        ResultSet rs = stmt.executeQuery( "SELECT * FROM questions WHERE question='"+cutLine[1]+"';");
		        if(rs.isAfterLast()){
		        	String sql = "INSERT INTO questions (id,type,question,answer) VALUES(null,"+
		        			cutLine[0]+",'"+cutLine[1]+"','"+cutLine[2]+"');";
		        	stmt.execute(sql);
		        }
		    }
		    inFile.close();
		} catch (IOException e) {
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished.");
	}

}
