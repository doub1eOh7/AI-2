import java.util.Comparator;

public class StateComparator_visited implements Comparator<GameState> {

	public int compare(GameState a, GameState b)
	{
		if(a.getX() > b.getX())
			return 1;
		else if(a.getX() < b.getX())
			return -1;
		else if(a.getY() > b.getY())
			return 1;
		else if(a.getY() < b.getY())
			return -1;
		else
			return 0;
	}
	
}