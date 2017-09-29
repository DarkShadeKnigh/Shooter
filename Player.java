class Player 
{
  private int type;
  private int speed;
  private int xPos;
  private int yPos;
  private int size;
  private int gunType;
  private double armour;
  private double health;
  private int numOfBulletTypes = 3;
  private int [] ammoo = new int [numOfBulletTypes];
  // remove once have solid num on num of bullets or not
  
   public void setStuff(int t,int s,int x,int y, int si,int a, int h,int g,int [] am)
  {
   type = t;
   speed = s;
   xPos = x;
   yPos = y;
   size = si;
   armour = a;
   health = h;
   gunType = g;
   for (int i =0;i<numOfBulletTypes;i++)
   {
     ammoo[i]=am[i];
   }

  }
  public void armourPickup(int a)
  {
    
  }
  int getX()
  {
   return xPos; 
  }
  int getY()
  {
    return yPos;
  }
  public void moveLeft()
  {
   xPos-=16; 
  }
  public void moveRight()
  {
   xPos+=16; 
  }
  public void moveUp()
  {
   yPos-=16; 
  }
  public void moveDown()
  {
   yPos+=16; 
  }

}