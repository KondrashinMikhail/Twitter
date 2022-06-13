package Twitter.Entities.User.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserSignupDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
