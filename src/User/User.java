package User;

/**
 *
 * @author neild
 */

public interface User {
    String getID();
    String getEmail();
    String getName();
    String getPassword();
    void setID(String id);
    void setEmail(String email);
    void setName(String name);
    void setPassword(String password);
}