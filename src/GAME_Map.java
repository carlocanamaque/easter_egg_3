package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public class GAME_Map {

	GAME_Processor processor;
	PARENT_Obstacle[] obstacles = new PARENT_Obstacle[2];

	public GAME_Map(GAME_Processor processor) {

		this.processor = processor;
	}
	private void designateObstacles() {

		// TO-DO: code here...
	}
	void draw(Graphics2D g2) { 

		for(int i = 0; i < obstacles.length; i++) {
			g2.drawImage(obstacles[i].gif, obstacles[i].x, obstacles[i].y,
			obstacles[i].width, obstacles[i].height, processor);
		}
	}
}
