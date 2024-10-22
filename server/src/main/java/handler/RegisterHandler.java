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
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    @Override
    public Object handleRequest(Request req, Response res) {
        Gson serializer = new Gson();

        UserService.RegisterRequest request = serializer.fromJson(req.body(), UserService.RegisterRequest.class);

        UserService service = new UserService();
        UserService.RegisterResult result = service.register(request, userDAO, authDAO);

        return serializer.toJson(result);

    }

}
