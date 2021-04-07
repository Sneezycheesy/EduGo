package nl.joshii.edugo.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GoStone extends Circle{
	private Color color;
	private Byte positionX;
	private Byte positionY;
	private Boolean played;
	
	public GoStone(Byte positionX, Byte positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.played = false;
	}
	
	public Byte getPositionX() {
		return this.positionX;
	}
	
	public Byte getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Change the fill and stroke colours of the stone so it´s visible on the board
	 * @param turn		The current turn (treu = white, false = black)
	 * @return			The new current turn (!turn)
	 */
	public Boolean showGoStone(Boolean turn) {
			if (!played) {
				this.color = turn ? Color.WHITE : Color.BLACK;
				this.setFill(color);
				this.setStroke(turn ? Color.BLACK : Color.WHITE);
				this.setStrokeWidth(1);
				this.played = true;
				this.setOpacity(100);
				return !turn;
			}	
		return turn;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Set the colour to null, opacity to 0 and played to false
	 * to make sure the spot can be played again
	 */
	public void setColor() {
		this.color = null;
		this.setOpacity(0);
		this.played = false;
	}
}
