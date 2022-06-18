package Madeline;

import Macro.*;

class States
{
	int spawnPointX;
	int spawnPointY;
	int dashing;
	int dashingAble;
	int dashingCounter;
	int Durability;
	int jumpCoolDown;
	int runningImageState;
	int runningImageCounter;
	int runningSoundCounter;
	int climbingImageState;
	int climbingImageCounter;
	int climbingSoundCounter;
	DashingDirection dashingDirection;
	FacingDirection facingDirection;
	States()
	{
		spawnPointX=GameMacro.MADELINESPAWN1X;
		spawnPointY=GameMacro.MADELINESPAWN1Y;
		dashing=0;
		dashingAble=1;
		dashingCounter=0;
		Durability=GameMacro.DURABILITY;
		jumpCoolDown=0;
		runningImageState=1;
		runningImageCounter=0;
		runningSoundCounter=0;
		climbingImageState=1;
		climbingImageCounter=0;
		climbingSoundCounter=0;
		facingDirection=FacingDirection.RIGHT;
	}
}