package tp.pr4.control;

import tp.pr4.logica.*;

public interface FactoriaTipoJuego {

	//Crea un jugador humano configurado para el juego correspondiente.
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in);
	
	//Crea un jugador aleatorio para el juego correspondiente.
	public Jugador creaJugadorAleatorio();
	
	//Crea un movimiento para el juego correspondiente.
	public Movimiento creaMovimiento(int col, int fila, Ficha color);
	
	//Crea las reglase del juego correspondiente.
	public ReglasJuego creaReglas();
}
