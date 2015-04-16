package tp.pr4;
import java.util.Scanner;

import tp.pr4.control.*;
import tp.pr4.logica.*;
import tp.pr4.vista.consola.VistaConsola;
import tp.pr4.vista.swing.VistaSwing;


public class Main {

	public static void main (String[] args) {
		
		Parametros parametros = new Parametros();
		
		//Se crea objeto Modo que recibirá los resultados del parser de Parametros.
		Parametros.Modo modo = null;
		
		try {

			modo = parametros.parser(args); //Se reciben los resultados del parser de Parametros.
			//Para la GUI
			if (modo.windowed()) {
				
				//Crea las reglas del juego.
				ReglasJuego reglas = modo.getFactoria().creaReglas();
				
				//Crea la partida.
				Partida partida = new Partida(reglas);
				
				//Lanza el controlador de la partida.
				ControladorSwing controlador = new ControladorSwing(modo.getFactoria(), partida);

				//Crea la vista
				VistaSwing vista = new VistaSwing(controlador);
				
				//Añade la vista como observadora de la partida
				partida.addObserver(vista);

				//Muestra por primera vez el tablero.
				partida.start();
			//Para la consola
			} else {
				
				//Habilita el scanner.
				Scanner in = new Scanner(System.in);
				
				//Crea las reglas del juego.
				ReglasJuego reglas = modo.getFactoria().creaReglas();
				
				//Crea la partida.
				Partida partida = new Partida(reglas);
				
				//Lanza el controlador de la partida.
				Controlador controlador = new Controlador(modo.getFactoria(), partida, in);

				//Crea la vista
				VistaConsola vista = new VistaConsola();

				//Añade la vista como observadora de la partida
				partida.addObserver(vista);
				
				//Muestra por primera vez el tablero.
				partida.start();
				
				//Inicia el juego.
				controlador.run();
				
				//Cierra el scanner.
				in.close();	
			}
		} catch (Exception e) {
			 //Si hay error en el parser, salida con valor 1.
			System.exit(1);
		}
	}
}
