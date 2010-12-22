package by.bsu.fami.openshop.gui.controls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.*;

import by.bsu.fami.openshop.resources.ResourcesProvider;

/**
 * The startup splash frame. 
 * @author eigenein
 *
 */
@SuppressWarnings("serial")
public class SplashFrame extends JFrame {

	private final Image logo;
	
	private float progress = 0.0f;
	
	/**
	 * Initializes a new instance.
	 */
	public SplashFrame() {
		super(ResourcesProvider.get().getString("openshop.splash.title"));
		
		logo = ResourcesProvider.get().getImage("openshop.logo");
		setUndecorated(true);
		setSize(logo.getWidth(null), logo.getHeight(null));
		setLocationRelativeTo(null);
	}
	
	/**
	 * Updates the progress.
	 */
	public void updateProgress(float progress) {
		this.progress = progress;
		repaint();
	}
	
	private static final int PROGRESS_HEIGHT = 5;
	private static final int PROGRESS_WIDTH = 460;
	private static final int PROGRESS_LEFT = 10;
	private static final int PROGRESS_TOP = 260;
	
	@Override
	public void paint(Graphics g) {
		/* Double buffering. */
		BufferedImage buffer = new BufferedImage(logo.getWidth(null), 
				logo.getHeight(null), 
				BufferedImage.TYPE_INT_RGB);
		Graphics bufferedG = buffer.getGraphics();
		
		/* Draw background. */
		bufferedG.drawImage(logo, 0, 0, null);
		
		/* Draw progress. */
		bufferedG.setColor(Color.BLACK);
		bufferedG.fillRect(PROGRESS_LEFT, PROGRESS_TOP, PROGRESS_WIDTH, PROGRESS_HEIGHT);
		bufferedG.setColor(Color.WHITE);
		bufferedG.fillRect(PROGRESS_LEFT, PROGRESS_TOP, 
				(int)(PROGRESS_WIDTH * progress), PROGRESS_HEIGHT);
		
		g.drawImage(buffer, 0, 0, null);
	}

}
