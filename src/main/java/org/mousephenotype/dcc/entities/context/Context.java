/*
 * Copyright 2013 Medical Research Council Harwell.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mousephenotype.dcc.entities.context;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gagarine Yaikhom <g.yaikhom@har.mrc.ac.uk>
 */
@Entity
@Table(name = "context", catalog = "phenodcc_context", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"subject"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Context.findAll", query = "SELECT c FROM Context c"),
    @NamedQuery(name = "Context.findById", query = "SELECT c FROM Context c WHERE c.id = :id"),
    @NamedQuery(name = "Context.findBySubject", query = "SELECT c FROM Context c WHERE c.subject = :subject"),
    @NamedQuery(name = "Context.findByParent", query = "SELECT c FROM Context c WHERE c.parent = :parent"),
    @NamedQuery(name = "Context.findByIsValid", query = "SELECT c FROM Context c WHERE c.isValid = :isValid"),
    @NamedQuery(name = "Context.findByIsActive", query = "SELECT c FROM Context c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Context.findByToCda", query = "SELECT c FROM Context c WHERE c.toCda = :toCda"),
    @NamedQuery(name = "Context.findByLastUpdate", query = "SELECT c FROM Context c WHERE c.lastUpdate = :lastUpdate")})
public class Context implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false)
    private long subject;
    private BigInteger parent;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean isValid;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean isActive;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean toCda;
    @Basic(optional = false)
    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @JoinColumn(name = "current_type", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Types currentType;

    public Context() {
    }

    public Context(Long id) {
        this.id = id;
    }

    public Context(
            Long id,
            long subject,
            boolean isValid,
            boolean isActive,
            boolean toCda,
            Date lastUpdate) {
        this.id = id;
        this.subject = subject;
        this.isValid = isValid;
        this.isActive = isActive;
        this.toCda = toCda;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getSubject() {
        return subject;
    }

    public void setSubject(long subject) {
        this.subject = subject;
    }

    public BigInteger getParent() {
        return parent;
    }

    public void setParent(BigInteger parent) {
        this.parent = parent;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getToCda() {
        return toCda;
    }

    public void setToCda(boolean toCda) {
        this.toCda = toCda;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Types getCurrentType() {
        return currentType;
    }

    public void setCurrentType(Types currentType) {
        this.currentType = currentType;
    }
}
