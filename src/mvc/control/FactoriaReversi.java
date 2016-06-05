package mvc.control;

import java.util.Scanner;

import mvc.logica.Ficha;
import mvc.logica.Movimiento;
import mvc.logica.MovimientoReversi;
import mvc.logica.ReglasJuego;
import mvc.logica.ReglasReversi;

public class FactoriaReversi implements FactoriaTipoJuego {

	public Jugador creaJugadorHumanoConsola(Scanner in) {

		return new JugadorHumano(in, this, true);
	}
	
	public JugadorSwing creaJugadorHumanoSwing(ControladorSwing controlador) {
	
		return new JugadorHumanoSwing(controlador);
	}

	public Jugador creaJugadorAleatorio() {

		return new JugadorAleatorioReversi(this);
	}

	public JugadorSwing creaJugadorAutomatico() {
		
		return new JugadorAutomaticoSwing();
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {

		return new MovimientoReversi(col, fila, color);
	}

	public ReglasJuego creaReglas() {

		return new ReglasReversi();
	}
}
