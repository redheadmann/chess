package handler;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import spark.*;

abstract class Handler {
    private final AuthDAO authDAO;

    public Handler(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }


    public abstract Object handleRequest(Request req, Response res);


    public Boolean validateAuthToken(String authToken) {
        try {
            authDAO.getAuth(authToken);
            return Boolean.TRUE;
        }
        catch (DataAccessException e) {
            return Boolean.FALSE;
        }

    }
}

