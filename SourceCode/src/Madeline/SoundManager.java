package Madeline;

import Macro.*;

final class SoundManager
{
	Madeline madeline;
	SoundType soundType=SoundType.NONE;
	SoundManager(Madeline madeline)
	{
		this.madeline=madeline;
	}
	void soundClimbing()
	{
		madeline.states.climbingSoundCounter+=1;
		if(madeline.states.climbingSoundCounter==GameMacro.CLIMBINGSOUNDCOUNTERLIMIT)
		{
			this.soundType=SoundType.CLIMBING;
			madeline.states.climbingSoundCounter=0;
		}
	}
	void soundDashing()
	{
		this.soundType=SoundType.DASHING;
	}
	void soundHitting()
	{
		this.soundType=SoundType.HITTING;
	}
	void soundJumping()
	{
		this.soundType=SoundType.JUMPING;
	}
	void soundLanding()
	{
		this.soundType=SoundType.LANDING;
	}
	void soundRunning()
	{
		madeline.states.runningSoundCounter+=1;
		if(madeline.states.runningSoundCounter==GameMacro.RUNNINGSOUNDCOUNTERLIMIT)
		{
			this.soundType=SoundType.RUNNING;
			madeline.states.runningSoundCounter=0;
		}
	}
	void soundDie()
	{
		this.soundType=SoundType.DIE;
	}
}
