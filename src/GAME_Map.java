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
	private void setDefaultValues() {

		// TO-DO: code here...
	}
	private void generateObstacles() {

		for(int i = 0; i < obstacles.length; i++) {

			double randAlignment = (Math.random() * 2);
			int randInt = ((int) randAlignment);
			if(randInt == 0) {
				obstacles[i].isVertical = true;
			} else {
				obstacles[i].isVertical = false;
			}

			double randAnchor = (Math.random() * 8);
			int randAnchorInt = ((int) randAnchor);
			if(obstacles[i].isVertical) {
				obstacles[i].x = (randAnchorInt * processor.TILE_SIZE);
				obstacles[i].y = processor.PLAY_AREA_HEIGHT;

			} else {
				obstacles[i].x = 0;
				obstacles[i].y = (processor.PLAY_AREA_HEIGHT +
				(randAnchorInt * processor.TILE_SIZE));
			}
		}

		// TO-DO: code here...
	}
	void draw(Graphics2D g2) { 

		for(int i = 0; i < obstacles.length; i++) {
			g2.drawImage(obstacles[i].gif, obstacles[i].x, obstacles[i].y,
			obstacles[i].width, obstacles[i].height, processor);
		}
	}
}
