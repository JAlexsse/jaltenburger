package ayi.microservicios.commonsusuarios.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 4002221912401133094L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String username;

    @Column(length = 60)
    private String password;

    private Boolean enabled;
    private String name;
    private String lastname;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "usuarios_roles", 
        joinColumns = @JoinColumn(name="usuario_id"), 
        inverseJoinColumns = @JoinColumn(name="role_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})}    
    )
    private List<Role> roles;
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }   

}
