package client.core;

import client.view.createlisting.ListingViewModel;

public class ViewModelFactory
{
  private final ModelFactory mf;
  private ListingViewModel listingViewModel;
  //other viewmodels

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
}
