package DAO;

import java.util.ArrayList;
import Model.Message;
import Model.Account;

public interface SocialMediaDAO {

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
    public abstract void deleteMessageById(int id);

    //update message by message id 
    public abstract Message updateMessageById(Message message);

    //get all messages by user account id
    public abstract Message getAllMessagesByUserId(int id);
}
