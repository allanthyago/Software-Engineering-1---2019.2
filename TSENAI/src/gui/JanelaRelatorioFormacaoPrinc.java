package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.RelatorioDAO;
import entity.Professor;
import net.sf.jasperreports.engine.JRException;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class JanelaRelatorioFormacaoPrinc extends JFrame {
	
	private static final String FOLDER_RELATORIOS="C:\\Users\\thyag\\Documents\\Allan\\UFAC\\6ºP\\TESI 2\\Senai\\MyReports\\relatorios";
	RelatorioDAO rdao = new RelatorioDAO();
	Professor p = new Professor();
	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public JanelaRelatorioFormacaoPrinc() {
		setTitle("Relat\u00F3rio por Forma\u00E7\u00E3o\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaRelatorioFormacaoPrinc.class.getResource("/Imagem/Ass Digital SENAI.png")));
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
				try {
					rdao.geraPdf(textField.getText());
					textField.setText("");
				} catch ( JRException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
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
}
