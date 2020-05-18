package database.account;

import stuffs.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDAO
{
  Account createAccount(String name, String email, String password, String address, String phoneNumber, String bio) throws SQLException;
  Account createAccount(String name, String email, String password, String address, String phoneNumber) throws SQLException;
  Account readById(int id) throws SQLException;
  Account readByEmail(String email) throws SQLException;
  List<Account> readByName(String searchString) throws SQLException;
  void update(Account account) throws SQLException;
  void delete(Account account) throws SQLException;
  int getAccountId(String email) throws SQLException;
  String getAccountName(String email) throws SQLException;

}
