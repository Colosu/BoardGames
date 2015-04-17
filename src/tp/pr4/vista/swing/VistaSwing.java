package tp.pr4.vista.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr4.control.ControladorSwing;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Partida.Observer;
import tp.pr4.logica.TableroInmutable;

public class VistaSwing extends JFrame implements Observer {

	//Crea el objeto de tipo VistaSwing para cotrolar la vista
	public VistaSwing(ControladorSwing control) {
		
		//Crea la ventana.
		super("Práctica 4 - TP");
		controlador = control;
		initGUI();
		setVisible(true);
	}
	
	//Inicia la ventana y añade todos sus componentes, además de prepararla para el primer juego.
	private void initGUI() {
		
		//Se inicializan todos los paneles y la ventana
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 2, 20, 10));
		JPanel pIzquierda = new JPanel(new BorderLayout());
		pDerecha = new JPanel(new BorderLayout());
		JPanel pPartida = new JPanel(new FlowLayout());
		JPanel pCambio = new JPanel(new BorderLayout());
		JPanel pCambioDeJuego = new JPanel(new BorderLayout(0, 10));
		pJuego = new JPanel(new BorderLayout(0, 50));
		pTablero = new JPanel(new GridLayout(1, 1));
		JPanel pDimensiones = new JPanel(new FlowLayout());
		JPanel pAleatorio = new JPanel(new FlowLayout());
		JPanel pSalir = new JPanel(new FlowLayout());
		JPanel pCambiar = new JPanel(new FlowLayout());

		//Se ponen bordes a ciertos paneles
		pJuego.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		pPartida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Partida", TitledBorder.LEFT,TitledBorder.TOP));
		pCambioDeJuego.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Cambio de Juego", TitledBorder.LEFT,TitledBorder.TOP));
		
		String opciones [] = {"conecta4", "complica", "gravity"};

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

		//Boton para realizar un movimiento aleatorio
		aleatorio = new JButton("Poner aleatorio");
		addBoton(aleatorio, pAleatorio, "res/iconos/random.png", new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				controlador.ordenAleatorio();
			}
		});
		
		//Boton para deshacer
		deshacer = new JButton("Deshacer");
		addBoton(deshacer, pPartida, "res/iconos/undo.png", new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {

				controlador.ordenDeshacer();
			}
		});

		//Boton para reiniciar
		reiniciar = new JButton("Reiniciar");
		addBoton(reiniciar, pPartida, "res/iconos/reiniciar.png", new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {

				controlador.ordenReiniciar();
			}
		});

		//Boton para cambiar de juego
		cambiar = new JButton("Cambiar");
		addBoton(cambiar, pCambiar, "res/iconos/aceptar.png", new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent event) {
				
				try {
					
					controlador.ordenCambiar(juegos.getSelectedItem().toString(), Integer.parseInt(columnas.getText()), Integer.parseInt(filas.getText()));
				} catch (NumberFormatException e) {

					JOptionPane alert = new JOptionPane("Columnas y/o filas no válidas.");
					alert.showMessageDialog(null, alert.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		//Boton para salir
		salir = new JButton("Salir");
		addBoton(salir, pSalir, "res/iconos/exit.png", new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent event) {
				
				JOptionPane popup = new JOptionPane("¿Quieres salir?");
				
				if (popup.showConfirmDialog(null, popup.getMessage(), "Atención", popup.YES_NO_OPTION) == 0) {

					controlador.ordenSalir();
				}
			}
		});
		
		//Texto que mostrará el turno y quien gana
		addTexto("Juegan Blancas", pJuego, BorderLayout.SOUTH);

		//Se añaden todos los componentes a sus respectivos paneles y a la ventana.
		pDimensiones.add(fils);
		pDimensiones.add(filas, BorderLayout.WEST);
		pDimensiones.add(cols);
		pDimensiones.add(columnas, BorderLayout.EAST);
		pCambioDeJuego.add(juegos, BorderLayout.NORTH);
		pCambioDeJuego.add(pDimensiones, BorderLayout.CENTER);
		pCambioDeJuego.add(pCambiar, BorderLayout.SOUTH);
		pCambio.add(pCambioDeJuego, BorderLayout.NORTH);
		pIzquierda.add(pPartida, BorderLayout.NORTH);
		pIzquierda.add(pCambio, BorderLayout.CENTER);
		pIzquierda.add(pSalir, BorderLayout.SOUTH);
		pJuego.add(pTablero, BorderLayout.CENTER);
		pDerecha.add(pAleatorio, BorderLayout.SOUTH);
		pDerecha.add(pJuego, BorderLayout.CENTER);
		this.add(pDerecha, BorderLayout.WEST);
		this.add(pIzquierda, BorderLayout.EAST);
	}
	
	//Añade un botón con una acción asociada.
	public void addBoton(JButton boton, JComponent componente, String icono, ActionListener accion) {
		
		boton.addActionListener(accion);
		boton.setIcon(new ImageIcon(icono));
		componente.add(boton);
	}
	
	//Crea y devuelve una celda.
	public Celda addCelda(JComponent componente, int posX, int posY) {
		
		Celda celda = new Celda(controlador, posX, posY);
		celda.addCelda(componente);
		return celda;
	}
	
	//Cambia el texto del parametro etiqueta.
	public void addTexto(String mensaje, JComponent componente, String pos) {
		
		etiqueta = new JLabel(mensaje, (int) java.awt.Component.CENTER_ALIGNMENT);
		componente.add(etiqueta, pos);
	}
	
	//Inicia un nuevo panel que contenga la representación del tablero.
	public void iniciaPTablero(int alto, int ancho) {
		
		pTablero.setLayout(new GridLayout(alto, ancho));
	}
	
	//Crea las celdas del nuevo tablero.
	public void iniciaCeldas(int alto, int ancho) {
		
		celdas = new Celda [ancho][alto];
		
		for (int i = 0; i < alto; i++) {
			
			for (int j = 0; j < ancho; j++) {
				
				celdas[j][i] = addCelda(pTablero, j+1, i+1);
			}
		}
	}
	
	//Se ejecuta al finalizar un movimiento para actualizar la ventana.
	@Override
	public void onMovimientoEnd(TableroInmutable estadoTablero, Ficha turno, Ficha siguiente) {

		for (int i = 0; i < estadoTablero.getAncho(); i++) {
			for (int j = 0; j < estadoTablero.getAlto(); j++) {
				
				if (estadoTablero.getCasilla(i+1, j+1) != celdas[i][j].getColor()) {
					
					celdas[i][j].setColor(estadoTablero.getCasilla(i+1, j+1));
				}
			}
		}
		
		switch (siguiente) {
		
		case BLANCA: etiqueta.setText("Juegan blancas"); break;
		case NEGRA: etiqueta.setText("Juegan negras"); break;
		default: break;
		}

		deshacer.setEnabled(true);
	}

	//Se ejecuta al realizar un movimiento no valido, para mostrar la causa del error.
	@SuppressWarnings("static-access")
	@Override
	public void onMovimientoIncorrecto(String explicacion, TableroInmutable estadoTablero, Ficha turno) {

		JOptionPane alert = new JOptionPane(explicacion);
		alert.showMessageDialog(null, alert.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
	}

	//Se llama al iniciar el movimiento.
	@Override
	public void onMovimientoStart(Ficha turno) {
		
	}

	//Se llama al reiniciarse la partida o al cambiar de juego y avisa de que la partida ha sido reiniciada.
	//Además, llama a onSet() para actualizar la ventana.
	@SuppressWarnings("static-access")
	@Override
	public void onReset(TableroInmutable estadoInicial, Ficha turno) {
		
		initState(estadoInicial, turno);
		JOptionPane alert = new JOptionPane("Partida reiniciada.");
		alert.showMessageDialog(null, alert.getMessage(), "Atención", JOptionPane.PLAIN_MESSAGE);
	}

	//Se llama al iniciar la aplicación o al reiniciar la partida o al cambiar de juego para actualizar la ventana.
	@Override
	public void initState(TableroInmutable estadoInicial, Ficha turno) {
		
		pTablero.removeAll();
		iniciaPTablero(estadoInicial.getAlto(), estadoInicial.getAncho());
		iniciaCeldas(estadoInicial.getAlto(), estadoInicial.getAncho());

		pTablero.validate();
		pTablero.repaint();
		
		pJuego.add(pTablero, BorderLayout.CENTER);
		
		aleatorio.setEnabled(true);
		cambiar.setEnabled(true);
		juegos.setEnabled(true);
		columnas.setEnabled(true);
		filas.setEnabled(true);
		deshacer.setEnabled(false);

		switch (turno) {
		
		case BLANCA: etiqueta.setText("Juegan blancas"); break;
		case NEGRA: etiqueta.setText("Juegan negras"); break;
		default: break;
		}
	}

	//Se llama al deshacer un movimiento para actualizar la ventana.
	@Override
	public void onUndo(TableroInmutable estadoTablero, Ficha turno, boolean hayMas) {

		for (int i = 0; i < estadoTablero.getAncho(); i++) {
			for (int j = 0; j < estadoTablero.getAlto(); j++) {
				
				if (estadoTablero.getCasilla(i+1, j+1) != celdas[i][j].getColor()) {
					
					celdas[i][j].setColor(estadoTablero.getCasilla(i+1, j+1));
				}
			}
		}
		
		if (hayMas) {
			
			deshacer.setEnabled(true);
		} else {

			deshacer.setEnabled(false);
		}

		switch (turno) {
		
		case BLANCA: etiqueta.setText("Juegan blancas"); break;
		case NEGRA: etiqueta.setText("Juegan negras"); break;
		default: break;
		}
	}

	//Se llama cuando no se pueden deshacer más movimientos y avisa de que no se pueden deshacer movimientos.
	@SuppressWarnings("static-access")
	@Override
	public void onUndoNotPossible() {

		JOptionPane alert = new JOptionPane("Imposible deshacer.");
		alert.showMessageDialog(null, alert.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
	}

	//Se llama cuando acaba la partida, actualiza la vista y muestra quien ha ganado.
	@Override
	public void partidaTerminada(TableroInmutable tableroFinal, Ficha ganador) {

		for (int i = 0; i < tableroFinal.getAncho(); i++) {
			for (int j = 0; j < tableroFinal.getAlto(); j++) {
				
				if (tableroFinal.getCasilla(i+1, j+1) != celdas[i][j].getColor()) {
					
					celdas[i][j].setColor(tableroFinal.getCasilla(i+1, j+1));
				}
			}
		}
		
		switch (ganador) {
		
		case BLANCA: etiqueta.setText("Ganan las blancas"); break;
		case NEGRA: etiqueta.setText("Ganan las negras"); break;
		default: etiqueta.setText("Partida terminada en tablas."); break;
		}
		
		aleatorio.setEnabled(false);
		deshacer.setEnabled(false);
		cambiar.setEnabled(false);
		juegos.setEnabled(false);
		columnas.setEnabled(false);
		filas.setEnabled(false);
	}

	
	private JLabel etiqueta;
	private Celda[][] celdas;
	private JPanel pTablero;
	private JPanel pJuego;
	private JPanel pDerecha;
	private JButton aleatorio;
	private JButton deshacer;
	private JButton reiniciar;
	private JButton cambiar;
	private JButton salir;
	private JComboBox<String> juegos;
	private JTextField columnas;
	private JTextField filas;
	private ControladorSwing controlador;
	private static final long serialVersionUID = 6901761972003151399L;
}
