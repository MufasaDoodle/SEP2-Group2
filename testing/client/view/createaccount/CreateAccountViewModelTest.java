package client.view.createaccount;

import client.model.*;
import client.networking.Client;
import client.networking.RMIClient;
import client.view.itemView.ItemViewModel;
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

class CreateAccountViewModelTest
{
    private CreateAccountViewModel viewModel;
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
        viewModel = new CreateAccountViewModel(masterModel, accountModel);
    }

    @Test
    public void createAccountSuccess()
    {
        StringProperty result = new SimpleStringProperty();

        viewModel.resultProperty().bindBidirectional(result);
        String name = "Ana-Maria Sima";
        String email = "anasima@ana.com";
        String password1 = "ana";
        String password2 = "ana";
        String address = "Aarhus C";
        String phoneNumber = "+4591102099";

        viewModel.createAccount(name, email, password1, password2, address, phoneNumber);
        assertEquals("OK", result.getValue());
    }

}