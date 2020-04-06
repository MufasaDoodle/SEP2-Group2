package client.core;

import client.view.createaccount.CreateAccountViewModel;
import client.view.createlisting.ListingViewModel;


public class ViewModelFactory
{
  private final ModelFactory mf;
  private ListingViewModel listingViewModel;
  //other viewmodels

  private CreateAccountViewModel createAccountViewModel;

  public ViewModelFactory(ModelFactory mf)
  {
    this.mf = mf;
  }

  public ListingViewModel getListingViewModel()
  {
    if (listingViewModel == null)
    {
      return (listingViewModel = new ListingViewModel(mf.getRentSystem()));
    }
    else
    {
      return listingViewModel;
    }
  }

  //methods for getting viewmodels



  public CreateAccountViewModel getCreateAccountViewModel()
  {
    if (createAccountViewModel == null)
    {
      createAccountViewModel = new CreateAccountViewModel(mf.getClientModel());
    }
    return createAccountViewModel;
  }
}
