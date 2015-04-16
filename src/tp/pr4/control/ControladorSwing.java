package tp.pr4.control;

import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasComplica;
import tp.pr4.logica.ReglasConecta4;
import tp.pr4.logica.ReglasGravity;
import tp.pr4.logica.ReglasJuego;

public class ControladorSwing {

	//Constructor de la clase Controlador, con sus atributos.
	public ControladorSwing(FactoriaTipoJuego f, Partida p) {

		factoria = f;
		partida = p;
	}
	
	//Crea un movimiento a partir de los datos recibidos de la vista.
	public void ordenPoner(int columna, int fila) {

		try {

			Movimiento movimiento = factoria.creaMovimiento(columna, fila, partida.getTurno());
			partida.ejecutaMovimiento(movimiento);
			partida.isTerminada();
		} catch (MovimientoInvalido mov) {
			
		}
	}
	
	//Crea un movimiento aleatorio en función del juego que se está jugando.
	public void ordenAleatorio() {

		try {
			Jugador jugador = factoria.creaJugadorAleatorio();
			Movimiento movimiento = jugador.getMovimiento(partida.getTablero(), partida.getTurno());
			partida.ejecutaMovimiento(movimiento);
			partida.isTerminada();
		} catch (MovimientoInvalido e) {
			
		}
	}
	
	//Deshace el ultimo movimiento.
	public void ordenDeshacer() {

		partida.undo();
	}
	
	//Reinicia la partida.
	public void ordenReiniciar() {

		partida.reset(partida.getReglas());
	}
	
	//Cambia de juego en función del String pasado.
	public void ordenCambiar(String juego, int cols, int fils) throws NumberFormatException {

		ReglasJuego reglas;
		switch(juego) {
		case "conecta4":
			reglas = new ReglasConecta4();
			factoria = new FactoriaConecta4();
			partida.reset(reglas);
			break;
		case "complica":
			reglas = new ReglasComplica();
			factoria = new FactoriaComplica();
			partida.reset(reglas);
			break;
		case "gravity":
			reglas = new ReglasGravity(cols, fils);
			factoria = new FactoriaGravity(cols, fils);
			partida.reset(reglas);
			break;
		default: break;
		}
	}
	
	//Termina la aplicacón.
	public void ordenSalir() {

		System.exit(0);
	}
	
	private FactoriaTipoJuego factoria;
	private Partida partida;
}
