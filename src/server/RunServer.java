package server;

import server.model.ServerModelImpl;
import server.networking.RMIServerImpl;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args) throws RemoteException, AlreadyBoundException
  {
    RMIServer ss = new RMIServerImpl(new ServerModelImpl());
    ss.startServer();
  }
}
