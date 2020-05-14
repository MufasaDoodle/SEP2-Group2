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
  private Scene createAccountScene;
  private Scene listingScene;
  private Scene chatScene;
  private Scene seeListingScene;
  private Scene itemScene;
  private Scene accountScene;
  private Scene editItemScene;


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

        stage.setTitle("Log in");
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

  public void openEditItemScene()
  {
    if (editItemScene == null)
    {
      try
      {
        Parent root = loadFXML("../view/editItem/editItem.fxml");

        stage.setTitle("Edit item");
        editItemScene = new Scene(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    seeListingScene = null;
    accountScene = null;
    stage.setScene(editItemScene);
    stage.show();
  }

  public void openAccountScene()
  {
    if (accountScene == null)
    {
      try
      {
        Parent root = loadFXML("../view/accountView/accountView.fxml");

        stage.setTitle("Account");
        accountScene = new Scene(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    itemScene = null;
    editItemScene = null;
    stage.setScene(accountScene);
    stage.show();
  }

  public void openCreateListingScene()
  {
    if (listingScene == null)
    {
      try
      {
        Parent root = loadFXML("../view/createlisting/CreateListing.fxml");

        stage.setTitle("Create Listing");
        listingScene = new Scene(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    seeListingScene = null;
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

        stage.setTitle("Listings");
        seeListingScene = new Scene(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    itemScene = null; //must be set to null in order to initialize properly with new items
    stage.setScene(seeListingScene);
    stage.show();
  }
  //change
  public void openAccountCreateScene()
  {
    if (createAccountScene == null)
    {
      try
      {
        Parent root = loadFXML("../view/createaccount/createAccount.fxml");

        createAccountScene = new Scene(root);
        stage.setTitle("Create account");

      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    stage.setScene(createAccountScene);
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
    itemScene = null; //must be set to null in order to initialize properly with new items
    stage.setScene(chatScene);
    stage.show();
  }

  public void openItemScene(){
    if (itemScene == null){
      try
      {
        Parent root = loadFXML("../view/itemView/itemView.fxml");

        itemScene = new Scene(root);
        stage.setTitle("Item");
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

    }
    seeListingScene = null;
    accountScene = null;
    chatScene = null;
    stage.setScene(itemScene);
    stage.show();
  }
}
