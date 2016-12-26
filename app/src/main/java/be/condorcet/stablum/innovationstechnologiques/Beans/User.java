package be.condorcet.stablum.innovationstechnologiques.Beans;

/**
 * Created by Ludovic on 26-12-16.
 */

public class User {
    private String login;
    private String password;

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getLogin()
    {
        return login;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
}