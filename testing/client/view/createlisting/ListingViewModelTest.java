package client.view.createlisting;

import client.model.*;
import client.networking.Client;
import client.networking.RMIClient;
import client.view.createaccount.CreateAccountViewModel;
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

class ListingViewModelTest
{
    private ListingViewModel viewModel;

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
        viewModel = new ListingViewModel(masterModel, listingsModel);
        CreateAccountViewModel createAccountVM = new CreateAccountViewModel(masterModel, accountModel);
        String name = "Ana-Maria Sima";
        String email = "anasimamaria@ana.com";
        String password1 = "ana";
        String password2 = "ana";
        String address = "Aarhus C";
        String phoneNumber = "+4591102099";

        createAccountVM.createAccount(name, email, password1, password2, address, phoneNumber);
    }
    @Test
    public void createListingSuccessfully()
    {
        StringProperty result = new SimpleStringProperty();
        String title = "Laptop";
        String descText = "for gaming";
        String price = "200";
        String category = "Electronics";
        String location = "Aarhus C";
        String duration = "one week";
        String promoted = "*";
        viewModel.getResultProperty().bindBidirectional(result);

        viewModel.createListing(title, descText, price, category, location, duration, promoted);
        assertEquals("OK", result.getValue());
    }

}