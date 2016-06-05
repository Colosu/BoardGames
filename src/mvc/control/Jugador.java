package mvc.control;

import mvc.logica.Ficha;
import mvc.logica.Movimiento;
import mvc.logica.Tablero;

public interface Jugador {

	//Crea un movimiento del juego en cuestión.
	//Si es un jugador humano, pregunta por las coordenadas de la ficha que se quiere colocar.
	public Movimiento getMovimiento(Tablero tab, Ficha color);
}