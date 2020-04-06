package client.networking;

public interface Client
{
  void createListing(String title, String descText, String price, String category, String location, String duration, String date);
  String createAccount(String name, String email, String password1, String address, String phoneNumber);
}
