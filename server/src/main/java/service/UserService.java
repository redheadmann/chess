package service;

import com.mysql.cj.log.Log;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;

import java.util.Objects;

public class UserService {
    public record RegisterRequest(String username, String password, String email) {}
    public record RegisterResult(String username, String authToken, String message) {}

    public RegisterResult register(RegisterRequest registerRequest, UserDAO userDAO, AuthDAO authDAO) {
        try {
            String username = registerRequest.username();
            // 1. get user
            UserData userData = userDAO.getUser(username);
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
            AuthData authData = authDAO.createAuth(username);

            return new RegisterResult(authData.username(), authData.authToken(), null);
        } catch (DataAccessException e) {
            String errorMessage = "Error: bad request";
            return new RegisterResult(null, null, errorMessage);
        }
    }


    public record LoginRequest(String username, String password) {}
    public record LoginResult(String username, String authToken, String message) {}

    public LoginResult login(LoginRequest request, UserDAO userDAO, AuthDAO authDAO) {
        String username = request.username();
        String password = request.password();

        // 1. getUser
        UserData userData = userDAO.getUser(username);
        // 2. verify password
        if (userData == null) { // user not in database
            return new LoginResult(null, null, "Error: unauthorized");
        } else if (!Objects.equals(userData.password(), password)) { // wrong password
            return new LoginResult(null, null, "Error: unauthorized");
        }
        // 3. create auth
        AuthData authData = authDAO.createAuth(username);
        return new LoginResult(authData.username(), authData.authToken(), null);
    }

    public record LogoutRequest(String authToken) {}
    public record LogoutResult(String message) {}

    public LogoutResult logout(LogoutRequest request, AuthDAO authDAO) {
        String authToken = request.authToken();

        // 1. delete Auth
        try {
            authDAO.deleteAuth(authToken);
            return new LogoutResult(null);
        }
        catch (DataAccessException e) {
            return new LogoutResult("Error: unauthorized");
        }
    }

}
