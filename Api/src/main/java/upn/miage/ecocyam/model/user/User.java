package upn.miage.ecocyam.model.user;

public class User {

    private final Integer userId;
    private final String userName;
    private final String lastName;
    private final String email;
    //private final String userRoleId;

    public User(Integer userId, String userName, String lastName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
