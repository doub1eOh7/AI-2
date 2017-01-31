import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;

class Agent {

	void drawPlan(Graphics g, Model m) {
		g.setColor(Color.red);
		g.drawLine((int)m.getX(), (int)m.getY(), (int)m.getDestinationX(), (int)m.getDestinationY());
	}

	void update(Model m)
	{
		Controller c = m.getController();
		while(true)
		{
			MouseEvent e = c.nextMouseEvent();
			if(e == null)
				break;
			m.setDestination(e.getX(), e.getY());
		}
	}

	public static void main(String[] args) throws Exception
	{
		Controller.playGame();
		Location startCoordinates = new Location(0, 0);
		GameState start = new GameState(0.0, null, startCoordinates);
		Location goalCoordinates = new Location(12, 15);
		GameState goal = new GameState(0.0, null, goalCoordinates);
		UniformCostSearch search = new UniformCostSearch();
		search.UniformCostSearch(start, goal);
	}
}
