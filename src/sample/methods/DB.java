package sample.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    Connection c = null;
    Statement s = null;

    public DB() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            System.out.println("connected to db");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void logger(String username, String password) {

        try {
            this.s = c.createStatement();
            ResultSet r = s.executeQuery("select * from users");
            boolean islogged = false;

            while(r.next()) {
                String u = r.getString("username");
                String p = r.getString("password");
                if (username.equals(u) && password.equals(p)) {
                    //logged
                    System.out.println("logged in successfully!");
                    islogged = true;
                    break;
                }
            }
            if (!islogged) {
                //failed to log in
                System.out.println("incorrect credentials");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(String username, String password, String rPassword, String defLocation) {

        try {
            this.s = c.createStatement();
            ResultSet r = s.executeQuery("select * from users");
            boolean validUsername = true;

            while(r.next()) {
                String u = r.getString("username");
                if (username.equals(u)) {
                    //logged
                    validUsername = false;
                    break;
                }
            }
            //weatherData w = new weatherData(defLocation) czy jakos tak XD
            if (validUsername && password.equals(rPassword)) { //&& localization exists
                try {
                    String query = "INSERT INTO users (username, password, defaultLoc) VALUES ('" + username + "', '" + password + "', '" + defLocation + "')";
                    this.s = c.createStatement();
                    s.execute(query);
                    System.out.println("signed up successfully!");
                }
                catch ( Exception e) {
                    e.printStackTrace();
                }
            }
            else if (!validUsername) {
                System.out.println("user exists");
            }
            else if (!password.equals(rPassword)){
                System.out.println("incorrect password");
            }
            else {
                System.out.println("incorrect location");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            c.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
