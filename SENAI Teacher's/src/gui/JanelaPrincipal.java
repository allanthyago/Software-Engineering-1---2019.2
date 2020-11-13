package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Conexao.Conexao;
import dao.UsuarioDAO;
import entity.Usuario;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Toolkit;

public class JanelaPrincipal {

	private static String user = System.getProperty("user.name");
	private static final String FOLDER_RELATORIOS = "C:\\Users\\" + user + "\\Documents\\SENAI Teachers\\MyReports\\relatorios";
	Usuario u = new Usuario();
	UsuarioDAO udao = new UsuarioDAO();

	JPanel p = new JPanel();
	// Barra
	JMenuBar mbBarra = new JMenuBar();
	JFrame frmSenai = new JFrame();

	public JanelaPrincipal(String codigo) {
		u = udao.getUsuarios(codigo);
		String nome = u.getNome();
		frmSenai.getContentPane().add(p);
		frmSenai.getContentPane().add(new JLabel(
				new ImageIcon("C:\\Users\\" + user + "\\Documents\\SENAI Teachers\\src\\Imagem\\Fundo de tela-SENAI.png")));
		frmSenai.setIconImage(
				Toolkit.getDefaultToolkit().getImage(JanelaPrincipal.class.getResource("/Imagem/Senai.png")));
		frmSenai.pack();
		frmSenai.setSize(1350, 730);
		frmSenai.setTitle("SENAI Teachers   |   " + nome);
		frmSenai.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja sair?", "Confirma Exit", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
				    System.out.println("Confirmou");
					System.exit(0);
				  }
				  else {
					  System.out.println("N�o confirmou");
					  frmSenai.setDefaultCloseOperation(frmSenai.DO_NOTHING_ON_CLOSE);
				  }
			}
		});

		frmSenai.setLocationRelativeTo(null);
		frmSenai.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		frmSenai.setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem miProfessores = new JMenuItem("Professores");
		miProfessores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == miProfessores) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								TabelaProfessores tp = new TabelaProfessores(u.getNome());
								tp.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		mnCadastro.add(miProfessores);

		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);

		JMenuItem miDadosPessoais = new JMenuItem("Dados Pessoais");
		miDadosPessoais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexao = Conexao.conector();
				int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impress�o deste relat�rio?", "Aten��o",
						JOptionPane.YES_NO_OPTION);
				if (confirma == JOptionPane.YES_OPTION) {
					try {
						JasperPrint print = JasperFillManager.fillReport(FOLDER_RELATORIOS + "\\Dados.jasper", null,
								conexao);
						JasperViewer.viewReport(print, false);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}
			}
		});
		mnRelatrios.add(miDadosPessoais);

		JMenuItem miRelatoriosFormacao = new JMenuItem("Forma\u00E7\u00E3o");
		miRelatoriosFormacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == miRelatoriosFormacao) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {

								JanelaRelatorioFormacaoPrinc rf = new JanelaRelatorioFormacaoPrinc();
								rf.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		mnRelatrios.add(miRelatoriosFormacao);

		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);

		JMenu mnFormao = new JMenu("Forma\u00E7\u00E3o");
		mnConsultas.add(mnFormao);

		JMenuItem miFormacaoPrincipal = new JMenuItem("Forma\u00E7\u00E3o Principal");
		miFormacaoPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == miFormacaoPrincipal) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								JanelaForma��oPrincipal jfp = new JanelaForma��oPrincipal();
								jfp.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		mnFormao.add(miFormacaoPrincipal);

		JMenuItem miFormacaosec = new JMenuItem("Forma\u00E7\u00E3o Secund\u00E1ria");
		miFormacaosec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == miFormacaosec) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								JanelaForma��oSec jfs = new JanelaForma��oSec();
								jfs.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		mnFormao.add(miFormacaosec);

		JMenu mnreaDeAtuao = new JMenu("\u00C1rea de Atua\u00E7\u00E3o");
		mnConsultas.add(mnreaDeAtuao);

		JMenuItem miAreaPrincipal = new JMenuItem("\u00C1rea Principal");
		miAreaPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == miAreaPrincipal) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								JanelaAtua��oPrinc jap = new JanelaAtua��oPrinc();
								jap.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		mnreaDeAtuao.add(miAreaPrincipal);

		JMenuItem miAreaSecundria = new JMenuItem("\u00C1rea Secund\u00E1ria");
		miAreaSecundria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == miAreaSecundria) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								JanelaAtua��oSec jas = new JanelaAtua��oSec();
								jas.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		mnreaDeAtuao.add(miAreaSecundria);

		JMenu mnUsurio = new JMenu("Usu\u00E1rio");
		menuBar.add(mnUsurio);

		JMenuItem mntmAlterarSenha = new JMenuItem("Alterar Senha");
		mntmAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == mntmAlterarSenha) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								AlterarSenha as = new AlterarSenha(u.getCodigo());
								as.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		mnUsurio.add(mntmAlterarSenha);
		
	}
}