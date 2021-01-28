package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UiController {
	//TODO

	@FXML
	private Button newQuote;

	public void createNewQuote(ActionEvent e) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/quotePack/NewQuote.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
