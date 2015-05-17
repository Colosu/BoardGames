package tp.pr4.vista.swing;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import tp.pr4.control.ControladorSwing;
import tp.pr4.control.UtilsOthelo;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.Partida.Observer;
import tp.pr4.logica.TipoJuego;

public class PTablero extends JPanel implements Observer {
	
	public PTablero(ControladorSwing control) {
		
		controlador = control;
		setLayout(new GridLayout(1, 1));
	}

	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {


		for (int i = 0; i < estadoTablero.getAncho(); i++) {
			for (int j = 0; j < estadoTablero.getAlto(); j++) {
				
				if (estadoTablero.getCasilla(i+1, j+1) != celdas[i][j].getColor() || celdas[i][j].getBackground().equals(Color.red)) {
					
					celdas[i][j].setColor(estadoTablero.getCasilla(i+1, j+1));
				}
				if (controlador.getJuego().equals(TipoJuego.Reversi) && UtilsOthelo.puedePoner(estadoTablero, i+1, j+1, siguiente)) {
					
					celdas[i][j].setPosible();
				}
			}
		}
	}

	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {

	}

	public void onMovimientoStart(Ficha turno) {

	}

	public void onReset(TableroInmutable estadoInicial, Ficha turno) {

		removeAll();
		setLayout(new GridLayout(estadoInicial.getAlto(), estadoInicial.getAncho()));
		iniciaCeldas(estadoInicial.getAncho(), estadoInicial.getAlto());

		for (int i = 0; i < estadoInicial.getAncho(); i++) {
			for (int j = 0; j < estadoInicial.getAlto(); j++) {
				
				if (estadoInicial.getCasilla(i+1, j+1) != celdas[i][j].getColor() || celdas[i][j].getBackground().equals(Color.red)) {
					
					celdas[i][j].setColor(estadoInicial.getCasilla(i+1, j+1));
				}
			}
		}
		validate();
		repaint();
	}

	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {


		for (int i = 0; i < estadoTablero.getAncho(); i++) {
			for (int j = 0; j < estadoTablero.getAlto(); j++) {
				
				if (estadoTablero.getCasilla(i+1, j+1) != celdas[i][j].getColor() || celdas[i][j].getBackground().equals(Color.red)) {
					
					celdas[i][j].setColor(estadoTablero.getCasilla(i+1, j+1));
				}
				if (controlador.getJuego().equals(TipoJuego.Reversi) && UtilsOthelo.puedePoner(estadoTablero, i+1, j+1, turno)) {
					
					celdas[i][j].setPosible();
				}
			}
		}
	}

	public void onUndoNotPossible() {

	}

	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {


		for (int i = 0; i < tableroFinal.getAncho(); i++) {
			for (int j = 0; j < tableroFinal.getAlto(); j++) {
				
				if (tableroFinal.getCasilla(i+1, j+1) != celdas[i][j].getColor() || celdas[i][j].getBackground().equals(Color.red)) {
					
					celdas[i][j].setColor(tableroFinal.getCasilla(i+1, j+1));
				}
			}
		}
	}
	
	//Crea las celdas del nuevo tablero.
	private void iniciaCeldas(int ancho, int alto) {
		
		celdas = new Celda [ancho][alto];
		
		for (int i = 0; i < alto; i++) {
			
			for (int j = 0; j < ancho; j++) {
				
				celdas[j][i] = new Celda(controlador, j+1, i+1);
				this.add(celdas[j][i]);
			}
		}
	}

	private ControladorSwing controlador;
	private Celda[][] celdas;
	private static final long serialVersionUID = 7376027550623146398L;
}
