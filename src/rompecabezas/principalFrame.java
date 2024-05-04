package rompecabezas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.UIManager;

public class principalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton[] botonNum = new JButton[16];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principalFrame frame = new principalFrame();
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
	public principalFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelPrincipal = new JPanel();
		panel.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new GridLayout(4, 4, 0, 0));
		
		//Aniadir action listner que se utilizara para darle acciones a los botones
		ActionListener listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (e.getSource() instanceof JButton) {
		            JButton btnSeleccionado = (JButton) e.getSource();
		            int posicionEspacioBlanco = encontrarEspacioBlanco();
		            int posicionBoton = -1;
		            for (int i = 0; i < botonNum.length; i++) {
		                if (botonNum[i] == btnSeleccionado) {
		                    posicionBoton = i;
		                    break;
		                }
		            }
		            System.out.println("Posición botón: " + posicionBoton);
		            System.out.println("Posición espacio: " + posicionEspacioBlanco);
		            if (posicionBoton != -1 && posicionEspacioBlanco != -1) {
		                if (esAdyacente(posicionBoton, posicionEspacioBlanco)) {
		                	int auxBotonActual = Integer.parseInt(botonNum[posicionBoton].getText());
		                	
		                	botonNum[posicionBoton].setEnabled(false);
		                	botonNum[posicionEspacioBlanco].setEnabled(true);
		                    botonNum[posicionBoton].setText("");
		                    botonNum[posicionEspacioBlanco].setText(""+(posicionBoton+1));
		                    
		                    System.out.println(auxBotonActual);
		                }
		            }
		        }
		    }
		};

        for (int i = 0; i < botonNum.length; i++) {
            botonNum[i] = new JButton("" + (i + 1));
            botonNum[i].setBackground(Color.white);
            botonNum[i].setForeground(new Color(162, 22, 6));
            botonNum[i].setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            botonNum[i].setFont(new Font("Yu Gothic Medium", Font.BOLD, 30));
            botonNum[i].addActionListener(listener);
            botonNum[i].setBorder(BorderFactory.createLineBorder(Color.gray, 3));
            panelPrincipal.add(botonNum[i]);
        }
        botonNum[15].setText("");
        botonNum[15].setEnabled(false);
    }
	
    public int encontrarEspacioBlanco() {
        for (int i = 0; i < botonNum.length; i++) {
            if (botonNum[i].getText().isEmpty()) {
                return i;
            }
        }
        return -1; 
    }
    
    public boolean esAdyacente(int posicionBoton, int posicionEspacioBlanco) {
        int diferencia = Math.abs(posicionBoton - posicionEspacioBlanco);
        return (diferencia == 1 && (posicionBoton / 4 == posicionEspacioBlanco / 4)) || (diferencia == 4); 
    }

}
