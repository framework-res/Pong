import java.awt.Color;
import java.awt.Graphics;

public class Giocatore
{

	public int numeroGiocatore;

	public int x, y, larghezza = 20, altezza = 180;

	public int punteggio;

	public Giocatore(Pong pong, int numeroGiocatore)
	{
		this.numeroGiocatore = numeroGiocatore;

		if (numeroGiocatore == 1)
		{
			this.x = 0;
		}

		if (numeroGiocatore == 2)
		{
			this.x = pong.larghezza - larghezza;
		}

		this.y = pong.altezza / 2 - this.altezza / 2;//Viene posto il giocatore a met�
	}

	public void grafica(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(x, y, larghezza, altezza);
	}

	public void muove(boolean up)
	{
		int velocit� = 20;

		if (up)
		{
			if (y - velocit� > 0)
			{
				y -= velocit�;//movimento verso sopra
			}
			else
			{
				y = 0;//� arrivato al limite
			}
		}
		else
		{
			if (y + altezza + velocit� < Pong.pong.altezza)
			{
				y += velocit�;
			}
			else
			{
				y = Pong.pong.altezza - altezza;
			}
		}
	}

}