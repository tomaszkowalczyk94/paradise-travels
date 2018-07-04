package paradiseTravels.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable, EntityIdInterface {

    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //można nie podawać Column name, jeżeli ono jest takie same jak nazwa columny w tabeli
    @NotNull
    @Size(min=2,max=30)
    private String firstName;

    @NotNull
    @Size(min=2,max=30)
    private String lastName;
    //wiele userow ma ten sam adres

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @NotNull
    @Size(min=2,max=30)
    private String email;

    @NotNull
    @Size(min=4,max=30)
    private String login;
    @NotNull
    @Size(min=4,max=30)
    private String password;

    private String role;

    public User() {
    }

    public User(String firstName, String lastName, Address address, String email, String login, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                ", user_id=" + id +
                ", login=" + login +
                ", password=" + password +
                ", role=" + role +
                "}";
    }
}
