package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GAME_Input implements KeyListener {

	boolean wPressed, aPressed, sPressed, dPressed, pPressed;	

	@Override
	public void keyTyped(KeyEvent e) {
	
		// NOTE: not implemented...
	}
	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		switch(code) {
			case KeyEvent.VK_W:
				wPressed = true;
				break;
			case KeyEvent.VK_A:
				aPressed = true;
				break;
			case KeyEvent.VK_S:
				sPressed = true;
				break;
			case KeyEvent.VK_D:
				dPressed = true;
				break;
			case KeyEvent.VK_P:
				pPressed = true;
				break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		switch(code) {
			case KeyEvent.VK_W:
				wPressed = false;
				break;
			case KeyEvent.VK_A:
				aPressed = false;
				break;
			case KeyEvent.VK_S:
				sPressed = false;
				break;
			case KeyEvent.VK_D:
				dPressed = false;
				break;
			case KeyEvent.VK_P:
				pPressed = false;
				break;
		}
	}
}
