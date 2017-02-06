//import java.util.Comparator;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.Timer;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;
import java.util.Arrays;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.util.TreeSet;
import java.util.ArrayList;
public class AStarSearch {
	
	TreeSet<GameState> frontier, visited;
	
	public GameState search(GameState startState, GameState goalState)
	{
		StateComparator comp_frontier = new StateComparator();
		StateComparator_visited comp_visited = new StateComparator_visited();
		frontier = new TreeSet<GameState>(comp_frontier);
		visited = new TreeSet<GameState>(comp_visited);
		//startState.setCost(0.0);
		//startState.setParent(null);
		//Location location = new Location((int)GameState.model.getX(), (int)GameState.model.getY());
		//startState.setLocation(location);
		visited.add(startState);
		frontier.add(startState);
		
		while(frontier.size() > 0)
		{
			GameState s = frontier.pollFirst();
			if(s.locationEquals(goalState))
			{
				
				//s.prettyPrint();
				//goalState.prettyPrint();
				//System.out.println(frontier.size());
				
				return s;
			}
			
			//Compute Next States
			ArrayList<GameState> nextStates = s.allTransitions();
			
			for(GameState child : nextStates)
			{
				if(visited.contains(child))
				{
					GameState oldChild = visited.floor(child);
					if(child.getCost() < oldChild.getCost())
					{
						oldChild.setCost(child.getCost());
						oldChild.setParent(s);
					}
					
				}
				else
				{
					GameState newChild = new GameState(child.getCost(), s, child.getX(), child.getY());
					newChild.setH(newChild.computeH(goalState.getX(), goalState.getY()));
					frontier.add(newChild);
					visited.add(newChild);
					//System.out.println("Added new frontier");
					//newChild.prettyPrint();
					//goalState.prettyPrint();
				}				
			}

			
		}
		/*
		System.out.println("####################");
		for(GameState state : visited)
		{
			state.prettyPrint();
		}
		System.out.println("####################");
		*/
		throw new RuntimeException("There is no path to the goal" + frontier.size());
	}
	
	public TreeSet<GameState> getFrontier()
	{
		return this.frontier;
	}
}
