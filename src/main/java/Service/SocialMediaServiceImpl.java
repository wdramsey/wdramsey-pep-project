package Service;

import java.util.ArrayList;

import DAO.SocialMediaDAO;
import DAO.SocialMediaDAOImpl;
import Model.Account;
import Model.Message;

public class SocialMediaServiceImpl implements SocialMediaService {
    //state
    private SocialMediaDAO socialMediaDAO;

    public SocialMediaServiceImpl() {
        this.socialMediaDAO = new SocialMediaDAOImpl();
    }

    @Override 
    public ArrayList<Account> getUsers() {
        return socialMediaDAO.getUsers();
    }
    @Override 
    public Boolean checkAccountExists(Message message) {
        return socialMediaDAO.checkAccountExists(message);
    }
    @Override
    public Boolean checkUserNameExists(Account account) {
        return socialMediaDAO.checkUserNameExists(account);
    }

    @Override
    public Account registerNewAccount(Account account) {
        //check if username already exists, password is 4 characters long, and username is not blank
        if (socialMediaDAO.checkUserNameExists(account) || account.getUsername() == "" || account.getPassword().length() < 4) {
            return null;
        } else return socialMediaDAO.registerNewAccount(account);
    }

    @Override
    public Account login(Account account) {
        return socialMediaDAO.login(account);
    }

    @Override
    public Message addMessage(Message message) {
        //check if message is not blank, less than 255 characters, and is posted by a registered user
        if (message.getMessage_text().length() > 254 || message.getMessage_text() == "" || !socialMediaDAO.checkAccountExists(message)) {
            return null;
        } else return socialMediaDAO.addMessage(message);
    }

    @Override
    public ArrayList<Message> getAllMessages() {
        return socialMediaDAO.getAllMessages();
    }

    @Override
    public Message getMessageById(int id) {
        return socialMediaDAO.getMessageById(id);
    }

    @Override
    public void deleteMessageById(Message message) {
        socialMediaDAO.deleteMessageById(message);
    }

    @Override
    public Message updateMessageById(Message message, int id) {
       if (message.getMessage_text().length() > 254 || message.getMessage_text() == "") {
            return null;
       } else return socialMediaDAO.updateMessageById(message, id);
    }

    @Override
    public ArrayList<Message> getAllMessagesByUserId(int id) {
        return socialMediaDAO.getAllMessagesByUserId(id);
    }
    
}
