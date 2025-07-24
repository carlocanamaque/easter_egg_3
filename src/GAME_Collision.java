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
	private void checkObstacleCollision() {

		for(int i = 0; i < map.obstacles.length; i++) {
			if(player.hitbox.intersects(map.obstacles[i].hitbox)) {
				System.out.println("Collided with: Obstacle");			// Debug code
			}
		}
	}
	private void checkHostileCollision() {

		for(int i = 0; i < hostiles.HOSTILE_BATCH_COUNT; i++) {
			for(int j = 0; j < hostiles.HOSTILE_COUNT_PER_BATCH; j++) {
				if(hostiles.hostiles[i][j].hitbox != null &&
				player.hitbox.intersects(hostiles.hostiles[i][j].hitbox)) {
					System.out.println("Collided with: Hostile");		// Debug code
				}
			}
		}
	}
	void update() {

		checkObstacleCollision();
		checkHostileCollision();
	}
}
