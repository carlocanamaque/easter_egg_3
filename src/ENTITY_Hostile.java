package src;

import java.awt.Image;
import java.awt.Toolkit;


public class ENTITY_Hostile extends PARENT_Entity {

	boolean hasProjectile, isEliminated;
	PARENT_Projectile projectile = new PARENT_Projectile();

	public ENTITY_Hostile() {

		projectile.gif = Toolkit.getDefaultToolkit().createImage("res/hostile_bullet.gif");
	}
}
