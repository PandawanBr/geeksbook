package blackbelt.com.geeksbook.utils;

import java.io.Serializable;

/**
 * Created by kobayashi on 05/11/17.
 */

public class Login implements Serializable{
    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
