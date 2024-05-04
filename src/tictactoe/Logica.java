package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Logica implements ActionListener{

	private JButton botones[] = new JButton[9];
	private boolean turnos = true;
	private int contadorVictorias;
	private int ganadasX;
	private int ganadasO;
	private boolean esGanador = false;
	private char auxX;
	private char auxY;

//	public Logica (int Turnos, int contadorVictorias,  int ganadasX,  int ganadasY, boolean esGanador, JButton botones[], char auxX, char auxY) {
//		this.turnos = true;
//		this.contadorVictorias = 0;
//		this.ganadasX = 0;
//		this.ganadasY = 0;
//		this.esGanador = true;
//		this.botones = null;
//		this.auxX = 'X';
//		this.auxY = 'Y';
//	}

	public Logica() {

	}

	public boolean getTurnos() {
		return turnos;
	}

	public void setTurnos(boolean turnos) {
		this.turnos = turnos;
	}

	public int getContadorVictorias() {
		return contadorVictorias;
	}

	public void setContadorVictorias(int contadorVictorias) {
		this.contadorVictorias = contadorVictorias;
	}

	public int getGanadasX() {
		return ganadasX;
	}

	public void setGanadasX(int ganadasX) {
		this.ganadasX = ganadasX;
	}

	public int getGanadasO() {
		return ganadasO;
	}

	public void setGanadasO(int ganadasY) {
		this.ganadasO = ganadasY;
	}

	public boolean isEsGanador() {
		return esGanador;
	}

	public void setEsGanador(boolean esGanador) {
		this.esGanador = esGanador;
	}

	public JButton[] getBotones() {
		return botones;
	}

	public void setBotones(JButton[] botones) {
		this.botones = botones;
	}

	public char getAuxX() {
		return auxX;
	}

	public void setAuxX(char auxX) {
		this.auxX = auxX;
	}

	public char getAuxY() {
		return auxY;
	}

	public void setAuxY(char auxY) {
		this.auxY = auxY;
	}

	// Crear Botones
	public void crearBotones(JPanel panelCentral) {

		for (int i = 0; i < 9; i++) {
			botones[i] = new JButton(" ");
			// Aniadir ActionListener al crear botones
			botones[i].addActionListener(this);
			panelCentral.add(botones[i]);
		}
	}

	// Turno de los jugadores, cambia el valor booleano de "turnios


	public void reiniciar(JPanel panel, JButton btnReset) {
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Hola");
				for (int i = 0; i < 9; i++) {
					botones[i].setText(" ");
					botones[i].setEnabled(true);
					turnos = true;
					// Aniadir ActionListener al crear botones
				}
			}
		});

	}

	public void reiniciarJuego() {
		// Reiniciar el estado del juego
		for (int i = 0; i < 9; i++) {
			botones[i].setText(" ");
			botones[i].setEnabled(true);
		}
		// Reiniciar turno para empezar en X
		turnos = true; 
	}

	public boolean verificarGanador() {
		
		//Verificar X
		
		// Verificar filas
		if (((botones[0].getText().equals("X")) && (botones[1].getText().equals("X")) && (botones[2].getText().equals("X"))) || ((botones[3].getText().equals("X")) && (botones[4].getText().equals("X")) && (botones[5].getText().equals("X"))) || ((botones[6].getText().equals("X")) && (botones[7].getText().equals("X")) && (botones[8].getText().equals("X")))) {
			JOptionPane.showMessageDialog(null, "Han ganado las X", "Ganador", JOptionPane.INFORMATION_MESSAGE);
			setGanadasX((getGanadasX()+1));
			return true;
		}
		
		// Verificar Columnas
		if (((botones[0].getText().equals("X")) && (botones[3].getText().equals("X")) && (botones[6].getText().equals("X"))) || ((botones[1].getText().equals("X")) && (botones[4].getText().equals("X")) && (botones[7].getText().equals("X"))) || ((botones[2].getText().equals("X")) && (botones[5].getText().equals("X")) && (botones[8].getText().equals("X")))) {
			JOptionPane.showMessageDialog(null, "Han ganado las X", "Ganador", JOptionPane.INFORMATION_MESSAGE);
			setGanadasX((getGanadasX()+1));
			return true;
		}
		
		// Verificar Diagonales
		if(((botones[0].getText().equals("X")) && (botones[4].getText().equals("X")) && (botones[8].getText().equals("X")))|| ((botones[2].getText().equals("X")) && (botones[4].getText().equals("X")) && (botones[6].getText().equals("X")))) {
			JOptionPane.showMessageDialog(null, "Han ganado las X", "Ganador", JOptionPane.INFORMATION_MESSAGE);
			setGanadasX((getGanadasX()+1));
			return true;
		}
		
		// Verificar o

		// Verificar filas
		if (((botones[0].getText().equals("O")) && (botones[1].getText().equals("O")) && (botones[2].getText().equals("O"))) || ((botones[3].getText().equals("O")) && (botones[4].getText().equals("O")) && (botones[5].getText().equals("O"))) || ((botones[6].getText().equals("O")) && (botones[7].getText().equals("O")) && (botones[8].getText().equals("O")))) {
			JOptionPane.showMessageDialog(null, "Han ganado las O", "Ganador", JOptionPane.INFORMATION_MESSAGE);
			setGanadasO((getGanadasO()+1));
			return true;
		}

		// Verificar Columnas
		if (((botones[0].getText().equals("O")) && (botones[3].getText().equals("O")) && (botones[6].getText().equals("O"))) || ((botones[1].getText().equals("O")) && (botones[4].getText().equals("O")) && (botones[7].getText().equals("O"))) || ((botones[2].getText().equals("O")) && (botones[5].getText().equals("O")) && (botones[8].getText().equals("O")))) {
			JOptionPane.showMessageDialog(null, "Han ganado las O", "Ganador", JOptionPane.INFORMATION_MESSAGE);
			setGanadasO((getGanadasO()+1));
			return true;
		}

		// Verificar Diagonales
		if (((botones[0].getText().equals("O")) && (botones[4].getText().equals("O")) && (botones[8].getText().equals("O"))) || ((botones[2].getText().equals("O")) && (botones[4].getText().equals("O")) && (botones[6].getText().equals("O")))) {
			JOptionPane.showMessageDialog(null, "Han ganado las O", "Ganador", JOptionPane.INFORMATION_MESSAGE);
			setGanadasO((getGanadasO()+1));
			return true;
		}
		
//		System.out.println("aun no hay ganador");
		return false;
	}

	public boolean verificarEmpate() {
		for(int i = 0; i < 9 ;i++) {
			if(botones[i].getText().equals(" ")) {
				return false;
			}
		}
		
		JOptionPane.showMessageDialog(null, "No hay Ganador", "Empate", JOptionPane.INFORMATION_MESSAGE);
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
