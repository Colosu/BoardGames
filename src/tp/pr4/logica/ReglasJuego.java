package tp.pr4.logica;

public interface ReglasJuego {
	
	//Inicia un tablero para el juego seleccionado
	public Tablero iniciaTablero();
	
	//Devuelve el jugador inicial del juego en cuestion
	public Ficha jugadorInicial();

	/*Este método comprueba si hay 4 en raya.
	 * Para ello cuenta cuantas fichas del mismo tipo de la última puesta hay en todas las direcciones válidas
	 * antes de llegar a una ficha del otro tipo o vacía. 
	 * Luego suma las cuentas de las direcciones opuestas y si hay 3 o más, con la última puesta, se hacen 4 en raya.*/
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero tablero);

	//Devuelve true si se han producido tablas, en cuyo caso, terminaremos la partida sin ganador.
	public boolean tablas(Ficha ultimoEnPoner, Tablero tablero);
	
	//Devuelve el jugador que juega a continuación.
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero tablero);
}
