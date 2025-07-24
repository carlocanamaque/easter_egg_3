package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class ENTITY_Player extends PARENT_Entity {

	final int PROJECTILE_LIMIT;

	GAME_Processor processor;
	GAME_Input input;

	PARENT_Projectile[] projectiles;
	int projectileCount;
	boolean projectileReload;

	public ENTITY_Player(GAME_Processor processor, GAME_Input input) {

		this.processor = processor;
		this.input = input;

		PROJECTILE_LIMIT  = (processor.MAX_SCREEN_COL / 2);

		setDefaultValues();
	}
	private void setDefaultValues() {

		x = 0;
		y = (processor.TILE_SIZE * (processor.MAX_SCREEN_ROW - 1));
		speed = processor.TILE_SIZE;
		gif = Toolkit.getDefaultToolkit().createImage("res/player.gif");

		projectiles = new PARENT_Projectile[PROJECTILE_LIMIT];
		projectileCount = 0;

		hitX = x;
		hitY = y;
		hitbox = new Rectangle(hitX, hitY, processor.TILE_SIZE, processor.TILE_SIZE);
	}
	private void useProjectile() {

		projectiles[projectileCount] = new PARENT_Projectile();
		projectiles[projectileCount].x = x;
		projectiles[projectileCount].y = (y - ((processor.MAX_SCREEN_ROW - 1) * processor.TILE_SIZE));
		projectiles[projectileCount].gif = Toolkit.getDefaultToolkit().createImage("res/player_bullet.gif");

		System.out.println(projectileCount);		// DEBUG: print code.

		projectileCount++;

		if(projectileCount == PROJECTILE_LIMIT) {

			projectileReload = true;
			System.out.println("Reloading...");	// DEBUG: print code.

			new javax.swing.Timer(2000, e -> {
				input.pPressed = false;
				projectiles = new PARENT_Projectile[PROJECTILE_LIMIT];
				projectileCount = 0;
				System.out.println("Loaded.");	// DEBUG: print code.
			((javax.swing.Timer) e.getSource()).stop();
			}).start();

			projectileReload = false;
		}
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

		if(input.pPressed && (projectileCount < PROJECTILE_LIMIT)) {
			useProjectile();
		}

		if (nextX >= 0 && nextX <= (processor.SCREEN_WIDTH - processor.TILE_SIZE) &&
		nextY >= processor.PLAY_AREA_HEIGHT && nextY <= (processor.SCREEN_HEIGHT - processor.TILE_SIZE)) {
			x = nextX;
			y = nextY;
		}

		hitX = x;
		hitY = y;
	}
	void draw(Graphics2D g2) {

		for(int i = 0; i < projectileCount; i++) {
			if(!projectileReload) {
				g2.drawImage(projectiles[i].gif, projectiles[i].x, projectiles[i].y,
				processor.TILE_SIZE, ((processor.MAX_SCREEN_ROW-1)*processor.TILE_SIZE),
				processor);
			}
		}

		g2.setColor(Color.WHITE);
		g2.drawLine(0, processor.PLAY_AREA_HEIGHT, processor.SCREEN_WIDTH, processor.PLAY_AREA_HEIGHT);

		g2.drawImage(gif, x, y, processor.TILE_SIZE, processor.TILE_SIZE, processor);
	}
}
