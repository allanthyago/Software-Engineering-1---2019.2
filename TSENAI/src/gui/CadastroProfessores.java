package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ProfessorDAO;
import dao.UsuarioDAO;
import entity.Professor;
import entity.Usuario;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.SystemColor;

public class CadastroProfessores extends JDialog {
	
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfEmail;
	private JTextField tfTelResidencial;
	private JTextField tfTelCel2;
	private JLabel lblFormacaoPrincipal;
	private JTextField tfFormaçãoPrinc;
	private JLabel lblAreaDeAtuacao;
	private JTextField tfAreaAtuaPrinc;
	private JLabel lblFormacaoSecundria;
	private JTextField tfFormaSec;
	private JLabel lblAreaDeAtuacao_1;
	private JTextField tfAtuaSec;
	private JButton btnAbrir;
	private BufferedImage imagem;
	private JTextField tfCurriculo;
	private String rota_arquivo="";
	private JLabel lbImagem;
	private JLabel lblPdf;
	private JButton button;
	private JButton btnCancelaPDF;
	private File rota;
	private JTextField tfTelCel1;
	Usuario u=new Usuario();
	UsuarioDAO udao = new UsuarioDAO();
	/**
	 * Create the frame.
	 */
	public CadastroProfessores(DefaultTableModel model, String nome) {
		u=udao.getUsuarios(nome);
		setTitle("TSENAI   |    Cadastro de Professores");
		getContentPane().add(new JLabel(new ImageIcon("C:\\Users\\thyag\\PicturesFundo de tela-SENAI.jpg")));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\thyag\\Pictures\\Senai.png"));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1165, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNome = new JLabel("Nome:");
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		
		JLabel lblTelefoneResidencial = new JLabel("Telefone Residencial:");
		
		tfTelResidencial = new JTextField();
		tfTelResidencial.setColumns(10);
		
		JLabel lblTelefoneCelular = new JLabel("Telefone Celular:");
		
		JLabel lblTelefoneCelular_1 = new JLabel("Telefone Celular:");
		
		tfTelCel2 = new JTextField();
		tfTelCel2.setColumns(10);
		
		lblFormacaoPrincipal = new JLabel("Forma\u00E7\u00E3o Principal:");
		
		tfFormaçãoPrinc = new JTextField();
		tfFormaçãoPrinc.setColumns(10);
		
		lblAreaDeAtuacao = new JLabel("\u00C1rea de Atua\u00E7\u00E3o Principal:");
		
		tfAreaAtuaPrinc = new JTextField();
		tfAreaAtuaPrinc.setColumns(10);
		
		lblFormacaoSecundria = new JLabel("Forma\u00E7\u00E3o Secund\u00E1ria:");
		
		tfFormaSec = new JTextField();
		tfFormaSec.setColumns(10);
		
		lblAreaDeAtuacao_1 = new JLabel("\u00C1rea de Atua\u00E7\u00E3o Secund\u00E1ria:");
		
		tfAtuaSec = new JTextField();
		tfAtuaSec.setColumns(10);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar cadastro?", "Cadastro de Professor", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
				    TabelaProfessores.carregaDados(model);
					setVisible(false);
					TabelaProfessores tp = new TabelaProfessores(u.getNome());
					tp.setVisible(true);
				  }
				
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja limpar janela?", "Cadastro de Professor", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
				    CadastroProfessores novaJanela = new CadastroProfessores(model, u.getNome());
					dispose();
					novaJanela.setVisible(true);
				  }
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfessorDAO pDAO = new ProfessorDAO();
				Professor p = new Professor();
				File rota = new File(rota_arquivo);
				if(tfNome.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "Nome obrigtório");
				}else {
					if(tfEmail.getText().trim().length()==0) {
						JOptionPane.showMessageDialog(null, "Email obrigtório");
					}else {
						if(imagem==null) {
							JOptionPane.showMessageDialog(null, "Cadastre Foto do professor");
						}
						else {
							if(rota_arquivo.trim().length() != 0) {
								p.setNome(tfNome.getText());
								p.setEmail(tfEmail.getText());
								p.setTelResidencial(tfTelResidencial.getText());
								p.setTelCel1(tfTelCel1.getText());
								p.setTelCel2(tfTelCel2.getText());
								p.setFormaPrinc(tfFormaçãoPrinc.getText());
								p.setAtuaPrinc(tfAreaAtuaPrinc.getText());
								p.setFormaSec(tfFormaSec.getText());
								p.setAtuaSec(tfAtuaSec.getText());
								p.setImagem(dao.ManipularImagem.getImgBytes(imagem));
								
								byte[] pdf = new byte[(int) rota.length()];
								p.setCurriculo(pdf);
								p.setNomeArquivo(rota_arquivo);
								p.setUsuario(udao.getUsuario(u.getCodigo()));
								pDAO.incluir(p, rota_arquivo);
								
								TabelaProfessores.carregaDados(model);
								setVisible(false);
								TabelaProfessores td = new TabelaProfessores(u.getNome());
								td.setVisible(true);
							}
							else {
								p.setNome(tfNome.getText());
								p.setEmail(tfEmail.getText());
								p.setTelResidencial(tfTelResidencial.getText());
								p.setTelCel1(tfTelCel1.getText());
								p.setTelCel2(tfTelCel2.getText());
								p.setFormaPrinc(tfFormaçãoPrinc.getText());
								p.setAtuaPrinc(tfAreaAtuaPrinc.getText());
								p.setFormaSec(tfFormaSec.getText());
								p.setAtuaSec(tfAtuaSec.getText());
								p.setImagem(dao.ManipularImagem.getImgBytes(imagem));
								p.setUsuario(udao.getUsuario(u.getCodigo()));;
								pDAO.incluir(p, rota_arquivo);
								
								TabelaProfessores.carregaDados(model);
								setVisible(false);
								TabelaProfessores td = new TabelaProfessores(u.getNome());
								td.setVisible(true);
							}
						}
					}
				}
				
			}
				
		});
		
		JLabel lblCurriculo = new JLabel("Curriculo:");
		
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
				if(res == JFileChooser.APPROVE_OPTION) {
					File arquivo = fc.getSelectedFile();
					imagem = dao.ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 160, 160);
					lbImagem.setIcon(new ImageIcon(imagem));
				}
				else {
					JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma imagem");
				}
			}
		});
		JButton btnAbreCurriculo = new JButton("Abrir");
		btnAbreCurriculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("PDF", "pdf");
				fc.addChoosableFileFilter(filtro);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setDialogType(JFileChooser.OPEN_DIALOG);
				int res = fc.showOpenDialog(null);
				if(res==0) {
					tfCurriculo.setText(""+fc.getSelectedFile().getName());
					rota_arquivo=fc.getSelectedFile().getAbsolutePath();
					lblPdf.setIcon(new javax.swing.ImageIcon("C:\\Users\\thyag\\Desktop\\Allan Thyago da Silva Chaves\\Teste Arquivo\\SQLpdf\\src\\Imagen\\lpdf.png"));
					File arquivo = fc.getSelectedFile();
					String caminho = arquivo.getAbsolutePath();
					caminho = caminho.replace('\\', '/');
					tfCurriculo.setText(caminho);                 
					String nomeArq = new File(caminho).getName();
					System.out.println(caminho);
				}else
					System.out.println("Deu tudo errado...");
			}
		});
		
		tfCurriculo = new JTextField();
		tfCurriculo.setColumns(10);
		
		lbImagem = new JLabel("");
		
		lblPdf = new JLabel("");
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					java.awt.Desktop.getDesktop().open(new File(rota_arquivo));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(CadastroProfessores.class.getResource("/Imagem/lpdf.png")));
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(SystemColor.activeCaptionBorder);
		
		btnCancelaPDF = new JButton("Cancelar");
		btnCancelaPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rota=null;
				rota_arquivo=null;
				tfCurriculo.setText("");
			}
		});
		
		tfTelCel1 = new JTextField();
		tfTelCel1.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNome)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(tfNome, GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblEmail)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblTelefoneResidencial)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(tfTelResidencial, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(lblTelefoneCelular))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblFormacaoPrincipal)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(tfFormaSec, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
														.addComponent(tfFormaçãoPrinc))))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(lblAreaDeAtuacao)
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addComponent(tfAreaAtuaPrinc, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(btnSalvar)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(btnLimpar)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(btnSair))
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(lblAreaDeAtuacao_1)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(tfAtuaSec, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(17)
													.addComponent(tfTelCel1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
													.addComponent(lblTelefoneCelular_1)
													.addGap(18)
													.addComponent(tfTelCel2, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)))))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblFormacaoSecundria)
									.addGap(800)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(70)
									.addComponent(btnAbrir))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(21)
									.addComponent(lbImagem, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
							.addGap(24))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCurriculo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(btnAbreCurriculo)
									.addGap(10)
									.addComponent(btnCancelaPDF)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPdf, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
								.addComponent(tfCurriculo, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(594))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(66)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail)
								.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefoneResidencial)
								.addComponent(tfTelResidencial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTelefoneCelular)
								.addComponent(tfTelCel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTelefoneCelular_1)
								.addComponent(tfTelCel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFormacaoPrincipal)
								.addComponent(tfFormaçãoPrinc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAreaDeAtuacao)
								.addComponent(tfAreaAtuaPrinc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbImagem, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFormacaoSecundria)
								.addComponent(tfFormaSec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAreaDeAtuacao_1)
								.addComponent(tfAtuaSec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(btnAbrir)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCurriculo)
								.addComponent(tfCurriculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPdf, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnSair)
										.addComponent(btnLimpar)
										.addComponent(btnSalvar))
									.addGap(26))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnAbreCurriculo)
										.addComponent(btnCancelaPDF))
									.addContainerGap())))))
		);
		contentPane.setLayout(gl_contentPane);
	}	
}
