package com.nasdaq.games;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ROWS = 40;
	public static final int COLS = 40;
	public static final int BLOCK_SIZE = 15;
	


	Snake s = new Snake(ROWS, COLS);
	Egg e = new Egg();
	
	Image offScreenImage = null;
	
	public void launch() {
		this.setLocation(300, 300);
		this.setSize(ROWS * BLOCK_SIZE, COLS*BLOCK_SIZE );
		this.addWindowListener(new WindowAdapter()
				{

					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
			
				});
		this.setVisible(true);
		
		new Thread(new PaintThread()).start();
		this.addKeyListener(new KeyMonitor());
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Yard().launch();
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.white);
		g.fillRect(0, 0,ROWS * (BLOCK_SIZE ), COLS*(BLOCK_SIZE ));
		
		g.setColor(Color.DARK_GRAY);
		
		for(int i=1; i<ROWS; i++)
		{//horizontal 
			g.drawLine(0, BLOCK_SIZE * i, COLS*(BLOCK_SIZE ), BLOCK_SIZE * i);
		}

		for(int i=1; i<COLS; i++)
		{//horizontal 
			g.drawLine(BLOCK_SIZE * i,0, BLOCK_SIZE * i, ROWS*(BLOCK_SIZE) );
		}
		
		g.setColor(c);
		
		s.eat(e);
		e.draw(g);
		s.draw(g);
	}
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if(offScreenImage == null) {
			offScreenImage = this.createImage(ROWS * (BLOCK_SIZE ), COLS*(BLOCK_SIZE));
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private class PaintThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				repaint();
				try {
					Thread.sleep(100);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
				
		}
		
	}

	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			s.keyPressed(e);
		}
		
	}
}
