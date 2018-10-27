package com.nasdaq.games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {

	private Node head = null;
	private Node tail = null;
	private int size = 0;
	private int Max_ROW = 0;
	private int Max_COL = 0;
	Dir dir = Dir.L;
	
	private Node n = new Node(10, 10, Dir.L);
	
	
	public Snake(int m_row, int m_col) {
		head = n;
		tail = n;
		size = 1;
		Max_ROW = m_row;
		Max_COL = m_col;
	}
	
	public void draw (Graphics g) {
		if(size <=0 ) 
		{
			return;
		}
			
		for (Node n = head; n != null;n = n.next)
		{
			n.draw(g);
		}
		move();
	}
	private void move() {
		addToHead();
		deleteFromTail();
	}

	private void deleteFromTail() {
		if (size == 0) return;
		tail = tail.prev;
		tail.next = null;
		//this.size--;
		}

	public void addToHead() {
		Node node = null;
		int new_R = head.row;
		int new_C = head.col;

		switch(head.dir) {
		case L:
			new_C = (head.col<=1)? Max_COL : (head.col - 1);
			break;
		case R:
			new_C = (head.col>= Max_COL)? 1 : (head.col + 1);
			break;
		case U:
			new_R = (head.row <= 1)? Max_ROW : (head.row - 1);
			break;
		case D:
			new_R = (head.row >= Max_ROW)? 1 : (head.row + 1);
			break;
			
		}
		node = new Node(new_R, new_C, head.dir);
		node.next = head;
		head.prev = node;
		head = node;
		size ++;
		
	}

	public void addToTail () {
		Node node = null;
		int new_R = head.row;
		int new_C = head.col;
		
		switch(head.dir) {
		case L:
			new_C += 1;
			break;
		case R:
			new_C -= 1;
			break;
		case U:
			new_R += 1;
			break;
		case D:
			new_R -= 1;
			break;
			
		}
		node = new Node(new_R, new_C, head.dir);

		tail.next = node;
		node.prev = tail;
		tail = node;
		size ++;
	}

	private class Node {
		int w = Yard.BLOCK_SIZE;
		int h = Yard.BLOCK_SIZE;
		int row, col;
		Dir dir = Dir.L;
		Node next = null;
		Node prev = null;
	
	
		public Node(int row, int col, Dir dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.next = null;
		}
		
		private void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(col * Yard.BLOCK_SIZE, row * Yard.BLOCK_SIZE, w, h);
			g.setColor(c);
			
		} 
	}
	public void eat(Egg e) {
		if(this.getRect().intersects(e.getRect())) {
			e.reAppear();
			this.addToHead();
		}
	}
	private Rectangle getRect() {
		return new Rectangle(head.col * Yard.BLOCK_SIZE, head.row * Yard.BLOCK_SIZE, head.w, head.h);
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			head.dir = Dir.L;
			break;
		case KeyEvent.VK_RIGHT:
			head.dir = Dir.R;
			break;
		case KeyEvent.VK_UP:
			head.dir = Dir.U;
			break;
		case KeyEvent.VK_DOWN:
			head.dir = Dir.D;
			break;
				}
		
	}
}
