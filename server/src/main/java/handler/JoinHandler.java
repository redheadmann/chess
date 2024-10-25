package handler;

import com.google.gson.Gson;
import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import model.AuthData;
import service.GameService;
import spark.Request;
import spark.Response;

public class JoinHandler extends Handler {
    public final AuthDAO authDAO;
    public final GameDAO gameDAO;

    public JoinHandler(AuthDAO authDAO, GameDAO gameDAO) {
        super(authDAO);
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }

    @Override
    public Object handleRequest(Request req, Response res) {
        Gson serializer = new Gson();
        try {
            // validate authToken
            String authToken = req.headers("authorization");
            Boolean valid = this.validateAuthToken(authToken);
            if (!valid) { // Ensure authToken is valid
                res.status(401);
                GameService.JoinResult result = new GameService.JoinResult("Error: unauthorized");
                return serializer.toJson(result);
            }

            // get username
            AuthData authData = authDAO.getAuth(authToken);
            String username = authData.username();

            // make object from request
            GameService.JoinRequest request = serializer.fromJson(req.body(), GameService.JoinRequest.class);

            // join game
            GameService service = new GameService();
            GameService.JoinResult result = service.join(request, gameDAO, username);

            // Set the status code
            if (result.message() != null) {
                if (result.message().equals("Error: bad request")) {
                    res.status(400);
                } else if (result.message().equals("Error: already taken")) {
                    res.status(403);
                }
            }

            // Return the body of the response
            res.type("application/json");
            return serializer.toJson(result);
        } catch (Exception e) {
            res.status(500);
            String errorMessage = "Error: " + e.getMessage();
            GameService.JoinResult result = new GameService.JoinResult(errorMessage);
            return serializer.toJson(result);
        }
    }
}
