package tictactoe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class TicTacToe extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Logica logicaJuego = new Logica();
	public JButton botones[];
	public JLabel labelJX = new JLabel("",0);
	public JLabel labelJO = new JLabel("",0);
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToe frame = new TicTacToe();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TicTacToe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(252, 247, 139));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(3, 3, 15, 15));
		
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(73, 73, 245));
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new GridLayout(0, 2, 0, 0));
		
		labelJX.setText("X: "+logicaJuego.getGanadasX());
		labelJX.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSuperior.add(labelJX);
		
		labelJO.setText("O: "+logicaJuego.getGanadasO());
		labelJO.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelSuperior.add(labelJO);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(150, 253, 138));
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		JButton btnReiniciar = new JButton("Reiniciar");
		panelInferior.add(btnReiniciar);
		
		btnReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logicaJuego.reiniciarJuego();
				
			}
		});

		logicaJuego.crearBotones(panelCentral);

		botones = logicaJuego.getBotones();
		for (JButton boton : botones) {
			boton.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton boton = (JButton) e.getSource();
		if (boton.isEnabled()) {
			if (logicaJuego.getTurnos()) {
				boton.setText("X");
			} else {
				boton.setText("O");
			}
			boton.setEnabled(false);
			if (logicaJuego.verificarGanador() || logicaJuego.verificarEmpate()) {
				logicaJuego.reiniciarJuego();
			} else {
				logicaJuego.setTurnos(!logicaJuego.getTurnos());
			}
			labelJX.setText("X: "+logicaJuego.getGanadasX());
			labelJO.setText("O: "+logicaJuego.getGanadasO());
		}
	}


}
