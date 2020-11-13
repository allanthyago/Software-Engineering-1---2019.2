package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Conexao.Conexao;
import entity.Observacao;

public class ObservacaoDAO {
	private String getSQL = "select * from observacao where obs_codigo=?";
	//private String listSQL = "select * from observacao order by obs_codigo";
	//private String listProfSQL = "SELECT obs_codigo, obs_texto, obs_tipo FROM senai.observacao, senai.professor where obs_cod_prof=? and obs_cod_prof=prof_codigo order by obs_codigo";
	private String insertSQL = "insert into observacao (obs_texto,obs_cod_prof,obs_cod_usua,obs_data,obs_hora,obs_tipo) values (?,?,?,?,?,?)";
	private String updateSQL = "update observacao set obs_texto=?,obs_cod_usua=?,obs_data=?, obs_hora=?, obs_tipo=? where obs_codigo=?";
	//private String lastObs = "select obs_texto from observacao, professor where obs_cod_prof=? and obs_cod_prof=prof_codigo order by obs_codigo desc limit 1";
	
	public Observacao getObservacao(int codigo) {
		Conexao c = new Conexao();
		Observacao obs = new Observacao();
		try {
			c.conectar();
			PreparedStatement stm;
			stm = c.getConnection().prepareStatement(getSQL);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				UsuarioDAO udao = new UsuarioDAO();
				ProfessorDAO pdao = new ProfessorDAO();
				obs.setCodigo(rs.getInt("obs_codigo"));
				obs.setTexto(rs.getString("obs_texto"));
				obs.setProfessor(pdao.getProfessor(rs.getInt("obs_cod_prof")));
				obs.setUsuario(udao.getUsuario(rs.getInt("obs_cod_usua")));
				obs.setData(rs.getString("obs_data"));
				obs.setHora(rs.getString("obs_hora"));
				obs.setTipo(rs.getString("obs_tipo"));
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na consulta de Observações"+sqle.getMessage());
		}
		c.desconecta();
		return obs;
	}
	
	/*public List<Observacao> listar() {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Observacao> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery(listSQL);
			while(rs.next()) {
				UsuarioDAO udao = new UsuarioDAO();
				ProfessorDAO pdao = new ProfessorDAO();
				Observacao obs = new Observacao();
				obs.setCodigo(rs.getInt(1));
				obs.setTexto(rs.getString(2));
				obs.setProfessor(pdao.getProfessores(rs.getString(3)));
				obs.setUsuario(udao.getUsuarios(rs.getString(4)));
				obs.setData(rs.getString(5));
				obs.setHora(rs.getString(6));
				obs.setTipo(rs.getString(7));
				list.add(obs);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Professor");
		}
		return list;
	}*/
	/*public List<Observacao> lastObs(int codigo){
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Observacao> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select obs_texto from observacao, professor where obs_cod_prof="+codigo+" and obs_cod_prof=prof_codigo order by obs_codigo desc limit 1");
			while(rs.next()) {
				Observacao obs = new Observacao();
				obs.setTexto(rs.getString(1));
				list.add(obs);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Observação");
		}
		return list;
	}*/
	
	public Observacao lastObs(int codigo) {
		Conexao c = new Conexao();
		Observacao obs = new Observacao();
		try {
			c.conectar();
			PreparedStatement stm;
			stm = c.getConnection().prepareStatement("select obs_codigo, obs_texto, obs_cod_prof, obs_cod_usua, obs_data, obs_hora, obs_tipo from observacao where obs_data=(select min(obs_data) from observacao, professor where prof_codigo=obs_cod_prof and prof_codigo=?);");
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				UsuarioDAO udao = new UsuarioDAO();
				ProfessorDAO pdao = new ProfessorDAO();
				obs.setCodigo(rs.getInt("obs_codigo"));
				obs.setTexto(rs.getString("obs_texto"));
				obs.setProfessor(pdao.getProfessor(rs.getInt("obs_cod_prof")));
				obs.setUsuario(udao.getUsuario(rs.getInt("obs_cod_usua")));
				obs.setData(rs.getString("obs_data"));
				obs.setHora(rs.getString("obs_hora"));
				obs.setTipo(rs.getString("obs_tipo"));
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na consulta de Observações\n"+sqle.getMessage());
		}
		c.desconecta();
		return obs;
	}
	
	public List<Observacao> listarProf(int codigo) {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Observacao> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT obs_codigo, obs_texto, obs_tipo FROM senai.observacao, senai.professor where obs_cod_prof="+codigo+" and obs_cod_prof=prof_codigo order by obs_codigo");
			while(rs.next()) {
				Observacao obs = new Observacao();
				obs.setCodigo(rs.getInt(1));
				obs.setTexto(rs.getString(2));
				obs.setTipo(rs.getString(3));
				list.add(obs);
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Observações\n"+sqle.getMessage());
		}
		return list;
	}
	
	public boolean verificaObsProf(int codigo) {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		List<Observacao> list = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT obs_codigo, obs_texto, obs_tipo FROM senai.observacao, senai.professor where obs_cod_prof="+codigo+" and obs_cod_prof=prof_codigo order by obs_codigo");
			while(rs.next()) {
				Observacao obs = new Observacao();
				obs.setCodigo(rs.getInt(1));
				obs.setTexto(rs.getString(2));
				obs.setTipo(rs.getString(3));
				list.add(obs);
				return true;
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na listagem dos Observações\n"+sqle.getMessage());
		}
		return false;
	}
	
	public void incluir(Observacao o) {
		Conexao c = new Conexao();
		c.conectar();
		PreparedStatement ps;
		try {
			ps = c.getConnection().prepareStatement(insertSQL);
			ps.setString(1, o.getTexto());
			ps.setInt(2, o.getProfessor().getCodigo());
			ps.setInt(3, o.getUsuario().getCodigo());
			ps.setString(4, o.getData());
			ps.setString(5, o.getHora());
			ps.setObject(6, o.getTipo());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Nova observação cadastrada.");
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro na inclusão de nova observação.\n"+sqle.getMessage());
		}
		
		c.desconecta();
	}
	
	public void atualizar(Observacao p) {
		Conexao c = new Conexao();
		try {
			c.conectar();
			PreparedStatement stm;
			stm = c.getConnection().prepareStatement(updateSQL);
			stm.setString(1, p.getTexto());
			stm.setInt(2, p.getUsuario().getCodigo());
			stm.setString(3, p.getData());
			stm.setString(4, p.getHora());
			stm.setString(5, p.getTipo());
			stm.setInt(6, p.getCodigo());
			stm.execute();
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Não foi possível atualizar observação do professor\n"+sqle.getMessage());
		}
		c.desconecta();
	}
	
	public void excluir(int codigo) {
		Conexao c = new Conexao();
		try {
			c.conectar();
			PreparedStatement stm;
			stm = c.getConnection().prepareStatement("delete from observacao where obs_codigo=?");
			stm.setInt(1, codigo);
			stm.execute();
			JOptionPane.showMessageDialog(null, "Observação excluída");
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Não foi possível excluir O");
		}
		c.desconecta();
	}
}
