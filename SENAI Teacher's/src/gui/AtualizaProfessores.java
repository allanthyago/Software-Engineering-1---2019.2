package gui;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.ManipularImagem;
import dao.ObservacaoDAO;
import dao.ProfessorDAO;
import dao.UsuarioDAO;
import entity.Observacao;
import entity.Professor;
import entity.Usuario;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AtualizaProfessores extends JDialog {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JFormattedTextField tfResidencial;
	private JFormattedTextField tfCelular1;
	private JFormattedTextField tfCelular2;
	private JTextField tfFormaPrinc;
	private JTextField tfFormaSec;
	private JTextField tfAtuaPrinc;
	private JTextField tfAtuaSec;
	private JTextField tfCodigo;
	private BufferedImage imagem;
	private JButton btnAbrir;
	private JLabel lblCurriculo;
	private JTextField tfCurriculo;
	private String rota_arquivo;
	private File rota;
	private JLabel lbImagem;
	private JButton btnMostraCurriculo;
	private JLabel lblUsurio;
	private JTextField tfUsuario;
	Usuario u = new Usuario();
	UsuarioDAO udao = new UsuarioDAO();
	Observacao o = new Observacao();
	ObservacaoDAO odao = new ObservacaoDAO();
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnVerObservaes;

	/**
	 * Create the frame.
	 */
	public AtualizaProfessores(DefaultTableModel model, int codigo, String nome) {
		u = udao.getUsuarios(nome);
		ProfessorDAO pdao = new ProfessorDAO();
		Professor p = pdao.getProfessor(codigo);

		setTitle("SENAI Teachers   |    Atualizar Professor " + p.getNome());
		String user = System.getProperty("user.name");
		getContentPane().add(new JLabel(
				new ImageIcon("C:\\Users\\" + user + "\\Documents\\SENAI Teachers\\src\\Imagem\\Fundo de tela-SENAI.png")));
		setIconImage(Toolkit.getDefaultToolkit().getImage(AtualizaProfessores.class.getResource("/Imagem/Senai.png")));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar?", "Confirma Exit",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					System.out.println("Confirmou");
					TabelaProfessores novaJanela = new TabelaProfessores(u.getNome());
					dispose();
					novaJanela.setVisible(true);
				}
				  else {
					  System.out.println("Não confirmou");
					  setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				  }
			}
		});
		setBounds(100, 100, 1091, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNome = new JLabel("Nome:");

		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setText(p.getNome());

		JLabel lblEmail = new JLabel("Email:");

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setText(p.getEmail());

		JLabel lblTelefoneResidencial = new JLabel("Telefone Residencial:");

		MaskFormatter form;
		try {
			form = new MaskFormatter("(##) ####-####");
			form.setValidCharacters("0123456789");
			tfResidencial = new JFormattedTextField(form);
			tfResidencial.setColumns(10);
			tfResidencial.setText(p.getTelResidencial());
		}catch(ParseException e) {
			e.printStackTrace();
		}

		JLabel lblTelefoneCelular = new JLabel("Telefone Celular:");

		MaskFormatter form2;
		try {
			form2 = new MaskFormatter("(##) #####-####");
			form2.setValidCharacters("0123456789");
			tfCelular1 = new JFormattedTextField(form2);
			tfCelular1.setColumns(10);
			tfCelular1.setText(p.getTelCel1());
		}catch(ParseException e) {
			e.printStackTrace();
		}
		JLabel lblTelefoneCelular_1 = new JLabel("Telefone Celular:");

		MaskFormatter form1;
		try {
			form1 = new MaskFormatter("(##) #####-####");
			form1.setValidCharacters("0123456789");
			tfCelular2 = new JFormattedTextField(form1);
			tfCelular2.setColumns(10);
			tfCelular2.setText(p.getTelCel2());
		}catch(ParseException e) {
			e.printStackTrace();
		}

		JLabel lblFormaoPrincipal = new JLabel("Forma\u00E7\u00E3o Principal:");

		tfFormaPrinc = new JTextField();
		tfFormaPrinc.setColumns(10);
		tfFormaPrinc.setText(p.getFormaPrinc());

		JLabel lblAtuaoPrincipal = new JLabel("Atua\u00E7\u00E3o Principal:");

		tfAtuaPrinc = new JTextField();
		tfAtuaPrinc.setColumns(10);
		tfAtuaPrinc.setText(p.getAtuaPrinc());

		JLabel lblFormaoSecundria = new JLabel("Forma\u00E7\u00E3o Secund\u00E1ria:");

		tfFormaSec = new JTextField();
		tfFormaSec.setColumns(10);
		tfFormaSec.setText(p.getFormaSec());

		JLabel lblAtuaoSecundria = new JLabel("Atua\u00E7\u00E3o Secund\u00E1ria:");

		tfAtuaSec = new JTextField();
		tfAtuaSec.setColumns(10);
		tfAtuaSec.setText(p.getAtuaSec());

		lblCurriculo = new JLabel("Curriculo:");
		tfCurriculo = new JTextField();
		tfCurriculo.setColumns(10);
	
		lbImagem = new JLabel("");
		if(p.getImagem()!=null) {
			ManipularImagem.exibirImagemLabel(p.getImagem(), lbImagem);
			lbImagem.setIcon(new ImageIcon(p.getImagem()));			
		}

		lblUsurio = new JLabel("Usu\u00E1rio:");

		tfUsuario = new JTextField();
		tfUsuario.setEditable(false);
		tfUsuario.setColumns(10);
		tfUsuario.setText(p.getUsuario().getLogin());

		scrollPane = new JScrollPane();
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		o = odao.lastObs(codigo);
		textArea.setText(o.getTexto());
		textArea.setEditable(false);

		btnMostraCurriculo = new JButton("");
		btnMostraCurriculo.setIcon(new ImageIcon(AtualizaProfessores.class.getResource("/Imagem/lpdf.png")));
		btnMostraCurriculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.awt.Desktop.getDesktop()
							.open(new File("C:\\Users\\" + user + "\\Documents\\SENAI Teachers\\src\\Curriculo\\novo.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("não exibiu\n" + e1.getMessage());
				}
			}
		});
		btnMostraCurriculo.setBackground(SystemColor.window);
		btnMostraCurriculo.setFont(new Font("Tahoma", Font.BOLD, 11));

		// pdao.getPDFData(p.getCodigo());

		tfCurriculo.setText(p.getNomeArquivo());
		JButton btnSair = new JButton("Cancelar");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar alteração?",
						"Alterar cadastro de Professor", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					System.out.println("Confirmou");
					dispose();
					TabelaProfessores td = new TabelaProfessores(u.getNome());
					td.setVisible(true);
				}
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfessorDAO pDAO = new ProfessorDAO();
				Professor p = new Professor();
//				File rota = new File(rota_arquivo);
//				JOptionPane.showMessageDialog(null, rota_arquivo);
				if (tfNome.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Nome obrigtório");
				} else {
					if (tfEmail.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "Email obrigtório");
					} else {
						if (imagem == null && rota_arquivo == null) {
							p.setCodigo(Integer.parseInt(tfCodigo.getText()));
							p.setNome(tfNome.getText());
							p.setEmail(tfEmail.getText());
							p.setTelResidencial(tfResidencial.getText());
							p.setTelCel1(tfCelular1.getText());
							p.setTelCel2(tfCelular2.getText());
							p.setFormaPrinc(tfFormaPrinc.getText());
							p.setAtuaPrinc(tfAtuaPrinc.getText());
							p.setFormaSec(tfFormaSec.getText());
							p.setAtuaSec(tfAtuaSec.getText());

							p.setUsuario(udao.getUsuario(u.getCodigo()));
							System.out.println("sem nada");
							pDAO.atualizarSemFotoSemArquivo(p, u.getNome());
							TabelaProfessores.carregaDados(model);
							setVisible(false);
							TabelaProfessores tp = new TabelaProfessores(u.getNome());
							tp.setVisible(true);
						} else {
							if (imagem != null) {
								p.setCodigo(Integer.parseInt(tfCodigo.getText()));
								p.setNome(tfNome.getText());
								p.setEmail(tfEmail.getText());
								p.setTelResidencial(tfResidencial.getText());
								p.setTelCel1(tfCelular1.getText());
								p.setTelCel2(tfCelular2.getText());
								p.setFormaPrinc(tfFormaPrinc.getText());
								p.setAtuaPrinc(tfAtuaPrinc.getText());
								p.setFormaSec(tfFormaSec.getText());
								p.setAtuaSec(tfAtuaSec.getText());
								p.setImagem(dao.ManipularImagem.getImgBytes(imagem));
								System.out.println("com foto sem arquivo");
								pDAO.atualizarSemArquivo(p, u.getNome());
								TabelaProfessores.carregaDados(model);
								setVisible(false);
								TabelaProfessores td = new TabelaProfessores(u.getNome());
								td.setVisible(true);
							} else {
								File rota = new File(rota_arquivo);
								if (rota_arquivo.trim().length() != 0) {
									p.setCodigo(Integer.parseInt(tfCodigo.getText()));
									p.setNome(tfNome.getText());
									p.setEmail(tfEmail.getText());
									p.setTelResidencial(tfResidencial.getText());
									p.setTelCel1(tfCelular1.getText());
									p.setTelCel2(tfCelular2.getText());
									p.setFormaPrinc(tfFormaPrinc.getText());
									p.setAtuaPrinc(tfAtuaPrinc.getText());
									p.setFormaSec(tfFormaSec.getText());
									p.setAtuaSec(tfAtuaSec.getText());

									byte[] pdf = new byte[(int) rota.length()];
									p.setCurriculo(pdf);
									p.setNomeArquivo(rota_arquivo);
//									System.out.println(rota_arquivo);
									System.out.println("sem foto com arquivo");
									pDAO.atualizarSemFoto(p, u.getNome(), rota_arquivo);
									JOptionPane.showMessageDialog(null, "nenhuma imagem");
									TabelaProfessores.carregaDados(model);
									setVisible(false);
									TabelaProfessores td = new TabelaProfessores(u.getNome());
									td.setVisible(true);
									System.out.println(rota);
								} else {

									p.setCodigo(Integer.parseInt(tfCodigo.getText()));
									p.setNome(tfNome.getText());
									p.setEmail(tfEmail.getText());
									p.setTelResidencial(tfResidencial.getText());
									p.setTelCel1(tfCelular1.getText());
									p.setTelCel2(tfCelular2.getText());
									p.setFormaPrinc(tfFormaPrinc.getText());
									p.setAtuaPrinc(tfAtuaPrinc.getText());
									p.setFormaSec(tfFormaSec.getText());
									p.setAtuaSec(tfAtuaSec.getText());
									p.setImagem(dao.ManipularImagem.getImgBytes(imagem));
									byte[] pdf = new byte[(int) rota.length()];
									p.setCurriculo(pdf);
									p.setNomeArquivo(rota_arquivo);
									p.setUsuario(udao.getUsuario(u.getCodigo()));
									System.out.println("normal");
					
									pDAO.atualizar(p, u.getNome(), rota_arquivo);
									TabelaProfessores.carregaDados(model);
									setVisible(false);
									TabelaProfessores td = new TabelaProfessores(u.getNome());
									td.setVisible(true);
									System.out.println(rota);
								}
							}
						}
					}
				}
			}
		});

		JLabel lblCdigo = new JLabel("C\u00F3digo:");

		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setColumns(10);
		tfCodigo.setText(String.valueOf(p.getCodigo()));

		btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Todos", "jpeg", "png", "jpg");
				FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("JPEG", "jpeg");
				FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("PNG", "png");
				FileNameExtensionFilter filtro3 = new FileNameExtensionFilter("JPG", "jpg");
				fc.addChoosableFileFilter(filtro);
				fc.addChoosableFileFilter(filtro1);
				fc.addChoosableFileFilter(filtro2);
				fc.addChoosableFileFilter(filtro3);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setDialogType(JFileChooser.OPEN_DIALOG);
				int res = fc.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION) {
					File arquivo = fc.getSelectedFile();
					imagem = dao.ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 160, 160);
					lbImagem.setIcon(new ImageIcon(imagem));
				} else {
					JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma imagem");
				}
			}
		});

		JButton btnAbrirCurrirulo = new JButton("Abrir");
		btnAbrirCurrirulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("PDF", "pdf");
				fc.addChoosableFileFilter(filtro);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setDialogType(JFileChooser.OPEN_DIALOG);
				int res = fc.showOpenDialog(null);
				if (res == 0) {
					tfCurriculo.setText("" + fc.getSelectedFile().getName());
					rota_arquivo = fc.getSelectedFile().getAbsolutePath();
					btnMostraCurriculo.setIcon(new javax.swing.ImageIcon(
							"C:\\Users\\" + user + "\\Documents\\SENAI Teachers\\src\\Imagem\\lpdf.png"));
					File arquivo = fc.getSelectedFile();
					String caminho = arquivo.getAbsolutePath();
					caminho = caminho.replace('\\', '/');
					tfCurriculo.setText(caminho);
					String nomeArq = new File(caminho).getName();
					System.out.println(caminho);
				} else
					System.out.println("Deu tudo errado...");
			}
		});

		btnVerObservaes = new JButton("Ver Observa\u00E7\u00F5es");
		btnVerObservaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Professor p = new Professor();
				ProfessorDAO pdao = new ProfessorDAO();
				p = pdao.getProfessor(codigo);
				TabelaObsEdirProf jo = new TabelaObsEdirProf(p.getCodigo(), u.getNome());
				setVisible(false);
				jo.setVisible(true);

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createSequentialGroup().addComponent(lblTelefoneResidencial)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(tfResidencial, GroupLayout.PREFERRED_SIZE, 184,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblTelefoneCelular)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(tfCelular1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblTelefoneCelular_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(tfCelular2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNome)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(tfNome, GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblEmail)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblFormaoPrincipal).addGap(18).addComponent(tfFormaPrinc,
														GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblFormaoSecundria)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(tfFormaSec,
														GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblCurriculo)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(tfCurriculo)))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(btnAbrirCurrirulo)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblAtuaoPrincipal)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(tfAtuaPrinc, GroupLayout.DEFAULT_SIZE,
																		273, Short.MAX_VALUE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblAtuaoSecundria)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(tfAtuaSec, GroupLayout.DEFAULT_SIZE, 259,
																		Short.MAX_VALUE))))))
						.addGap(44)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbImagem, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(61).addComponent(btnAbrir)))
						.addGap(219))
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblCdigo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnMostraCurriculo, GroupLayout.PREFERRED_SIZE, 45,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(tfCodigo, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblUsurio)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 355, Short.MAX_VALUE)
												.addComponent(btnSalvar).addGap(18).addComponent(btnSair).addGap(386))))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 778, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnVerObservaes).addContainerGap(279, Short.MAX_VALUE)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(42)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNome).addComponent(
						tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(17)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail)
										.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTelefoneResidencial)
										.addComponent(tfResidencial, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTelefoneCelular)
										.addComponent(tfCelular1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(tfCelular2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTelefoneCelular_1))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblFormaoPrincipal)
										.addComponent(tfFormaPrinc, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAtuaoPrincipal).addComponent(tfAtuaPrinc,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblFormaoSecundria).addComponent(lblAtuaoSecundria)
										.addComponent(tfAtuaSec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(tfFormaSec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCurriculo)
										.addComponent(tfCurriculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnAbrirCurrirulo)))
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lbImagem, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnAbrir)))
				.addGroup(
						gl_contentPane
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnMostraCurriculo, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 84,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblCdigo)
														.addComponent(tfCodigo, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblUsurio)
														.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnSair).addComponent(btnSalvar))))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(74)
										.addComponent(btnVerObservaes)))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}
}
