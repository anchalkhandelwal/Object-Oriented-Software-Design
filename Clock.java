import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class Clock implements Observer, Sprite, Cloneable, ActionListener{
	
	
    protected int clockSeconds =0;
    protected int clockMiliSecs = 0;
	protected int clockMinutes = 0;
	protected Timer t;
	private int timerStartFlag = 0;
	protected int pauseFlag = 0;
	  
	Clock cloneClock;
	  
	public Clock() { 
	    t = new Timer(10,this);	   
	}  
	
	  
	Clock(Clock clock)
	{
		cloneClock = clock;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();	
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(timerStartFlag <= 0){
			t.start();
			timerStartFlag++;
		}
		
		
	}
	
	public void registerClock()
	{
		BreakoutObservable oberservable = new BreakoutObservable();
		oberservable.addObserver(this);
	}
	
	public void unregisterClock()
	{
		BreakoutObservable observable = new BreakoutObservable();
		observable.removeObserver(this);
	}

	@Override
	public void draw(Graphics2D g2d) {
		  
		 g2d.setColor(Color.GREEN);
		 g2d.setFont(new Font("serif", Font.BOLD,40));
		 g2d.drawString((""+clockMinutes+ ":"+ clockSeconds + ":" + clockMiliSecs), (Constants.BOARD_WIDTH - 135), 33); 	
	}




	  @Override
		public void actionPerformed(ActionEvent e) {
		  	if(pauseFlag == 0)
		  	{
		  		ClockCommands clockCommands = new ClockCommands(this);
		  		clockCommands.execute();
		  	}
			 	  
		}


	public int getClockSeconds() {
		return clockSeconds;
	}


	public void setClockSeconds(int clockSeconds) {
		this.clockSeconds = clockSeconds;
	}


	public int getClockMiliSecs() {
		return clockMiliSecs;
	}


	public void setClockMiliSecs(int clockMiliSecs) {
		this.clockMiliSecs = clockMiliSecs;
	}


	public int getClockMinutes() {
		return clockMinutes;
	}


	public void setClockMinutes(int clockMinutes) {
		this.clockMinutes = clockMinutes;
	}
	  	 	
}


	
	 
	    
		


