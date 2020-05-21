package database.messages;

import shared.transferobjects.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessagesDAO
{
  void saveMessage(Message message) throws SQLException;
  List<Message> getAllMessagesFromAccount(int fromAccount) throws SQLException;
  List<Message> getAllMessagesToAccount(int toAccount) throws SQLException;
  List<Message> getAllMessagesInvolvingAccount(int account) throws SQLException;
  List<Message> getAllMessagesBetween(int account1, int account2) throws SQLException;
  void deleteByAccount(int accountId)throws SQLException;
}
