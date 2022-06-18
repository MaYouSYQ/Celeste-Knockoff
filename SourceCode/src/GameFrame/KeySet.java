package GameFrame;

import java.util.HashSet;
import java.util.Set;
import Macro.*;

public class KeySet
{
	private static final Set<Integer> keySet = new HashSet<>();
	public static boolean used(Keys key)
	{
		return keySet.contains(key.getKeyValue());
	}
	public static void add(int keyCode)
	{
		keySet.add(keyCode);
	}
	public static void remove(int keyCode)
	{
		keySet.remove(keyCode);
	}
}