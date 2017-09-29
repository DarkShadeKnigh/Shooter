//add separate draws for each
//main
//for Y map[].lenght for X
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Shooter extends JFrame implements  Runnable, KeyListener
{
  //xf=yi
  //yf=(max-1)-xi absolute
  Thread th=new Thread(this);
  int mapX=100;
  int mapY=100;
  int [][] map = new int [mapY][mapX];
  int screenX = 0;
  int screenY = 0;
  int screenXR =0;
  int screenYR =0;
  int xOfset =-240;
  int yOfset =-240;
  int [] ammo = new int[3];
  BufferedImage wall01;
  BufferedImage floor01;
  BufferedImage player01;
  private Image dbImage;
  private Graphics dbg;
  Player fish = new Player();
  MyDrawPanel draw1=new MyDrawPanel();
  public static void main(String[]args)
  {
    new Shooter();
  }
  public Shooter ()
  {
    
    setMap();
    
    addKeyListener(this);
    this.setLayout(new GridLayout(1,1));
    this.add(draw1);
    this.setSize(800,800);
    repaint();
    initialize();
    this.setVisible(true);
  }
  
  class MyDrawPanel extends JPanel
  {
    public void paintComponent(Graphics g) {
      
      Graphics2D g2 = (Graphics2D)g;
      screenXR=0;
      screenYR=0;
      for (int i=screenY, screenYR=0;i<screenY+800;i+=16,screenYR+=16)
      {
        for (int a=screenX, screenXR=0;a<screenX+800;a+=16,screenXR+=16)
        {
          if (map[i/16][a/16]==0)
          {
            g.setColor(Color.GREEN);
            //g.fillRect(screenXR,screenYR,10,10); 
            g.drawImage(floor01,screenXR,screenYR,null);
          }
          if (map[i/16][a/16]==1)
          {
            g.setColor(Color.RED);
            //g.fillRect(screenXR,screenYR,10,10);
            g.drawImage(wall01,screenXR,screenYR,null);
          }
          if (map[i/16][a/16]==3)
          {
            g.setColor(Color.BLUE);
            //g.fillRect(screenXR,screenYR,16,16);
            g.drawImage(player01,screenXR,screenYR,screenXR+16,screenYR+16,0,0,16,16,this);
          }
          
        }
      }
    }
  }
  
  public void update(Graphics g)
  {
    System.out.println("HI");
//    
//    dbImage = createImage(400,300);
//    dbg=dbImage.getGraphics();
//    if (dbg==null)
//    {
//      dbg.setColor(getBackground());
//      dbg.fillRect(0,0,400,300);
//    }
//    dbg.setColor(getForeground());
//    paint(dbg);
//    
//    g.drawImage(dbImage,0,0,this); 
  }
  
  public void run()
  {
    Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
    while(true)
    {
      
      try
      {
        Thread.sleep(10);
      }
      catch(InterruptedException ex)
      {  
      }
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    }
  }
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode()==KeyEvent.VK_LEFT)
    {
      moveLeft();
      repaint();
    }
    if (e.getKeyCode()==KeyEvent.VK_RIGHT)
    {
      moveRight();
      repaint();
      
    }
    if (e.getKeyCode()==KeyEvent.VK_UP)
    {
      moveUp();
      repaint();
    }
    if (e.getKeyCode()==KeyEvent.VK_DOWN)
    {
      moveDown();
      repaint();
    }
  }
  
  public void keyReleased(KeyEvent e)
  {
    
  }
  
  public void keyTyped(KeyEvent e)
  {
    
  }
  public void setMap()
  {
    for (int i=0;i<mapY;i++)
    {
      for (int a=0;a<mapX;a++)
      {
        if (i==0||i==(mapY-1))
        {
          map[i][a]=1; 
        }
        else if (a==0||a==(mapX-1))
        {
          map[i][a]=1; 
        }
        else
        {
          map[i][a]=0;
        }
      }
    }
//    for (int i=0;i<mapY;i+=2)
//   {
//    for (int a=0;a<mapX;a+=2)
//    {
//      if (i==0||i==(mapY-1))
//      {
//       map[i][a]=1; 
//      }
//      else if (a==0||a==(mapX-1))
//      {
//       map[i][a]=1; 
//      }
//      else
//      {
//        map[i][a]=0;
//      }
//    }
//    }
  }
  
  public void initialize()
  {
    fish.setStuff(0,1,160,160,16,0,100,0,ammo);
    map[fish.getY()/16][fish.getX()/16]=3;
    try {
            wall01 = ImageIO.read(new File("wall01.png"));
        } catch (IOException e) {
        }
         try {
            floor01 = ImageIO.read(new File("floor01.jpg"));
        } catch (IOException e) {
        }
          try {
            player01 = ImageIO.read(new File("spaceship_game___sprites_by_ails-d6dros0.gif"));
        } catch (IOException e) {
        }
  }
  public void moveUp()
  {
    if (map[(fish.getY()-16)/16][fish.getX()/16]==0)
    {
      if (yOfset==0)
      {
        screenY-=16;
      }
      
      if (screenY>=0)
      {
        
        fish.moveUp();
        map[fish.getY()/16][fish.getX()/16]=3;
        map[(fish.getY()+16)/16][(fish.getX()/16)]=0;
        if (yOfset!=0)
        {
         yOfset-=16; 
        }
        
      }
      else 
      {
        fish.moveUp();
        map[fish.getY()/16][fish.getX()/16]=3;
        map[(fish.getY()+16)/16][(fish.getX()/16)]=0;
        screenY+=16; 
        yOfset-=16;
      }
    }
  }
  public void moveDown()
  {
    if (map[(fish.getY()+16)/16][fish.getX()/16]==0)
    {
      if (yOfset==0)
      {
      screenY+=16;
      }
      if ((screenY+800)/16<=map.length)
      {
        
        fish.moveDown();
        map[fish.getY()/16][fish.getX()/16]=3;
        map[(fish.getY()-16)/16][(fish.getX()/16)]=0;
        if (yOfset!=0)
        {
          yOfset+=16;
      }
      }
      else 
      {
        
        fish.moveDown();
        map[fish.getY()/16][fish.getX()/16]=3;
        map[(fish.getY()-16)/16][(fish.getX()/16)]=0;
        yOfset+=16;
        screenY-=16; 
      }
    }
  }
  public void moveLeft()
  {
    if (map[fish.getY()/16][(fish.getX()-16)/16]==0)
      {
      if (xOfset==0)
      {
    screenX-=16;
      }
    if (screenX>=0)
    {
      
        fish.moveLeft();
        map[fish.getY()/16][fish.getX()/16]=3;
        map[(fish.getY()/16)][(fish.getX()+16)/16]=0;
        if (xOfset!=0)
        {
         xOfset-=16; 
        }
      
    }
    else
    {
    
        fish.moveLeft();
        map[fish.getY()/16][fish.getX()/16]=3;
        map[(fish.getY()/16)][(fish.getX()+16)/16]=0;
      xOfset-=16;
      screenX+=16;
    }
    }
  }
  public void moveRight()
  {
    if (map[fish.getY()/16][(fish.getX()+16)/16]==0)
    {
      if (xOfset==0)
      {
      screenX+=16;
      }
      if((screenX+800)/16<=map[0].length)
      {
        
        fish.moveRight();
        map[fish.getY()/16][fish.getX()/16]=3;
        map[(fish.getY()/16)][(fish.getX()-16)/16]=0;
        if (xOfset!=0)
        {
         xOfset+=16; 
        }
      }
      else 
      {
        
        fish.moveRight();
        map[fish.getY()/16][fish.getX()/16]=3;
        map[(fish.getY()/16)][(fish.getX()-16)/16]=0;
        xOfset+=16;
        screenX-=16; 
      }
    }
  }
}