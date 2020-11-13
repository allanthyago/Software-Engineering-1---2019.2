package gui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import dao.ObservacaoDAO;
import dao.ProfessorDAO;
import dao.UsuarioDAO;
import entity.Observacao;
import entity.Professor;
import entity.Usuario;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class CadastroObservações extends JDialog {
	ProfessorDAO pdao = new ProfessorDAO();
	Professor p = new Professor();
	UsuarioDAO udao = new UsuarioDAO();
	Usuario u = new Usuario();
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 * @param string 
	 * @param modelTD 
	 * @param string 
	 */
	public CadastroObservações(int prof, String string, int codigo) {
		p=pdao.getProfessor(prof);
		u=udao.getUsuario(codigo);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		String user = System.getProperty("user.name");
		setTitle("SENAI Teachers   |    Cadastro de Observa\u00E7\u00F5es");
		getContentPane().add(new JLabel(new ImageIcon("C:\\Users\\"+user+"\\Documents\\SENAI Teachers\\src\\Imagem\\Fundo de tela-SENAI.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroObservações.class.getResource("/Imagem/Senai.png")));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar?", "Confirma Exit", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
				    TabelaObservacao tp = new TabelaObservacao(p.getCodigo(), u.getNome());
					dispose();
					tp.setVisible(true);
				  }
				  else {
					  System.out.println("Não confirmou");
					  setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				  }
			}
		});
		setBounds(100, 100, 607, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JComboBox comboBox = new JComboBox();
		
		JScrollPane scrollPane = new JScrollPane();
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		Date dataAtual = new Date();
		SimpleDateFormat Data = new SimpleDateFormat("dd/MM/yyyy"); 
		String data = Data.format(dataAtual);
		SimpleDateFormat Hora = new SimpleDateFormat("HH:mm"); 
		String hora = Hora.format(dataAtual);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObservacaoDAO odao = new ObservacaoDAO();
				Observacao o = new Observacao();
				o.setTexto(textArea.getText());
				o.setData(data);
				o.setHora(hora);
				o.setProfessor(pdao.getProfessor(p.getCodigo()));
				o.setUsuario(udao.getUsuario(u.getCodigo()));
				o.setTipo((String) comboBox.getSelectedItem());
				odao.incluir(o);
				setVisible(false);
				TabelaObservacao jo = new TabelaObservacao(p.getCodigo(), u.getNome());
				jo.setVisible(true);
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja limpar janela?", "Cadastro de Observações", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
				    CadastroObservações novaJanela = new CadastroObservações(prof, string, codigo);
					dispose();
					novaJanela.setVisible(true);
				  }
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar cadastro?", "Cadastro de Observações", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION)
				{
					System.out.println("Confirmou");
					setVisible(false);
					TabelaObservacao jo = new TabelaObservacao(p.getCodigo(), u.getNome());
					jo.setVisible(true);
				}
			}
		});
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Positiva", "Negativa", "Informativa"}));
		
		
		JLabel lblProfessor = new JLabel("Professor:");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setText(p.getNome());
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(btnSalvar)
							.addGap(18)
							.addComponent(btnLimpar)
							.addGap(18)
							.addComponent(btnCancelar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 529, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblObservao)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblProfessor)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProfessor)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObservao)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnLimpar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
	}
}
