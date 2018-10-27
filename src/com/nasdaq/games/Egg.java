package com.nasdaq.games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	private static Random r = new Random();
	int row, col;
	int w = Yard.BLOCK_SIZE;
	int h = Yard.BLOCK_SIZE;
	
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Egg() {
		this(r.nextInt(Yard.ROWS), r.nextInt(Yard.COLS));
	}
	public void reAppear() {
		this.row = r.nextInt(Yard.ROWS - 3) + 3;
		this.col = r.nextInt(Yard.COLS - 3) + 3;
		
	}
	public Rectangle getRect() {
		return new Rectangle(this.col * Yard.BLOCK_SIZE, this.row * Yard.BLOCK_SIZE, this.w, this.h);
	}
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillOval(col * Yard.BLOCK_SIZE, row * Yard.BLOCK_SIZE, w, h);
		g.setColor(c);
	}
		
	
}
