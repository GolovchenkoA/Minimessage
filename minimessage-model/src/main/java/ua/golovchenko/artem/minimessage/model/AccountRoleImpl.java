package ua.golovchenko.artem.minimessage.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Artem on 17.04.2017.
 *
 * <p>Accounts roles that stores in db</p>
 *
 * @author Golovchenko Artem
 *
 */
@Component
@Entity
@Table(name = "ACCOUNTS_ROLES")
public class AccountRoleImpl implements AccountRole,Serializable {

    /** Role index.Is assigned and stored in the database. */
    private Long id;

    @NotNull(message = "role name is blank")
    @Size(min=4, max=25)
    @Pattern(regexp="^[ A-Za-z0-9_]*$",message="Role name must be alphanumeric with no spaces. Can contain underscores. Min 4 and Max 25 characters")
    private String name;
    private Collection<UserAccount> roleMembers;

    public AccountRoleImpl() {
    }

    public AccountRoleImpl(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "accountRoles")
    public Collection<UserAccount> getRoleMembers() {
        return roleMembers;
    }

    public void setRoleMembers(Collection<UserAccount> roleMembers) {
        this.roleMembers = roleMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountRoleImpl that = (AccountRoleImpl) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name;
    }
}
