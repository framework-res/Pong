
	import java.awt.Color;
	import java.awt.Graphics;
import java.util.Random;


	public class Palla 
	{

		public int x, y, larghezza = 30, altezza = 30;

		public int movO, movV;

		public Random random;

		private Pong pong;
		

		public int colpi;
	
		public Palla(Pong pong)
		{
			this.pong = pong;

			this.random = new Random();

			diNuovo();
		}

		public void movimento(Giocatore g1, Giocatore g2, int velocità)
		{
			this.x += movO * velocità;
			this.y += movV * velocità;

			
			//muro
			if (this.y + altezza - movV > pong.altezza || this.y + movV < 0)
			{
				if (this.movV < 0)
				{
					this.y = 0;
					this.movV = random.nextInt(4);//movimento random sopra o sotto

					if (movV == 0)
					{
						movV = 1;
					}
				}
				else
				{
					this.movV = -random.nextInt(4);
					this.y = pong.altezza - altezza;

					if (movV == 0)
					{
						movV = -1;
					}
				}
			}
			//Tocca il giocatore1
			if (Tocco(g1) == 1)
			{
				this.movO = 1 + (colpi/2);
				this.movV = -2 + random.nextInt(4);

				if (movV == 0)
				{
					movV = 1;
				}

				colpi++;
			}
			//Tocca il giocatore2
			else if (Tocco(g2) == 1)
			{
				this.movO = -1 - (colpi/2);
				this.movV = -2 + random.nextInt(4);

				if (movV == 0)
				{
					movV = 1;
				}

				colpi++;
			}
			//Controlla se è punto
			if (Tocco(g1) == 2)
			{
				g2.punteggio++;
				diNuovo();
			}
			else if (Tocco(g2) == 2)
			{
				g1.punteggio++;
				diNuovo();
			}
		}
		//palla al centro
		public void diNuovo()
		{
			this.colpi = 0;
			this.x = pong.larghezza / 2 - this.larghezza / 2;
			this.y = pong.altezza / 2 - this.altezza / 2;

			this.movV = -2 + random.nextInt(4);

			if (movV == 0)
			{
				movV = 1;
			}

			if (random.nextBoolean())
			{
				movO = 1;
			}
			else
			{
				movO = -1;
			}
		}

		public int Tocco(Giocatore g)
		{
			// se la posizione è minore di quella del giocatore , la posizione più la larghezza 
			//è maggiore di quella del giocatore e l' altezza è compresa tra le dimensioni del giocatore allora lo tocca
			if (this.x <= g.x + g.larghezza && this.x + larghezza >= g.x && this.y < g.y + g.altezza && this.y + altezza > g.y)
			{
				return 1; //rimbalzo
			}
			else if ((g.x > x && g.numeroGiocatore == 1) || (g.x < x - larghezza && g.numeroGiocatore == 2))
			{
				return 2; //punteggio
			}

			return 0; //niente
		}

		public void grafica(Graphics g)
		{
			g.setColor(Color.ORANGE);
			g.fillOval(x, y, larghezza, altezza);
		}		
		
		

	}


