package dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;


import Conexao.Conexao;
import entity.Professor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioDAO {

	private static String user = System.getProperty("user.name");
	private static final String FOLDER_RELATORIOS="C:\\Users\\"+user+"\\Documents\\SENAI Teachers\\MyReports\\relatorios";
	private String getSQL = "select * from professor order by prof_nome";
	private String formacao = "select * from professor where prof_formaprinc = ? order by prof_nome";
	
	public Professor getForm(String nome) {
		Conexao c = new Conexao();
		Professor p = new Professor();
		try {
			c.conectar();
			PreparedStatement stm;
			stm = c.getConnection().prepareStatement(formacao);
			stm.setString(1, nome);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
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
			}
		}catch(SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro na consulta Professor"+sqle.getMessage());
		}
		c.desconecta();
		return p;
	}
	
	public List<Professor> listFormacao(String nome) {
		Conexao c = new Conexao();
		c.conectar();
		Statement st;
		String lista = "select * from professor where prof_formaprinc like '"+nome+"%' order by prof_nome";
		List<Professor> listaUS = new ArrayList<>();
		try {
			st = c.getConnection().createStatement();
			ResultSet rs = st.executeQuery(lista);
			while(rs.next()) {
				Professor p = new Professor();
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
				listaUS.add(p);
				System.out.println(p);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUS;
	}
	
	public void geraPdf(String nome) throws FileNotFoundException, JRException {
		Connection conexao = Conexao.conector();
		String jrxml = FOLDER_RELATORIOS+"\\Relatorio.jrxml";
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("formacao", nome);
		Connection c = Conexao.conector();
		java.io.OutputStream saida = new FileOutputStream("C:\\Users\\"+user+"\\Desktop\\Relatorio.pdf");

		// compila jrxml em um arquivo .jasper
		String jasper = JasperCompileManager.compileReportToFile(jrxml);

		// preenche relatorio
		JasperPrint print = JasperFillManager.fillReport(jasper, parametros, conexao);

		// exporta para pdf
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
		exporter.exportReport();
		JasperViewer.viewReport(print,false);
	}
}
