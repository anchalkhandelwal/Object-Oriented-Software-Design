
public class ClockCommands implements Command {

	Clock clock;
	public ClockCommands(Clock clock) {
		// TODO Auto-generated constructor stub
		this.clock = clock;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(clock.getClockMiliSecs() == 99)
  		{
  			clock.setClockSeconds(clock.getClockSeconds() + 1);
  			clock.setClockMiliSecs(0);
  		}
		if(clock.getClockSeconds() == 59){
			clock.setClockMinutes(clock.getClockMinutes() + 1);
			clock.setClockSeconds(0);
		}
		else
		{
			clock.setClockMiliSecs(clock.getClockMiliSecs() + 1);
		}
	}

	@Override
	public Object undo() {
		// TODO Auto-generated method stub
		clock = Breakout.clockObjects.pop();
		return clock;
	}

	@Override
	public Object replay() {
		// TODO Auto-generated method stub
		clock = Breakout.clockQueue.get(Breakout.counter);
		return clock;
	}

}
