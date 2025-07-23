package src;


public class GAME_Collision {

	GAME_Processor processor;
	ENTITY_Player player;
	GAME_Map map;
	GAME_Hostiles hostiles;

	public GAME_Collision(GAME_Processor processor) {

		this.processor = processor;
		this.player = processor.player;
		this.map = processor.map;
		this.hostiles = processor.hostiles;
	}
	private boolean checkObstacleCollision() {

		// TO-DO: code here...
		return false;
	}
	private boolean checkHostileCollision() {

		// TO-DO: code here...
		return false;
	}
	void update() {

		if(checkObstacleCollision() || checkHostileCollision()) {

			// TO-DO: code here...
		}
	}
}
