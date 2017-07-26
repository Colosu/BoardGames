package mvc.control;

import java.util.Scanner;

import mvc.logica.*;

public class FactoriaComplica implements FactoriaTipoJuego {

	public Jugador creaJugadorHumanoConsola(Scanner in) {

		return new JugadorHumano(in, this, false);
	}
	
	public JugadorSwing creaJugadorHumanoSwing(ControladorSwing controlador) {
	
		return new JugadorHumanoSwing(controlador);
	}

	public Jugador creaJugadorAleatorio() {

		return new JugadorAleatorioComplica(this);
	}

	public JugadorSwing creaJugadorAutomatico() {
		
		return new JugadorAutomaticoSwing();
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {

		return new MovimientoComplica(col, color);
	}

	public ReglasJuego creaReglas() {
		
		return new ReglasComplica();
	}

}
