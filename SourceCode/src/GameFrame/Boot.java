package GameFrame;

import java.io.IOException;
import Elements.*;
import Madeline.Madeline;
import Map.LoadMap;

public class Boot
{
	public static void main(String[] args)
	{
		try {
			LoadMap.loadMap1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BackGround backGround=new BackGround();
		Madeline madeline=new Madeline();
		GameFrame gameFrame=new GameFrame();
		ImagePanel imagePanel=new ImagePanel(backGround,madeline);
		gameFrame.gameStart(imagePanel,backGround,madeline);
	}
}