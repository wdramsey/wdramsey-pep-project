package Service;

import java.util.ArrayList;
import Model.Message;
import Model.Account;

public interface SocialMediaService {
     public abstract ArrayList<Account> getUsers();

     //check if username exists
     public abstract Boolean checkUserNameExists(Account account);

     //check if account exists
     public abstract Boolean checkAccountExists(Message message);
     
     //user registration
     public abstract Account registerNewAccount(Account account);

     //login
     public abstract Account login(Account account);
 
     //Create New message
     public abstract Message addMessage(Message message);
 
     //Get all messages
     public abstract ArrayList<Message> getAllMessages();

     //Get one message by message id
     public abstract Message getMessageById(int id);
 
     //delete message by message id
     public abstract void deleteMessageById(Message message);
 
     //update message by message id 
     public abstract Message updateMessageById(Message message, int id);
     
     //get all messages by user account id
     public abstract ArrayList<Message> getAllMessagesByUserId(int id);
}
