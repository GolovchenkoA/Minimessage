package ua.golovchenko.artem.minimessage.model;

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
@Entity
@Table(name="USER_ACCOUNTS", schema = "messages")
public class UserAccount implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Set<Message> messages = new HashSet();
    private Date created;

    @Id
    @GeneratedValue
    @Column( name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @return user login
     */
    @Column( name = "id", unique = true, nullable = false, length = 25)
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

    @OneToMany
    @JoinColumn(name="ID")
    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
}
