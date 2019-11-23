package dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexao.Conexao;
import entity.Professor;
import entity.Usuario;

public class ProfessorDAO {
	UsuarioDAO udao = new UsuarioDAO();
	private String insertSQL = "insert into professor (prof_nome, prof_email, prof_telresidencial, prof_telcel1, prof_telcel2, prof_formaprinc, prof_atuaprinc, prof_formasec, prof_atuasec, prof_imagem, prof_curriculo,prof_nome_arquivo, prof_usua_codigo) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String listSQL = "select * from professor order by prof_nome";
	private String updateSQL = "update professor set prof_nome=?, prof_email=?, prof_telresidencial=?, prof_telcel1=?, prof_telcel2=?, prof_formaprinc=?, prof_atuaprinc=?, prof_formasec=?, prof_atuasec=?, prof_imagem=?, prof_curriculo=?, prof_nome_arquivo=?, prof_usua_codigo=? where prof_codigo=?";
	private String deleteSQL = "delete from professor where prof_codigo=?";
	private String getSQL = "select * from professor where prof_codigo=?";
	
	public Professor getProfessor(int codigo) {
		Conexao c = new Conexao();
		Professor p = new Professor();
		try {
			byte[] fileBytes;
			c.conectar();
			PreparedStatement stm;
			stm = c.getConnection().prepareStatement(getSQL);
			stm.setInt(1, codigo);
			
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				UsuarioDAO udao = new UsuarioDAO();
				p.setCodigo(rs.getInt("prof_codigo"));
				p.setNome(rs.getString("prof_nome"));
				p.setEmail(rs.getString("prof_email"));
				p.setTelResidencial(rs.getString("prof_telresidencial"));
				p.setTelCel1(rs.getString("prof_telcel1"));
				p.setTelCel2(rs.getString("prof_telcel2"));
				p.setFormaPrinc(rs.getString("prof_formaprinc"));
				p.setAtuaPrinc(rs.getString("prof_atuaprinc"));
				p.setFormaSec(rs.getString("prof_formasec"));
				p.setAtuaSec(rs.getString("prof_atuasec"));
				p.setImagem(rs.getBytes("prof_imagem"));
				p.setCurriculo(rs.getBytes("prof_curriculo"));
				fileBytes = rs.getBytes("prof_curriculo");
				OutputStream targetFile=  new FileOutputStream("C:\\Users\\thyag\\Documents\\Allan\\UFAC\\6ºP\\novo.pdf");
                targetFile.write(fileBytes);
                targetFile.close();
				p.setNomeArquivo(rs.getString("prof_nome_arquivo"));
				p.setUsuario(udao.getUsuario(rs.getInt("prof_usua_codigo")));
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na consulta Professor"+sqle.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.desconecta();
		return p;
	}
	
	public Professor getProfessores(String nome) {
		Conexao c = new Conexao();
		Professor p = new Professor();
		try {
			byte[] fileBytes;
			c.conectar();
			PreparedStatement stm;
			stm = c.getConnection().prepareStatement(getSQL);
			stm.setString(1, nome);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				UsuarioDAO udao= new UsuarioDAO();
				p.setCodigo(rs.getInt("prof_codigo"));
				p.setNome(rs.getString("prof_nome"));
				p.setEmail(rs.getString("prof_email"));
				p.setTelResidencial(rs.getString("prof_telresidencial"));
				p.setTelCel1(rs.getString("prof_telcel1"));
				p.setTelCel2(rs.getString("prof_telcel2"));
				p.setFormaPrinc(rs.getString("prof_formaprinc"));
				p.setAtuaPrinc(rs.getString("prof_atuaprinc"));
				p.setFormaSec(rs.getString("prof_formasec"));
				p.setAtuaSec(rs.getString("prof_atuasec"));
				p.setImagem(rs.getBytes("prof_imagem"));
				p.setCurriculo(rs.getBytes("prof_curriculo"));
				fileBytes = rs.getBytes("prof_curriculo");
				OutputStream targetFile=  new FileOutputStream("C:\\Users\\thyag\\Documents\\Allan\\UFAC\\6ºP\\novo.pdf");
                targetFile.write(fileBytes);
                targetFile.close();
				p.setNomeArquivo(rs.getString("prof_nome_arquivo"));

				p.setUsuario(udao.getUsuario(rs.getInt("prof_usua_codigo")));
				
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na consulta Professores");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.desconecta();
		return p;
	}
	
	public void excluir(int codigo) {
		Conexao c = new Conexao();
		try {
			c.conectar();
			PreparedStatement stm;
			stm = c.getConnection().prepareStatement(deleteSQL);
			stm.setInt(1, codigo);
			stm.execute();
			JOptionPane.showMessageDialog(null, "Professor excluído");
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Não foi possível excluir Professor");
		}
		c.desconecta();
	}
	
	public void atualizar(Professor p, String nome) {
		Conexao c = new Conexao();
		Usuario usua = udao.getUsuarios(nome);
		try {
			c.conectar();
			PreparedStatement ps;
			ps = c.getConnection().prepareStatement(updateSQL);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getEmail());
			ps.setString(3, p.getTelResidencial());
			ps.setString(4, p.getTelCel1());
			ps.setString(5, p.getTelCel2());
			ps.setString(6, p.getFormaPrinc());
			ps.setString(7, p.getAtuaPrinc());
			ps.setString(8, p.getFormaSec());
			ps.setString(9, p.getAtuaSec());
			ps.setBytes(10, p.getImagem());
			ps.setBytes(11, p.getCurriculo());
			ps.setString(12, p.getNomeArquivo());
			ps.setInt(13, usua.getCodigo());
			ps.setInt(14, p.getCodigo());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar dados do paciente\n"+sqle.getMessage());
		}
		c.desconecta();
	}
	public void atualizarSemFoto(Professor p, String nome) {
		Conexao c = new Conexao();
		Usuario usua = udao.getUsuarios(nome);
		try {
			c.conectar();
			PreparedStatement ps;
			ps = c.getConnection().prepareStatement("update professor set prof_nome=?, prof_email=?, prof_telresidencial=?, prof_telcel1=?, prof_telcel2=?, prof_formaprinc=?, prof_atuaprinc=?, prof_formasec=?, prof_atuasec=?, prof_curriculo=?, prof_nome_arquivo=?, prof_usua_codigo=? where prof_codigo=?");
			ps.setString(1, p.getNome());
			ps.setString(2, p.getEmail());
			ps.setString(3, p.getTelResidencial());
			ps.setString(4, p.getTelCel1());
			ps.setString(5, p.getTelCel2());
			ps.setString(6, p.getFormaPrinc());
			ps.setString(7, p.getAtuaPrinc());
			ps.setString(8, p.getFormaSec());
			ps.setString(9, p.getAtuaSec());
			ps.setBytes(10, p.getCurriculo());
			ps.setString(11, p.getNomeArquivo());
			ps.setInt(12, usua.getCodigo());
			ps.setInt(13, p.getCodigo());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar dados do paciente\n"+sqle.getMessage());
		}
		c.desconecta();
	}
	
	public void atualizarSemFotoSemArquivo(Professor p, String nome) {
		Conexao c = new Conexao();
		Usuario usua = udao.getUsuarios(nome);
		try {
			c.conectar();
			PreparedStatement ps;
			ps = c.getConnection().prepareStatement("update professor set prof_nome=?, prof_email=?, prof_telresidencial=?, prof_telcel1=?, prof_telcel2=?, prof_formaprinc=?, prof_atuaprinc=?, prof_formasec=?, prof_atuasec=?, prof_usua_codigo=? where prof_codigo=?");
			ps.setString(1, p.getNome());
			ps.setString(2, p.getEmail());
			ps.setString(3, p.getTelResidencial());
			ps.setString(4, p.getTelCel1());
			ps.setString(5, p.getTelCel2());
			ps.setString(6, p.getFormaPrinc());
			ps.setString(7, p.getAtuaPrinc());
			ps.setString(8, p.getFormaSec());
			ps.setString(9, p.getAtuaSec());
			ps.setInt(10, usua.getCodigo());
			ps.setInt(11, p.getCodigo());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar dados do paciente\n"+sqle.getMessage());
		}
		c.desconecta();
	}
	
	public List<Professor> listar() {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Professor> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery(listSQL);
			while(rs.next()) {
				UsuarioDAO udao = new UsuarioDAO();
				Professor p = new Professor();
				p.setCodigo(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setEmail((rs.getString(3)));
				p.setTelResidencial(rs.getString(4));
				p.setTelCel1(rs.getString(5));
				p.setTelCel2(rs.getString(6));
				p.setFormaPrinc(rs.getString(7));
				p.setAtuaPrinc(rs.getString(8));
				p.setFormaSec(rs.getString(9));
				p.setAtuaSec(rs.getString(10));
				p.setImagem(rs.getBytes(11));
				p.setCurriculo(rs.getBytes(12));
				p.setNomeArquivo(rs.getString(13));
				p.setUsuario(udao.getUsuarios(rs.getString(14)));
				list.add(p);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Professor");
		}
		return list;
	}
	public List<Professor> listarNome(String nome) {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Professor> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from professor where prof_nome like '"+nome+"%'");
			while(rs.next()) {
				Professor p = new Professor();
				p.setCodigo(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setEmail((rs.getString(3)));
				p.setTelResidencial(rs.getString(4));
				p.setTelCel1(rs.getString(5));
				p.setTelCel2(rs.getString(6));
				p.setFormaPrinc(rs.getString(7));
				p.setAtuaPrinc(rs.getString(8));
				p.setFormaSec(rs.getString(9));
				p.setAtuaSec(rs.getString(10));
				p.setImagem(rs.getBytes(11));
				p.setCurriculo(rs.getBytes(12));
				p.setNomeArquivo(rs.getString(13));
				list.add(p);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Professor");
		}
		return list;
	}
	public List<Professor> listarFormaPrinc(String nome) {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Professor> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from professor where prof_formaprinc like '"+nome+"%'");
			while(rs.next()) {
				Professor p = new Professor();
				p.setCodigo(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setEmail((rs.getString(3)));
				p.setTelResidencial(rs.getString(4));
				p.setTelCel1(rs.getString(5));
				p.setTelCel2(rs.getString(6));
				p.setFormaPrinc(rs.getString(7));
				p.setAtuaPrinc(rs.getString(8));
				p.setFormaSec(rs.getString(9));
				p.setAtuaSec(rs.getString(10));
				p.setImagem(rs.getBytes(11));
				p.setCurriculo(rs.getBytes(12));
				p.setNomeArquivo(rs.getString(13));
				list.add(p);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Professor");
		}
		return list;
	}
	
	public List<Professor> listarFormaSec(String nome) {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Professor> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from professor where prof_formasec like '"+nome+"%'");
			while(rs.next()) {
				Professor p = new Professor();
				p.setCodigo(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setEmail((rs.getString(3)));
				p.setTelResidencial(rs.getString(4));
				p.setTelCel1(rs.getString(5));
				p.setTelCel2(rs.getString(6));
				p.setFormaPrinc(rs.getString(7));
				p.setAtuaPrinc(rs.getString(8));
				p.setFormaSec(rs.getString(9));
				p.setAtuaSec(rs.getString(10));
				p.setImagem(rs.getBytes(11));
				p.setCurriculo(rs.getBytes(12));
				p.setNomeArquivo(rs.getString(13));
				list.add(p);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Professor");
		}
		return list;
	}
	
	public List<Professor> listarAtuaPrinc(String nome) {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Professor> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from professor where prof_atuaprinc like '"+nome+"%'");
			while(rs.next()) {
				Professor p = new Professor();
				p.setCodigo(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setEmail((rs.getString(3)));
				p.setTelResidencial(rs.getString(4));
				p.setTelCel1(rs.getString(5));
				p.setTelCel2(rs.getString(6));
				p.setFormaPrinc(rs.getString(7));
				p.setAtuaPrinc(rs.getString(8));
				p.setFormaSec(rs.getString(9));
				p.setAtuaSec(rs.getString(10));
				p.setImagem(rs.getBytes(11));
				p.setCurriculo(rs.getBytes(12));
				p.setNomeArquivo(rs.getString(13));
				list.add(p);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Professor");
		}
		return list;
	}
	
	public List<Professor> listarAtuaSec(String nome) {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Professor> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from professor where prof_atuasec like '"+nome+"%'");
			while(rs.next()) {
				Professor p = new Professor();
				p.setCodigo(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setEmail((rs.getString(3)));
				p.setTelResidencial(rs.getString(4));
				p.setTelCel1(rs.getString(5));
				p.setTelCel2(rs.getString(6));
				p.setFormaPrinc(rs.getString(7));
				p.setAtuaPrinc(rs.getString(8));
				p.setFormaSec(rs.getString(9));
				p.setAtuaSec(rs.getString(10));
				p.setImagem(rs.getBytes(11));
				p.setCurriculo(rs.getBytes(12));
				p.setNomeArquivo(rs.getString(13));
				list.add(p);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Professor");
		}
		return list;
	}
	
	public void incluir(Professor p, String filename) {
		Conexao c = new Conexao();
		c.conectar();
		PreparedStatement ps;
		
		try {
			int len;
			ps = c.getConnection().prepareStatement(insertSQL);
			File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            len = (int)file.length();
			ps.setString(1, p.getNome());
			ps.setString(2, p.getEmail());
			ps.setString(3, p.getTelResidencial());
			ps.setString(4, p.getTelCel1());
			ps.setObject(5, p.getTelCel2());
			ps.setString(6, p.getFormaPrinc());
			ps.setString(7, p.getAtuaPrinc());
			ps.setString(8, p.getFormaSec());
			ps.setString(9, p.getAtuaSec());
			ps.setBytes(10, p.getImagem());
			ps.setBinaryStream(11, fis, len);
			ps.setString(12, p.getNomeArquivo());
			ps.setInt(13, p.getUsuario().getCodigo());
			ps.execute();
			ps.close();
			JOptionPane.showMessageDialog(null, "Novo professor cadastrado.");
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro na inclusão do novo professor\n"+sqle.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.desconecta();
	}

	public Professor BuscaProfessor(String nome) {
		Conexao c = new Conexao();
		Professor p = new Professor();
		c.conectar();
		Statement st;
		ResultSet rs = null;
		String sql = ("select * from Professor where prof_nome like '"+nome+"%'");
		try {
			st = c.getConnection().createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				p.setNome(rs.getString("prof_nome"));
				p.setCodigo(rs.getInt("prof_codigo"));
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Não existe Professor cadastrado com esse nome.\n"+sqle.getMessage());
		}
		c.desconecta();
		return p;
    }
	
	public void getPDFData(int codigo) {
		Conexao con = new Conexao();
        byte[] fileBytes;
        String query;
        try {
            query = 
              "select prof_curriculo from professor where prof_nome_arquivo like '%.pdf%'";
            PreparedStatement state = con.getConnection().prepareStatement(query);
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                fileBytes = rs.getBytes(1);
                OutputStream targetFile=  new FileOutputStream("C:\\Users\\thyag\\Documents\\Allan\\UFAC\\6ºP\\novo.pdf");
                targetFile.write(fileBytes);
                targetFile.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
