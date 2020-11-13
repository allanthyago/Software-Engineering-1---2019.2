package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ObservacaoDAO;
import dao.ProfessorDAO;
import dao.UsuarioDAO;
import entity.Observacao;
import entity.Professor;
import entity.Usuario;

public class TabelaObsEdirProf extends JDialog {

	private JPanel contentPane;
	private JTable tableObs;
	private JLabel label;
	Professor p = new Professor();
	ProfessorDAO pdao = new ProfessorDAO();
	Usuario u=new Usuario();
	UsuarioDAO udao = new UsuarioDAO();

	/**
	 * Launch the application.
	 */
	public static void carregaDadosObs(DefaultTableModel model, int codigo) {
		model.setNumRows(0);
		ObservacaoDAO obsdao = new ObservacaoDAO();
		List<Observacao> list = obsdao.listarProf(codigo);
		for (Observacao obs : list) {
			model.addRow(new Object[] {obs.getCodigo(),
									   obs.getTipo(),
									   obs.getTexto()
			});
		}
	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	
	public TabelaObsEdirProf(int codigo1, String nome) {
		p=pdao.getProfessor(codigo1);
		u=udao.getUsuarios(nome);
		System.out.println(u.getNome());
		System.out.println(p.getNome());
		DefaultTableModel modelTD = new DefaultTableModel();
		
		setTitle("SENAI Teachers   |    Observa\u00E7\u00F5es");
		String user = System.getProperty("user.name");
		getContentPane().add(new JLabel(new ImageIcon("C:\\Users\\"+user+"\\Documents\\SENAI Teachers\\src\\Imagem\\Fundo de tela-SENAI.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage(TabelaObsEdirProf.class.getResource("/Imagem/Senai.png")));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar?", "Confirma Exit", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
				    AtualizaProfessores tp = new AtualizaProfessores(modelTD, p.getCodigo(), u.getLogin());
					dispose();
					tp.setVisible(true);
				  }
				  else {
					  System.out.println("Não confirmou");
					  setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				  }
			}
		});
		setBounds(100, 100, 889, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		tableObs = new JTable(modelTD);
		modelTD.addColumn("Código");
		modelTD.addColumn("Tipo");
		modelTD.addColumn("Observação");
		tableObs.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableObs.getColumnModel().getColumn(0).setResizable(false);
        tableObs.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableObs.getColumnModel().getColumn(1).setResizable(false);
        tableObs.getColumnModel().getColumn(2).setPreferredWidth(650);
        tableObs.getColumnModel().getColumn(2).setResizable(false);
        
        tableObs.getTableHeader().setReorderingAllowed(false);
        tableObs.setAutoResizeMode(tableObs.AUTO_RESIZE_OFF);
        tableObs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		carregaDadosObs(modelTD, p.getCodigo());
		scrollPane.setViewportView(tableObs);
		
		JLabel lblProfessor = new JLabel("Professor:");
		
		label = new JLabel();
		label.setText(p.getNome());
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNovo) {
					setVisible(false);
					CadastroObservaçõesEdProf cp = new CadastroObservaçõesEdProf( p.getCodigo(), u.getNome(), u.getCodigo());
					cp.setVisible(true);
					System.out.println(u.getNome());
				}
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tableObs.getSelectedRow();
				int codigo = (int)tableObs.getValueAt(linha, 0);
				setVisible(false);
				AtualizarObservacaoEdProf ao = new AtualizarObservacaoEdProf( codigo, p.getCodigo(), u.getNome(), u.getCodigo());
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
					//TabelaProfessores tp = new TabelaProfessores(u.getNome());
					//tp.setVisible(true);
					AtualizaProfessores ap = new AtualizaProfessores(modelTD, p.getCodigo(), u.getLogin());
					ap.setVisible(true);
				  }
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = tableObs.getSelectedRow();
				int codigo = (int) tableObs.getValueAt(linha, 0);
				int reply = JOptionPane.showConfirmDialog(null, "Deseja excluir cadastro?", "Cadastro de Observação", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION) {
						ObservacaoDAO pdao = new ObservacaoDAO();
						pdao.excluir(codigo);
						TabelaObservacao.carregaDadosObs(modelTD, p.getCodigo());  
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
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
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
						.addComponent(btnCancelar)
						.addComponent(btnExcluir))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
