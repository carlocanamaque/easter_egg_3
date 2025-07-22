package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public class ENTITY_Player extends PARENT_Entity {

	final int PROJECTILE_LIMIT;

	GAME_Processor processor;
	GAME_Input input;

	PARENT_Projectile[] projectiles;
	int projectileCount;

	public ENTITY_Player(GAME_Processor processor, GAME_Input input) {

		this.processor = processor;
		this.input = input;

		PROJECTILE_LIMIT  = (processor.MAX_SCREEN_COL / 2);

		setDefaultValues();
	}
	private void setDefaultValues() {

		x = 0;
		y = 0;
		speed = processor.TILE_SIZE;
		gif = Toolkit.getDefaultToolkit().createImage("res/player.gif");

		projectiles = new PARENT_Projectile[PROJECTILE_LIMIT];
		projectileCount = 0;
	}
	private void useProjectile() {

		projectiles[projectileCount] = new PARENT_Projectile();
		projectiles[projectileCount].x = x;
		projectiles[projectileCount].y = (y - ((processor.MAX_SCREEN_ROW - 1) * processor.TILE_SIZE));
		projectiles[projectileCount].gif = Toolkit.getDefaultToolkit().createImage("res/player_bullet.gif");

		projectileCount++;
	}
	void update() {

		// SOL'N-TO-BUG: utilize a timer to freeze pPressed reading 
		//               so projectiles wouldn't stack with fast FPS.
		if(input.pPressed && projectileCount < PROJECTILE_LIMIT) {
			useProjectile();
		}

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

		for(int i = 0; i < (projectileCount - 1); i++) {
			g2.drawImage(projectiles[i].gif, projectiles[i].x, projectiles[i].y,
			processor.TILE_SIZE, ((processor.MAX_SCREEN_ROW-1)*processor.TILE_SIZE),
			processor);
		}

		g2.drawImage(gif, x, y, processor.TILE_SIZE, processor.TILE_SIZE, processor);
	}
}
