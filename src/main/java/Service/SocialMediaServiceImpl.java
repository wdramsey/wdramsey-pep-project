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
        if (socialMediaDAO.checkUserNameExists(account) || account.getUsername() == null || account.getPassword().length() < 4) {
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
        if (message.getMessage_text().length() > 255 || message.getMessage_text().length() == 0 || socialMediaDAO.checkAccountExists(message)) {
            return null;
        } else return socialMediaDAO.addMessage(message);
    }

    @Override
    public ArrayList<Message> getAllMessages() {
        return socialMediaDAO.getAllMessages();
    }

    @Override
    public Message getMessageById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessageById'");
    }

    @Override
    public void deleteMessageById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMessageById'");
    }

    @Override
    public Message updateMessageById(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMessageById'");
    }

    @Override
    public Message getAllMessagesByUserId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMessagesByUserId'");
    }
    
}
