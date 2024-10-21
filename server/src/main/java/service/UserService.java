package service;

public class UserService {

    public record RegisterRequest(String username, String password, String email) {}
    public record RegisterResult(String username, String authToken) {}

    public RegisterResult register(RegisterRequest) {
        // 1. get user
        // 2. if no user create new user
        // 3. create auth token
    }
}
