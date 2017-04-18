package ua.golovchenko.artem.minimessage.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

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

    @NotNull(message = "login is blank")
    @Size(min=1, max=25)
    @Pattern(regexp="^[a-zA-Z0-9]+$",
            message="Username must be alphanumeric with no spaces. Max 25 characters")
    private String username;

    @NotNull(message = "password is blank")
    @Size(min=6, max=25,
            message="The password must be at least 6 characters long and maximum 25 characters.") //<co id="co_enforceSize"/>
    private String password;
    private Set<Message> messages = new HashSet<>();
    private Set<AccountRoleImpl> accountRoles = new HashSet<>();

    private Date created;
    private boolean enabled;

    public UserAccount(){}

    public UserAccount(String username, String password, Date created) {
        this.username = username;
        this.password = password;
        this.created = created;
    }

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

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER) //, cascade = CascadeType.ALL)
    //@OrderBy("created ASC")
    //@JoinColumn(name="id")
    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ACCOUNTS_AND_ROLES",
            joinColumns = @JoinColumn(name = "ACCOUNT_ID",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID",nullable = false))
    public Set<AccountRoleImpl> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(Set<AccountRoleImpl> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public void addRole(AccountRoleImpl role){
        accountRoles.add(role);
    }

     public void removeRole(AccountRoleImpl role){
        accountRoles.remove(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount account = (UserAccount) o;

        if (!accountRoles.equals(account.accountRoles)) return false;
        if (messages != null ? !messages.equals(account.messages) : account.messages != null) return false;
        if (!password.equals(account.password)) return false;
        if (!username.equals(account.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    // Equals and HashCode without AccountRoles
    /*    @Override
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
    }*/

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", created=" + created +
                '}';
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
