package paradiseTravels.service.user.register;

import paradiseTravels.model.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterRequest {
    @Valid
    User user;
    @NotNull
    @Size(min=4,max=30)
    String passwordRepeat;

    public RegisterRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
    //
}
