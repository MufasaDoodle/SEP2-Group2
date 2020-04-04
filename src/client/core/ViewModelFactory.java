package client.core;

import client.view.createaccount.CreateAccountViewModel;

public class ViewModelFactory
{
  private final ModelFactory mf;

  private CreateAccountViewModel createAccountViewModel;

  public ViewModelFactory(ModelFactory mf)
  {
    this.mf = mf;
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
