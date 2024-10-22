package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;

public class UserService implements Service {

    public record RegisterRequest(String username, String password, String email) {}
    public record RegisterResult(String username, String authToken, String message) {}

    public RegisterResult register(RegisterRequest registerRequest, UserDAO userDAO, AuthDAO authDAO) {
        try {
            // 1. get user
            UserData userData = userDAO.getUser(registerRequest.username());
            // 2. if no user create new user
            if (userData == null) {
                userData = new UserData(registerRequest.username(),
                        registerRequest.password(),
                        registerRequest.email());
                userDAO.createUser(userData);
            } else {
                String errorMessage = "Error: already taken";
                return new RegisterResult(null, null, errorMessage);
            }
            // 3. create auth token
            AuthData authData = authDAO.createAuth(registerRequest.username());

            return new RegisterResult(authData.username(), authData.authToken(), null);
        } catch (DataAccessException e) {
            String errorMessage = "Error: bad request";
            return new RegisterResult(null, null, errorMessage);
        }
    }

    public AuthData login(UserData user) {
        return new AuthData("token", "user");
    }
    public void logout(AuthData auth) {
    }

    public void clear() {

    }
}
