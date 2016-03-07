import java.sql.*;
import java.io.*;
import java.util.Scanner;
class Uber {

    public static void main(String args[]) throws IOException {
        String url;
        Connection conn;
        PreparedStatement pStatement;
        ResultSet rs;
        String queryString;

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Failed to find the JDBC driver");
        }

        try {
            // In this try block, you should
            // 1) Connect to the database and set the search path
            // 2) Prompt the user for the name of a client
            // 3) Make the appropriate database query string
            //    (use the '?' placeholder!!)
            // 4) Output the results
            // 5) Close the connection

            // NOTE: We didn't cover this in lecture, but you'll need to
            // execute a "SET search_path TO uber" statement before
            // executing any queries. Use the following code snippet to do so:
            //
	    url = "jdbc:postgresql://localhost:5432/csc343h-g5kumars";
	    conn = DriverManager.getConnection(url, "g5kumars", "");

             queryString = "SET search_path TO uber";
             pStatement = conn.prepareStatement(queryString);
             pStatement.execute();

            // YOUR CODE GOES HERE
	    
	    //read input
	    Scanner reader = new Scanner(System.in);
	    System.out.println("Enter a Client name:");
	    String name = reader.nextLine();
	    
	    //System.out.println("Client name: " + name);
	    
	    queryString = "select firstname from dispatch natural join driver where request_id in (select request_id from client natural join request where firstname='" + name + "');";
	    pStatement = conn.prepareStatement(queryString);
	    rs = pStatement.executeQuery();
	    
	    while(rs.next()){
		System.out.println(rs.getString("firstname"));
	    }
        }
        catch (SQLException se) {
            System.err.println("SQL Exception." +
                    "<Message>: " + se.getMessage());
        }
    }
}
