package client.core;

import client.view.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Scene loginScene;
  private Stage stage;
  private ViewModelFactory vmf;
  private Scene accountScene;
  private Scene listingScene;
  private Scene chatScene;
  private Scene seeListingScene;

  public ViewHandler(ViewModelFactory vmf)
  {
    this.vmf = vmf;
  }

  public void start()
  {
    stage = new Stage();
    openLogInScene();
  }

  public void openLogInScene()
  {
    if (loginScene == null)
    {
      try
      {
        Parent root = loadFXML("../view/login/LogIn.fxml");

        stage.setTitle("Rent - Log in");
        loginScene = new Scene(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    stage.setScene(loginScene);
    stage.show();
  }

  public void openListingScene()
  {
    if (listingScene == null)
    {
      try
      {
        Parent root = loadFXML("../view/createlisting/CreateListing.fxml");

        stage.setTitle("Renting thingy - Create Listing");
        listingScene = new Scene(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    stage.setScene(listingScene);
    stage.show();
  }
  public void openSeeListingScene()
  {
    if (seeListingScene == null)
    {
      try
      {
        Parent root = loadFXML("../view/listingView/listingView.fxml");

        stage.setTitle("See Listings");
        seeListingScene = new Scene(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    stage.setScene(seeListingScene);
    stage.show();
  }
  //change
  public void openAccountCreateScene()
  {
    if (accountScene == null)
    {
      try
      {
        Parent root = loadFXML("../view/createaccount/createAccount.fxml");

        accountScene = new Scene(root);
        stage.setTitle("Create account");

      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    stage.setScene(accountScene);
    stage.show();
  }

  private Parent loadFXML(String path) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(path));
    Parent root = loader.load();

    ViewController ctrl = loader.getController();
    ctrl.init(this, vmf);
    return root;
  }

  public void openChatScene(){
    if (chatScene == null){
      try
      {
        Parent root = loadFXML("../view/chatView/chatView.fxml");

        chatScene = new Scene(root);
        stage.setTitle("Chat");
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

    }
    stage.setScene(chatScene);
    stage.show();
  }
}
