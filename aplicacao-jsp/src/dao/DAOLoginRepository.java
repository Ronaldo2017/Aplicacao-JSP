package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();//pega a conexao
	}
	
	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception {
		String sql = "select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?) ";
		PreparedStatement statement = connection.prepareStatement(sql);//prepara o sql
		
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		
		//resultado 
		ResultSet resultSet = statement.executeQuery();
		
		//se tiver usuario com login e senha
		if(resultSet.next()) {
			return true;//autenticado
		}
		
		return false;//nao autenticado
	}

}
