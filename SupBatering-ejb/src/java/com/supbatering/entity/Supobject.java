/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oumartraore
 */
@Entity
@XmlRootElement
public class Supobject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Supobject() {
        
    }
    
    public Supobject(String name, String description, Long price, String filePath, Supuser supuser) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.filepath = filePath;
        this.supuser = supuser;
    }
    
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private Long price; 
    @Column(name = "picture", nullable = false)
    private String filepath;
    
    @ManyToOne
    @JoinColumn(name = "supuser_fk")
    private Supuser supuser;

    
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
        if (!(object instanceof Supobject)) {
            return false;
        }
        Supobject other = (Supobject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supbatering.entity.Supobject[ id=" + id + " ]";
    }

    /**
     * @return the _name
     */
    public String getName() {
        return name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * @return the _description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param _description the _description to set
     */
    public void setDescription(String _description) {
        this.description = _description;
    }

    /**
     * @return the price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * @return the supuser
     */
    public Supuser getSupuser() {
        return supuser;
    }

    /**
     * @param supuser the supuser to set
     */
    public void setSupuser(Supuser supuser) {
        this.supuser = supuser;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
