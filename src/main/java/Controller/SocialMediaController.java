package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.SocialMediaService;
import Service.SocialMediaServiceImpl;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    private SocialMediaService socialMediaService;

    public SocialMediaController() {
        this.socialMediaService = new SocialMediaServiceImpl();
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByIdHandler);
        app.get("/users", this::getUsers);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    
    private void getUsers(Context context) throws JsonProcessingException {
        context.json(socialMediaService.getUsers());
    }
    private void postRegisterHandler(Context context) throws JsonProcessingException {
        //get request information as needed
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        //call service method
        Account newAccount = socialMediaService.registerNewAccount(account);

        // send results to client
        if (newAccount != null) {
            Account expectedAccount = new Account(2, "user123", "pass123");
            // System.out.println(mapper.readValue(context.body().toString(), Account.class));
            context.json(newAccount);
            System.out.println((newAccount.equals(expectedAccount)));
        } else {
            context.status(400);
        }
    }
    private void postLoginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body().toString(), Account.class);
        Account loginAccount = socialMediaService.login(account);
        System.out.println("here, login " + account);
        if (loginAccount != null) {
            context.json(mapper.writeValueAsString(loginAccount));
        } else {
            context.status(401);
        }
    }
    private void postMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body().toString(), Message.class);
        // int posted_by = Integer.parseInt(context.pathParam("posted_by"));
        // String message_text = context.pathParam("message_text");
        // String time_posted_epoch = context.pathParam("time_posted_epoch");
        Message postedMessage = socialMediaService.addMessage(message);

        if (postedMessage != null) {
            context.json(mapper.writeValueAsString(postedMessage));
        } else {
            context.status(400);
        }
    }
    private void getAllMessagesHandler(Context context) {
        context.json(socialMediaService.getAllMessages());
    }
    private void getMessageByIdHandler(Context context) {

    }
    private void deleteMessageByIdHandler(Context context) {

    }
    private void updateMessageByIdHandler(Context context) throws JsonProcessingException {

    }
    private void getAllMessagesByIdHandler(Context context) {

    }


}