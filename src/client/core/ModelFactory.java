package client.core;

import client.model.*;

public class ModelFactory
{
  private final ClientFactory cf;
  private ListingsModel listingsModel;
  private MasterModelInterface masterModel;
  private AccountModel accountModel;
  private FeedbackModel feedbackModel;
  private ChatModelManager chatModel;
  private ModeratorModel moderatorModel;
  private TransactionModel transactionModel;
  private DataModel dataModel;

  public ModelFactory(ClientFactory cf)
  {
    this.cf = cf;
  }

  public ModeratorModel getModeratorModel()
  {
    if (moderatorModel == null)
    {
      moderatorModel = new ModeratorModelManager(getDataModel(), getMasterModel());
    }
    return moderatorModel;
  }

  public TransactionModel getTransactionModel()
  {
    if (transactionModel == null)
    {
      transactionModel = new TransactionModelManager(getDataModel(), getMasterModel());
    }
    return transactionModel;
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
}
