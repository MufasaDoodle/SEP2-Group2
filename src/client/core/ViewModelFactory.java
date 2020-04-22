package client.core;

import client.view.chatView.ChatViewModel;
import client.view.createaccount.CreateAccountViewModel;
import client.view.createlisting.ListingViewModel;
import client.view.itemView.ItemViewModel;
import client.view.login.LoginViewModel;

public class ViewModelFactory
{
  private final ModelFactory mf;
  private ListingViewModel listingViewModel;
  private CreateAccountViewModel createAccountViewModel;
  private LoginViewModel loginViewModel;
  private ChatViewModel chatViewModel;
  private ItemViewModel itemViewModel;

  public ViewModelFactory(ModelFactory mf)
  {
    this.mf = mf;
  }

  public ListingViewModel getListingViewModel()
  {
    if (listingViewModel == null)
    {
      return (listingViewModel = new ListingViewModel(mf.getClientModel()));
    }
    else
    {
      return listingViewModel;
    }
  }

  public CreateAccountViewModel getCreateAccountViewModel()
  {
    if (createAccountViewModel == null)
    {
      createAccountViewModel = new CreateAccountViewModel(mf.getClientModel());
    }
    return createAccountViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    if (loginViewModel == null)
    {
      loginViewModel = new LoginViewModel(mf.getClientModel());
    }
    return loginViewModel;
  }

  public ChatViewModel getChatViewModel()
  {
    if (chatViewModel == null)
      chatViewModel = new ChatViewModel(mf.getClientModel());
    return chatViewModel;
  }

  public ItemViewModel getItemViewModel()
  {
    if (itemViewModel == null)
      itemViewModel = new ItemViewModel(mf.getClientModel());
    return itemViewModel;
  }
}
