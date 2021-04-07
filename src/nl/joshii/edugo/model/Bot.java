package nl.joshii.edugo.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import nl.joshii.edugo.controller.GoStoneController;

public class Bot extends TimerTask {
	private Byte size;
	private Boolean turn;
	private ArrayList<GoStone> goStones;
	private GoStoneController goStonecontroller;
	
	public Bot(Boolean turn, Byte size, ArrayList<GoStone> goStones, GoStoneController goStoneController) {
		this.turn = turn;
		this.size = size;
		this.goStones = goStones;
		this.goStonecontroller = goStoneController;
	}
	
	@Override
	public void run() {
		Boolean loop = turn;
		do {
			// Generate a random number between 0 and the width/height of the board + 1
			int x = new Random().nextInt(this.size + 1), y = new Random().nextInt(this.size + 1);
			
			// "Click" on the tile matching the x and y values
			for (GoStone gs : this.goStones) {
				if (gs.getPositionX() == x && gs.getPositionY() == y) {
					if(this.goStonecontroller.checkGoStonePosition(gs, turn)) {
						turn = gs.showGoStone(turn);
					}
				}
			}
		} while ((loop == turn));
	}
}
