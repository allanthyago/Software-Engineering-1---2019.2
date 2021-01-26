package gui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UsuarioDAO;
import entity.Usuario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class AlterarSenha extends JDialog {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;


	/**
	 * Create the frame.
	 */
	public AlterarSenha(int codigo) {
		
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.getUsuario(codigo);
		
		setTitle("SENAI Teachers   |    Alterar Senha de "+u.getNome());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar?", "Confirma Exit", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
					dispose();
				  }
				  else {
					  System.out.println("Não confirmou");
					  setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				  }
			}
		});
		String user = System.getProperty("user.name");
		getContentPane().add(new JLabel(new ImageIcon("C:\\Users\\"+user+"\\Documents\\SENAI Teachers\\src\\Imagem\\Fundo de tela-SENAI.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlterarSenha.class.getResource("/Imagem/Senai.png")));
		
		setBounds(100, 100, 337, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		
		JLabel lblSenhaAtual = new JLabel("Senha Atual:");
		
		JLabel lblNovaSenha = new JLabel("Nova Senha:");
		
		JLabel lblConfirmeSenha = new JLabel("Confirme senha:");
		
		passwordField = new JPasswordField();
		
		passwordField_1 = new JPasswordField();
		
		passwordField_2 = new JPasswordField();
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnConfirmar) {
					String novaSenha;
					udao.verificaSenha(u.getLogin() ,new String(passwordField.getPassword()));
					novaSenha=u.getSenha();
					System.out.println(novaSenha);
//					byte[] byteAr = u.getSenha().getBytes();
//					decod = new String(Arrays.toString(byteAr));
//					System.out.println(decod+"\n\n");					
					System.out.println("Senha de "+u.getNome());
					if(!(new String(passwordField.getPassword())).equals(novaSenha)) {
						JOptionPane.showMessageDialog(null, "Senha incorreta");
						passwordField.setText("");
						passwordField_1.setText("");
						passwordField_2.setText("");
					}else {
						if(new String(passwordField_1.getPassword()).equals(new String(passwordField_2.getPassword()))&& !(new String(passwordField_1.getPassword()).equals(new String(passwordField.getPassword()))) ) {
							if(!(new String(passwordField.getPassword())==(u.getSenha()))) {
								u.setSenha(new String(passwordField_2.getPassword()));
								JOptionPane.showMessageDialog(null, "Senha alterada com sucesso");
								udao.alterarSenha(u);
								setVisible(false);
							}
						}else {
							if(!(new String(passwordField_1.getPassword()).equals(new String(passwordField_2.getPassword())))) {
								JOptionPane.showMessageDialog(null, "Campos de nova senha são diferentes");
								passwordField.setText("");
								passwordField_1.setText("");
								passwordField_2.setText("");
							}else {
								if(new String(passwordField_1.getPassword()).equals(new String(passwordField.getPassword()))){
									JOptionPane.showMessageDialog(null, "Nova senha não pode ser igual a antiga");
									passwordField.setText("");
									passwordField_1.setText("");
									passwordField_2.setText("");
								}else {
									if(new String(passwordField.getPassword()).trim().length()==0 || new String(passwordField_1.getPassword()).trim().length()==0 || new String(passwordField_2.getPassword()).trim().length()==0) {
										JOptionPane.showMessageDialog(null, "Campos em branco");
										passwordField.setText("");
										passwordField_1.setText("");
										passwordField_2.setText("");
									}
								}
							}
						}
					}
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar cadastro?", "Cadastro de Professor", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
					setVisible(false);
				  }
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSenhaAtual)
						.addComponent(lblNovaSenha)
						.addComponent(lblConfirmeSenha))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(passwordField_2)
						.addComponent(passwordField_1)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
					.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(btnConfirmar)
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addComponent(btnCancelar)
					.addGap(38))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenhaAtual)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNovaSenha)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmeSenha)
						.addComponent(passwordField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConfirmar)
						.addComponent(btnCancelar))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}