package client.view.accountView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import shared.util.EmailCheck;
import stuffs.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AccountViewModel
{
  private ClientModel clientModel;
  private StringProperty name, address, phone, bio, avgRate, emailEdit, addressEdit, numberEdit, bioEdit, pass1, pass2;
  private ObservableList<Listing> listings;

  private ObservableList<RequestListing> requests;
  private ObservableList<TransactionListing> transactions;
  private boolean isDeclined;

  public AccountViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    name = new SimpleStringProperty();
    address = new SimpleStringProperty();
    phone = new SimpleStringProperty();
    bio = new SimpleStringProperty();
    avgRate = new SimpleStringProperty();
    emailEdit = new SimpleStringProperty();
    addressEdit = new SimpleStringProperty();
    numberEdit = new SimpleStringProperty();
    bioEdit = new SimpleStringProperty();
    pass1 = new SimpleStringProperty();
    pass2 = new SimpleStringProperty();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public StringProperty addressProperty()
  {
    return address;
  }

  public StringProperty phoneProperty()
  {
    return phone;
  }

  public StringProperty bioProperty()
  {
    return bio;
  }

  public StringProperty avgRateProperty()
  {
    return avgRate;
  }

  public StringProperty emailEditProperty()
  {
    return emailEdit;
  }

  public StringProperty addressEditProperty()
  {
    return addressEdit;
  }

  public StringProperty numberEditProperty()
  {
    return numberEdit;
  }

  public StringProperty bioEditProperty()
  {
    return bioEdit;
  }

  public StringProperty pass1Property()
  {
    return pass1;
  }

  public StringProperty pass2Property()
  {
    return pass2;
  }

  public boolean getIsDeclined()
  {
    return isDeclined;
  }

  public void setIsDeclined(boolean declined)
  {
    isDeclined = declined;
  }

  public void setOwner()
  {
    Account tempCheck = clientModel
        .getAccountById(clientModel.getCurrentAccountID());
    if (tempCheck == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Your account is banned");
      alert.showAndWait();
    }
    else if (clientModel.getFromListingViewOpen())
    {
      Account temp = clientModel
          .getAccountById(clientModel.getCurrentAccountID());
      name.set(temp.getName());
      address.set(temp.getAddress());
      phone.set(temp.getPhoneNumber());
      bio.set(temp.getBio());
    }
    else if (clientModel.getModeratorOpen())
    {
      Account temp = clientModel.getModeratedAccount();
      name.set(temp.getName());
      address.set(temp.getAddress());
      phone.set(temp.getPhoneNumber());
      bio.set(temp.getBio());
    }
    else if (!clientModel.getFromListingViewOpen())
    {
      Listing listing = clientModel
          .getListingByID(clientModel.getCurrentItemID());
      if (listing == null)
      {
        clientModel.setCurrentChatterID(1);
      }
      else
      {
        int itemId = clientModel.getCurrentItemID();
        int accountId = clientModel.getListingByID(itemId).getAccountId();
        Account temp = clientModel.getAccountById(accountId);

        name.set(temp.getName());
        address.set(temp.getAddress());
        phone.set(temp.getPhoneNumber());
        bio.set(temp.getBio());
      }
    }
    else if (!clientModel.getFromListingViewOpen())
    {
      int itemId = clientModel.getCurrentItemID();
      if (clientModel.getListingByID(itemId) == null)
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Item was not chosen!");
        alert.showAndWait();
      }
      else
      {
        int accountId = clientModel.getListingByID(itemId).getAccountId();
        Account temp = clientModel.getAccountById(accountId);
        name.set(temp.getName());
        address.set(temp.getAddress());
        phone.set(temp.getPhoneNumber());
        bio.set(temp.getBio());
      }
    }
    //Need an avg rate for account
    //avgRate.set(String.valueOf(temp.));
  }

  ObservableList<Listing> getListings()
  {
    return listings;
  }

  public void listOfOwnerListings()
  {
    if (clientModel.getFromListingViewOpen())
    {
      List<Listing> list = clientModel
          .getListingsByAccount(clientModel.getCurrentAccountID());
      listings = FXCollections.observableArrayList(list);
    }
    else if (clientModel.getModeratorOpen())
    {
      List<Listing> list = clientModel
          .getListingsByAccount(clientModel.getModeratedAccount().getId());
      listings = FXCollections.observableArrayList(list);
    }
    else if (!clientModel.getFromListingViewOpen())
    {
      Listing temp = clientModel.getListingByID(clientModel.getCurrentItemID());
      if (clientModel.getListingByID(clientModel.getCurrentItemID()) == null)
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Cannot contact no one!");
        alert.showAndWait();
      }
      else
      {
        List<Listing> list = clientModel.getListingsByAccount(
            clientModel.getAccountById(temp.getAccountId()).getId());
        listings = FXCollections.observableArrayList(list);
      }
    }
    else if (!clientModel.getFromListingViewOpen())
    {

      if (clientModel.getListingByID(clientModel.getCurrentItemID()) != null)
      {
        Listing temp = clientModel
            .getListingByID(clientModel.getCurrentItemID());
        List<Listing> list = clientModel.getListingsByAccount(
            clientModel.getAccountById(temp.getAccountId()).getId());
        listings = FXCollections.observableArrayList(list);
      }
    }
  }

  public void listOfOwnerRentals()
  {
    List<TransactionListing> rentedTo = clientModel
        .getTransactionByRentedTo(clientModel.getCurrentAccountID());
    List<TransactionListing> rentedFrom = clientModel
        .getTransactionByRentedFrom(clientModel.getCurrentAccountID());

    List<TransactionListing> result = new ArrayList<>();

    result.addAll(rentedTo);
    result.addAll(rentedFrom);
    transactions = FXCollections.observableArrayList(result);
  }

  ObservableList<TransactionListing> getTransactions()
  {
    return transactions;
  }

  public void listOfOwnerRequests()
  {
    List<RequestListing> requestList = clientModel
        .getRequestByAccountId(clientModel.getCurrentAccountID());
    requests = FXCollections.observableArrayList(requestList);
  }

  ObservableList<RequestListing> getRequests()
  {
    return requests;
  }

  public void setItem(int itemID)
  {
    clientModel.setCurrentItemID(itemID);
  }

  public void updateEditFields()
  {
    Account account = clientModel
        .getAccountById(clientModel.getCurrentAccountID());
    emailEdit.set(account.getEmail());
    addressEdit.set(account.getAddress());
    numberEdit.set(account.getPhoneNumber());
    bioEdit.set(account.getBio());
    pass1.set(account.getPassword());
    pass2.set(account.getPassword());
  }

  public void updateAccountInfo(String email, String pass1, String pass2,
      String address, String number, String bio)
  {
    if (!email.equals("") && !pass1.equals("") && !pass2.equals("") && !address
        .equals("") && !number.equals("") && !bio.equals(""))
    {
      if (EmailCheck.isValid(email))
      {
        if (pass1.equals(pass2))
        {
          if (clientModel.isEmailTaken(email) && !clientModel
              .getAccountById(clientModel.getCurrentAccountID()).getEmail()
              .equals(email))
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Email is already in use!");
            alert.setContentText("Please choose another!");
            alert.showAndWait();
          }
          else
          {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Account");
            alert.setHeaderText("Do you want to update your account?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
              if (clientModel.updateAccount(email, pass1, address, number, bio))
              {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Account Updated");
                conf.setHeaderText("Account successfully updated!");
                conf.showAndWait();
                setOwner();
              }
              else
              {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Account Update");
                warning.setHeaderText(
                    "Account could not be updated, try again later");
                warning.showAndWait();
              }
            }
          }
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Warning");
          alert.setHeaderText("Passwords do not match!");
          alert.setContentText("Please check your passwords!");
          alert.showAndWait();
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Email is not valid!");
        alert.setContentText("Please check your email again!");
        alert.showAndWait();
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("All * fields must be filled!");
      alert.setContentText("Please fill all * fields!");
      alert.showAndWait();
    }
  }

  public boolean checkOwner(String name)
  {
    if (clientModel.checkOwner())
    {
      return true;
    }
    return false;
    /*
    if (clientModel.getAccountById(clientModel.getCurrentAccountID()).getName().equals(name))
    {
      return true;
    }
    return false;

     */
  }

  public int getCurrentItemId()
  {
    return clientModel.getCurrentItemID();
  }

  public List<Integer> getDeletedItemIds()
  {
    return clientModel.getDeletedItemIds();
  }

  public int getAccountID()
  {
    return clientModel.getCurrentAccountID();
  }

  public void acceptRent(int itemId, int rentedTo)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    int rentedFrom = clientModel.getCurrentAccountID();
    clientModel.createTransaction(itemId, dateFormat.format(date), rentedTo,
        rentedFrom);
    clientModel.deleteRequest(itemId);
  }

  public void declineRent(int itemId, int rentedTo)
  {
    clientModel.deleteDecline(itemId, rentedTo);
  }

  public Request getRequest(int itemId, int requestFrom)
  {
    return clientModel.getRequest(itemId, requestFrom);
  }

  public Listing getListing(int itemId)
  {
    return clientModel.getListingByID(itemId);
  }

  public void updateListing(String title, String description, String category,
      String location, String duration, double price, String rented, int itemId,
      int accountId, String promoted)
  {
    clientModel
        .updateListingRented(title, description, category, location, price,
            duration, rented, itemId, accountId, promoted);
  }

  public void reportAccount()
  {
    if (clientModel.getCurrentAccountID() != 1)
    {
      int reportFrom = clientModel.getCurrentAccountID();
      int itemId = clientModel.getCurrentItemID();
      if (clientModel.getListingByID(itemId) == null)
      {
        int accountId = clientModel.getCurrentAccountID();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        clientModel
            .createReport(reportFrom, 0, accountId, 0, dateFormat.format(date));
      }
      else
      {
        int accountId = clientModel.getListingByID(itemId).getAccountId();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        clientModel
            .createReport(reportFrom, 0, accountId, 0, dateFormat.format(date));
      }
    }
  }

  public boolean itemCheck()
  {
    return clientModel.getListingByID(clientModel.getCurrentItemID()) != null;
  }

  public boolean getReportByAccountId()
  {
    int itemId = clientModel.getCurrentItemID();
    if (clientModel.getListingByID(itemId) != null)
    {
      int accountId = clientModel.getListingByID(itemId).getAccountId();
      return clientModel.getReportByAccountId(accountId) == null;
    }
    else
    {
      int accountId = clientModel.getCurrentAccountID();
      return clientModel.getReportByAccountId(accountId) == null;
    }

  }

  public boolean accountCheck()
  {
    return clientModel.accountCheck();
  }

}
