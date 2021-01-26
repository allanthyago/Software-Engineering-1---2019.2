package gui;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.UsuarioDAO;
import entity.Usuario;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class CadastroUsuario extends JDialog {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfLogin;
	private JPasswordField pfSenha;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CadastroUsuario(DefaultTableModel model) {
		setTitle("SENAI Teachers   |    Cadastro de Usuários");
		String user = System.getProperty("user.name");
		getContentPane().add(new JLabel(new ImageIcon("C:\\Users\\"+user+"\\Documents\\SENAI Teachers\\src\\Imagem\\Fundo de tela-SENAI.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroUsuario.class.getResource("/Imagem/Senai.png")));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar?", "Confirma Exit", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
					TabelaUsuarios tp = new TabelaUsuarios();
					dispose();
					tp.setVisible(true);
				  }
				  else {
					  System.out.println("Não confirmou");
					  setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				  }
			}
		});
		
		setBounds(100, 100, 453, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNome = new JLabel("* Nome:");
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		
		JLabel lblLogin = new JLabel("* Login");
		
		tfLogin = new JTextField();
		tfLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("* Senha:");
		
		pfSenha = new JPasswordField();
		
		JLabel lblPerfil = new JLabel("* Perfil:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Coordenador\t"}));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnSalvar) {
					UsuarioDAO pdao = new UsuarioDAO();
					Usuario p = new Usuario();
					if(tfNome.getText().trim().length()==0 || tfLogin.getText().trim().length()==0 || pfSenha.getPassword().length==0) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
					}else {
						p.setNome(tfNome.getText());
						p.setLogin(tfLogin.getText());
						p.setSenha(new String(pfSenha.getPassword()));
						p.setPerfil(comboBox.getSelectedItem().toString());
						pdao.adicionar(p);
						
						TabelaUsuarios.carregaDados(model);
						setVisible(false);
						TabelaUsuarios td = new TabelaUsuarios();
						td.setVisible(true);
					}
				}
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja limpar janela?", "Cadastro de Professor", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION)
				{
					System.out.println("Confirmou");
					CadastroUsuario novaJanela = new CadastroUsuario(model);
					dispose();
					novaJanela.setVisible(true);
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
		
		JLabel lblCamposObrigatrios = new JLabel("* Campos obrigat\u00F3rios");
		lblCamposObrigatrios.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addComponent(lblLogin)
								.addComponent(lblSenha))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tfNome)
								.addComponent(tfLogin)
								.addComponent(pfSenha, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPerfil)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(98, Short.MAX_VALUE)
					.addComponent(btnSalvar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLimpar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancelar)
					.addGap(21))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCamposObrigatrios)
					.addContainerGap(284, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogin)
						.addComponent(tfLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSenha)
						.addComponent(pfSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPerfil)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCamposObrigatrios)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnLimpar)
						.addComponent(btnCancelar)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
