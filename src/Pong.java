
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pong extends JPanel implements ActionListener, KeyListener, Runnable
{

	private static final long serialVersionUID = 1L;

	public static Pong pong;

	public int larghezza = 1366, altezza = 768;

	public Grafica grafica;
	
	JButton button = new JButton("Click");

	public Giocatore giocatore1;

	public Giocatore giocatore2;

	public static Palla palla;

	public boolean w, s, up, down;

	public int stato = 0, limite = 5, vittoria;//0 = Menu, 1 = Pausa, 2 = In gioco, 3 = Fine

	public Random random;

	public JFrame jframe;
	
	public int difficoltà; // 0 = facile; 1 = medio ; 2 = difficile
	
	public String nome;
	
	public String Nome(int difficoltà){
		
		String a = null;
		if(difficoltà == 0){
			a = "Principiante";
		}
		else if(difficoltà == 1)
		{
			a = "Amatoriale";
		}
		else if(difficoltà == 2)
		{
			a ="Esperto";
		}
		return a;
	}

	public Pong()
	{
		
		Timer timer = new Timer(15, this);//riceve un intero in millisecondi ed un ActionListener
		random = new Random();

		jframe = new JFrame("Pong");

		grafica = new Grafica();

		jframe.setSize(larghezza + 15, altezza + 35);
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(grafica);
		jframe.addKeyListener(this);
		timer.start();
		

	}

	public void start()
	{
		stato = 2;
		giocatore1 = new Giocatore(this, 1);
		giocatore2 = new Giocatore(this, 2);
		palla = new Palla(this);
	}

	public void update()
	{
		if (giocatore1.punteggio >= limite)
		{
			vittoria = 1;
			stato = 3;
		}

		if (giocatore2.punteggio >= limite)
		{
			stato = 3;
			vittoria = 2;
		}

		if (w)
		{
			giocatore1.muove(true);
		}
		if (s)
		{
			giocatore1.muove(false);
		}

		else if (up)
			{
				giocatore2.muove(true);
			}
		else if (down)
			{
				giocatore2.muove(false);
			}
		
		

		palla.movimento(giocatore1, giocatore2,(difficoltà+1)*3);
	}

	public void render(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, larghezza, altezza);
		

		if (stato == 0)
		{
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Comic Sans", 1, 50));
			g.drawString("PONG MULTIPLAYER", larghezza / 2 - 250, 50);
			g.setFont(new Font("Comic Sans", 5, 30));
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("Premi G per iniziare a giocare", larghezza / 2 - 175, altezza / 2 - 200);
			g.drawString(" Punti per Vincere: " + limite , larghezza / 2 - 90, altezza / 2 -100);
			g.drawString("Difficoltà:" + Nome(difficoltà), larghezza / 2 - 100, altezza / 2 );
			g.setFont(new Font("Comic Sans", 1, 20));
			g.setColor(Color.RED);
			g.drawString("Comandi:", larghezza / 2 + 250, altezza/2 +150);
			g.drawString("Premi P per andare in pausa",larghezza / 2 + 160 , altezza/2 +200);
			g.drawString("Ripremi P per tornare al gioco",larghezza / 2 + 160 , altezza/2 +230);
			g.drawString("Premi ESC per tornare qui", larghezza/2 + 160, altezza/2+260);
			g.drawString("Premi LEFT e RIGHT per cambiare il limite", larghezza/2 + 160, altezza/2+290);
			g.drawString("Premi D e A per cambiare la difficoltà", larghezza/2 + 160, altezza/2+320);
			
		}


		if (stato == 1)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", 1, 200));
			g.drawString("PAUSA", larghezza / 4 , altezza / 2 +50);
		}

		if (stato == 1 || stato == 2)
		{
			g.setColor(Color.WHITE);
			g.drawLine(larghezza / 2, 0, larghezza / 2, altezza);
			g.setFont(new Font("Arial", 1, 50));
			g.drawString(String.valueOf(giocatore1.punteggio), larghezza / 2 - 65, 50);
			g.drawString(String.valueOf(giocatore2.punteggio), larghezza / 2 + 35, 50);

			giocatore1.grafica(g);
			giocatore2.grafica(g);
			palla.grafica(g);
		}

		if (stato == 3)
		{
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", 1, 50));
		

			g.drawString("PONG MULTIPLAYER", larghezza / 2 - 250, 50);

			
			g.drawString(" IL GIOCATORE " + vittoria + " HA VINTO!", larghezza / 2 - 350, 200);
			

			g.setFont(new Font("Arial", 1, 30));

			g.drawString("Premi G per rigiocare", larghezza / 2 - 250, altezza / 2 - 25);
			g.drawString("Premi ESC per tornare al menù", larghezza / 2 - 230, altezza / 2 + 25);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (stato == 2)
		{
			update();
		}

		grafica.repaint();
	}

	public static void main(String[] args)
	{
		pong = new Pong();
		//create and start threads.
		Thread ball = new Thread(pong);
		ball.start();
		Thread p1 = new Thread(pong);
		Thread p2 = new Thread(pong);
		p2.start();
		p1.start();
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int id = e.getKeyCode();

		if (id == KeyEvent.VK_W)
		{
			w = true;
		}
		else if (id == KeyEvent.VK_S)
		{
			s = true;
		}
		else if (id == KeyEvent.VK_UP)
		{
			up = true;
		}
		else if (id == KeyEvent.VK_DOWN)
		{
			down = true;
		}
		else if( id == KeyEvent.VK_RIGHT && stato == 0 && limite > 0){
			limite++;
		}
		else if (id == KeyEvent.VK_LEFT && stato == 0 && limite > 1)
		{
			limite--;
		}
		else if (id == KeyEvent.VK_ESCAPE && (stato == 2 || stato == 3))
		{
			stato = 0;
		}
		else if (id == KeyEvent.VK_G && stato==0)
		{
			start();
		}
		else if (id == KeyEvent.VK_P && stato == 1)
		{
			stato = 2;
		}
		else if (id == KeyEvent.VK_P &&stato == 2)
		{
			stato = 1;
		}
		else if ( id == KeyEvent.VK_D && stato == 0 && difficoltà >=0 && difficoltà <2 )
		{
			difficoltà ++;
		}
		else if ( id == KeyEvent.VK_A && stato == 0 && difficoltà >0 && difficoltà <=2 )
		{
			difficoltà --;
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e)
	{
		int id = e.getKeyCode();

		if (id == KeyEvent.VK_W)
		{
			w = false;
		}
		else if (id == KeyEvent.VK_S)
		{
			s = false;
		}
		else if (id == KeyEvent.VK_UP)
		{
			up = false;
		}
		else if (id == KeyEvent.VK_DOWN)
		{
			down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void run() {
		
		
	}
	
}
