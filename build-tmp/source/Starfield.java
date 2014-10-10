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

Particle[] mol = new Particle[257];
public void setup()
{
	noStroke();
	size(512, 512);
	background(0);
	mol[0] = new OddballParticle();
	for(int i = 1;i<mol.length;i++)
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
	if(key == CODED)
	{
		switch (keyCode)
		{
			case UP:
			{	
				for(int i = 1;i<mol.length;i++)
				{
					mol[i].setSize(-1);
				}
				break;
			}
			case DOWN:
			{	
				for(int i = 1;i<mol.length;i++)
				{
					mol[i].setSize(1);
				}
				break;
			}
			case LEFT:
			{	
				mol[0].setSize(-1);
				break;
			}
			case RIGHT:
			{	
				mol[0].setSize(1);
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
	int col;
	int sz;
	NormalParticle()
	{
		x = 256;
		y = 256;
		col = color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
		angle = Math.random()*2*PI;
		speed = Math.random()*7.5f+0.5f;
		sz = 4;
	}
	public void move()
	{
		x += speed*Math.cos(angle);
		y += speed*Math.sin(angle);
	}
	public void show()
	{
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
	public void setSize(int j)
	{
		sz+=j;
	}
}
interface Particle
{
	public void move();
	public void show();
	public void bounce();
	public void setSize(int j);
}
class OddballParticle implements Particle
{
	double x;
	double y;
	double angle;
	double speed;
	int col;
	int sz;
	OddballParticle()
	{
		x = 256;
		y = 256;
		col = color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
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
		fill(col);
		ellipse((int)(x), (int)(y), sz, sz);
	}
	public void bounce()
	{
		if(x >= 512 || x < 0)
		{
			angle = PI -angle;
		}
		if(y >= 512 || y < 0)
		{
			angle = -angle;
		}
	}
	public void setSize(int j)
	{
		sz+=j;
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
