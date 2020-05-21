package shared.networking;

import shared.transferobjects.Message;
import stuffs.FeedbackToItem;
import stuffs.Listing;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
  void update(Message msg) throws RemoteException;
}
