package View;

import java.sql.SQLException;

import Controller.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.fxml.FXMLLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = (TabPane) FXMLLoader.load(getClass().getResource("View1.fxml"));
			Scene scene = new Scene(root, 300, 300);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.resizableProperty().set(false);
			primaryStage.setOnCloseRequest(c -> closeEvent(c));
		} catch (Exception e) {
			logger.error("Exception caught: ", e);
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void closeEvent(WindowEvent event) {
		try {
			ViewController.DAO.c.close();
		} catch (SQLException e) {
			logger.error("SQLException caught: ", e.getMessage());
		}
	}

}
