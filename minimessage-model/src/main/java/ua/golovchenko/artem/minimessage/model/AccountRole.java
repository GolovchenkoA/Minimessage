package ua.golovchenko.artem.minimessage.model;

/**
 * Created by Artem on 17.04.2017.
 *  Account roles. example; user, admin etc
 */
public interface AccountRole {
    public Long getId();
    public void setId(Long id);
    public String getName();
    public void setName(String name);
}
