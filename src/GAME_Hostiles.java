package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public class GAME_Hostiles {

	GAME_Processor processor;
	ENTITY_Hostile hostiles[] = new ENTITY_Hostile[5];
	int[] spawnpoints = new int[8];

	public GAME_Hostiles(GAME_Processor processor) {

		this.processor = processor;

		setDefaultValues();
	}
	private void setDefaultValues() {

		for(int i = 0; i < hostiles.length; i++) {
			hostiles[i] = new ENTITY_Hostile();
		}

		generateHostiles();
	}
	private boolean isSpawnUsed(int spawnpoint) {
		
		for(int i : spawnpoints) {
			if(i == spawnpoint) {
				return true;
			}
		}
		return false;
	}
	void generateHostiles() {

		for(int i = 0; i < hostiles.length; i++) {

			double randAlignment = (Math.random() * 8);
			int randAlignmentInt = ((int) randAlignment);

			if(!isSpawnUsed(randAlignmentInt)) {}
			else {
				while(isSpawnUsed(randAlignmentInt)) {
					randAlignment = (Math.random() * 8);
					randAlignmentInt = ((int) randAlignment);
				}
			}
			hostiles[i].x = (randAlignmentInt * processor.TILE_SIZE);
			hostiles[i].y = -processor.TILE_SIZE;
			spawnpoints[i] = randAlignmentInt;

			double randSpeed = (Math.random() * 3);
			int randSpeedInt = (((int) randSpeed) + 2);
			hostiles[i].speed = randSpeedInt;
			if(randSpeedInt == 2) {
				hostiles[i].gif =
				Toolkit.getDefaultToolkit().createImage("res/hostile_slow.gif");
			}
			else if (randSpeedInt == 3) {
				hostiles[i].gif =
				Toolkit.getDefaultToolkit().createImage("res/hostile_moderate.gif");
			}
			else if (randSpeedInt == 4) {
				hostiles[i].gif =
				Toolkit.getDefaultToolkit().createImage("res/hostile_fast.gif");
			}

			double randProj = (Math.random() * 2);
			int randProjInt = ((int) randProj);
			if(randProjInt == 0) {
				hostiles[i].hasProjectile = true;
			} else {
				hostiles[i].hasProjectile = false;
			}
		}
	}
	void update() {

		for(int i = 0; i < hostiles.length; i++) {
			hostiles[i].y += hostiles[i].speed;
		}
	}
	void draw(Graphics2D g2) {

		for(int i = 0; i < hostiles.length; i++) {
			g2.drawImage(hostiles[i].gif, hostiles[i].x, hostiles[i].y,
			processor.TILE_SIZE, processor.TILE_SIZE, processor);
		}
	}
}
