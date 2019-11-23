package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ProfessorDAO;
import entity.Professor;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RelatorioAtuacaoSec extends JFrame {

	private DefaultTableModel modelTD = new DefaultTableModel();
	private JPanel contentPane;
	private JTable table;
	JanelaAtuaçãoSec jfp = new JanelaAtuaçãoSec();	
	
	public static void carregaDados(DefaultTableModel model, String nome) {
		model.setNumRows(0);
		ProfessorDAO pdao = new ProfessorDAO();
		List<Professor> list = pdao.listarAtuaSec(nome);
		for (Professor p : list) {
			model.addRow(new Object[] {p.getCodigo(),
									p.getNome(),
									p.getEmail(),
									p.getTelResidencial(),
									p.getTelCel1(),
									p.getTelCel2(),
									p.getFormaPrinc(),
									p.getAtuaPrinc(),
									p.getFormaSec(),
									p.getAtuaSec()
			});
		}
	}
	/**
	 * Create the frame.
	 * @param palavra 
	 */
	public RelatorioAtuacaoSec(String palavra) {
		setTitle("TSENAI");
		getContentPane().add(new JLabel(new ImageIcon("C:\\Users\\thyag\\Pictures\\Fundo de tela-SENAI.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\thyag\\Pictures\\Senai.png"));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable(modelTD);
		modelTD.addColumn("Código");
		modelTD.addColumn("Nome");
		modelTD.addColumn("Email");
		modelTD.addColumn("Tel. Residencial");
		modelTD.addColumn("Tel. Celular");
		modelTD.addColumn("Tel. Celular");
		modelTD.addColumn("Formação Principal");
		modelTD.addColumn("Atuação Principal");
		modelTD.addColumn("Formação Secundária");
		modelTD.addColumn("Atuação Secundária");
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setResizable(false);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setResizable(false);
        table.getColumnModel().getColumn(8).setPreferredWidth(150);
        table.getColumnModel().getColumn(8).setResizable(false);
        table.getColumnModel().getColumn(9).setPreferredWidth(150);
        table.getColumnModel().getColumn(9).setResizable(false);
        
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
		scrollPane.setViewportView(table);
        carregaDados(modelTD, palavra);
		
		JButton btnNovaConsulta = new JButton("Nova Consulta");
		btnNovaConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNovaConsulta) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								dispose();
								jfp.setVisible(true);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(143)
							.addComponent(btnNovaConsulta, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
							.addGap(154)
							.addComponent(btnSair, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addGap(266)))
					.addGap(12))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovaConsulta)
						.addComponent(btnSair))
					.addGap(17))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
