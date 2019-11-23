package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.UsuarioDAO;
import entity.Usuario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class TabelaUsuarios extends JDialog {

	private DefaultTableModel modelTD = new DefaultTableModel();
	private JPanel contentPane;
	private JTable table;
	private JTextField tfBusca;

	public static void carregaDados(DefaultTableModel model) {
		model.setNumRows(0);
		UsuarioDAO pdao = new UsuarioDAO();
		List<Usuario> list = pdao.listar();
		for (Usuario p : list) {
			model.addRow(new Object[] {p.getCodigo(),
									p.getNome(),
									p.getLogin(),
									p.getPerfil()
			});
		}
	}
	
	public static void carregaDadosNome(DefaultTableModel model, String nome) {
		model.setNumRows(0);
		UsuarioDAO udao = new UsuarioDAO();
		List<Usuario> list = udao.listarNome(nome);
		for (Usuario p : list) {
			model.addRow(new Object[] {p.getCodigo(),
									p.getNome(),
									p.getLogin(),
									p.getPerfil()
			});
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaUsuarios frame = new TabelaUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TabelaUsuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TabelaUsuarios.class.getResource("/Imagem/Ass Digital SENAI.png")));
		setTitle("TSENAI   |    Usuários");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable(modelTD);
		modelTD.addColumn("Código");
		modelTD.addColumn("Nome");
		modelTD.addColumn("Login");
		modelTD.addColumn("Perfil");
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        carregaDados(modelTD);
		scrollPane.setViewportView(table);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNovo) {
					setVisible(false);
					CadastroUsuario cp = new CadastroUsuario(modelTD);
					cp.setVisible(true);
				}
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				int codigo = (int)table.getValueAt(linha, 0);
				AtualizaUsuario atuaProf = new AtualizaUsuario(modelTD, codigo);
				setVisible(false);
				atuaProf.setVisible(true);
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				int codigo = (int) table.getValueAt(linha, 0);
				int reply = JOptionPane.showConfirmDialog(null, "Deseja excluir cadastro?", "Cadastro de Professor", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION) {
						UsuarioDAO pdao = new UsuarioDAO();
						pdao.excluir(codigo);
						TabelaUsuarios.carregaDados(modelTD);  
				  }
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		tfBusca = new JTextField();
		tfBusca.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario p = new Usuario();
				int prof;
				UsuarioDAO pdao = new UsuarioDAO();
				p = pdao.BuscaUsuario(tfBusca.getText());
				prof = p.getCodigo();
				System.out.println(p);
				System.out.println(prof);
				if(prof==0) {
					JOptionPane.showMessageDialog(null, "Não existe usuario cadastrado com este nome");
					tfBusca.setText(" ");
				}
				else {
					carregaDadosNome(modelTD, tfBusca.getText());
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(tfBusca, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnPesquisar))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNovo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnEditar)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnExcluir)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnSair)))))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfBusca, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
