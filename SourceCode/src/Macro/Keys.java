package Macro;

import java.awt.event.KeyEvent;

public enum Keys
{
	LEFT(KeyEvent.VK_LEFT),
	RIGHT(KeyEvent.VK_RIGHT),
	UP(KeyEvent.VK_UP),
	DOWN(KeyEvent.VK_DOWN),
	GRAB(KeyEvent.VK_Z),
	DASH(KeyEvent.VK_X),
	JUMP(KeyEvent.VK_C);
	
	private int keyValue;
	private Keys(int keyValue)
	{
		this.keyValue=keyValue;
	}
	public int getKeyValue()
	{
		return keyValue;
	}
}
