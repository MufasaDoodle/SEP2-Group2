package client.core;

import client.view.createaccount.CreateAccountViewModel;
import client.view.createlisting.ListingViewModel;
import client.view.login.LoginViewModel;

public class ViewModelFactory
{
  private final ModelFactory mf;
  private ListingViewModel listingViewModel;
  private CreateAccountViewModel createAccountViewModel;
  private LoginViewModel loginViewModel;

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
}
