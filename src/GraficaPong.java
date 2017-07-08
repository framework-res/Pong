import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class GraficaPong {

	private JFrame frame;

	/**
	 * Lancia applicazione
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraficaPong window = new GraficaPong();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Inizializza
	 */
	public GraficaPong() {
		initialize();
	}

	/**
	 * Inizializza contenuto base del frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/Immagine.png")));
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pong.main(null);
			}
		});
		btnNewButton.setRequestFocusEnabled(false);
		btnNewButton.setBounds(405, 241, 228, 48);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/immsfondo.jpg")));
		lblNewLabel.setBounds(0, -34, 1008, 763);
		frame.getContentPane().add(lblNewLabel);
		
		
		
	}
}
