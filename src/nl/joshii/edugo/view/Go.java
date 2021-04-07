package nl.joshii.edugo.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nl.joshii.edugo.controller.GoStoneController;
import nl.joshii.edugo.model.GoStone;

public class Go extends Application {
	private Group selectionGroup = new Group();
	private Group group = new Group(); //creating Group
	private Byte size = 13;
	private int dimension = 700 / size;
	private Boolean turn = false;
	private GoStoneController goStoneController;
	private Stage primaryStage;
	
	//################################
	//####### Public Methods #########
	//################################
	
	@Override  
    public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.goStoneController = new GoStoneController(this.size);
        // TODO Auto-generated method stub
//	    this.createBoard();			
//	    this.createStones();
		this.createBoardSelection();
		
			
	    this.primaryStage.setTitle("EduGO");   
	     
	    Scene scene = new Scene(selectionGroup,1000,1000,Color.rgb(211, 192, 107));  
	    this.primaryStage.setScene(scene);  
	    this.primaryStage.show();
	}
			
	public static void main(String[] args) {  
	    launch(args);  
	}
	

	//################################
	//####### Private Methods ########
	//################################
	
	/**
	 * Create a scene to select the size of the board
	 * The usual sizes are 
	 * 5X5(Very small)
	 * 9x9(Small)
	 * 13x13(Medium)
	 * 19x19(Large)
	 */
	private void createBoardSelection() {
		Button button;
		Byte[] sizes = new Byte[] {
				5,
				9,
				13,
				19
		};
		Byte j = 0;
		for (Byte i : sizes) 
		{
			button = new Button(i.toString());
			button.setLayoutX(200);
			button.setLayoutY(20 + 30 * j);
			button.setPrefWidth(200);
			button.setPrefHeight(20);
			
			button.setOnMousePressed(event -> {
				this.size = i;
				this.createBoard();
				this.createStones();
				Scene scene = new Scene(group, 1000, 1000, Color.rgb(211, 192, 107));
				this.primaryStage.setScene(scene);
				this.primaryStage.show();
			});
			
			this.selectionGroup.getChildren().addAll(button);
			j++;
		}
	}
	
	
	/**
	 * Add a GoStone object to the board (it stays hidden though)
	 * @param loop1 Used for calculating the x coordinate
	 * @param loop2 Used for calculating the y coordinate
	 */
	private void addStone(Byte loop1, Byte loop2) {
		GoStone goStone = this.goStoneController.createGoStone(loop1, loop2);
		if(loop2 == -1) {
		    goStone.setCenterX((200 + loop1 * dimension) + (dimension));
		    goStone.setCenterY(50 + (dimension / 2));
		} else {
			goStone.setCenterX((200 - (dimension / 2)) + loop1 * dimension + dimension / 2);
			goStone.setCenterY((50 - (dimension / 2)) + loop2 * dimension + dimension / 2);
		}
		
	    goStone.setRadius(dimension / 2);
	    goStone.setOpacity(0);
	    goStone.setOnMousePressed(
	    		event -> {
	    			Boolean trun = turn;
	    			if (!turn) {
		    				turn = this.goStoneController.showGoStone(turn, goStone);
		    			if (trun != turn) {
			    			turn = this.goStoneController.playAsBot(turn);	    				
		    			}
	    			}	    			
	    		});
	    group.getChildren().addAll(goStone);
	    this.goStoneController.addGostone(goStone);

	}
	
	/**
	 * Create a visible game board
	 * [TODO] Make the loop dynamic (let user choose the size of the board (i & j))
	 */
	private void createBoard() {
		// BOARD
		for(Byte i = 0; i < size - 1; i++) {
			for (Byte j = 0; j < size - 1; j++) {
				Rectangle rect = new Rectangle(); //instantiating Rectangle   
			    rect.setX(200 + i * dimension); //setting the X coordinate of upper left corner of rectangle   
			    rect.setY(50 + j * dimension); //setting the Y coordinate of upper left corner of rectangle   
			    rect.setWidth(dimension); //setting the width of rectangle   
			    rect.setHeight(dimension); // setting the height of rectangle
			    rect.setStrokeWidth(1);
			    rect.setStroke(Color.BLACK);
			    rect.setFill(Color.rgb(221, 169, 46));
			    group.getChildren().addAll(rect); //adding rectangle to the group
			}
		}
	}
	
	private void createStones() {
		// CREATE BUTTON LAYOUT 
				for (Byte i = 0; i < size; i++) {
					for (Byte j = 0; j < size; j++) {
						this.addStone(i, j);
					}
				}
	}
}
