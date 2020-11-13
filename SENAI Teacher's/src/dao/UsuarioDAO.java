package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.xmlbeans.impl.util.Base64;

import Conexao.Conexao;
import entity.Usuario;

public class UsuarioDAO {
	

//	private String insert = "insert into usuario(usua_nome,usua_login,usua_senha,usua_perfil) values(?,?,aes_encrypt(?,'çsenha'),?)";
//	private  String update = "UPDATE set usua_nome=?,usua_login=?, usua_senha = aes_decrypt(?,'çsenha'),usua_perfil=? where usua_codigo=?";
	private String insert = "insert into usuario(usua_nome,usua_login,usua_senha,usua_perfil) values(?,?,?,?)";
	private  String update = "UPDATE set usua_nome=?,usua_login=?, usua_senha = ?,usua_perfil=? where usua_codigo=?";
	private String deleteSQL = "delete from usuario where usua_codigo=?";
	private String listSQL = "select * from usuario order by usua_nome";
	
	PreparedStatement pst = null;
    ResultSet rs = null;
    
	
	public void adicionar(Usuario u) {
		 Conexao c = new Conexao();
		 c.conectar();
		 try {
			 pst = c.getConnection().prepareStatement(insert);
			 pst.setString(1, u.getNome());
			 pst.setString(2, u.getLogin());
			 pst.setString(3, u.getSenha());
			 pst.setString(4, u.getPerfil());
	//validação dos campos obrigatórios
			 if ((u.getNome().isEmpty()) || (u.getLogin().isEmpty()) || (u.getSenha().isEmpty())) {
				 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			 } else {
				 pst.execute();
				 pst.close();
				 JOptionPane.showMessageDialog(null, "Novo usuário cadastrado.");
			 }
		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e);
		 }
		 c.desconecta();
	 }
	 
	public void alterar(Usuario u) {
		 Conexao c = new Conexao();
		 c.conectar();
		 try {
			 pst = c.getConnection().prepareStatement(update);
			 pst.setString(1, u.getNome());
			 pst.setString(2, u.getLogin());
			 pst.setString(3, u.getSenha());
			 pst.setString(4, u.getPerfil());
			 pst.setInt(5, u.getCodigo());
			 if ((u.getNome().isEmpty()) || (u.getLogin().isEmpty()) || (u.getSenha().isEmpty())) {
				 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			 } else {
	            	
	//a linha abaixo atualiza a tabela usuario com os dados do formulário
	                //a etrutura abaixo é usada para confirmar a alteração dos dados na tabela
				 pst.executeUpdate();
				 
			 }
		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e);
		 }
		 c.desconecta();
	 }
	 
	public void excluir(int codigo) {
			Conexao c = new Conexao();
			try {
				c.conectar();
				PreparedStatement stm;
				stm = c.getConnection().prepareStatement(deleteSQL);
				stm.setInt(1, codigo);
				stm.execute();
				JOptionPane.showMessageDialog(null, "Usuário excluído");
			}catch(SQLException sqle) {
				JOptionPane.showMessageDialog(null, "Não foi possível excluir Usuário");
			}
			c.desconecta();
		}
	 public Usuario getUsuario(int codigo) {
		 Connection c = Conexao.conector();
		 Usuario p = new Usuario();
		 try {
			 PreparedStatement stm;
			 stm = c.prepareStatement("select * from usuario where usua_codigo=?");
			 stm.setInt(1, codigo);	
			 ResultSet rs = stm.executeQuery();
			 while(rs.next()){
				 p.setCodigo(rs.getInt("usua_codigo"));
				 p.setNome(rs.getString("usua_nome"));
				 p.setLogin(rs.getString("usua_login"));
				 p.setSenha(rs.getString("usua_senha"));
				 p.setPerfil(rs.getString("usua_perfil"));
			 }
		 }catch(SQLException sqle) {
			 JOptionPane.showMessageDialog(null, "Erro na consulta Usuário\n"+sqle.getMessage());
		 }
		 return p;
	 }

	 public Usuario getUsuarios(String nome) {
			Conexao c = new Conexao();
			Usuario p = new Usuario();
			try {
				c.conectar();
				PreparedStatement stm;
				stm = c.getConnection().prepareStatement( "select * from usuario where usua_nome=?");
				stm.setString(1, nome);
				
				ResultSet rs = stm.executeQuery();
				while(rs.next()){
					p.setCodigo(rs.getInt("usua_codigo"));
					p.setNome(rs.getString("usua_nome"));
					p.setLogin(rs.getString("usua_login"));
					p.setSenha(rs.getString("usua_senha"));
					p.setPerfil(rs.getString("usua_perfil"));
					
				}
			}catch(SQLException sqle) {
				JOptionPane.showMessageDialog(null, "Erro na consulta Usuário\n"+sqle.getMessage());
			}
			c.desconecta();
			return p;
		}
		public List<Usuario> listar() {
			Conexao c = new Conexao();
			c.conectar();
			Statement st;
			List<Usuario> list = new ArrayList<>();
			try {
				st = c.getConnection().createStatement();
				ResultSet rs = st.executeQuery(listSQL);
				while(rs.next()) {
					Usuario p = new Usuario();
					p.setCodigo(rs.getInt(1));
					p.setNome(rs.getString(2));
					p.setLogin((rs.getString(3)));
					p.setSenha(rs.getString(4));
					p.setPerfil(rs.getString(5));
					
					list.add(p);
				}
			}catch(SQLException sqle) {
				JOptionPane.showMessageDialog(null, "Erro na listagem dos Usuários");
			}
			return list;
		}
		
		public List<Usuario> listarNome(String nome) {
			Conexao c = new Conexao();
			c.conectar();
			Statement st;
			List<Usuario> list = new ArrayList<>();
			try {
				st = c.getConnection().createStatement();
				ResultSet rs = st.executeQuery("select * from usuario where usua_nome like '%"+nome+"%'");
				while(rs.next()) {
					Usuario p = new Usuario();
					p.setCodigo(rs.getInt(1));
					p.setNome(rs.getString(2));
					p.setLogin((rs.getString(3)));
					p.setSenha(rs.getString(4));
					p.setPerfil(rs.getString(5));
					
					list.add(p);
				}
			}catch(SQLException sqle) {
				JOptionPane.showMessageDialog(null, "Erro na listagem dos Usuários");
			}
			return list;
		}
		
		public Usuario BuscaUsuario(String nome) {
			Conexao c = new Conexao();
			Usuario p = new Usuario();
			c.conectar();
			Statement st;
			ResultSet rs = null;
			String sql = ("select * from usuario where usua_nome like '"+nome+"%'");
			try {
				st = c.getConnection().createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()) {
					p.setNome(rs.getString("usua_nome"));
					p.setCodigo(rs.getInt("usua_codigo"));
				}
			}catch(SQLException sqle) {
				JOptionPane.showMessageDialog(null, "Não existe Usuário cadastrado com esse nome.\n"+sqle.getMessage());
			}
			c.desconecta();
			return p;
	    }
		
		public void alterarSenha(Usuario u) {
			Conexao c = new Conexao();
			c.conectar();
			try {
				pst = c.getConnection().prepareStatement("update usuario set usua_senha = ? where usua_codigo=?");
				pst.setString(1, u.getSenha());
				pst.setInt(2, u.getCodigo());
				if ((u.getSenha().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
				} else {
		            	
					//a linha abaixo atualiza a tabela usuario com os dados do formulário
					//a etrutura abaixo é usada para confirmar a alteração dos dados na tabela
					pst.executeUpdate();
					 
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			c.desconecta();
		}
		
//		public void alterarSenha(Usuario u) {
//			Conexao c = new Conexao();
//			c.conectar();
//			try {
//				pst = c.getConnection().prepareStatement("update usuario set usua_senha = aes_encrypt(?, 'çsenha') where usua_codigo=?");
//				pst.setString(1, u.getSenha());
//				pst.setInt(2, u.getCodigo());
//				if ((u.getSenha().isEmpty())) {
//					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
//				} else {
//		            	
//					//a linha abaixo atualiza a tabela usuario com os dados do formulário
//					//a etrutura abaixo é usada para confirmar a alteração dos dados na tabela
//					pst.executeUpdate();
//					 
//				}
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, e);
//			}
//			c.desconecta();
//		}
		
		public Usuario verificaSenha(String login, String senha) {
			Connection c = Conexao.conector();
			Usuario u=new Usuario();
			try {
				pst=c.prepareStatement("select * from usuario where usua_login = ? and usua_senha = ?");
				pst.setString(1, u.getLogin());
				pst.setString(2, u.getSenha());
				ResultSet rs = pst.executeQuery();
				 while(rs.next()){
					 u.setCodigo(rs.getInt("usua_codigo"));
					 u.setNome(rs.getString("usua_nome"));
					 u.setLogin(rs.getString("usua_login"));
					 u.setSenha(rs.getString("usua_senha"));
					 u.setPerfil(rs.getString("usua_perfil"));
					 System.out.println("existe");
				 }
			 }catch(SQLException sqle) {
				 JOptionPane.showMessageDialog(null, "Erro na consulta Usuário\n"+sqle.getMessage());
			 }
			 return u;
		}
//		public Usuario verificaSenha(String login, String senha) {
//			Connection c = Conexao.conector();
//			Usuario u=new Usuario();
//			try {
//				pst=c.prepareStatement("select * from usuario where usua_login = ? and usua_senha = cast(aes_decrypt(usua_senha, 'çsenha') as char)=?");
//				pst.setString(1, u.getLogin());
//				pst.setString(2, u.getSenha());
//				ResultSet rs = pst.executeQuery();
//				 while(rs.next()){
//					 u.setCodigo(rs.getInt("usua_codigo"));
//					 u.setNome(rs.getString("usua_nome"));
//					 u.setLogin(rs.getString("usua_login"));
//					 u.setSenha(rs.getString("usua_senha"));
//					 u.setPerfil(rs.getString("usua_perfil"));
//					 System.out.println("existe");
//				 }
//			 }catch(SQLException sqle) {
//				 JOptionPane.showMessageDialog(null, "Erro na consulta Usuário\n"+sqle.getMessage());
//			 }
//			 return u;
//		}
}
