package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GAME_Processor extends JPanel {

	final int ORIG_TILE_SIZE = 16;
	final int TILE_SIZE_SCALE_RATE = 3;
	final int TILE_SIZE = (ORIG_TILE_SIZE * TILE_SIZE_SCALE_RATE);

	final int MAX_SCREEN_COL = 8;
	final int MAX_SCREEN_ROW = 16;
	final int SCREEN_WIDTH = (TILE_SIZE * MAX_SCREEN_COL);
	final int SCREEN_HEIGHT = (TILE_SIZE * MAX_SCREEN_ROW);

	final int PLAY_AREA_HEIGHT = (TILE_SIZE * (MAX_SCREEN_ROW / 2));

	final int FRAME_RATE_PER_SEC = 12;

	private GAME_Input input = new GAME_Input();
	private ENTITY_Player player = new ENTITY_Player(this, input);
	private GAME_Map map = new GAME_Map(this);
	private GAME_Hostiles hostiles = new GAME_Hostiles(this);

	private Runnable mainRunnable;
	private Runnable obstacleRunnable;
	private Runnable hostileRunnable;

	private Thread mainThread;
	private Thread obstacleThread;
	private Thread hostileThread;
	
	public GAME_Processor() {

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLUE);
		this.setDoubleBuffered(true);
		this.addKeyListener(input);
		this.setFocusable(true);

		mainRunnable = () -> {
       	    		double drawInterval = 1000000000/FRAME_RATE_PER_SEC;
			double delta = 0;
			long lastTime = System.nanoTime();
			long currentTime;

			while (mainThread != null) {

				currentTime = System.nanoTime();

				delta += (currentTime - lastTime) / drawInterval;
				lastTime = currentTime;

				if (delta >= 1) {

					update();
					repaint();
					delta--;
				}
			}
        	};
		obstacleRunnable = () -> {
			double drawInterval = 1000000000/FRAME_RATE_PER_SEC;
			double delta = 0;
			long lastTime = System.nanoTime();
			long currentTime;

			while (mainThread != null) {

				currentTime = System.nanoTime();

				delta += (currentTime - lastTime) / drawInterval;
				lastTime = currentTime;

				if (delta >= 30) {

					map.generateObstacles();
					delta -= 30;
				}
			}
		};
		hostileRunnable = () -> {
			double drawInterval = 1000000000/FRAME_RATE_PER_SEC;
			double delta = 0;
			long lastTime = System.nanoTime();
			long currentTime;

			while (mainThread != null) {

				currentTime = System.nanoTime();

				delta += (currentTime - lastTime) / drawInterval;
				lastTime = currentTime;

				if (delta >= 30) {

					hostile.generateHostiles();
					delta -= 15;
				}
			}
		};

	}
	void startAllThreads() {

		mainThread = new Thread(mainRunnable);
		obstacleThread = new Thread(obstacleRunnable);
		hostileThread = new Thread(hostileRunnable);

		mainThread.start();
		obstacleThread.start();
		hostileThread.start();
	}
	private void update() {

		player.update();
		hostiles.update();
	}
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		map.draw(g2);
		player.draw(g2);
		hostiles.draw(g2);

		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("linux")) {
			Toolkit.getDefaultToolkit().sync();
		}

		g2.dispose();
	}
	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("easter_egg_3");

		GAME_Processor processor = new GAME_Processor();
		window.add(processor);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		processor.startAllThreads();
	}
}


