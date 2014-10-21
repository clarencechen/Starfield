import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Starfield extends PApplet {

Particle[] mol = new Particle[258];
public void setup()
{
	noStroke();
	size(512, 512);
	background(0);
	mol[0] = new OddballParticle();
	mol[1] = new JumboParticle();
	for(int i = 2;i<mol.length;i++)
	{
		mol[i] = new NormalParticle();
	}
}
public void draw()
{
	fill(0);
	rect(0,0,512,512);
	for(int i = 0;i<mol.length;i++)
	{
		mol[i].move();
		mol[i].show();
		mol[i].bounce();
	}
}
public void keyPressed()
{
	switch (key)
	{
		case 'a' :
		{
			((OddballParticle)(mol[0])).setColor(51,0,0);
			break;
		}
		case 's' :
		{
			((OddballParticle)(mol[0])).setColor(0,51,0);
			break;
		}
		case 'd' :
		{
			((OddballParticle)(mol[0])).setColor(0,0,51);
			break;
		}
		case 'z' :
		{
			((OddballParticle)(mol[0])).setColor(-51,0,0);
			break;
		}
		case 'x' :
		{
			((OddballParticle)(mol[0])).setColor(0,-51,0);
			break;
		}
		case 'c' :
		{
			((OddballParticle)(mol[0])).setColor(0,0,-51);
			break;
		}
	}
	if(key == CODED)
	{
		switch (keyCode)
		{
			case UP:
			{	
				((JumboParticle)(mol[1])).setSize(0,-1);
				break;
			}
			case DOWN:
			{	
				((JumboParticle)(mol[1])).setSize(0,1);
				break;
			}
			case LEFT:
			{	
				((JumboParticle)(mol[1])).setSize(-1,0);
				break;
			}
			case RIGHT:
			{	
				((JumboParticle)(mol[1])).setSize(1,0);
				break;
			}
		}
	}
}
class NormalParticle implements Particle
{
	double x;
	double y;
	double angle;
	double speed;
	private int col;
	private int sz;
	NormalParticle()
	{
		x = 256;
		y = 256;
		col = color((int)(Math.random()*6)*51,(int)(Math.random()*6)*51,(int)(Math.random()*6)*51);
		angle = Math.random()*2*PI;
		speed = Math.random()*7.5f+0.5f;
		sz = 2;
	}
	public void move()
	{
		x += speed*Math.cos(angle);
		y += speed*Math.sin(angle);
	}
	public void show()
	{
		noStroke();
		fill(col);
		ellipse((int)(x), (int)(y), sz, sz);
	}
	public void bounce()
	{
		if(x >= 512 || x < 0 || y >= 512 || y < 0)
		{
			x = 256;
			y = 256;
		}
	}
}
interface Particle
{
	public void move();
	public void show();
	public void bounce();
}
class OddballParticle implements Particle
{
	double x;
	double y;
	double angle;
	double speed;
	protected int col;
	protected int sz;
	OddballParticle()
	{
		x = 256;
		y = 256;
		col = color((int)(Math.random()*6)*51,(int)(Math.random()*6)*51,(int)(Math.random()*6)*51);
		angle = Math.random()*2*PI;
		speed = 4;
		sz = 16;
	}
	public void move()
	{
		x += speed*Math.cos(angle);
		y += speed*Math.sin(angle);
	}
	public void show()
	{
		stroke(255);
		fill(col);
		ellipse((int)(x), (int)(y), sz, sz);
	}
	public void bounce()
	{
		if(x >= 512 -sz/2 || x < 0 +sz/2)
		{
			angle = PI -angle;
		}
		if(y >= 512 -sz/2 || y < 0 +sz/2)
		{
			angle = -angle;
		}
	}
	public void setColor(int a, int b, int c)
	{
		col = color(red(col) +a,green(col) +b,blue(col) +c);
	}
}
class JumboParticle extends OddballParticle
{
	protected int szx;
	protected int szy;
	JumboParticle()
	{
		x = 256;
		y = 256;
		col = color((int)(Math.random()*6)*51,(int)(Math.random()*6)*51,(int)(Math.random()*6)*51);
		angle = Math.random()*2*PI;
		speed = 4;
		sz = 0;
		szx = 32;
		szy = 32;
	}
	public void show()
	{
		noStroke();
		fill(col);
		ellipse((int)(x), (int)(y), szx, szy);
	}
	public void bounce()
	{
		if(x >= 512 -szx/2 || x < 0 +szx/2)
		{
			angle = PI -angle;
		}
		if(y >= 512 -szy/2 || y < 0 +szy/2)
		{
			angle = -angle;
		}
	}
	public void setSize(int j, int k)
	{
		szx += 2*j;
		szy += 2*k;
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
