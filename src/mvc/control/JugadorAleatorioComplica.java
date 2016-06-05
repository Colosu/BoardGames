package mvc.control;

import mvc.logica.Ficha;
import mvc.logica.Movimiento;
import mvc.logica.Tablero;

public class JugadorAleatorioComplica implements Jugador {

	public JugadorAleatorioComplica(FactoriaTipoJuego fact) {
		
		factoria = fact;
	}
	
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		return factoria.creaMovimiento( (int) ((Math.random()*tab.getAncho()) + 1), 0, color);
	}
	
	private FactoriaTipoJuego factoria;
}
