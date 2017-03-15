package ua.golovchenko.artem.minimessage.model;

import java.sql.Date;

/**
 * Created by головченко on 15.03.2017.
 */
public class Message {
    private Long id;
    private UserAccount account;
    private String text;
    private Date created;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
