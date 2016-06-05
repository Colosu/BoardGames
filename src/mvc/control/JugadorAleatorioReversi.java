package mvc.control;

import java.util.LinkedList;
import java.util.List;

import mvc.logica.Ficha;
import mvc.logica.Movimiento;
import mvc.logica.Tablero;
import mvc.logica.UtilsOthello;

public class JugadorAleatorioReversi implements Jugador {

	public JugadorAleatorioReversi(FactoriaTipoJuego fact) {
		
		factoria = fact;
	}
	
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		int pos;
		

		List<int[]> posibles = new LinkedList<int[]>();
		posibles = UtilsOthello.getPosibles(tab, color);
		
		pos = (int) ((Math.random()*posibles.size()));
		
		return factoria.creaMovimiento(posibles.get(pos)[0], posibles.get(pos)[1], color);
	}

	private FactoriaTipoJuego factoria;
}
