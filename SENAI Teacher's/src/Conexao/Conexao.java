package Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private static Connection con;
    Statement stm;
    boolean status;
    public ResultSet rs;

    public Connection getConnection(){
        return con;
    }

    public boolean conectar(){
//    	String url = "jdbc:mysql://192.168.0.4:3306/senai";	
    	String url = "jdbc:mysql://localhost/senai";	
    	String usuario = "root";
		String senha = "root";
		try {
			con = DriverManager.getConnection(url, usuario, senha);
			stm = con.createStatement();
			status = true;
			System.out.println("Conexão realizada com sucesso.");
		}catch(SQLException sqle) {
			status = false;
			System.err.println("Sem conexão com o banco\n"+sqle.getMessage());
		}
		return status;
    }

    public boolean desconecta(){
    	if(!status) {
			System.out.println("Desconectado!");
			status = true;
		}else {
			try {
				con.close();
				status = true;
				System.out.println("Desconectado!");
			} catch (SQLException e) {
				System.out.println("Não desconectou\n"+e.getMessage());
			}
		}
		return status;
    }

    public int incluir(String sql){
    	int result;
		try {
			stm = con.createStatement();
			result = stm.executeUpdate(sql);
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
			result = -1;
		}
		return result;
    }

    public ResultSet consultar(String sql){
    	ResultSet rs = null;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return rs;
	}
    
    public static Connection conector() {
        java.sql.Connection conexao = null;
        String driver = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://192.168.0.4:3306/senai";
    	String url = "jdbc:mysql://localhost/senai";	
        String user = "root";
        String password = "root";
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;

        } catch (Exception e) {
            return null;
        }

    }
}
