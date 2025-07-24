package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

// DEBUG IMPORT 
import java.awt.Color;


public class GAME_Map {

	GAME_Processor processor;
	PARENT_Obstacle[] obstacles = new PARENT_Obstacle[2];

	public GAME_Map(GAME_Processor processor) {

		this.processor = processor;

		setDefaultValues();
	}
	private void setDefaultValues() {

		for(int i = 0; i < obstacles.length; i++) {
			obstacles[i] = new PARENT_Obstacle();
		}

		generateObstacles();
	}
	void generateObstacles() {

		for(int i = 0; i < obstacles.length; i++) {

			double randAlignment = (Math.random() * 2);
			int randAlignmentInt = ((int) randAlignment);
			if(randAlignmentInt == 0) {
				obstacles[i].isVertical = true;
			} else {
				obstacles[i].isVertical = false;
			}

			double randAnchor = (Math.random() * 8);
			int randAnchorInt = ((int) randAnchor);
			if(obstacles[i].isVertical) {
				obstacles[i].gif = Toolkit.getDefaultToolkit().createImage("res/obstacle_vertical.gif");
				obstacles[i].x = (randAnchorInt * processor.TILE_SIZE);
				obstacles[i].y = processor.PLAY_AREA_HEIGHT;
				obstacles[i].width = processor.TILE_SIZE;
				obstacles[i].height = processor.PLAY_AREA_HEIGHT;
			} else {
				obstacles[i].gif = Toolkit.getDefaultToolkit().createImage("res/obstacle_horizontal.gif");
				obstacles[i].x = 0;
				obstacles[i].y = (processor.PLAY_AREA_HEIGHT + (randAnchorInt * processor.TILE_SIZE));
				obstacles[i].width = processor.SCREEN_WIDTH;
				obstacles[i].height = processor.TILE_SIZE;
			}

			obstacles[i].hitbox = new Rectangle(obstacles[i].x,
			obstacles[i].y, obstacles[i].width, obstacles[i].height);
		}
	}
	void draw(Graphics2D g2) { 

		for(int i = 0; i < obstacles.length; i++) {
			g2.drawImage(obstacles[i].gif, obstacles[i].x, obstacles[i].y,
			obstacles[i].width, obstacles[i].height, processor);

			// DEBUG CODE
			g2.setColor(Color.GREEN);
			g2.drawRect(obstacles[i].x, obstacles[i].y, obstacles[i].width, obstacles[i].height);
		}
	}
}
