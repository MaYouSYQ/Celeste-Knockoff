package GameFrame;

import javax.swing.JFrame;

import Elements.BackGround;
import Macro.*;
import Madeline.*;
import Map.ElementSet;
import Map.LoadMap;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameFrame extends JFrame
{
	private static int currentLevel=1;
	public GameFrame()
	{
		this.setTitle("Celeste");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1294,758);
	}
	public void gameStart(ImagePanel imagePanel,BackGround backGround,Madeline madeline)
	{
		this.add(imagePanel);
		this.setVisible(true);
		this.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e)
			{
				KeySet.add(e.getKeyCode());
			}
			public void keyReleased(KeyEvent e)
			{
				KeySet.remove(e.getKeyCode());
			}
		});
		new Thread("")
		{
			public void run()
			{
				playBGM();
			}
		}.start();
		Timer timer=new Timer();
		TimerTask timerTask=new TimerTask()
		{
			public void run()
			{
				if(madeline.nextLevel())
				{
					try {
						nextLevel(backGround,madeline);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				madeline.upgrade();
				imagePanel.repaint();
				soundGeneration(madeline);
			}
		};
		timer.schedule(timerTask,0,GameMacro.GAMEPERIOD);
	}
	private void playBGM()
	{
		MusicPlayer.bgm1().start(true);
		while(true)
		{
			MusicPlayer.bgm2().start(true);
		}
	}
	private void soundGeneration(Madeline madeline)
	{
		if(madeline.getSoundType()==SoundType.CLIMBING)
		{
			new Thread("")
			{
			     public void run()
			     {
			    	 MusicPlayer.climbing().start(true); 
			     }
			}.start();
		}
		else if(madeline.getSoundType()==SoundType.DASHING)
		{
			new Thread("")
			{
			     public void run()
			     {
			    	 MusicPlayer.dashing().start(true); 
			     }
			}.start();
		}
		else if(madeline.getSoundType()==SoundType.HITTING)
		{
			new Thread("")
			{
			     public void run()
			     {
			    	 MusicPlayer.hitting().start(true); 
			     }
			}.start();
		}
		else if(madeline.getSoundType()==SoundType.JUMPING)
		{
			new Thread("")
			{
			     public void run()
			     {
			    	 MusicPlayer.jumping().start(true); 
			     }
			}.start();
		}
		else if(madeline.getSoundType()==SoundType.LANDING)
		{
			new Thread("")
			{
			     public void run()
			     {
			    	 MusicPlayer.landing().start(true); 
			     }
			}.start();
		}
		else if(madeline.getSoundType()==SoundType.RUNNING)
		{
			new Thread("")
			{
			     public void run()
			     {
			    	 MusicPlayer.running().start(true); 
			     }
			}.start();
		}
		else if(madeline.getSoundType()==SoundType.DIE)
		{
			new Thread("")
			{
			     public void run()
			     {
			    	 MusicPlayer.die().start(true); 
			     }
			}.start();
		}
	}
	private void nextLevel(BackGround backGround,Madeline madeline) throws IOException
	{
		if(currentLevel==1)
		{
			backGround.setImage(ImageData.backGround2);
			ElementSet.elements.clear();
			LoadMap.loadMap2();
			madeline.setSpawnPoint(GameMacro.MADELINESPAWN2X,GameMacro.MADELINESPAWN2Y);
			madeline.respawn();
			currentLevel+=1;
		}
		else if(currentLevel==2)
		{
			backGround.setImage(ImageData.backGround3);
			ElementSet.elements.clear();
			LoadMap.loadMap3();
			madeline.setSpawnPoint(GameMacro.MADELINESPAWN3X,GameMacro.MADELINESPAWN3Y);
			madeline.respawn();
			currentLevel+=1;
		}
		else
		{
			backGround.setImage(ImageData.backGround4);
			ElementSet.elements.clear();
			LoadMap.loadMap4();
			madeline.setSpawnPoint(GameMacro.MADELINESPAWN4X,GameMacro.MADELINESPAWN4Y);
			madeline.respawn();
			currentLevel+=1;
		}
	}
}