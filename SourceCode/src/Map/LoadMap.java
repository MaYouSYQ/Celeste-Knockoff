package Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import Elements.*;

public final class LoadMap
{
	static File file;
	static BufferedReader reader=null;
	static String line=null;
	static int x,y,width,height,counter;
	public static void loadMap1() throws IOException
	{
		file = new File("Data/1.txt");
		loadMap();
	}
	public static void loadMap2() throws IOException
	{
		file = new File("Data/2.txt");
		loadMap();
	}
	public static void loadMap3() throws IOException
	{
		file = new File("Data/3.txt");
		loadMap();
	}
	public static void loadMap4() throws IOException
	{
		file = new File("Data/4.txt");
		loadMap();
	}
	private static void loadMap() throws IOException
	{
		reader=new BufferedReader(new FileReader(file));
		while ((line=reader.readLine())!=null&&!line.equals(""))
		{
			counter=0;
			String tempString="";
			char[] tempCharArray=line.toCharArray();
            for(int i=0;i<tempCharArray.length;i++)
            {
            	if('0'<=tempCharArray[i]&&tempCharArray[i]<='9')
            	{
            		tempString+=tempCharArray[i];
            	}
            	else if(!tempString.equals(""))
            	{
            		if(counter==0)
            		{
            			x=Integer.parseInt(tempString)-1;
            		}
            		if(counter==1)
            		{
            			y=Integer.parseInt(tempString);
            		}
            		tempString="";
            		counter+=1;
            	}
            }
            if(!tempString.equals(""))
        	{
            	height=Integer.parseInt(tempString)-y;
        	}
            ElementSet.elements.add(new LeftWall(x,y,1,height));
	    }
		while ((line=reader.readLine())!=null&&!line.equals(""))
		{
			counter=0;
			String tempString="";
			char[] tempCharArray=line.toCharArray();
            for(int i=0;i<tempCharArray.length;i++)
            {
            	if('0'<=tempCharArray[i]&&tempCharArray[i]<='9')
            	{
            		tempString+=tempCharArray[i];
            	}
            	else if(!tempString.equals(""))
            	{
            		if(counter==0)
            		{
            			y=Integer.parseInt(tempString)-1;
            		}
            		if(counter==1)
            		{
            			x=Integer.parseInt(tempString);
            		}
            		tempString="";
            		counter+=1;
            	}
            }
            if(!tempString.equals(""))
        	{
            	width=Integer.parseInt(tempString)-x;
        	}
            ElementSet.elements.add(new UpWall(x,y,width,1));
	    }
		while ((line=reader.readLine())!=null&&!line.equals(""))
		{
			counter=0;
			String tempString="";
			char[] tempCharArray=line.toCharArray();
            for(int i=0;i<tempCharArray.length;i++)
            {
            	if('0'<=tempCharArray[i]&&tempCharArray[i]<='9')
            	{
            		tempString+=tempCharArray[i];
            	}
            	else if(!tempString.equals(""))
            	{
            		if(counter==0)
            		{
            			x=Integer.parseInt(tempString);
            		}
            		if(counter==1)
            		{
            			y=Integer.parseInt(tempString);
            		}
            		tempString="";
            		counter+=1;
            	}
            }
            if(!tempString.equals(""))
        	{
            	height=Integer.parseInt(tempString)-y;
        	}
            ElementSet.elements.add(new RightWall(x,y,1,height));
	    }
		while ((line=reader.readLine())!=null&&!line.equals(""))
		{
			counter=0;
			String tempString="";
			char[] tempCharArray=line.toCharArray();
            for(int i=0;i<tempCharArray.length;i++)
            {
            	if('0'<=tempCharArray[i]&&tempCharArray[i]<='9')
            	{
            		tempString+=tempCharArray[i];
            	}
            	else if(!tempString.equals(""))
            	{
            		if(counter==0)
            		{
            			y=Integer.parseInt(tempString);
            		}
            		if(counter==1)
            		{
            			x=Integer.parseInt(tempString);
            		}
            		tempString="";
            		counter+=1;
            	}
            }
            if(!tempString.equals(""))
        	{
            	width=Integer.parseInt(tempString)-x;
        	}
            ElementSet.elements.add(new DownWall(x,y,width,1));
	    }
		while ((line=reader.readLine())!=null&&!line.equals(""))
		{
			counter=0;
			String tempString="";
			char[] tempCharArray=line.toCharArray();
            for(int i=0;i<tempCharArray.length;i++)
            {
            	if('0'<=tempCharArray[i]&&tempCharArray[i]<='9')
            	{
            		tempString+=tempCharArray[i];
            	}
            	else if(!tempString.equals(""))
            	{
            		if(counter==0)
            		{
            			x=Integer.parseInt(tempString);
            		}
            		if(counter==1)
            		{
            			y=Integer.parseInt(tempString);
            		}
            		if(counter==2)
            		{
            			width=Integer.parseInt(tempString)-x;
            		}
            		tempString="";
            		counter+=1;
            	}
            }
            if(!tempString.equals(""))
        	{
            	height=Integer.parseInt(tempString)-y;
        	}
            ElementSet.elements.add(new Hazard(x,y,width,height));
	    }
		while ((line=reader.readLine())!=null&&!line.equals(""))
		{
			counter=0;
			String tempString="";
			char[] tempCharArray=line.toCharArray();
            for(int i=0;i<tempCharArray.length;i++)
            {
            	if('0'<=tempCharArray[i]&&tempCharArray[i]<='9')
            	{
            		tempString+=tempCharArray[i];
            	}
            	else if(!tempString.equals(""))
            	{
            		if(counter==0)
            		{
            			x=Integer.parseInt(tempString);
            		}
            		if(counter==1)
            		{
            			y=Integer.parseInt(tempString);
            		}
            		if(counter==2)
            		{
            			width=Integer.parseInt(tempString)-x;
            		}
            		tempString="";
            		counter+=1;
            	}
            }
            if(!tempString.equals(""))
        	{
            	height=Integer.parseInt(tempString)-y;
        	}
            ElementSet.elements.add(new NextLevel(x,y,width,height));
	    }
	    reader.close();
	}
}
