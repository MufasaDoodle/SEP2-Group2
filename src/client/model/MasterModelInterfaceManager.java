package client.model;

import client.networking.Client;
import javafx.scene.control.Alert;
import stuffs.Account;
import stuffs.FeedbackToItem;
import stuffs.Listing;

public class MasterModelInterfaceManager implements MasterModelInterface
{
  private DataModel dataModel;

  public MasterModelInterfaceManager(Client client, DataModel dataModel)
  {
    dataModel.setClient(client);
    dataModel.getClient().startClient();
    this.dataModel = dataModel;
  }

  @Override public Account getAccountById(int id)
  {
    return dataModel.getClient().getAccountById(id);
  }

  @Override public Listing getListingByID(int id)
  {
    return dataModel.getClient().getListingByID(id);
  }

  @Override public int getCurrentItemID()
  {
    return dataModel.getCurrentItemID();
  }

  @Override public void setCurrentItemID(int itemID)
  {
    dataModel.setCurrentItemID(itemID);
    if (dataModel.getClient().getListingByID(dataModel.getCurrentItemID()) != null)
    {
      dataModel.setItemName(dataModel.getClient().getListingByID(dataModel.getCurrentItemID()).getTitle());
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item deleted");
      alert.showAndWait();
    }
  }

  @Override public void setCurrentAccountID(String email)
  {
    dataModel.setCurrentAccountID(dataModel.getClient().getAccountId(email));
  }

  @Override public int getCurrentAccountID()
  {
    return dataModel.getCurrentAccountID();
  }

  @Override public String getCurrentAccountName()
  {
    return dataModel.getCurrentAccountName();
  }

  @Override public boolean accountCheck()
  {
    Account account = getAccountById(getCurrentAccountID());
    return account != null;
  }

  @Override public FeedbackToItem getFeedbackById(int id)
  {
    return dataModel.getClient().getFeedbackById(id);
  }

  @Override public void setFeedbackId(int feedbackId)
  {
    dataModel.setFeedbackId(feedbackId);
  }

  @Override public int getFeedbackId()
  {
    return dataModel.getFeedbackId();
  }

  @Override public int getCurrentChatterID()
  {
    return dataModel.getCurrentChatterID();
  }

  @Override public void setCurrentChatterID(int currentChatterID)
  {
    if (!(getCurrentAccountID() == currentChatterID))
    {
      dataModel.setCurrentChatterID(currentChatterID);
    }
    else
    {
      dataModel.setCurrentChatterID(1);
    }
    saveChatterName();
  }

  @Override public void saveChatterName()
  {
    if (dataModel.getClient().getAccountById(dataModel.getCurrentChatterID()) == null)
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is deleted");
    }
    else
    {
      dataModel.setChatterName(dataModel.getClient().getAccountById(dataModel.getCurrentChatterID()).getName());
    }
  }

  @Override public int getViewingAccountID()
  {
    return dataModel.getViewingAccountID();
  }

  @Override public void setViewingAccountID(int viewingAccountID)
  {
    dataModel.setViewingAccountID(viewingAccountID);
  }
}
