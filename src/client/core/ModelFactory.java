package client.core;

import client.model.*;

public class ModelFactory
{
  private final ClientFactory cf;
  private ClientModel clientModel;
  private ListingsModel listingsModel;
  private MasterModelInterface masterModel;
  private AccountModel accountModel;
  private FeedbackModel feedbackModel;
  private ChatModelManager chatModel;
  private DataModel dataModel;

  public ModelFactory(ClientFactory cf)
  {
    this.cf = cf;
  }

  public ChatModelManager getChatModel()
  {
    if (chatModel == null)
    {
      chatModel = new ChatModelManager(getDataModel(), getMasterModel());
    }
    return chatModel;
  }

  public MasterModelInterface getMasterModel()
  {
    if (masterModel == null)
    {
      masterModel = new MasterModelInterfaceManager(cf.getClient(), getDataModel());
    }
    return masterModel;
  }
  public AccountModel getAccountModel()
  {
    if (accountModel == null)
    {
      accountModel = new AccountModelManager(getDataModel(), getMasterModel());
    }
    return accountModel;
  }

  public FeedbackModel getFeedbackModel()
  {
    if (feedbackModel == null)
    {
      feedbackModel = new FeedbackModelManager(getDataModel(), getMasterModel());
    }
    return feedbackModel;
  }

  public DataModel getDataModel()
  {
    if (dataModel == null)
    {
      dataModel = new DataModel();
    }
    return dataModel;
  }

  public ListingsModel getListingsModel()
  {
    if (listingsModel == null)
    {
      listingsModel = new ListingsModelManager(getDataModel(), getMasterModel());
    }
    return listingsModel;
  }

  public ClientModel getClientModel()
  {
    if (clientModel == null)
    {
      clientModel = new ClientModelManager(cf.getClient());
    }
    return clientModel;
  }
}
