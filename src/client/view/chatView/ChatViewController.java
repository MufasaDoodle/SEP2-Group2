package client.view.chatView;

import client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ChatViewController
{
  private ChatViewModel viewModel;
  private ViewHandler vh;

  @FXML private Button sendButton;
  @FXML private Button backButton;

  @FXML private TextArea messageArea;
  @FXML private TextArea chatArea;

  @FXML private Label ownerName;
  @FXML private Label itemName;



}
