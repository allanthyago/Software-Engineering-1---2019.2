package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ObservacaoDAO;
import dao.ProfessorDAO;
import dao.UsuarioDAO;
import entity.Observacao;
import entity.Professor;
import entity.Usuario;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class JanelaObservacao extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private JLabel label;
	Professor p = new Professor();
	ProfessorDAO pdao = new ProfessorDAO();
	Usuario u=new Usuario();
	UsuarioDAO udao = new UsuarioDAO();
	/**
	 * Create the frame.
	 * @param string 
	 * @param codigo 
	 * @param modelTD 
	 */
	
	public static void carregaDados(DefaultTableModel model) {
		model.setNumRows(0);
		ObservacaoDAO obsdao = new ObservacaoDAO();
		List<Observacao> list = obsdao.listar();
		for (Observacao obs : list) {
			model.addRow(new Object[] {obs.getCodigo(),
									   obs.getTexto()
			});
		}
	}
	public JanelaObservacao(DefaultTableModel modelTD, int codigo, String nome) {
		p=pdao.getProfessor(codigo);
		u=udao.getUsuarios(nome);
		System.out.println(u.getNome());
		System.out.println(p.getNome());
		
		setTitle("TSENAI   |    Observa\u00E7\u00F5es");
		getContentPane().add(new JLabel(new ImageIcon("C:\\Users\\thyag\\Pictures\\Fundo de tela-SENAI.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\thyag\\Pictures\\Senai.png"));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 889, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		table = new JTable(modelTD);
		modelTD.addColumn("Código");
		modelTD.addColumn("Obsrvação");
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setResizable(false);
        
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		carregaDados(modelTD);
		scrollPane.setViewportView(table);
		
		JLabel lblProfessor = new JLabel("Professor:");
		
		label = new JLabel();
		label.setText(p.getNome());
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNovo) {
					setVisible(false);
					CadastroObservações cp = new CadastroObservações(modelTD, p.getCodigo(), u.getNome(), u.getCodigo());
					cp.setVisible(true);
					System.out.println(u.getNome());
				}
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				int codigo = (int)table.getValueAt(linha, 0);
				setVisible(false);
				AtualizarObservacao ao = new AtualizarObservacao(modelTD, codigo, u.getNome(), u.getCodigo());
				ao.setVisible(true);
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar?", "Observações do professor ", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
					dispose();
					TabelaProfessores tp = new TabelaProfessores(u.getNome());
					tp.setVisible(true);
				  }
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(btnNovo)
							.addGap(18)
							.addComponent(btnEditar)
							.addGap(18)
							.addComponent(btnCancelar))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblProfessor)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProfessor)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(btnCancelar))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
