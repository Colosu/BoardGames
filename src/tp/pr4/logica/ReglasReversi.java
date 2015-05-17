package tp.pr4.logica;

import tp.pr4.control.UtilsOthelo;

public class ReglasReversi implements ReglasJuego {

	public Tablero iniciaTablero() {

		Tablero tablero = new Tablero(8, 8);
		
		tablero.setCasilla(4, 4, Ficha.BLANCA);
		tablero.setCasilla(4, 5, Ficha.NEGRA);
		tablero.setCasilla(5, 4, Ficha.NEGRA);
		tablero.setCasilla(5, 5, Ficha.BLANCA);
		
		return tablero;
	}

	public Ficha jugadorInicial() {

		return Ficha.NEGRA;
	}

	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero tablero) {

		int blancas = 0;
		int negras = 0;
		
		int i = 1;
		boolean vacia = false;
		while (!vacia && i <= tablero.getAncho()) {
			int j = 1;
			while (!vacia && j <= tablero.getAlto()) {
				
				if(tablero.getCasilla(i, j).equals(Ficha.BLANCA)) {
					
					blancas++;
				} else if (tablero.getCasilla(i, j).equals(Ficha.NEGRA)) {
					
					negras++;
				} else {
					
					vacia = true;
				}
				j++;
			}
			i++;
		}
		
		if (vacia || negras == blancas) {
			
			return Ficha.VACIA;
		} else if (negras > blancas) {
			
			return Ficha.NEGRA;
		} else {
			
			return Ficha.BLANCA;
		}
	}

	public boolean tablas(Ficha ultimoEnPoner, Tablero tablero) {

		int blancas = 0;
		int negras = 0;
		
		int i = 1;
		boolean vacia = false;
		while (!vacia && i <= tablero.getAncho()) {
			int j = 1;
			while (!vacia && j <= tablero.getAlto()) {
				
				if(tablero.getCasilla(i, j).equals(Ficha.BLANCA)) {
					
					blancas++;
				} else if (tablero.getCasilla(i, j).equals(Ficha.NEGRA)) {
					
					negras++;
				} else {
					
					vacia = true;
				}
				j++;
			}
			i++;
		}
		
		if (!vacia && negras == blancas) {
			
			return true;
		} else {
			
			return false;
		}
	}

	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero tablero) {
		
		switch (ultimoEnPoner) {
		
		case BLANCA: ultimoEnPoner = Ficha.NEGRA; break;
		case NEGRA: ultimoEnPoner = Ficha.BLANCA; break;
		default: ultimoEnPoner = Ficha.VACIA; break;
		}
		
		int i = 1;
		boolean puedePoner = false;
		while (!puedePoner && i <= tablero.getAncho()) {
			int j = 1;
			while (!puedePoner && j <= tablero.getAlto()) {
				
				puedePoner = UtilsOthelo.puedePoner(tablero, i, j, ultimoEnPoner);
				
				j++;
			}
			i++;
		}
		
		if(!puedePoner) {

			switch (ultimoEnPoner) {
			
			case BLANCA: ultimoEnPoner = Ficha.NEGRA; break;
			case NEGRA: ultimoEnPoner = Ficha.BLANCA; break;
			default: ultimoEnPoner = Ficha.VACIA; break;
			}
		}
		return ultimoEnPoner;
	}

	public TipoJuego getJuego() {
		
		return TipoJuego.Reversi;
	}
}
