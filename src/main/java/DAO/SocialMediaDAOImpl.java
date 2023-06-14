package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

public class SocialMediaDAOImpl implements SocialMediaDAO {
        // interact with database
        // 1. get a connection
        // 2. create a statement
        // 3. execute query
        // 4. process results
        // 5. close connection

    @Override
    public ArrayList<Account> getUsers() {
        Connection connection = ConnectionUtil.getConnection();
        ArrayList<Account> accounts = new ArrayList<>();
        try {
             //create a statement
             String sql = "SELECT * FROM account";
             PreparedStatement statement = connection.prepareStatement(sql);
 
             // execute query
             ResultSet rs = statement.executeQuery();
             
             while(rs.next()) {
                int account_id = rs.getInt("account_id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                accounts.add(new Account(account_id, username, password));
             }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return accounts;
    }
    @Override
    public Boolean checkUserNameExists(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, account.getUsername());
            ResultSet rs = statement.executeQuery(); 
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean checkAccountExists(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, message.getPosted_by());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Account registerNewAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getUsername() );
            statement.setString(2, account.getPassword());

            statement.executeUpdate();
             // process results
             ResultSet keys = statement.getGeneratedKeys();

             // if there is a key to return
             if (keys.next()) {
                 // getInt(1) gets the first int
                 return new Account(keys.getInt(1), account.getUsername(), account.getPassword());
             }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Account login(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, account.getUsername()); 
            statement.setString(2, account.getPassword());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                 return new Account(rs.getInt(1), rs.getString("username"), rs.getString("password"));
             }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Message addMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, message.getPosted_by());
            statement.setString(2, message.getMessage_text());
            statement.setLong(3, message.getTime_posted_epoch());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                // getInt(1) gets the first int
                return new Message(keys.getInt(1), message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        ArrayList<Message> messages = new ArrayList<>();
        try {
             //create a statement
             String sql = "SELECT * FROM message";
             PreparedStatement statement = connection.prepareStatement(sql);
 
             // execute query
             ResultSet rs = statement.executeQuery();
             while(rs.next()) {
                int id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted = rs.getLong("time_posted_epoch");//may have a problem here with the biginteger type in sql table

                messages.add(new Message(id, posted_by, message_text, time_posted));
             }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messages;
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
