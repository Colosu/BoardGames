package mvc.control;

import java.util.Scanner;

import mvc.logica.*;

public class FactoriaGravity implements FactoriaTipoJuego {
	
	public FactoriaGravity (int cols, int fils) {
		
		columnas = cols;
		filas = fils;
	}
	
	public JugadorSwing creaJugadorHumanoSwing(ControladorSwing controlador) {
	
		return new JugadorHumanoSwing(controlador);
	}

	public Jugador creaJugadorHumanoConsola(Scanner in) {

		return new JugadorHumano(in, this, true);
	}

	public Jugador creaJugadorAleatorio() {

		return new JugadorAleatorioGravity(this);
	}

	public JugadorSwing creaJugadorAutomatico() {
		
		return new JugadorAutomaticoSwing();
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {

		return new MovimientoGravity(col, fila, color);
	}

	public ReglasJuego creaReglas() {

		return new ReglasGravity(columnas, filas);
	}

	private int columnas;
	private int filas;
}
