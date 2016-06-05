package mvc.control.ordenes;

import mvc.control.Controlador;
import mvc.logica.Partida;
import mvc.logica.ReglasJuego;
import mvc.logica.ReglasReversi;

public class OrdenJugarRv implements Orden {

	public OrdenJugarRv(java.util.Scanner scanner, Controlador control) {
		
		in = scanner;
		controlador = control;
	}

	public void ejecutaOrden(Partida partida) {

		ReglasJuego reglas;

		reglas = new ReglasReversi();
		controlador.setFactoria(3, 0, 0);
		controlador.setJugador(0, 0);
		controlador.setJugador(1, 0);
		partida.reset(reglas);
	}

	public Orden parser(String linea) {

		String [] cadena = new String [5];
		cadena = linea.split(" +");

		if (cadena[0].equals("jugar") && cadena.length > 1 && cadena[1].equals("rv")) {
			
			return new OrdenJugarRv(in, controlador);
		} else {
			
			return null;
		}
	}

	public String getAyuda() {

		return OrdenJugarRv.ayuda;
	}

	private java.util.Scanner in;
	private Controlador controlador;
	private static String ayuda = "";
}
