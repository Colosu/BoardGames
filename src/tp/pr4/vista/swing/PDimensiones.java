package tp.pr4.vista.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr4.control.ControladorSwing;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.Partida.Observer;

public class PDimensiones extends JPanel implements Observer {
	
	public PDimensiones(ControladorSwing controlador) {

		setLayout(new BorderLayout());
		
		JPanel pDimensiones = new JPanel(new FlowLayout());

		//Cajas de texto para elegir las filas y columnas de la partida de gravity
		JLabel fils = new JLabel("Filas");
		JLabel cols = new JLabel("Columnas");
		filas = new JTextField(5);
		columnas = new JTextField(5);
		fils.setVisible(false);
		filas.setVisible(false);
		cols.setVisible(false);
		columnas.setVisible(false);
		
		filas.setText("0");
		columnas.setText("0");
		
		//Desplegable para elegir el juego.
		String opciones [] = {"conecta4", "complica", "gravity", "reversi"};
		
		juegos = new JComboBox<String>(opciones);
		juegos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				if (juegos.getSelectedItem().toString().equals("gravity")) {

					fils.setVisible(true);
					filas.setVisible(true);
					cols.setVisible(true);
					columnas.setVisible(true);
				} else {

					fils.setVisible(false);
					filas.setVisible(false);
					cols.setVisible(false);
					columnas.setVisible(false);
				}
				juegos.updateUI();
			}
		});
		
		pDimensiones.add(fils);
		pDimensiones.add(filas, BorderLayout.WEST);
		pDimensiones.add(cols);
		pDimensiones.add(columnas, BorderLayout.EAST);
		add(juegos, BorderLayout.NORTH);
		add(pDimensiones, BorderLayout.SOUTH);
	}
	
	public String getJuego() {
		
		return juegos.getSelectedItem().toString();
	}
	
	public int getColumnas() {
		
		return Integer.parseInt(columnas.getText());
	}
	
	public int getFilas() {
		
		return Integer.parseInt(filas.getText());
	}

	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {

	}

	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {

	}

	public void onMovimientoStart(Ficha turno) {

	}

	public void onReset(TableroInmutable estadoInicial, Ficha turno) {

		juegos.setEnabled(true);
		columnas.setEnabled(true);
		filas.setEnabled(true);
	}

	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {

	}

	public void onUndoNotPossible() {

	}

	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {

		juegos.setEnabled(false);
		columnas.setEnabled(false);
		filas.setEnabled(false);
	}

	private JComboBox<String> juegos;
	private JTextField columnas;
	private JTextField filas;
	private static final long serialVersionUID = 2066504212642035954L;
}
