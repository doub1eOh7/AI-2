import java.util.Comparator;

//To work with Coordinate State
public class StateComparator implements Comparator<GameState> {

	public int compare(GameState a, GameState b)
	{
		if(a.getCost() > b.getCost())
			return 1;
		else if(a.getCost() < b.getCost())
			return -1;
		else if(a.getLocation().getX() > b.getLocation().getX())
			return 1;
		else if(a.getLocation().getX() < b.getLocation().getX())
			return -1;
		else if(a.getLocation().getY() > b.getLocation().getY())
			return 1;
		else if(a.getLocation().getY() < b.getLocation().getY())
			return -1;
		else
			return 0;
	}
	
}
