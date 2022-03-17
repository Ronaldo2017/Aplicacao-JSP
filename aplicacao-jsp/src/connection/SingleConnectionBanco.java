package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	//autoReconnect=true = se a conexao cair ele consegue se reconectar
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;
	
	
	//retorna a conexao
	public static Connection getConnection() {
		return connection;
	}
	
	//a classe é chamada diretamente
	static {
		conectar();
	}
	
	//abrir uma instancia e conecta/ se abrir uma nova instancia
	public SingleConnectionBanco() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");//carrega o driver de conexao do bd
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);//nao efetua operacao no banco sem o comando do usuario
			}
			
		} catch (Exception e) {
			e.printStackTrace();//mostra erro no console ao conectar
		}
	}

}
