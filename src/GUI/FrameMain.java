package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Logica.TableroAux;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FrameMain {

	private JFrame framePrincipalDelJuego;
	private TableroAux tablero;
	private JPanel contenedorBotones;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMain window = new FrameMain();
					window.framePrincipalDelJuego.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 *
	 *
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public FrameMain() throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		initialize();
	}

	@SuppressWarnings("unchecked")
	private void initialize() {

		framePrincipalDelJuego = new JFrame();
		contenedorBotones = new JPanel();
		contenedorBotones.setForeground(new Color(128, 0, 128));
		contenedorBotones.setBorder(new EmptyBorder(0, 8, 0, 0));

		framePrincipalDelJuego.setBounds(100, 100, 929, 572);
		framePrincipalDelJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// DROPDOWN
		/* btn reset opciones de tablero */
		// Variable para almacenar el item seleccionado

		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(119, 33, 284, 22);
		// evento que captura que item se selecciono y actualiza el tablero de pantalla
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String itemSeleccionado = comboBox.getSelectedItem().toString();
				if (esNumero(itemSeleccionado)) {
					Integer indice = casteoANum(itemSeleccionado);
					contenedorBotones.removeAll();
					tablero = null;
					tablero = new TableroAux(indice);
					dubujarTablero(tablero);
					comboBox.setVisible(false);
					contenedorBotones.repaint();

				}

			}

			private Integer casteoANum(String itemSeleccionado) {
				return Integer.parseInt(itemSeleccionado.charAt(0) + "");
			}

			private boolean esNumero(String itemSeleccionado) {
				return itemSeleccionado.charAt(0) > 50 && itemSeleccionado.charAt(0) < 54;
			}

			private void dubujarTablero(TableroAux tablero) {
				/*
				 * recorrer el tablero creaarBoton()
				 */
				int btndimension = 50;
				int espacioFila = 40;
				int espacioCol = 20;

				for (int fila = 0; fila < tablero.tamanio(); fila++) {
					for (int col = 0; col < tablero.tamanio(); col++) {
						crearBoton(fila, col, btndimension, espacioCol, espacioFila);
						espacioFila += btndimension;

					}
					espacioFila = 40;
					espacioCol += btndimension;

				}
			}

			private void crearBoton(int fila, int col, int btnAncho, int espacioCol, int espacioFila) {
				JButton btn = new JButton("Button ");
				btn.setBounds(espacioFila, espacioCol, btnAncho, btnAncho);
				btn.setOpaque(true);
				btn.setBackground(tablero.celdaEstaEncendida(fila, col) ? Color.GREEN : Color.red);
				contenedorBotones.add(btn);
			}
		});

		framePrincipalDelJuego.getContentPane().setLayout(null);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setToolTipText("Seleccionar el tamanio de la cuadricula");
		comboBox.setName("dropdown");
		comboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Seleccione tamanio de la cuadricula", "3x3", "4x4", "5x5" }));
		comboBox.setSelectedIndex(0);
		framePrincipalDelJuego.getContentPane().add(comboBox);

		// Panel de informacion del juego
		JPanel panelInformacionJuego = new JPanel();
		panelInformacionJuego.setBounds(557, 102, 333, 383);
		panelInformacionJuego.setBackground(new Color(128, 255, 255));
		framePrincipalDelJuego.getContentPane().add(panelInformacionJuego);
		panelInformacionJuego.setLayout(null);

		// Label CLICKS
		JLabel labelClicks = new JLabel("Clicks:");
		labelClicks.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		labelClicks.setBounds(21, 29, 54, 28);
		panelInformacionJuego.add(labelClicks);

		// Label PUNTAJE
		JLabel labelPuntaje = new JLabel("Puntaje:");
		labelPuntaje.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelPuntaje.setBounds(21, 68, 64, 35);
		panelInformacionJuego.add(labelPuntaje);

		// PUNTAJE
		JLabel clickText = new JLabel("0");
		clickText.setFont(new Font("Tahoma", Font.BOLD, 18));
		clickText.setBounds(97, 33, 20, 14);
		panelInformacionJuego.add(clickText);

		// CLICKS
		JLabel puntajeText = new JLabel("0");
		puntajeText.setFont(new Font("Tahoma", Font.BOLD, 18));
		puntajeText.setBounds(95, 79, 20, 14);
		panelInformacionJuego.add(puntajeText);

		/* metodos generales */

		// contenedor de botones
		contenedorBotones.setBounds(89, 102, 406, 361);
		framePrincipalDelJuego.getContentPane().add(contenedorBotones);
		contenedorBotones.setBackground(Color.red);
		contenedorBotones.setLayout(null);
		JButton btnResetComboBox = new JButton("Cambiar tablero");
		btnResetComboBox.setBounds(173, 473, 255, 35);
		framePrincipalDelJuego.getContentPane().add(btnResetComboBox);
		btnResetComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setVisible(true);
			}
		});

	}

	public Point posicionBtn(Component[] components, JButton btn, int tamanioMatriz) {
		int pos = obtonerPosicion(components, btn);
		return calcularCoor(pos, tamanioMatriz);

	}

	private int obtonerPosicion(Component[] components,JButton btn) {
		int pos = 0;
		for (int posComp = 0; posComp < components.length; posComp++) {

			if (btn.equals(components[posComp])) {
				pos = posComp + 1;
			}
		}
		return pos;
	}

	private Point calcularCoor(int pos, int tamanioMatriz) {
		/* caso base que la pos del elemento sea de la primera row */
		if (pos <= tamanioMatriz) {
			return new Point(0, pos - 1);
		} else {
			/* caso general */
			if (pos % tamanioMatriz != 0) {
				int dividendo = pos / tamanioMatriz;
				int resto = pos % tamanioMatriz - 1;
				return new Point(dividendo, resto);
			}
			/* caso borde donde a|b */
			return new Point(pos / tamanioMatriz - 1, pos / tamanioMatriz - 1);
		}
	}
}
