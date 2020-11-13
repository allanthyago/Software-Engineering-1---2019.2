package gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ObservacaoDAO;
import dao.ProfessorDAO;
import dao.UsuarioDAO;
import entity.Professor;
import entity.Usuario;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class TabelaProfessores extends JDialog {
	Usuario u = new Usuario();
	UsuarioDAO udao = new UsuarioDAO();

	private DefaultTableModel modelTD = new DefaultTableModel();
	private JPanel contentPane;
	private JTable table;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JTextField tfBusca;
	private JButton btnObservaes;

	public static void carregaDados(DefaultTableModel model) {
		model.setNumRows(0);
		ProfessorDAO pdao = new ProfessorDAO();
		List<Professor> list = pdao.listar();
		for (Professor p : list) {
			model.addRow(new Object[] { p.getCodigo(), p.getNome(), p.getEmail(), p.getTelResidencial(), p.getTelCel1(),
					p.getTelCel2(), p.getFormaPrinc(), p.getAtuaPrinc(), p.getFormaSec(), p.getAtuaSec(), });
		}
	}

	public static void carregaDadosNome(DefaultTableModel model, String nome) {
		model.setNumRows(0);
		ProfessorDAO pdao = new ProfessorDAO();
		List<Professor> list = pdao.listarNome(nome);
		for (Professor p : list) {
			model.addRow(new Object[] { p.getCodigo(), p.getNome(), p.getEmail(), p.getTelResidencial(), p.getTelCel1(),
					p.getTelCel2(), p.getFormaPrinc(), p.getAtuaPrinc(), p.getFormaSec(), p.getAtuaSec() });
		}
	}

	/**
	 * Create the frame.
	 */
	public TabelaProfessores(String codigo) {
		u = udao.getUsuarios(codigo);
		String nome = u.getNome();
		setTitle("SENAI Teachers   |    Professores");
		String user = System.getProperty("user.name");
		getContentPane().add(new JLabel(
				new ImageIcon("C:\\Users\\" + user + "\\Documents\\SENAI Teachers\\src\\Imagem\\Fundo de tela-SENAI.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage(TabelaProfessores.class.getResource("/Imagem/Senai.png")));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar?", "Confirma Exit",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					System.out.println("Confirmou");
					dispose();
				} else {
					System.out.println("Não confirmou");
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setBounds(100, 100, 1100, 613);
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

		carregaDados(modelTD);
		scrollPane.setViewportView(table);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNovo) {
					setVisible(false);
					CadastroProfessores cp = new CadastroProfessores(modelTD, u.getNome());
					cp.setVisible(true);
				}
			}
		});

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				int codigo = (int) table.getValueAt(linha, 0);
				AtualizaProfessores atuaProf = new AtualizaProfessores(modelTD, codigo, u.getNome());
				setVisible(false);
				atuaProf.setVisible(true);
			}
		});

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			ObservacaoDAO odao = new ObservacaoDAO();

			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				int codigo = (int) table.getValueAt(linha, 0);
				boolean teste = odao.verificaObsProf(linha);
				int reply = JOptionPane.showConfirmDialog(null, "Deseja excluir cadastro?", "Cadastro de Professor",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					if (teste == true) {
						ProfessorDAO pdao = new ProfessorDAO();
						pdao.excluir(codigo);
						TabelaProfessores.carregaDados(modelTD);
					}else {
						JOptionPane.showMessageDialog(null, "Exclua todas as Observações!");
					}
				}
			}
		});

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				TabelaProfessores.carregaDados(modelTD);
			}
		});

		tfBusca = new JTextField();
		tfBusca.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Professor p = new Professor();
				int prof;
				ProfessorDAO pdao = new ProfessorDAO();
				p = pdao.BuscaProfessor(tfBusca.getText());
				prof = p.getCodigo();
				System.out.println(p);
				System.out.println(prof);
				if (prof == 0) {
					JOptionPane.showMessageDialog(null, "Não existe professor cadastrado com este nome");
					tfBusca.setText(" ");
				} else {
					carregaDadosNome(modelTD, tfBusca.getText());
					tfBusca.setText("");
				}
			}
		});

		btnObservaes = new JButton("Observa\u00E7\u00F5es");
		btnObservaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Professor p = new Professor();
				ProfessorDAO pdao = new ProfessorDAO();
				int linha = table.getSelectedRow();
				if (linha == -1) {
					JOptionPane.showMessageDialog(null, "Selecione professor");
				} else {
					table.removeAll();
					int codigo = (int) table.getValueAt(linha, 0);
					p = pdao.getProfessor(codigo);
					TabelaObservacao jo = new TabelaObservacao(p.getCodigo(), u.getNome());
					setVisible(false);
					jo.setVisible(true);
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(scrollPane,
								GroupLayout.PREFERRED_SIZE, 1044, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(27).addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING, false).addComponent(tfBusca)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(29).addComponent(btnNovo)
										.addGap(18).addComponent(btnEditar).addGap(18).addComponent(btnExcluir)))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPesquisar))
										.addGroup(gl_contentPane.createSequentialGroup().addGap(18)
												.addComponent(btnObservaes).addGap(18).addComponent(btnSair)))))
				.addContainerGap(20, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
						.addGap(26)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfBusca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPesquisar))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNovo)
								.addComponent(btnEditar).addComponent(btnExcluir).addComponent(btnObservaes)
								.addComponent(btnSair))
						.addGap(60)));

		contentPane.setLayout(gl_contentPane);
	}
}
