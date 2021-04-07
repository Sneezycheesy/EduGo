package nl.joshii.edugo.controller;

import java.util.ArrayList;
import java.util.Timer;

import javafx.scene.paint.Color;
import nl.joshii.edugo.model.Bot;
import nl.joshii.edugo.model.GoStone;

public class GoStoneController {
	private ArrayList<GoStone> goStones = new ArrayList<GoStone>();
	private Byte size;
	
	public GoStoneController(Byte size) {
		this.size = size;
	}
	
	public void addGostone(GoStone goStone) {
		this.goStones.add(goStone);
	}
	
	public GoStone createGoStone(Byte loop1, Byte loop2) {
		return new GoStone(loop1, loop2);
	}
	
	/**
	 * Check the position of the clicked stone, if it´s surrounded by the opposing player, it can´t be placed
	 * @param goStone 		The stone that was clicked
	 * @return				The possibility of placing the stone in this spot
	 */
	public Boolean checkGoStonePosition(GoStone goStone, Boolean turn) {
		Byte x = goStone.getPositionX();
		Byte y = goStone.getPositionY();
		Color color = goStone.getColor();
		if (color == null) {
			color = turn ? Color.WHITE : Color.BLACK;
		}
		
		ArrayList<GoStone> goStones = new ArrayList<GoStone>();
		for (GoStone gs : this.goStones) {
			if (gs.getColor() != color && gs.getColor() != null) {
				if (gs.getPositionX() == x - 1 && gs.getPositionY() == y) { 			// Stone to the Left
					goStones.add(gs);
				} else if (gs.getPositionX() == x + 1 && gs.getPositionY() == y) {		// Stone to the Right
					goStones.add(gs);	
				} else if (gs.getPositionX() == x && gs.getPositionY() == y - 1) { 		// Stone Above
					goStones.add(gs);
				} else if (gs.getPositionX() == x && gs.getPositionY() == y + 1) {		// Stone Below
					goStones.add(gs);
				}
			}
		}
		switch (goStones.size()) {
			case 4: goStone.setColor(); 
					return false;
			default: return true;
		}
	}
	
	/**
	 * Check what Stone was clicked on and change the colour accordingly
	 * @param turn			The turn it is (true = white; false = black)
	 * @param goStone		The place on the board, or "GoStone" that was clicked
	 * @return				The new turn value, if it changes the other player can click
	 * 						if it doesn´t, no Stone will be placed and the current turn is not over
	 */
	public Boolean showGoStone(Boolean turn, GoStone goStone) {		
		if (this.checkGoStonePosition(goStone, turn)) {
			turn = goStone.showGoStone(turn);
			for (GoStone gs : this.goStones) {
				this.checkGoStonePosition(gs, turn);
			}
		}
		
		return turn;
	}
	
	/**
	 * [TODO] Add multithreading to wait 1.5-2 seconds before placing a stone
	 * [BUG] The player can place new stones while the bot is "deciding" what to play
	 * 		 Make sure the player has to wait 1.5 seconds before placing a new stone
	 * Place a stone as the "AI"
	 * @param turn			The current turn, to decide which colour to place (this is always white)
	 * @return				!turn, to declare it´s the other player´s turn
	 */
	public Boolean playAsBot(Boolean turn) {
		ArrayList<GoStone> goStones = (ArrayList<GoStone>)this.goStones.clone();
		Bot bot = new Bot(turn, this.size, goStones, this);
		Timer timer = new Timer();
		timer.schedule(bot, 1500);
		for (GoStone gs : this.goStones) {
			this.checkGoStonePosition(gs, turn);
		}
		return !turn;
	}
}
