package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.*;

import connection.SingleConnectionBanco;

//intercepta todas as requisições que vierem do projeto ou mapeamento
@WebFilter({ "/principal/*" })
public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {

	}

	// encerra os processos quando o servidor é parado
	public void destroy() {

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * //intercepta as requisicoes e a as respostas no sistema //tudo que for feito
	 * no sistema passa por aqui //atenticacao de validadcao/ commit e rollback de
	 * transacoes no bd
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			
			// pega o usuario da sessao
			// request, é o parametro do doFilter
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			// usuario logado, vem da ServletLogin
			String usuarioLogado = (String) session.getAttribute("usuario");

			// url que esta sendo acessado
			String urlParaAutenticar = req.getServletPath();

			// valida se esta logado, se nao vai pra tela de login
			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {// nao esta
																											// logado

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Favor realizar o login!");
				redireciona.forward(request, response);
				return;// para a execução e redireciona para o login
			} else {
				chain.doFilter(request, response);
			}
			
			connection.commit();//comita as alteracoes no bd
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// inicia os processos ou recursos, quando o servidor sobe o projeto
	// inicia a conexão com DB
	public void init(FilterConfig fConfig) throws ServletException {

		//retorna a conexao
		connection = SingleConnectionBanco.getConnection();
	}

}
