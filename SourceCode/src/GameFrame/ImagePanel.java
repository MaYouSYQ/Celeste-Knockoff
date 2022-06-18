package GameFrame;

import java.awt.Graphics;
import javax.swing.JPanel;
import Elements.ImageElement;

public class ImagePanel extends JPanel
{
	private ImageElement imageElements[];
	public ImagePanel(ImageElement... imageElements)
	{
		this.imageElements=imageElements;
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		for(ImageElement i:imageElements)
		{
			g.drawImage(i.getImage(),i.getImageX(),i.getImageY(),i.getImageWidth(),i.getImageHeight(),this);
		}
	}
}
