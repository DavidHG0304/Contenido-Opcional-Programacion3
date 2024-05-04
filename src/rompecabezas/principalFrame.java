package rompecabezas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.UIManager;

public class principalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private ArrayList <Integer> numerosBotones;
	private ArrayList<JButton> listBotones;
	private ActionListener listener;
	private JPanel panelPrincipal;
	private String auxGanar;

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
		numerosBotones = new ArrayList<Integer>();
		listBotones = new ArrayList<JButton>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panelPrincipal = new JPanel();
		panel.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new GridLayout(4, 4, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(143, 143, 143));
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JButton botonReiniciar = new JButton("Reiniciar");
		botonReiniciar.setBackground(Color.white);
		botonReiniciar.setForeground(new Color(0, 0, 0));
		botonReiniciar.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		botonReiniciar.setFont(new Font("Yu Gothic Medium", Font.BOLD, 20));
		botonReiniciar.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		botonReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			}
		});
		panel_1.add(botonReiniciar);
		
		crearBotones();
		
    }
	
    public int encontrarEspacioBlanco() {
        for (int i = 0; i < listBotones.size(); i++) {
            if (listBotones.get(i).getText().isEmpty()) {
                return i;
            }
        }
        return -1; 
    }
    
    public boolean esAdyacente(int posicionBoton, int posicionEspacioBlanco) {
        int diferencia = Math.abs(posicionBoton - posicionEspacioBlanco);
        return (diferencia == 1 && (posicionBoton / 4 == posicionEspacioBlanco / 4)) || (diferencia == 4); 
    }
    
	public void crearBotones(){
		accionDeBotonesMoverse();
		for (int i = 0; i < 16; i++) {
			numerosBotones.add(i+1);
//			System.out.println(numerosBotones);
			Collections.shuffle(numerosBotones);
		}
		for (int i = 0; i < 16; i++) {
			listBotones.add(new JButton("" + (numerosBotones.get(i))));
			listBotones.get(i).setBackground(Color.white);
			listBotones.get(i).setForeground(new Color(162, 22, 6));
			listBotones.get(i).setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			listBotones.get(i).setFont(new Font("Yu Gothic Medium", Font.BOLD, 30));
			listBotones.get(i).addActionListener(listener);
			listBotones.get(i).setBorder(BorderFactory.createLineBorder(Color.gray, 3));
			panelPrincipal.add(listBotones.get(i));
		}
		
		for (JButton jButton : listBotones) {
			if(jButton.getText().equals("16")) {
				jButton.setText("");
				jButton.setEnabled(false);
			}
		}
//		listBotones.get(15).setText("");
//		listBotones.get(15).setEnabled(false);
	}
	
	public void accionDeBotonesMoverse() {
		listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (e.getSource() instanceof JButton) {
		            JButton btnSeleccionado = (JButton) e.getSource();
		            int posicionEspacioBlanco = encontrarEspacioBlanco();
		            int posicionBoton = -1;
		            for (int i = 0; i < listBotones.size(); i++) {
		                if (listBotones.get(i) == btnSeleccionado) {
		                    posicionBoton = i;
		                    break;
		                }
		            }
//		            System.out.println("Posición botón " + posicionBoton);
//		            System.out.println("Posición espacio " + posicionEspacioBlanco);
		            if (posicionBoton != -1 && posicionEspacioBlanco != -1) {
		                if (esAdyacente(posicionBoton, posicionEspacioBlanco)) {
		                	String auxBotonPresionado = listBotones.get(posicionBoton).getText();
		                	
		                	listBotones.get(posicionBoton).setEnabled(false);
		                	listBotones.get(posicionEspacioBlanco).setEnabled(true);
		                	listBotones.get(posicionBoton).setText("");
		                	listBotones.get(posicionEspacioBlanco).setText(""+ auxBotonPresionado);
		                	if(ganar()) {
		                		JOptionPane.showMessageDialog(null, "Ganaste");
		                		reiniciar();
		                	}
		                }
		            }
		        }
		    }
		};
	}
	
	public boolean ganar() {
		System.out.print("\n");
		auxGanar = "";
		for(int i = 0; i < listBotones.size(); i++){
			auxGanar += listBotones.get(i).getText();
		}
		System.out.print(auxGanar);
		if(auxGanar.equals("123456789101112131415")) {
			System.out.println("\nGano");
			return true;
		}
		return false;
	}
	
	public void reiniciar() {
		panelPrincipal.removeAll();
		listBotones.clear();
		numerosBotones.clear();
		repaint();
		crearBotones();
		
	}
	

}
