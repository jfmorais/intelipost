package intelipost.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import intelipost.login.bean.Perfil;
import intelipost.login.data.RedisConfig;
import intelipost.login.repository.PerfilRepository;
import intelipost.login.repository.PerfilRepositoryImpl;
import intelipost.login.util.ConnectionFactory;

public class LoginDAO {
	private static RedisConfig config = new RedisConfig();
	private static PerfilRepositoryImpl repo = new PerfilRepositoryImpl(config.redisTemplate());
	
	public static boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		Perfil p = null;
		
		try {
			
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement("Select id, senha, nome, sexo, idade from usuario_perfil where id = ? and senha = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				p = new Perfil();
				p.setId(rs.getString("id"));
				p.setSenha(rs.getString("senha"));
				p.setNome(rs.getString("nome"));
				p.setIdade(Integer.valueOf(rs.getString("idade")));
				p.setSexo(rs.getString("sexo"));
				System.out.println(p.toString());
//				repo.savePerfil(p);
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			ConnectionFactory.close(con);
		}
		return false;
	}
	
	public static boolean procuraRedis(String user, String password) {
		try {
			Perfil p = new Perfil();
			System.out.println(user);
			p=repo.findPerfil(user);
			if (p != null) {
				return true;
			}
		}catch (Exception ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		}
		return false;
	}
}