package repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private Properties jdbcProp;

    public JdbcUtils(Properties props){
        jdbcProp=props;
    }
    private Connection instance=null;
    private Connection getNewConncetion(){
        String driver=jdbcProp.getProperty("Motociclete.jdbc.driver");
        String url=jdbcProp.getProperty("Motociclete.jdbc.url");
        String user=jdbcProp.getProperty("Motociclete.jdbc.user");
        String pass=jdbcProp.getProperty("Motociclete.jdbc.pass");
        Connection con=null;
        try{
            Class.forName("org.sqlite.JDBC");
            if(user!=null&&pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con=DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public Connection getConnection(){
        try{
            if(instance==null || instance.isClosed())
                instance=getNewConncetion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }
}




    /*
    public Connection getConnection(){
        Connection conn=null;
        try{
            //db parameters
            Class.forName("Motociclete.jdbc.driver");
            String url = "jdbc:sqlite:D:\\FACULTATE\\AN2\\SEM2\\MEDII DE PROIECTARE SI PROGRAMARE\\Motociclete.db";
            //create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Conexiunea la SQLite a fost facuta");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }*/
