import java.util.ArrayList;
//Creates State object for GameState, this one is for 2 coordinates (ints).
public class State {
	
	private int x = 0;
	private int y = 0;
	
	State()
	{

	}
	
	State(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	State(State s)
	{
		this.x = s.getX();
		this.y = s.getY();
	}
	
	public boolean equals(State other)
	{
		boolean equal = true;
		if(other.x != this.x)
			equal = false;
		else if(other.y != this.y)
			equal = false;
		
		return equal;
	}
	
	public State transition(int dx, int dy)
	{
		int newX = this.x + dx;
		int newY = this.y + dy;
		State newState = new State(newX, newY);
		
		return newState;
	}
	
	public ArrayList<State> allTransitions()
	{
		ArrayList<State> nextStates = new ArrayList<State>();
		State right = this.transition(1, 0);
		State left = this.transition(-1, 0);
		State up = this.transition(0, -1);
		State down = this.transition(0, 1);
		nextStates.add(right);
		nextStates.add(left);
		nextStates.add(up);
		nextStates.add(down);
		
		return nextStates;

	}
	
	public double cost(State nextState)
	{
		double cost = 0;
		
		
		
		return cost;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int[] getCoordinates()
	{
		int[] coordinates = new int[2];
		coordinates[0] = this.x;
		coordinates[1] = this.y;
		return coordinates;
	}
	
	public boolean isEqual(State state2)
	{
		int state2X = state2.getX();
		int state2Y = state2.getY();
		
		boolean equals = true;
		if(state2X != this.x)
			equals = false;
		if(state2Y != this.y)
			equals = false;
		return equals;
	}

}
