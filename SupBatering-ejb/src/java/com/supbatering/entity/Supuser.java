/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author oumartraore
 */
@Entity
@XmlRootElement
public class Supuser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Supuser() {
        
    }
    
    public Supuser(String username, String password, String email,
            String firstname, String lastname, int codePostale) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.codePostale = codePostale;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "codePostale", nullable = false)
    private int codePostale;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    
    @OneToMany(mappedBy = "supuser")
    private Collection<Supobject> supobjects;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supuser)) {
            return false;
        }
        Supuser other = (Supuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supbatering.entity.Supuser[ id=" + id + " ]";
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the _email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param _email the _email to set
     */
    public void setEmail(String _email) {
        this.email = _email;
    }

    /**
     * @return the _codePostale
     */
    public int getCodePostale() {
        return codePostale;
    }

    /**
     * @param _codePostale the _codePostale to set
     */
    public void setCodePostale(int _codePostale) {
        this.codePostale = _codePostale;
    }

    /**
     * @return the supobjects
     */
    @XmlTransient
    public Collection<Supobject> getSupobjects() {
        return supobjects;
    }

    /**
     * @param supobjects the supobjects to set
     */
    public void setSupobjects(Collection<Supobject> supobjects) {
        this.supobjects = supobjects;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
}
