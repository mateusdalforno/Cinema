package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.bean.Filme;
import model.dao.FilmeDAO;

public class JFAtualizarFilme extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtCategoria;
	
	private static int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFAtualizarFilme frame = new JFAtualizarFilme(id);
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
	public JFAtualizarFilme(int id) {
		setTitle("Alterar Filme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Alterar Filme");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 150, 14);
		contentPane.add(lblNewLabel);
		
		FilmeDAO fdao = new FilmeDAO();
		Filme f = fdao.read(id);
		
		JLabel lblNewLabel_7 = new JLabel("ID do filme");
		lblNewLabel_7.setBounds(166, 38, 89, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblId = new JLabel("New label");
		lblId.setBounds(232, 38, 46, 14);
		contentPane.add(lblId);
		
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo");
		lblNewLabel_1.setBounds(10, 53, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(10, 74, 559, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sinopse");
		lblNewLabel_2.setBounds(10, 105, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 559, 119);
		contentPane.add(scrollPane);
		
		JTextArea txtSinopse = new JTextArea();
		scrollPane.setViewportView(txtSinopse);
		
		JLabel lblNewLabel_3 = new JLabel("Categoria");
		lblNewLabel_3.setBounds(10, 260, 67, 14);
		contentPane.add(lblNewLabel_3);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(10, 285, 559, 20);
		contentPane.add(txtCategoria);
		txtCategoria.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Tempo");
		lblNewLabel_4.setBounds(10, 316, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JSpinner spTempo = new JSpinner();
		spTempo.setBounds(10, 339, 46, 33);
		contentPane.add(spTempo);
		
		JLabel lblNewLabel_5 = new JLabel("Imagem");
		lblNewLabel_5.setBounds(101, 316, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JRadioButton rdbtn2d = new JRadioButton("2D");
		rdbtn2d.setBounds(101, 344, 54, 23);
		contentPane.add(rdbtn2d);
		
		JRadioButton rdbtn3d = new JRadioButton("3D");
		rdbtn3d.setBounds(158, 344, 54, 23);
		contentPane.add(rdbtn3d);
		
		ButtonGroup imagem = new ButtonGroup();
		imagem.add(rdbtn2d);
		imagem.add(rdbtn3d);
		
		JLabel lblNewLabel_6 = new JLabel("\u00C1udio");
		lblNewLabel_6.setBounds(281, 316, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JRadioButton rdbtnDublado = new JRadioButton("Dublado");
		rdbtnDublado.setBounds(281, 344, 81, 23);
		contentPane.add(rdbtnDublado);
		
		JRadioButton rdbtnLegendado = new JRadioButton("Legendado");
		rdbtnLegendado.setBounds(364, 344, 109, 23);
		contentPane.add(rdbtnLegendado);
		
		ButtonGroup audio = new ButtonGroup();
		audio.add(rdbtnDublado);
		audio.add(rdbtnLegendado);
		
		lblId.setText(String.valueOf(f.getIdFilme()));
		txtTitulo.setText(f.getTitulo());
		txtSinopse.setText(f.getSinopse());
		txtCategoria.setText(f.getCategoria());
		spTempo.setValue(f.getTempo());
		if(f.isImagem3d() == true) {
			rdbtn3d.setSelected(true);
		}else if (f.isImagem3d() == false) {
			rdbtn2d.setSelected(true);
		}
		if(f.isDublado() == true) {
			rdbtnDublado.setSelected(true);
		}else if (f.isDublado() == false) {
			rdbtnLegendado.setSelected(true);
		}
		
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Filme f = new Filme();
				FilmeDAO dao = new FilmeDAO();
				
				f.setIdFilme(Integer.parseInt(lblId.getText()));
				f.setTitulo(txtTitulo.getText());
				f.setSinopse(txtSinopse.getText());
				f.setCategoria(txtCategoria.getText());
				f.setTempo(Integer.parseInt(spTempo.getValue().toString()));
				if(rdbtn2d.isSelected()) {
					f.setImagem3d(false);
				}else if (rdbtn3d.isSelected()) {
					f.setImagem3d(true);
				}
				if(rdbtnDublado.isSelected()) {
					f.setDublado(true);
				}else if (rdbtnLegendado.isSelected()) {
					f.setDublado(false);
				}
				dao.update(f);
			}
		});
		btnAlterar.setBounds(10, 400, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(137, 400, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(269, 400, 89, 23);
		contentPane.add(btnCancelar);
		

		
		
	}
}
