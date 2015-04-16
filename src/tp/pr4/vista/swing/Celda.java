package tp.pr4.vista.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import tp.pr4.control.*;
import tp.pr4.logica.*;

//Clase auxiliar para implementar la vista Swing facilitando el trabajo con las celdas del tablero.
public class Celda {

	//Crea un objeto de tipo Celda.
	public Celda(ControladorSwing controlador, int posX, int posY) {
		

		celda = new JButton();
		celda.setBackground(Color.green);
		color = Ficha.VACIA;
		columna = posX;
		fila = posY;
		addAction(controlador);
	}
	
	//Añade una acción al boton de la celda.
	public void addAction(ControladorSwing controlador) {
		
		celda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				controlador.ordenPoner(columna, fila);
			}});
	}
	
	//Añade la celda correspondiente al componente pasado por parámetro.
	public void addCelda(JComponent componente) {
		
		componente.add(celda);
	}
	
	//Añade la celda correspondiente al componente pasado por parámetro en la posición indicada.
	public void addCelda(JComponent componente, String pos) {
		
		componente.add(celda, pos);
	}
	
	//Cambia el color de la celda.
	public void setColor(Ficha colr) {

		switch (colr) {
		case BLANCA: color = Ficha.BLANCA; celda.setBackground(Color.white); break;
		case NEGRA: color = Ficha.NEGRA; celda.setBackground(Color.black); break;
		default: color = Ficha.VACIA; celda.setBackground(Color.green); break;
		}
	}
	
	//Devuelve la columna de la celda.
	public int getCol(){
		
		return columna;
	}
	
	//Devuelve la fila de la celda.
	public int getFil() {
		
		return fila;
	}
	
	//Devuelve el color de la celda.
	public Ficha getColor() {
		
		return color;
	}
	
	private int columna;
	private int fila;
	private JButton celda;
	private Ficha color;
}
