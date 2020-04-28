package prepper;
import java.util.*;
import java.io.*;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;





public class Mikey extends Application implements EventHandler<ActionEvent>{
	public static void main(String[] args) {
		launch(args);
	}
	
	Stage window;
	TextField input = new TextField();
	TextArea terminal = new TextArea();
	Scene scene, scene2;
	final double GR = 1.61803398875;
	Nettverket n = new Nettverket();
	
	
	public void start(Stage vinduet) {
		window = vinduet;
		final int HEIGHT = 600;
		
		final int WIDTH = (int)(HEIGHT*GR);
		
		BorderPane root = new BorderPane();
		
		BorderPane controllers = new BorderPane();
		Button tilbake = new Button("animated!");
		
		root.setBottom(input);
		input.setStyle("-fx-control-inner-background: #160915; -fx-text-fill: #BEABC3; -fx-background: #000;");
		terminal.setFont(Font.font ("Verdana", 14));
		terminal.setStyle("-fx-control-inner-background: #160915; -fx-text-fill: #BEABC3; -fx-background: #000;" );
		root.setCenter(terminal);
		
		controllers.setCenter(tilbake);
		
		scene = new Scene(root, WIDTH, HEIGHT);
		scene2 = new Scene(controllers, WIDTH, HEIGHT);
		
		window.setScene(scene);
		window.setTitle("Made by Mikey");
		window.setResizable(false);

		window.show();
		
		
		input.setOnAction(this);
		
		tilbake.setOnAction(event -> {
			//window.setScene(scene);
			Circle a1 = new Circle();
			double xx = Math.random()*3;
			if (xx > 2) {
			a1.setFill(Color.NAVY);
			} else if (xx > 1 && xx < 2){
				a1.setFill(Color.BLUEVIOLET);
			} else {
				a1.setFill(Color.AQUAMARINE);
			}
			
			a1.setRadius((int)(Math.random()*20+10));
			a1.relocate((int)(Math.random()*WIDTH-10), 0);
			
			TranslateTransition t1 = new TranslateTransition();
			t1.setDuration(Duration.seconds(a1.getRadius()/2));
			double tempX = t1.getFromX();
			t1.setToX(tempX);
			t1.setToY(HEIGHT+a1.getRadius());
			t1.setNode(a1);
			t1.play();
			
			
			
			controllers.getChildren().add(a1);
			
			
			
		});
	}
	
	boolean filfinnes = false;
	
	public void handle(ActionEvent event) {
		File temper = new File("savedtxt.txt");
		if (temper.exists()) {
			filfinnes = true;
		}
		
		if (event.getSource()==input) {
			
			String temp = input.getText();
			if ( temp.equals("a2")) {
				window.setScene(scene2);
			} else if ( temp.toLowerCase().equals("!save")) {
				
				try {
					PrintWriter skriv = new PrintWriter(temper);
					skriv.print(terminal.getText());
					skriv.close();
					terminal.clear();
					input.clear();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (temp.toLowerCase().equals("!load")) {
				if (filfinnes) {
					try {
						Scanner les = new Scanner(temper);
						while (les.hasNext()) {
							String a = terminal.getText();
							String b = les.nextLine();
							a = a + b + "\n";
							terminal.setText(a);
						}
						les.close();
						
						input.clear();
					} catch (Exception e) {
						System.out.print("Error: " + e);
					}
				} else {
					terminal.clear();
					terminal.setText("No file to be loaded!");
				}
			} else if ( temp.toLowerCase().equals("!clear") ) {
				terminal.clear();
				input.clear();
			} else if ( temp.toLowerCase().equals("!whatismyip") ) {
				terminal.setText(n.getMyIp());
				input.clear();
			} else {
			String terminalEarly = terminal.getText();
			if (terminalEarly.contentEquals("") || terminalEarly.isEmpty()) {
				terminalEarly += temp;
			} else {
				terminalEarly += "\n" + temp;
			}
			terminal.setText(terminalEarly);
			input.clear();
			}
		}
	}
	
	
}
