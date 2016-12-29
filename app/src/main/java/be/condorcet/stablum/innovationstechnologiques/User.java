package be.condorcet.stablum.innovationstechnologiques;

/**
 * Created by Ludovic on 26-12-16.
 */

public class User {
    ///////////////
    // Attributes //
    ///////////////
    private int id;
    private String login;
    private String password;

    /////////////////////
    // Getters/Setters //
    /////////////////////
    // Id
    public void setId(int id)
    {
        this.id = id;
    }
    public int getid()
    {
        return id;
    }
    // Login
    public void setLogin(String login)
    {
        this.login = login;
    }
    public String getLogin()
    {
        return login;
    }
    // Password
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword() { return password; }
}