package ua.golovchenko.artem.minimessage.model;

import javax.persistence.*;
import java.io.Serializable;

//import javax.persistence.Id;
//import javax.persistence.Table;

/**
 * Created by головченко on 15.03.2017.
 */

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
}
