package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Conexao.Conexao;
import dao.RelatorioDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class JanelaRelatorioFormacaoSec extends JFrame {

	private static final String FOLDER_RELATORIOS="C:\\Users\\thyag\\Documents\\Allan\\UFAC\\6ºP\\TESI 2\\Senai\\MyReports\\relatorios";
	RelatorioDAO rdao = new RelatorioDAO();
	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public JanelaRelatorioFormacaoSec() {
		setTitle("Relat\u00F3rio por Forma\u00E7\u00E3o\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaRelatorioFormacaoSec.class.getResource("/Imagem/Ass Digital SENAI.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 338, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexao = Conexao.conector();
				try {
					rdao.geraPdf(textField.getText());
					JasperPrint print = JasperFillManager.fillReport("C:\\Users\\thyag\\JaspersoftWorkspace\\MyReports\\Relatorio.jasper",null,conexao);
					JasperViewer.viewReport(print,false);
					textField.setText("");
				} catch (FileNotFoundException | JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(14, Short.MAX_VALUE)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(114)
					.addComponent(btnPesquisar)
					.addContainerGap(119, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnPesquisar)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/*public void geraPdf() throws FileNotFoundException, JRException {
		Connection conexao = Conexao.conector();
		String jrxml = "C:\\Users\\thyag\\JaspersoftWorkspace\\MyReports\\Relatorio.jrxml";
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("prof_formaprinc", textField.getText());
		Connection c = Conexao.conector();
		OutputStream saida = new FileOutputStream("C:\\Users\\thyag\\JaspersoftWorkspace\\MyReports\\Relatorio.pdf");

		// compila jrxml em um arquivo .jasper
		String jasper = JasperCompileManager.compileReportToFile(jrxml);

		// preenche relatorio
		JasperPrint print = JasperFillManager.fillReport(jasper, parametros, conexao);

		// exporta para pdf
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
		exporter.exportReport();
		RelatorioDAO ldao = new RelatorioDAO();
		ldao.gerarRealatorio(textField.getText());
	}*/
}
