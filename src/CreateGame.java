


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class CreateGame {
	static boolean gameDone = false;
	static Stage stage;
	static GridPane view;
	public static GameLogic game = new GameLogic();
	public CreateGame(Stage gamestage) {
		stage = gamestage;
		
	};
	
	public void CreateGameBoard(){
		view = new GridPane();
		view.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 150, .5), CornerRadii.EMPTY, Insets.EMPTY)));
		view.setPadding((new Insets(40, 20, 20, 20)));
		view.setHgap(10);
		view.setVgap(10);
		
	for(int i = 0; i < 7; i++){
		  int num = i;
		  Button drop = new Button("drop");
		 drop.setPrefSize(80, 40);
		 drop.setStyle("-fx-background-color: navy; -fx-text-fill: white; -fx-font-size: 14px; "
		 		+ "-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 ); -fx-font-weight: bold;");
		  drop.setOnAction(event -> {
			  if(gameDone == false) {
			  game.findPieceSlot(num);
			 }
			 });
		  
		  view.add(drop, num, 0);
	}	
	
	 
	//return node from 0 to 41 - changes color to red

		for(int i = 1; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				Circle circle = new Circle(50, 50, 40);
				StackPane stack = new StackPane();
				Text connectText = new Text("");
				connectText.setFont(Font.font(60));
				 stack.getChildren().addAll(circle, connectText);
				
				view.add(stack, j, i);
			}
		}
				
		
		
		
		

		Scene scene = new Scene(view);

		stage.setTitle("Connect 4");
		stage.setScene(scene);
		stage.show(); 
		
	}
	
	
	public static void clearFields(){
		
	}
	
	// 2 for team yellow, 1 for team red
	public static void  setPiece(int pos, int team ){
		
	//	System.out.println("enter set piece" + pos + " " + team + " " + view.getChildren().size());
		if(team == 1) {
			//retrieves stackpane then select first element which is is circle and changes the color
			((Shape) ( (StackPane) view.getChildren().get(pos)).getChildren().get(0)).setFill(javafx.scene.paint.Color.RED);
			//((Text) ( (StackPane) view.getChildren().get(pos)).getChildren().get(1)).setText("X");
		}else if(team == 2) {
			((Shape) ( (StackPane) view.getChildren().get(pos)).getChildren().get(0)).setFill(javafx.scene.paint.Color.YELLOW);
			

		}
		
	}
	

	
	public static void drawWinner(int first, int second, int third, int fourth, int team){
		System.out.println("enter draw winner");
		
		first += 7;
		second += 7;
		third  += 7;
		fourth += 7;

		((Text) ( (StackPane) view.getChildren().get(first)).getChildren().get(1)).setText("X");
		((Text) ( (StackPane) view.getChildren().get(second)).getChildren().get(1)).setText("X");
		((Text) ( (StackPane) view.getChildren().get(third)).getChildren().get(1)).setText("X");
		((Text) ( (StackPane) view.getChildren().get(fourth)).getChildren().get(1)).setText("X");
		((Text) ( (StackPane) view.getChildren().get(fourth)).getChildren().get(1)).setText("X");
		gameDone = true;
		
		playAgain(team);
		
		
		 
	}
	
	
	public static void playAgain(int team) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText(team == 1 ? "Game Over, Red Team Wins" : "Game Over, Yellow Team wins");
		alert.setContentText("Do you want to play again?");
		

		ButtonType buttonTypeOne = new ButtonType("Play Again");
		ButtonType buttonTypeCancel = new ButtonType("Exit", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
		    // ... user chose "One"
			// resets all squares too blank
			for(int i = 7; i < 49; i++) {
				((Shape) ( (StackPane) view.getChildren().get(i)).getChildren().get(0)).setFill(javafx.scene.paint.Color.BLACK);
				((Text) ( (StackPane) view.getChildren().get(i)).getChildren().get(1)).setText("");
			}
			
			
			
			game.resetGame();
			gameDone = false;
			
		
		} else {
		    // ... user chose CANCEL or closed the dialog
			Platform.exit();
			System.exit(0);
		}

	}
	
}
