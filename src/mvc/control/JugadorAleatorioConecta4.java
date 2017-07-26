package mvc.control;

import mvc.logica.Ficha;
import mvc.logica.Movimiento;
import mvc.logica.Tablero;

public class JugadorAleatorioConecta4 implements Jugador {

	public JugadorAleatorioConecta4(FactoriaTipoJuego fact) {
		
		factoria = fact;
	}
	
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		int col = (int) ((Math.random()*tab.getAncho()) + 1);
		while (tab.getCasilla(col, 1) != Ficha.VACIA) {
			
			col = (int) ((Math.random()*tab.getAncho()) + 1);
		}
		return factoria.creaMovimiento(col, 0, color);
	}
	
	private FactoriaTipoJuego factoria;

}
