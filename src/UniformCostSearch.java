//import java.util.Comparator;
import java.util.TreeSet;
import java.util.ArrayList;
public class UniformCostSearch {
	
	public GameState UniformCostSearch(GameState startState, GameState goalState)
	{
		StateComparator comp = new StateComparator();
		TreeSet<GameState> frontier = new TreeSet<GameState>(comp);
		TreeSet<GameState> visited = new TreeSet<GameState>(comp);
		startState.setCost(0.0);
		startState.setParent(null);
		visited.add(startState);
		frontier.add(startState);
		
		while(frontier.size() > 0)
		{
			GameState s = frontier.pollFirst();
			if(s.getLocation().isEqual(goalState.getLocation()))
			{
				return s;
			}
			
			//Compute Next States
			ArrayList<Location> nextStates = s.getLocation().allTransitions();
			
			for(Location child : nextStates)
			{
				//Compute Cost for each new state
				double cost = s.getLocation().cost(child); // HOW TO FIND COST IF NO ACCESS TO MODEL
				GameState validChild = new GameState(cost, s, child);
				if(visited.contains(validChild))
				{
					validChild.prettyPrint();
					GameState oldChild = visited.floor(validChild);
					oldChild.prettyPrint();
				}
				frontier.add(validChild);
				
			}
			
			
		}
		throw new RuntimeException("There is no path to the goal");
	}
}
