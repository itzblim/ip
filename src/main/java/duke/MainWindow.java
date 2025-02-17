package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private final String userColor = "DarkOrange";
    private final String dukeColor = "DodgerBlue";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.initialize(), dukeImage, dukeColor)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        assert !response.isEmpty() : "response should not be empty";
        if (Ui.isByeMsg(response)) {
            // Exit the program if bye command is used.
            assert input.equals("bye") : "input should be bye if exit is to occur";
            Platform.exit();
        }
        // Otherwise, add new dialog boxes.
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage, userColor),
                DialogBox.getDukeDialog(response, dukeImage, dukeColor)
        );
        userInput.clear();
    }
}
