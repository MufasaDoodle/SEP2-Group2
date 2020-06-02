package client.view.itemView;

import client.model.*;
import client.networking.Client;
import client.networking.RMIClient;
import client.view.createaccount.CreateAccountViewModel;
import client.view.createlisting.ListingViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.ServerModel;
import server.model.ServerModelImpl;
import server.networking.RMIServerImpl;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ItemViewModelTest
{
    private ItemViewModel viewModel;
    private CreateAccountViewModel createAccountVM;
    private StringProperty result;
    private ListingViewModel listingVM;

    @BeforeEach
    public void setUp()
    {
        ServerModel serverModel = new ServerModelImpl();
        try {
            RMIServer rmiServer = new RMIServerImpl(serverModel);
            rmiServer.startServer();
        }
        catch (RemoteException | AlreadyBoundException e)
        {
            e.printStackTrace();
        }
        Client client = new RMIClient();
        DataModel dataModel = new DataModel();
        MasterModelInterface masterModel = new MasterModelInterfaceManager(client, dataModel);
        ListingsModel listingsModel = new ListingsModelManager(dataModel, masterModel);
        FeedbackModel feedbackModel = new FeedbackModelManager(dataModel, masterModel);
        ModeratorModel moderatorModel = new ModeratorModelManager(dataModel, masterModel);
        AccountModel accountModel = new AccountModelManager(dataModel, masterModel);
        TransactionModel transactionModel = new TransactionModelManager(dataModel, masterModel);
        viewModel = new ItemViewModel(masterModel, listingsModel, feedbackModel, moderatorModel, transactionModel);
        createAccountVM = new CreateAccountViewModel(masterModel, accountModel);
        String name = "Ana-Maria Sima";
        String email = "anasimamaria999999@ana.com";
        String password1 = "ana";
        String password2 = "ana";
        String address = "Aarhus C";
        String phoneNumber = "+4591102099";
        createAccountVM.createAccount(name, email, password1, password2, address, phoneNumber);
        listingVM = new ListingViewModel(masterModel, listingsModel);
        String title = "LaptopLenovo";
        String descText = "for gaming";
        String price = "200";
        String category = "Electronics";
        String location = "Aarhus C";
        String duration = "one week";
        String promoted = "*";
        listingVM.createListing(title, descText, price, category, location, duration, promoted);
        StringProperty itemId = new SimpleStringProperty();
        StringProperty rentedFrom = new SimpleStringProperty();
        result = new SimpleStringProperty();
        viewModel.resultProperty().bindBidirectional(result);
        viewModel.idProperty().bindBidirectional(itemId);
        viewModel.accountIdProperty().bindBidirectional(rentedFrom);
        viewModel.setItem();
    }

    @Test
    public void reportItemAlreadyDeleted()
    {
        viewModel.reportItem();
        assertEquals("reported", result.getValue());
    }
}