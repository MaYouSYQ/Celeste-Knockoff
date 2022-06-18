package GameFrame;

import java.io.IOException;

import jmp123.PlayBack;

public class MusicPlayer
{
	static PlayBack temp;
	static PlayBack bgm1()
	{
		temp=new PlayBack(new jmp123.output.Audio());
		try {
			temp.open("Audio/bgm1.mp3","");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	static PlayBack bgm2()
	{
		temp=new PlayBack(new jmp123.output.Audio());
		try {
			temp.open("Audio/bgm2.mp3","");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	static PlayBack climbing()
	{
		temp=new PlayBack(new jmp123.output.Audio());
		try {
			temp.open("Audio/climbing.mp3","");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	static PlayBack dashing()
	{
		temp=new PlayBack(new jmp123.output.Audio());
		try {
			temp.open("Audio/dashing.mp3","");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	static PlayBack hitting()
	{
		temp=new PlayBack(new jmp123.output.Audio());
		try {
			temp.open("Audio/hitting.mp3","");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	static PlayBack jumping()
	{
		temp=new PlayBack(new jmp123.output.Audio());
		try {
			temp.open("Audio/jumping.mp3","");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	static PlayBack landing()
	{
		temp=new PlayBack(new jmp123.output.Audio());
		try {
			temp.open("Audio/landing.mp3","");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	static PlayBack running()
	{
		temp=new PlayBack(new jmp123.output.Audio());
		try {
			temp.open("Audio/running.mp3","");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	static PlayBack die()
	{
		temp=new PlayBack(new jmp123.output.Audio());
		try {
			temp.open("Audio/die.mp3","");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}
