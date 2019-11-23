package gui;

import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.UsuarioDAO;
import entity.Usuario;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AtualizaUsuario extends JDialog {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfLogin;
	private JPasswordField pfSenha;

	/**
	 * Create the frame.
	 */
	public AtualizaUsuario(DefaultTableModel model, int codigo) {
		setTitle("TSENAI   |    Atualizar Usu\u00E1rio");
		getContentPane().add(new JLabel(new ImageIcon("C:\\Users\\thyag\\Pictures\\Fundo de tela-SENAI.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\thyag\\Pictures\\Senai.png"));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.getUsuario(codigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setColumns(10);
		tfCodigo.setText(String.valueOf(u.getCodigo()));
		
		JLabel lblNome = new JLabel("Nome:");
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setText(u.getNome());
		
		JLabel lblLogin = new JLabel("Login:");
		
		tfLogin = new JTextField();
		tfLogin.setColumns(10);
		tfLogin.setText(u.getLogin());
		
		JLabel lblSenha = new JLabel("Senha:");
		
		pfSenha = new JPasswordField();
		pfSenha.setText(u.getSenha());
		
		JLabel lblPerfil = new JLabel("Perfil:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Coordenador"}));
		comboBox.setSelectedItem(u.getPerfil());
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnSalvar) {
					UsuarioDAO udao = new UsuarioDAO();
					Usuario u = new Usuario();
					u.setCodigo(Integer.parseInt(tfCodigo.getText()));
					u.setNome(tfNome.getText());
					u.setLogin(tfLogin.getText());
					u.setSenha(new String(pfSenha.getPassword()));
					u.setPerfil(comboBox.getSelectedItem().toString());
					udao.alterar(u);
					TabelaUsuarios.carregaDados(model);
					setVisible(false);
					TabelaUsuarios td = new TabelaUsuarios();
					td.setVisible(true);
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar cadastro?", "Cadastro de Professor", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
				    TabelaUsuarios.carregaDados(model);
					setVisible(false);
					TabelaUsuarios tp = new TabelaUsuarios();
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCdigo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(tfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNome)
									.addGap(18)
									.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLogin)
										.addComponent(lblSenha))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(pfSenha, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
										.addComponent(tfLogin, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))))
							.addGap(86))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPerfil)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(340, Short.MAX_VALUE))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(100)
					.addComponent(btnSalvar)
					.addGap(31)
					.addComponent(btnCancelar)
					.addContainerGap(141, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCdigo)
						.addComponent(tfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogin)
						.addComponent(tfLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(pfSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPerfil)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addGap(23))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
