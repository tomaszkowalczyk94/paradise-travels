package paradiseTravels.service.user.login;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginRequest {
    @XmlElement
    String login;

    @XmlElement
    String password;
}
