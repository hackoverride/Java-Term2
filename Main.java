import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	final int HEIGHT = 600;
	final int WIDTH = (int)(HEIGHT*1.618);
	DataBase db = new DataBase("jdbc:sqlite:todo.db");
	private Todo[] todoListen; 
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage hoved) {
		System.out.print("FX TIME!");
		MenuBar menylinje = new MenuBar();
		Menu filmeny = new Menu("Fil");
		MenuItem fjernAlt = new MenuItem("Fjern hele listen");
		fjernAlt.setOnAction(event -> {
			System.out.println(" DROP ALL ! ");
			db.lagNy("DROP TABLE IF EXISTS todo");
			db.sjekker();
		});
		
		filmeny.getItems().addAll(fjernAlt);
		menylinje.getMenus().addAll(filmeny);
		
		
		
		BorderPane root = new BorderPane();
		HBox gjøreLinje = new HBox(5);
		VBox listen = new VBox(10);
		
		Label newLabelTodo = new Label("Nytt Gjøremål: ");
		TextField gjøremål = new TextField();
		Button newTodo = new Button("Lag nytt gjøremål");
		
		gjøreLinje.getChildren().addAll(newLabelTodo, gjøremål, newTodo);
		
		newTodo.setOnAction(event -> { 
			String tempTodo = gjøremål.getText();
			System.out.println(tempTodo);
			if (tempTodo.isBlank()) {
				gjøremål.setPromptText("Du må skrive inn noe her");
			} else {
				if ( db.sjekker() ) {
				Todo temp = new Todo(tempTodo, db);
				temp.leggTilIDb();
				} else {
					// databasen eksisterer ikke, så derfor kan ikke legge inn noe.
					System.out.println("Database feil! Systemet fungerer ikke");
				}
			}
		}); // <- set event action for button.
		gjøreLinje.setAlignment(Pos.CENTER);
		root.setBottom(gjøreLinje);
		root.setCenter(listen);
		root.setTop(menylinje);
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		hoved.setScene(scene);
		hoved.setTitle("Gjøremål");
		hoved.show();
		
	}
	
	
}
