package Madeline;

import java.awt.Rectangle;
import Elements.*;
import GameFrame.KeySet;
import Macro.*;
import Map.ElementSet;

public class Madeline extends ImageElement
{
	int xSpeed,ySpeed;
	States states;
	private SoundManager soundManager;
	private ImageManager imageManager;
	public Madeline()
	{
		super(GameMacro.MADELINESPAWN1X,GameMacro.MADELINESPAWN1Y,GameMacro.MADELINEWIDTH,GameMacro.MADELINEHEIGHT,"Images/Madeline/normalRight.png",ImageData.normalRight.imageX,ImageData.normalRight.imageY,ImageData.normalRight.imageWidth,ImageData.normalRight.imageHeight);
		xSpeed=0;
		ySpeed=0;
		states=new States();
		soundManager=new SoundManager(this);
		imageManager=new ImageManager(this);
	}
	public void upgrade()
	{
		soundManager.soundType=SoundType.NONE;
		failureDetection();
		defineSpeed();
		advance();
		collisionDetection();
	}
	private void failureDetection()
	{
		for(BaseElement i:ElementSet.elements)
		{
			if(this.getRectangle().intersects(i.getRectangle()))
			{
				if(i instanceof Hazard)
				{
					soundManager.soundDie();
					respawn();
				}
			}
		}
	}
	public void respawn()
	{
		states.dashing=0;
		states.dashingCounter=0;
		states.jumpCoolDown=0;
		states.facingDirection=FacingDirection.RIGHT;
		xSpeed=0;
		ySpeed=0;
		x=states.spawnPointX;
		y=states.spawnPointY;
	}
	public void setSpawnPoint(int x,int y)
	{
		states.spawnPointX=x;
		states.spawnPointY=y;
	}
	public boolean nextLevel()
	{
		for(BaseElement i:ElementSet.elements)
		{
			if(this.getRectangle().intersects(i.getRectangle()))
			{
				if(i instanceof NextLevel)
				{
					return true;
				}
			}
		}
		return false;
	}
	private void defineSpeed()
	{
		if(states.jumpCoolDown>0) states.jumpCoolDown-=GameMacro.DELTAJUMPCOOLDOWN;
		if(states.dashing==1)
		{	
			this.setDashingSpeed();
			states.dashingCounter+=1;
			if(states.dashingCounter==10)
			{
				states.dashing=0;
				states.dashingCounter=0;
			}
		}
		else if(KeySet.used(Keys.DASH)&&states.dashingAble==1)
		{
			this.dash();
			imageManager.imageDashing();
			soundManager.soundDashing();
			states.dashing=1;
			states.dashingAble=0;
		}
		else if(wallGrabbing())
		{
			imageManager.imageGrabbing();
			if(leftAdhere())
			{
				states.facingDirection=FacingDirection.LEFT;
			}
			else
			{
				states.facingDirection=FacingDirection.RIGHT;
			}
			xSpeed=0;
			if(states.jumpCoolDown==0)
			{
				if(KeySet.used(Keys.UP)&&KeySet.used(Keys.DOWN))
				{
					ySpeed=0;
					states.Durability-=GameMacro.DELTADURABILITY;
				}
				else if(KeySet.used(Keys.UP))
				{
					soundManager.soundClimbing();
					imageManager.imageClimbing();
					ySpeed=-GameMacro.GRABSPEED;
					states.Durability-=GameMacro.UPDELTADURABILITY;
				}
				else if(KeySet.used(Keys.DOWN))
				{
					ySpeed=GameMacro.GRABSPEED;
				}
				else
				{
					ySpeed=0;
					states.Durability-=GameMacro.DELTADURABILITY;
				}
				
				if(KeySet.used(Keys.JUMP))
				{
					wallJumpGrabbing();
					soundManager.soundJumping();
					states.jumpCoolDown=GameMacro.JUMPCOOLDOWN;
					states.Durability-=GameMacro.JUMPDELTADURABILITY;
				}
			}
			else
			{
				ySpeed+=GameMacro.DELTAYSPEED;
			}
		}
		else if(downAdhere())
		{
			if(xSpeed==0)
			{
				imageManager.imageStanding();
			}
			else
			{
				soundManager.soundRunning();
				imageManager.imageRunning();
			}
			states.dashingAble=1;
			states.Durability=GameMacro.DURABILITY;
			horizontalGround();
			if(KeySet.used(Keys.JUMP))
			{
				soundManager.soundJumping();
				ySpeed=-GameMacro.YJUMPSPEED;
				states.jumpCoolDown=GameMacro.JUMPCOOLDOWN;
			}
		}
		else if(wallAdhere())
		{
			imageManager.imageGrabbing();
			horizontalAirborne();
			if(states.jumpCoolDown==0)
			{
				if(KeySet.used(Keys.DOWN))
				{
					this.ySpeed=GameMacro.ACTIVEDOWNADHERESPEED;
				}
				else
				{
					this.ySpeed=GameMacro.PASSIVEDOWNADHERESPEED;
				}
				
				if(KeySet.used(Keys.JUMP))
				{
					soundManager.soundJumping();
					wallJump();
					states.jumpCoolDown=GameMacro.JUMPCOOLDOWN;
				}
			}
			else
			{
				this.ySpeed+=GameMacro.DELTAYSPEED;
			}
		}
		else if(wallAdjacent())
		{
			imageManager.imageAirborne();
			horizontalAirborne();
			ySpeed+=GameMacro.DELTAYSPEED;
			if(ySpeed>GameMacro.YSPEEDLIMIT)
			{
				ySpeed=GameMacro.YSPEEDLIMIT;
			}
			if(states.jumpCoolDown==0)
			{
				if(KeySet.used(Keys.JUMP))
				{
					soundManager.soundJumping();
					wallJumpAdjacent();
					states.jumpCoolDown=GameMacro.JUMPCOOLDOWN;
				}
			}
		}
		else
		{	
			imageManager.imageAirborne();
			horizontalAirborne();
			ySpeed+=GameMacro.DELTAYSPEED;
			if(ySpeed>GameMacro.YSPEEDLIMIT)
			{
				ySpeed=GameMacro.YSPEEDLIMIT;
			}
		}
	}
	private void advance()
	{
		this.x+=this.xSpeed;
		this.y+=this.ySpeed;
	}
	private void horizontalAirborne()
	{
		if(xSpeed>GameMacro.XSPEEDLIMIT)
		{
			xSpeed-=GameMacro.DELTAXSPEED;
		}
		else if(xSpeed<-GameMacro.XSPEEDLIMIT)
		{
			xSpeed+=GameMacro.DELTAXSPEED;
		}
		else if(KeySet.used(Keys.LEFT)&&KeySet.used(Keys.RIGHT))
		{
			if(xSpeed>0)
			{
				xSpeed-=GameMacro.DELTAXSPEED;
			}
			else if (xSpeed<0)
			{
				xSpeed+=GameMacro.DELTAXSPEED;
			}
		}
		else if(KeySet.used(Keys.LEFT))
		{
			states.facingDirection=FacingDirection.LEFT;
			xSpeed-=GameMacro.DELTAXSPEED;
			if(xSpeed<-GameMacro.XSPEEDLIMIT)
			{
				xSpeed=-GameMacro.XSPEEDLIMIT;
			}
		}
		else if(KeySet.used(Keys.RIGHT))
		{
			states.facingDirection=FacingDirection.RIGHT;
			xSpeed+=GameMacro.DELTAXSPEED;
			if(xSpeed>GameMacro.XSPEEDLIMIT)
			{
				xSpeed=GameMacro.XSPEEDLIMIT;
			}
			
		}
		else
		{
			if(xSpeed>0)
			{
				xSpeed-=GameMacro.DELTAXSPEED;
			}
			else if (xSpeed<0)
			{
				xSpeed+=GameMacro.DELTAXSPEED;
			}
		}
	}
	private void horizontalGround()
	{
		if(KeySet.used(Keys.LEFT)&&KeySet.used(Keys.RIGHT))
		{
			xSpeed=0;
		}
		else if(KeySet.used(Keys.LEFT))
		{
			xSpeed=-GameMacro.XGROUNDSPEED;
			states.facingDirection=FacingDirection.LEFT;
		}
		else if(KeySet.used(Keys.RIGHT))
		{
			xSpeed=GameMacro.XGROUNDSPEED;
			states.facingDirection=FacingDirection.RIGHT;
		}
		else
		{
			xSpeed=0;
		}
	}
	private void setDashingSpeed()
	{
		if(states.dashingDirection==DashingDirection.LEFT)
		{
			xSpeed=-GameMacro.DIRECTDASHINGSPEED[states.dashingCounter];
			ySpeed=0;
		}
		else if(states.dashingDirection==DashingDirection.LEFTUP)
		{
			xSpeed=-GameMacro.DIAGNALDASHINGSPEED[states.dashingCounter];
			ySpeed=-GameMacro.DIAGNALDASHINGSPEED[states.dashingCounter];
		}
		else if(states.dashingDirection==DashingDirection.UP)
		{
			xSpeed=0;
			ySpeed=-GameMacro.DIRECTDASHINGSPEED[states.dashingCounter];
		}
		else if(states.dashingDirection==DashingDirection.RIGHTUP)
		{
			xSpeed=GameMacro.DIAGNALDASHINGSPEED[states.dashingCounter];
			ySpeed=-GameMacro.DIAGNALDASHINGSPEED[states.dashingCounter];
		}
		else if(states.dashingDirection==DashingDirection.RIGHT)
		{
			xSpeed=GameMacro.DIRECTDASHINGSPEED[states.dashingCounter];
			ySpeed=0;
		}
		else if(states.dashingDirection==DashingDirection.RIGHTDOWN)
		{
			xSpeed=GameMacro.DIAGNALDASHINGSPEED[states.dashingCounter];
			ySpeed=GameMacro.DIAGNALDASHINGSPEED[states.dashingCounter];
		}
		else if(states.dashingDirection==DashingDirection.DOWN)
		{
			xSpeed=0;
			ySpeed=GameMacro.DIRECTDASHINGSPEED[states.dashingCounter];
		}
		else
		{
			xSpeed=-GameMacro.DIAGNALDASHINGSPEED[states.dashingCounter];
			ySpeed=GameMacro.DIAGNALDASHINGSPEED[states.dashingCounter];
		}
	}
	private void dash()
	{
		if(KeySet.used(Keys.LEFT)&&KeySet.used(Keys.UP)&&KeySet.used(Keys.RIGHT)&&KeySet.used(Keys.DOWN))
		{
			if(states.facingDirection==FacingDirection.LEFT)
			{
				states.dashingDirection=DashingDirection.LEFT;
			}
			else
			{
				states.dashingDirection=DashingDirection.RIGHT;
			}
		}
		else if(KeySet.used(Keys.LEFT)&&KeySet.used(Keys.UP)&&KeySet.used(Keys.RIGHT))
		{
			if(states.facingDirection==FacingDirection.LEFT)
			{
				states.dashingDirection=DashingDirection.LEFTUP;
			}
			else
			{
				states.dashingDirection=DashingDirection.RIGHTUP;
			}
		}
		else if(KeySet.used(Keys.LEFT)&&KeySet.used(Keys.UP)&&KeySet.used(Keys.DOWN))
		{
			states.dashingDirection=DashingDirection.LEFT;
			states.facingDirection=FacingDirection.LEFT;
		}
		else if(KeySet.used(Keys.LEFT)&&KeySet.used(Keys.RIGHT)&&KeySet.used(Keys.DOWN))
		{
			if(states.facingDirection==FacingDirection.LEFT)
			{
				states.dashingDirection=DashingDirection.LEFTDOWN;
			}
			else
			{
				states.dashingDirection=DashingDirection.RIGHTDOWN;
			}
		}
		else if(KeySet.used(Keys.UP)&&KeySet.used(Keys.RIGHT)&&KeySet.used(Keys.DOWN))
		{
			states.dashingDirection=DashingDirection.RIGHT;
			states.facingDirection=FacingDirection.RIGHT;
		}
		else if(KeySet.used(Keys.LEFT)&&KeySet.used(Keys.UP))
		{
			states.dashingDirection=DashingDirection.LEFTUP;
			states.facingDirection=FacingDirection.LEFT;
		}
		else if(KeySet.used(Keys.LEFT)&&KeySet.used(Keys.RIGHT))
		{
			if(states.facingDirection==FacingDirection.LEFT)
			{
				states.dashingDirection=DashingDirection.LEFT;
			}
			else
			{
				states.dashingDirection=DashingDirection.RIGHT;
			}
		}
		else if(KeySet.used(Keys.UP)&&KeySet.used(Keys.RIGHT))
		{
			states.dashingDirection=DashingDirection.RIGHTUP;
			states.facingDirection=FacingDirection.RIGHT;
		}
		else if(KeySet.used(Keys.LEFT)&&KeySet.used(Keys.DOWN))
		{
			states.dashingDirection=DashingDirection.LEFTDOWN;
			states.facingDirection=FacingDirection.LEFT;
		}
		else if(KeySet.used(Keys.UP)&&KeySet.used(Keys.DOWN))
		{
			if(states.facingDirection==FacingDirection.LEFT)
			{
				states.dashingDirection=DashingDirection.LEFT;
			}
			else
			{
				states.dashingDirection=DashingDirection.RIGHT;
			}
		}
		else if(KeySet.used(Keys.RIGHT)&&KeySet.used(Keys.DOWN))
		{
			states.dashingDirection=DashingDirection.RIGHTDOWN;
			states.facingDirection=FacingDirection.RIGHT;
		}
		else if(KeySet.used(Keys.LEFT))
		{
			states.dashingDirection=DashingDirection.LEFT;
			states.facingDirection=FacingDirection.LEFT;
		}
		else if(KeySet.used(Keys.UP))
		{
			states.dashingDirection=DashingDirection.UP;
		}
		else if(KeySet.used(Keys.RIGHT))
		{
			states.dashingDirection=DashingDirection.RIGHT;
			states.facingDirection=FacingDirection.RIGHT;
		}
		else if(KeySet.used(Keys.DOWN))
		{
			states.dashingDirection=DashingDirection.DOWN;
		}
		else
		{
			if(states.facingDirection==FacingDirection.LEFT)
			{
				states.dashingDirection=DashingDirection.LEFT;
			}
			else
			{
				states.dashingDirection=DashingDirection.RIGHT;
			}
		}
	}
	private void wallJump()
	{
		if(this.leftAdhere())
		{
			if(KeySet.used(Keys.LEFT)||KeySet.used(Keys.RIGHT))
			{
				xSpeed=GameMacro.XENHANCEDJUMPSPEED;
			}
			else
			{
				xSpeed=GameMacro.XJUMPSPEED;
			}
		}
		else
		{
			if(KeySet.used(Keys.LEFT)||KeySet.used(Keys.RIGHT))
			{
				xSpeed=-GameMacro.XENHANCEDJUMPSPEED;
			}
			else
			{
				xSpeed=-GameMacro.XJUMPSPEED;
			}
		}
		this.ySpeed=-GameMacro.YJUMPSPEED;
	}
	private void wallJumpAdjacent()
	{
		if(this.leftAdjacent())
		{
			if(KeySet.used(Keys.LEFT)||KeySet.used(Keys.RIGHT))
			{
				xSpeed=GameMacro.XENHANCEDJUMPSPEED;
			}
			else
			{
				xSpeed=GameMacro.XJUMPSPEED;
			}
		}
		else
		{
			if(KeySet.used(Keys.LEFT)||KeySet.used(Keys.RIGHT))
			{
				xSpeed=-GameMacro.XENHANCEDJUMPSPEED;
			}
			else
			{
				xSpeed=-GameMacro.XJUMPSPEED;
			}
		}
		this.ySpeed=-GameMacro.YJUMPSPEED;
	}
	private void wallJumpGrabbing()
	{
		if(this.leftAdhere())
		{
			if(KeySet.used(Keys.RIGHT))
			{
				xSpeed=GameMacro.XENHANCEDJUMPSPEED;
				states.facingDirection=FacingDirection.RIGHT;
			}
			else
			{
				xSpeed=0;
			}
		}
		else
		{
			if(KeySet.used(Keys.LEFT))
			{
				xSpeed=-GameMacro.XENHANCEDJUMPSPEED;
				states.facingDirection=FacingDirection.LEFT;
			}
			else
			{
				xSpeed=0;
			}
		}
		this.ySpeed=-GameMacro.YJUMPSPEED;
	}
	private void collisionDetection()
	{
		for(BaseElement i:ElementSet.elements)
		{
			if(this.getRectangle().intersects(i.getRectangle()))
			{
				if(i instanceof Wall)
				{
					if(i instanceof LeftWall)
					{
						if(this.getPrevLeftRectangle().getX()>i.getRectangle().getX()&&this.getLeftRectangle().getX()<=i.getRectangle().getX())
						{
							if(this.getPrevLeftRectangle().getX()>i.getRectangle().getX()+1)
							{
								soundManager.soundHitting();
							}
							this.x=i.getX()+i.getWidth();
							xSpeed=0;
						}
					}
					if(i instanceof UpWall)
					{
						if(this.getPrevUpRectangle().getY()>i.getRectangle().getY()&&this.getUpRectangle().getY()<=i.getRectangle().getY())
						{
							if(this.getPrevUpRectangle().getY()>i.getRectangle().getY()+1)
							{
								soundManager.soundHitting();
							}
							this.y=i.getY()+i.getHeight();
							ySpeed=0;
						}
					}
					if(i instanceof RightWall)
					{
						if(this.getPrevRightRectangle().getX()<i.getRectangle().getX()&&this.getRightRectangle().getX()>=i.getRectangle().getX())
						{
							if(this.getPrevRightRectangle().getX()<i.getRectangle().getX()-1)
							{
								soundManager.soundHitting();
							}
							this.x=i.getX()-this.width;
							xSpeed=0;
						}
					}
					if(i instanceof DownWall)
					{
						if(this.getPrevDownRectangle().getY()<i.getRectangle().getY()&&this.getDownRectangle().getY()>=i.getRectangle().getY())
						{
							if(this.getPrevDownRectangle().getY()<i.getRectangle().getY()-1)
							{
								soundManager.soundLanding();
							}
							this.y=i.getY()-this.height;
							ySpeed=0;
						}
					}
				}
			}
		}
	}
	private Rectangle getLeftRectangle()
	{
		return new Rectangle(this.x,this.y,1,this.height);
	}
	private Rectangle getUpRectangle()
	{
		return new Rectangle(this.x,this.y,this.width,1);
	}
	private Rectangle getRightRectangle()
	{
		return new Rectangle(this.x+this.width-1,this.y,1,this.height);
	}
	private Rectangle getDownRectangle()
	{
		return new Rectangle(this.x,this.y+this.height-1,this.width,1);
	}
	private Rectangle getPrevLeftRectangle()
	{
		return new Rectangle(this.x-xSpeed,this.y-ySpeed,1,this.height);
	}
	private Rectangle getPrevUpRectangle()
	{
		return new Rectangle(this.x-xSpeed,this.y-ySpeed,this.width,1);
	}
	private Rectangle getPrevRightRectangle()
	{
		return new Rectangle(this.x-xSpeed+this.width-1,this.y-ySpeed,1,this.height);
	}
	private Rectangle getPrevDownRectangle()
	{
		return new Rectangle(this.x-xSpeed,this.y-ySpeed+this.height-1,this.width,1);
	}
	private Rectangle getLeftBorderLowerRectangle()
	{
		return new Rectangle(this.x-1,this.y,1,GameMacro.LOWERRECTANGLEHEIGHT);
	}
	private Rectangle getRightBorderLowerRectangle()
	{
		return new Rectangle(this.x+this.width,this.y,1,GameMacro.LOWERRECTANGLEHEIGHT);
	}
	private Rectangle getDownBorderRectangle()
	{
		return new Rectangle(this.x,this.y+this.height,this.width,1);
	}
	private Rectangle getLeftAdjacentRectangle()
	{
		return new Rectangle(this.x-GameMacro.ADJACENTRECTANGLEWIDTH,this.y,GameMacro.ADJACENTRECTANGLEWIDTH,this.height);
	}
	private Rectangle getRightAdjacentRectangle()
	{
		return new Rectangle(this.x+this.width,this.y,GameMacro.ADJACENTRECTANGLEWIDTH,this.height);
	}
	private boolean leftAdhere()
	{
		for(BaseElement i:ElementSet.elements)
		{
			if(i instanceof LeftWall)
			{
				if(this.getLeftBorderLowerRectangle().intersects(i.getRectangle())) return true;
			}
		}
		return false;
	}
	private boolean rightAdhere()
	{
		for(BaseElement i:ElementSet.elements)
		{
			if(i instanceof RightWall)
			{
				if(this.getRightBorderLowerRectangle().intersects(i.getRectangle())) return true;
			}
		}
		return false;
	}
	private boolean downAdhere()
	{
		for(BaseElement i:ElementSet.elements)
		{
			if(i instanceof DownWall)
			{
				if(this.getDownBorderRectangle().intersects(i.getRectangle())) return true;
			}
		}
		return false;
	}
	private boolean leftAdjacent()
	{
		for(BaseElement i:ElementSet.elements)
		{
			if(i instanceof LeftWall)
			{
				if(this.getLeftAdjacentRectangle().intersects(i.getRectangle())) return true;
			}
		}
		return false;
	}
	private boolean rightAdjacent()
	{
		for(BaseElement i:ElementSet.elements)
		{
			if(i instanceof RightWall)
			{
				if(this.getRightAdjacentRectangle().intersects(i.getRectangle())) return true;
			}
		}
		return false;
	}
	private boolean wallAdhere()
	{
		return (this.leftAdhere()&&KeySet.used(Keys.LEFT))||(this.rightAdhere()&&KeySet.used(Keys.RIGHT))||((this.leftAdhere()||this.rightAdhere())&&KeySet.used(Keys.GRAB)&&(states.Durability<=0));
	}
	private boolean wallGrabbing()
	{
		return (this.leftAdhere()||this.rightAdhere())&&KeySet.used(Keys.GRAB)&&(states.Durability>0);
	}
	private boolean wallAdjacent()
	{
		return (this.leftAdjacent()||this.rightAdjacent());
	}
	
	public SoundType getSoundType()
	{
		return soundManager.soundType;
	}
	public States getStates()
	{
		return states;
	}
}