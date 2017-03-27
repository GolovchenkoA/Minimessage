package ua.golovchenko.artem.minimessage.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

//import javax.persistence.Id;
//import javax.persistence.Table;

/**
 * Created by головченко on 15.03.2017.
 */

@Component
@Entity
@Table(name="MESSAGES")
public class Message implements Serializable {
    private Long id;
    private UserAccount account;
    private String text;
    private java.util.Date created;

    public Message(){};

    public Message(UserAccount account, String text) {
        this.account = account;
        this.text = text;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public java.util.Date getCreated() {
        return created;
    }

    public void setCreated(java.util.Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", account=" + account +
                ", text='" + text + '\'' +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!account.equals(message.account)) return false;
        if (!text.equals(message.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = account.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
