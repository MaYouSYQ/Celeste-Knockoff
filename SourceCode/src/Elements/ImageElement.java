package Elements;

import java.awt.Image;
import javax.swing.ImageIcon;

import Macro.ImageDataContainer;

public class ImageElement extends BaseElement
{
	private Image image;
	private int imageX,imageY,imageWidth,imageHeight;
	public ImageElement(int x,int y,int width,int height,String str,int imageX,int imageY,int imageWidth,int imageHeight)
	{
		super(x,y,width,height);
		this.image=new ImageIcon(str).getImage();
		this.imageX=imageX;
		this.imageY=imageY;
		this.imageWidth=imageWidth;
		this.imageHeight=imageHeight;
	}
	public Image getImage()
	{
		return this.image;
	}
	public int getImageX()
	{
		return this.imageX+this.x;
	}
	public int getImageY()
	{
		return this.imageY+this.y;
	}
	public int getImageWidth()
	{
		return this.imageWidth;
	}
	public int getImageHeight()
	{
		return this.imageHeight;
	}
	public void setImage(ImageDataContainer imageData)
	{
		this.image=new ImageIcon(imageData.image).getImage();
		this.imageX=imageData.imageX;
		this.imageY=imageData.imageY;
		this.imageWidth=imageData.imageWidth;
		this.imageHeight=imageData.imageHeight;
	}
}
