import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class BreakoutObservable extends Observable
{
	public static ArrayList<Observer> observers = new ArrayList<Observer>();
	Paddle paddle;
	Ball ball;
	public BreakoutObservable(Paddle paddle, Ball ball) {
		// TODO Auto-generated constructor stub
		this.paddle = paddle;
		this.ball = ball;
	}
	
	public BreakoutObservable()
	{
		
	}
	
	public void addObserver(Observer obs)
	{
		observers.add(obs);
	}
	
	public void removeObserver(Observer obs)
	{
		observers.remove(obs);
	}
	
	public int countObservers(){
		return observers.size();
	}
	
	public void notifyObservers(){
		HashMap<String, Object> objects = new HashMap<String, Object>();
		objects.put("Ball", this.ball);
		objects.put("Paddle", this.paddle);
		for(int i = 0; i < observers.size(); i++)
		{
			if(i == 0)
			{
				observers.get(i).update(this, objects);
			}
			else{
			observers.get(i).update(this, objects);
			}
			Breakout breakout = new Breakout();
			breakout.repaint();
		}
	}
}
