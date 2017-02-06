import java.util.Comparator;

//To work with Coordinate State
public class StateComparator implements Comparator<GameState> {

	public int compare(GameState a, GameState b)
	{
		double cha = a.getCost() + a.getH();
		double chb = b.getCost() + b.getH();
		if(cha > chb)
			return 1;
		else if(cha < chb)
			return -1;
		else if(a.getX() > b.getX())
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
