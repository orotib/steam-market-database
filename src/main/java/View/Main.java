package View;

import java.sql.SQLException;

import Controller.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = (TabPane) FXMLLoader.load(getClass().getResource("View1.fxml"));
			Scene scene = new Scene(root, 300, 300);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.resizableProperty().set(false);
			primaryStage.setOnCloseRequest(c -> closeEvent(c));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void closeEvent(WindowEvent event) {
		try {
			ViewController.DAO.c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
