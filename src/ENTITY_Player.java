package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public class ENTITY_Player extends PARENT_Entity {

	GAME_Processor processor;
	GAME_Input input;

	public ENTITY_Player(GAME_Processor processor, GAME_Input input) {

		this.processor = processor;
		this.input = input;

		setDefaultValues();
	}
	private void setDefaultValues() {

		x = 0;
		y = 0;

		speed = processor.TILE_SIZE;

		gif = Toolkit.getDefaultToolkit().createImage("res/ENTITY_Player_gif.gif");
	}
	void update() {

		int nextX = x;
		int nextY = y;

		if (input.wPressed) {
			nextY -= speed;
		} else if (input.aPressed) {
			nextX -= speed;
		} else if (input.sPressed) {
			nextY += speed;
		} else if (input.dPressed) {
			nextX += speed;
		}

		if (nextX >= 0 && nextX <= (processor.SCREEN_WIDTH - processor.TILE_SIZE) &&
		    nextY >= 0 && nextY <= (processor.SCREEN_HEIGHT - processor.TILE_SIZE)) {
			x = nextX;
			y = nextY;
		}
	}
	void draw(Graphics2D g2) {

		g2.drawImage(gif, x, y, processor.TILE_SIZE, processor.TILE_SIZE, processor);
	}
}
