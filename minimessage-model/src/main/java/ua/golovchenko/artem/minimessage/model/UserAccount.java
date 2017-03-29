package ua.golovchenko.artem.minimessage.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by головченко on 15.03.2017.
 *
 */

@Component
@Scope("prototype")
@Entity
@Table(name="USER_ACCOUNTS")
public class UserAccount implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Set<Message> messages = new HashSet();
    private Date created;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @return user login
     */
    @Column( name = "username", unique = true, nullable = false, length = 25)
    public String getUsername() {
        return username;
    }

    /**
     * @param username - this is user login
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", unique = false, nullable = false, length = 25)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", unique = false, nullable = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(mappedBy = "account") //, cascade = CascadeType.ALL)
    //@JoinColumn(name="id")
    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (!password.equals(that.password)) return false;
        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", created=" + created +
                '}';
    }
}
