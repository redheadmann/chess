package handler;

import com.google.gson.Gson;
import dataaccess.AuthDAO;
import dataaccess.UserDAO;
import service.UserService;
import spark.Request;
import spark.Response;


public class RegisterHandler extends Handler {
    public final UserDAO userDAO;
    public final AuthDAO authDAO;

    public RegisterHandler(UserDAO userDAO, AuthDAO authDAO) {
        super(authDAO);
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    @Override
    public Object handleRequest(Request req, Response res) {
        Gson serializer = new Gson();
        try {
            UserService.RegisterRequest request = serializer.fromJson(req.body(), UserService.RegisterRequest.class);

            UserService service = new UserService(userDAO);
            UserService.RegisterResult result = service.register(request, authDAO);

            // Set the status code
            if (result.message().equals("Error: bad request")) {
                res.status(400);
            } else if (result.message().equals("Error: already taken")) {
                res.status(403);
            } else {
                res.status(200);
            }

            // Return the body of the response
            res.type("application/json");
            return serializer.toJson(result);
        } catch (Exception e) {
            res.status(500);
            return serializer.toJson(new Error("Internal server error: " + e.getMessage()));
        }
    }

}
