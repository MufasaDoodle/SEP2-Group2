package client.core;

import client.view.accountView.AccountViewModel;
import client.view.chatView.ChatViewModel;
import client.view.createaccount.CreateAccountViewModel;
import client.view.createlisting.ListingViewModel;
import client.view.editItem.EditItemViewModel;
import client.view.listingView.SeeListingViewModel;
import client.view.itemView.ItemViewModel;
import client.view.login.LoginViewModel;
import client.view.messages.MessagesViewModel;
import client.view.moderator.ModeratorViewModel;

public class ViewModelFactory
{
  private final ModelFactory mf;
  private SeeListingViewModel seeListingViewModel;
  private ListingViewModel listingViewModel;
  private CreateAccountViewModel createAccountViewModel;
  private LoginViewModel loginViewModel;
  private ChatViewModel chatViewModel;
  private ItemViewModel itemViewModel;
  private AccountViewModel accountViewModel;
  private EditItemViewModel editItemViewModel;
  private MessagesViewModel messagesViewModel;
  private ModeratorViewModel moderatorViewModel;

  public ViewModelFactory(ModelFactory mf)
  {
    this.mf = mf;
  }

  public MessagesViewModel getMessagesViewModel()
  {
    if (messagesViewModel == null)
    {
      return (messagesViewModel = new MessagesViewModel(mf.getMasterModel(), mf.getListingsModel(), mf.getChatModel()));
    }
    else
    {
      return messagesViewModel;
    }
  }

  public ModeratorViewModel getModeratorViewModel()
  {
    if (moderatorViewModel == null)
    {
      return (moderatorViewModel = new ModeratorViewModel(mf.getMasterModel(), mf.getListingsModel(), mf.getAccountModel(), mf.getFeedbackModel(), mf.getChatModel(), mf.getModeratorModel(), mf.getTransactionModel()));
    }
    else
    {
      return moderatorViewModel;
    }
  }

  public ListingViewModel getListingViewModel()
  {
    if (listingViewModel == null)
    {
      return (listingViewModel = new ListingViewModel(mf.getMasterModel(), mf.getListingsModel()));
    }
    else
    {
      return listingViewModel;
    }
  }

  public AccountViewModel getAccountViewModel()
  {
    if (accountViewModel == null)
    {
      return (accountViewModel = new AccountViewModel(mf.getMasterModel(), mf.getListingsModel(), mf.getAccountModel(), mf.getChatModel(), mf.getModeratorModel(), mf.getTransactionModel()));
    }
    else
    {
      return accountViewModel;
    }
  }

  public SeeListingViewModel getSeeListingViewModel()
  {
    if (seeListingViewModel == null)
    {
      return (seeListingViewModel = new SeeListingViewModel(mf.getMasterModel(), mf.getListingsModel(), mf.getChatModel()));
    }
    else
    {
      return seeListingViewModel;
    }
  }

  public CreateAccountViewModel getCreateAccountViewModel()
  {
    if (createAccountViewModel == null)
    {
      createAccountViewModel = new CreateAccountViewModel(mf.getMasterModel(), mf.getAccountModel());
    }
    return createAccountViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    if (loginViewModel == null)
    {
      loginViewModel = new LoginViewModel(mf.getMasterModel(), mf.getListingsModel(), mf.getAccountModel());
    }
    return loginViewModel;
  }

  public ChatViewModel getChatViewModel()
  {
    if (chatViewModel == null)
      chatViewModel = new ChatViewModel(mf.getMasterModel(), mf.getListingsModel(), mf.getChatModel());
    return chatViewModel;
  }

  public EditItemViewModel getEditItemViewModel()
  {
    if (editItemViewModel == null)
      editItemViewModel = new EditItemViewModel(mf.getMasterModel(), mf.getListingsModel(), mf.getFeedbackModel(), mf.getModeratorModel(), mf.getTransactionModel());
    return editItemViewModel;
  }

  public ItemViewModel getItemViewModel()
  {
    if (itemViewModel == null)
      itemViewModel = new ItemViewModel(mf.getMasterModel(), mf.getListingsModel(), mf.getFeedbackModel(), mf.getModeratorModel(), mf.getTransactionModel());
    return itemViewModel;
  }
}
