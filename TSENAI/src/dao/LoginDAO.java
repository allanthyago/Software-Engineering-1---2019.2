package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Conexao.Conexao;
import entity.Usuario;
import gui.JanelaPrincipal;
import gui.JanelaPrincipalSenai;

public class LoginDAO {
	private String getSQL = "select * from usuario where usua_login=? and usua_senha=?";
	
	UsuarioDAO udao = new UsuarioDAO();
	public Usuario logar(String login, String senha) {
		Usuario u=new  Usuario();
        Connection conexao = Conexao.conector(); 
        try {
        	//as linhas abaixo preparam a consulta em função do que foi 
        	//digitado nas caixas de texto. O ? é substituído pelo conteúdo
        	//das variáveis que são armazenadas em pst.setString
            PreparedStatement pst = conexao.prepareStatement(getSQL);
            pst.setString(1, login);
            pst.setString(2, senha);
            //a linha abaixo executa a query(consulta)
            ResultSet rs = pst.executeQuery();
            //se existir um usuário e senha correspondente
            if (rs.next()) {
            	u.setCodigo(rs.getInt("usua_codigo"));
            	u.setNome(rs.getString("usua_nome"));
				u.setLogin(rs.getString("usua_login"));
				u.setSenha(rs.getString("usua_senha"));
				u.setPerfil(rs.getString("usua_perfil"));
                	                
            } else {
                JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido(s)");
            }

        } catch (Exception ee) {
            JOptionPane.showMessageDialog(null, ee);
        }
		if(u.getPerfil().equals("Administrador")) {
			JanelaAdmin(u.getNome());
		}else {
			JanelaUsuario(u.getNome());
		}
		return u;
	}
   
	public void JanelaAdmin(String codigo) {
		Usuario u = udao.getUsuarios(codigo);
		System.out.println(u.getNome());
		JanelaPrincipalSenai jps = new JanelaPrincipalSenai(u.getNome());
		
	}
	public void JanelaUsuario(String codigo) {
		Usuario u = udao.getUsuarios(codigo);
		System.out.println(u.getNome());
		JanelaPrincipal jp = new JanelaPrincipal(u.getNome());
		
	}
}
