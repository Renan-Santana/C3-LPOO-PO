import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class telaApp extends JFrame {

	private JPanel contentPane;
	private JTextField textResultFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaApp frame = new telaApp();
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
	public telaApp() {
		setBackground(Color.LIGHT_GRAY);
		setFont(new Font("Source Code Pro", Font.BOLD | Font.ITALIC, 16));
		setTitle("Trabalho de LPOO e PO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(telaApp.class.getResource("/img/compras.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 370);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("T\u00EDtulo que ser\u00E1 definido");
		lblTitle.setIcon(new ImageIcon(telaApp.class.getResource("/img/purchase-order.png")));
		lblTitle.setLabelFor(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBackground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Source Code Pro", Font.PLAIN, 29));
		lblTitle.setBounds(10, 11, 580, 59);
		contentPane.add(lblTitle);

		JLabel lblEscolhaOArquivo = new JLabel("Escolha o arquivo:");
		lblEscolhaOArquivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEscolhaOArquivo.setForeground(Color.WHITE);
		lblEscolhaOArquivo.setFont(new Font("Source Code Pro", Font.PLAIN, 15));
		lblEscolhaOArquivo.setBackground(Color.LIGHT_GRAY);
		lblEscolhaOArquivo.setBounds(10, 117, 270, 37);
		contentPane.add(lblEscolhaOArquivo);

		JLabel lblEscolhaAEstrutura = new JLabel("Escolha a estrutura de dados:");
		lblEscolhaAEstrutura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEscolhaAEstrutura.setForeground(Color.WHITE);
		lblEscolhaAEstrutura.setFont(new Font("Source Code Pro", Font.PLAIN, 15));
		lblEscolhaAEstrutura.setBackground(Color.LIGHT_GRAY);
		lblEscolhaAEstrutura.setBounds(10, 81, 270, 37);
		contentPane.add(lblEscolhaAEstrutura);

		JComboBox comboBoxArquivo = new JComboBox();
		comboBoxArquivo.setModel(new DefaultComboBoxModel(
				new String[] { "compra500ord", "compra500inv", "compra500alea", "compra1000ord", "compra1000inv",
						"compra1000alea", "compra5000ord", "compra5000inv", "compra5000alea", "compra10000ord",
						"compra10000inv", "compra10000alea", "compra50000ord", "compra50000inv", "compra50000alea" }));
		comboBoxArquivo.setMaximumRowCount(20);
		comboBoxArquivo.setFont(new Font("Source Code Pro", Font.PLAIN, 15));
		comboBoxArquivo.setBackground(Color.GRAY);
		comboBoxArquivo.setBounds(290, 124, 293, 22);
		contentPane.add(comboBoxArquivo);

		JComboBox comboBoxEstrutura = new JComboBox();
		comboBoxEstrutura.setModel(new DefaultComboBoxModel(new String[] { "\u00C1rvore Bin\u00E1ria de Busca (ABB)",
				"\u00C1rvore AVL (AVL)", "Tabela de dispers\u00E3o (HASHING)" }));
		comboBoxEstrutura.setMaximumRowCount(20);
		comboBoxEstrutura.setFont(new Font("Source Code Pro", Font.PLAIN, 15));
		comboBoxEstrutura.setBackground(Color.GRAY);
		comboBoxEstrutura.setBounds(290, 89, 293, 22);
		contentPane.add(comboBoxEstrutura);

		JButton btnGenerate = new JButton("GERAR ARQUIVO");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String fileTxt = (String) comboBoxArquivo.getSelectedItem();

				String estruturaTxt = (String) comboBoxEstrutura.getSelectedItem();

				if (estruturaTxt.contains("ABB")) {

					ArrayList<Compra> lista = new ArrayList<Compra>();

					CadCompra cadastro = new CadCompra(lista);

					String pathCpf = "C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\files\\compra.txt";

					String pathPurchase = "C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\files\\" + fileTxt + ".txt";

					Long start = System.currentTimeMillis();

					App.loadArchive(cadastro, pathPurchase);

					Abb abb = new Abb();

					App.insertAbb(cadastro, abb);

					App.balanceAbb(abb);

					ArrayList<String> cpfs = new ArrayList<String>();

					App.loadArchive(cpfs, pathCpf);

					String content = App.generateArchiveFinal(abb, cpfs);

					Long timeSeconds = (System.currentTimeMillis() - start);

					try {
						String archive = fileTxt + "___" + timeSeconds + "___ABB.txt";
						Archive.Write(
								"C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\fileResults\\withAbb\\" + archive,
								content);
					} catch (Exception e1) {
						System.err.println(e1.getMessage());
					}

					String result = "runtime file " + fileTxt + " in MilliSeconds with ABB: " + timeSeconds + "; \n\n";
					textResultFile.setText(result);
					//JOptionPane.showMessageDialog(null, result, "Resultado com ABB", JOptionPane.INFORMATION_MESSAGE);
				}

				else if (estruturaTxt.contains("AVL")) {

					ArrayList<Compra> lista1 = new ArrayList<Compra>();
					CadCompra cadastro1 = new CadCompra(lista1);
					String pathCpf1 = "C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\files\\compra.txt";

					String pathPurchase1 = "C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\files\\" + fileTxt + ".txt";

					Long start1 = System.currentTimeMillis();

					AppAVL.loadArchive(cadastro1, pathPurchase1);

					AVL avl = new AVL();

					AppAVL.insertAVL(cadastro1, avl);

					ArrayList<String> cpfs1 = new ArrayList<String>();

					AppAVL.loadArchive(cpfs1, pathCpf1);

					String content1 = AppAVL.generateArchiveFinal(avl, cpfs1);

					Long timeSeconds1 = (System.currentTimeMillis() - start1);

					try {
						String archive1 = fileTxt + "___" + timeSeconds1 + "___AVL.txt";
						Archive.Write(
								"C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\fileResults\\withAvl\\" + archive1,
								content1);
					} catch (Exception e1) {
						System.err.println(e1.getMessage());
					}

					String result1 = "runtime file " + fileTxt + " in MilliSeconds with AVL: " + timeSeconds1 + " \n\n";
					textResultFile.setText(result1);
					//JOptionPane.showMessageDialog(null, result1, "Resultado com AVL", JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(null, "Implementar o HASHING");

				}

			}
		});
		btnGenerate.setForeground(Color.WHITE);
		btnGenerate.setIcon(new ImageIcon(telaApp.class.getResource("/img/file.png")));
		btnGenerate.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
		btnGenerate.setBackground(Color.GRAY);
		btnGenerate.setBounds(113, 221, 167, 27);
		contentPane.add(btnGenerate);

		JButton btnExit = new JButton("SAIR");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setIcon(new ImageIcon(telaApp.class.getResource("/img/logout.png")));
		btnExit.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
		btnExit.setBackground(Color.GRAY);
		btnExit.setBounds(317, 221, 167, 27);
		contentPane.add(btnExit);
		
		textResultFile = new JTextField();
		textResultFile.setHorizontalAlignment(SwingConstants.CENTER);
		textResultFile.setForeground(Color.WHITE);
		textResultFile.setBackground(Color.DARK_GRAY);
		textResultFile.setFont(new Font("Source Code Pro", Font.PLAIN, 12));
		textResultFile.setEditable(false);
		textResultFile.setBounds(65, 289, 476, 20);
		contentPane.add(textResultFile);
		textResultFile.setColumns(10);
	}
}
