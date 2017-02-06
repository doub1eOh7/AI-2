import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Collections;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;

class Agent {
	
	public float finalX = -1, finalY = -1;
	public boolean flag = true;
	public ArrayList<GameState> solution = new ArrayList<GameState>();
	public TreeSet<GameState> frontier = new TreeSet<GameState>();
	public int buttonClick = 0;
/*
	void drawPlan(Graphics g, Model m) {
		g.setColor(Color.red);
		g.drawLine((int)xx(), (int)m.getY(), (int)m.getDestinationX(), (int)m.getDestinationY());
	}
*/	
	void drawPlan(Graphics g, Model m) {
		g.setColor(Color.red);
		if(!solution.isEmpty())
			for(GameState state : solution)
			{
				g.drawLine((int)state.getParent().getX(), (int)state.getParent().getY(), (int)state.getX(), (int)state.getY());
			}
	}
	
	void drawFrontier(Graphics g) {
		g.setColor(Color.orange);
		if(frontier != null)
			for(GameState state : frontier)
			{
				g.fillOval(state.getX(), state.getY(), 5, 5);
			}
	}

	void update(Model m)
	{
		if(flag)
		{
			GameState.initializeModel(m);
			GameState.initializeMaxSpeed();
			flag = false;
		}
		if(finalX >= 0 && finalY >= 0)
			m.setDestination(finalX, finalY);
		Controller c = m.getController();
		while(true)
		{
			MouseEvent e = c.nextMouseEvent();
			if(e == null)
				break;
			m.setDestination(e.getX(), e.getY());
			buttonClick = e.getButton();
			//System.out.println(e.getButton());
			finalX = e.getX();
			finalY = e.getY();
		}
		//System.out.println(m.getX());
		//System.out.println(m.getY());
		//System.out.println(m.getDestinationX());
		//System.out.println(m.getDestinationY());
		int xx = Math.round(m.getX()/10)*10;
		int yy = Math.round(m.getY()/10)*10;
		int xxDest = Math.round(m.getDestinationX()/10)*10;
		int yyDest = Math.round(m.getDestinationY()/10)*10;
		//System.out.println(xx);
		//System.out.println(yy);
		//System.out.println(xxDest);
		//System.out.println(yyDest);
		if(xx != xxDest  || yy != yyDest)
		{
			//System.out.println("Am I here?");
			GameState start = new GameState(0.0, null, xx, yy);
			GameState goal = new GameState(0.0, null, xxDest, yyDest);
			GameState done = new GameState();
			if(buttonClick == 1)
			{
				//System.out.println("Do Uniform Cost Search");
				UniformCostSearch ucs = new UniformCostSearch();
				done = ucs.search(start, goal);
				this.frontier = ucs.getFrontier();
			}
			else if(buttonClick == 3)
			{
				//System.out.println("Do A* Search");
				AStarSearch ass = new AStarSearch();
				done = ass.search(start, goal);
				this.frontier = ass.getFrontier();
			}
			GameState iterator = new GameState(done);
			solution.clear();
			while(iterator.getParent() != null)
			{
				solution.add(iterator);
				//iterator.prettyPrint();
				iterator = iterator.getParent();
			}
			//start.prettyPrint();
			if(solution.size() - 1 >= 0)
			{
				//System.out.println(solution.get(solution.size() - 1).getX() + ", " + solution.get(solution.size() - 1).getY());
				m.setDestination(solution.get(solution.size() - 1).getX(), solution.get(solution.size() - 1).getY());
			}
			else
			{
				m.setDestination(finalX, finalY);
				solution.clear();
			}
			Collections.reverse(solution);
			/*
			if(flag)
			{
				System.out.println("-------------Start------------");
				start.prettyPrint();
				for(GameState state : solution)
				{
					state.prettyPrint();
				}
				goal.prettyPrint();
				//flag = false;
			}
			*/
		}
	}

	public static void main(String[] args) throws Exception
	{
		Controller.playGame();
	}
}
