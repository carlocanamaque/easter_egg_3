package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public class GAME_Hostiles {

	private final int HOSTILE_BATCH_COUNT = 10;
	private final int HOSTILE_COUNT_PER_BATCH = 5;

	GAME_Processor processor;
	ENTITY_Hostile hostiles[][] = new ENTITY_Hostile[HOSTILE_BATCH_COUNT][HOSTILE_COUNT_PER_BATCH];
	int[] spawnpoints;

	public GAME_Hostiles(GAME_Processor processor) {

		this.processor = processor;

		setDefaultValues();
	}
	private void setDefaultValues() {

		spawnpoints = new int[processor.MAX_SCREEN_COL];

		for(int i = 0; i < HOSTILE_BATCH_COUNT; i++) {
			for(int j = 0; j < HOSTILE_COUNT_PER_BATCH; j++) {
				hostiles[i][j] = new ENTITY_Hostile();
				hostiles[i][j].isEliminated = true;
			}
		}
	}
	private boolean isSpawnUsed(int spawnpoint) {
		
		for(int i : spawnpoints) {
			if(i == spawnpoint) {
				return true;
			}
		}
		return false;
	}
	void generateHostileBatch(int row) {

		for(int i = 0; i < HOSTILE_COUNT_PER_BATCH; i++) {

			double randAlignment = (Math.random() * 8);
			int randAlignmentInt = (((int) randAlignment) + 1);

			if(!isSpawnUsed(randAlignmentInt)) {}
			else {
				while(isSpawnUsed(randAlignmentInt)) {
					randAlignment = (Math.random() * 8);
					randAlignmentInt = (((int) randAlignment) + 1);
				}
			}
			hostiles[row][i].x = ((randAlignmentInt - 1) * processor.TILE_SIZE);
			hostiles[row][i].y = -processor.TILE_SIZE;

			for(int j = 0; j < spawnpoints.length; j++) {
				if(spawnpoints[j] == 0) {
					spawnpoints[j] = randAlignmentInt;
				}
			}

			double randSpeed = (Math.random() * 3);
			int randSpeedInt = (((int) randSpeed) + 2);
			hostiles[row][i].speed = randSpeedInt;
			if(randSpeedInt == 2) {
				hostiles[row][i].gif =
				Toolkit.getDefaultToolkit().createImage("res/hostile_slow.gif");
			}
			else if (randSpeedInt == 3) {
				hostiles[row][i].gif =
				Toolkit.getDefaultToolkit().createImage("res/hostile_moderate.gif");
			}
			else if (randSpeedInt == 4) {
				hostiles[row][i].gif =
				Toolkit.getDefaultToolkit().createImage("res/hostile_fast.gif");
			}

			double randProj = (Math.random() * 2);
			int randProjInt = ((int) randProj);
			if(randProjInt == 0) {
				hostiles[row][i].hasProjectile = true;
			} else {
				hostiles[row][i].hasProjectile = false;
			}

			hostiles[row][i].isEliminated = false;
		}
	}
	void update() {

		for(int i = 0; i < HOSTILE_BATCH_COUNT; i++) {
			for(int j = 0; j < HOSTILE_COUNT_PER_BATCH; j++) {
				if(!hostiles[i][j].isEliminated) {
					hostiles[i][j].y += hostiles[i][j].speed;
				}
			}
		}
	}
	void draw(Graphics2D g2) {

		for(int i = 0; i < HOSTILE_BATCH_COUNT; i++) {
			for(int j = 0; j < HOSTILE_COUNT_PER_BATCH; j++) {
				if(!hostiles[i][j].isEliminated) {
					g2.drawImage(hostiles[i][j].gif, hostiles[i][j].x,
					hostiles[i][j].y, processor.TILE_SIZE,
					processor.TILE_SIZE, processor);
				}
			}
		}
	}
}
