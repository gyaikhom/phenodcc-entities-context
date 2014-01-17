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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gagarine Yaikhom <g.yaikhom@har.mrc.ac.uk>
 */
@Entity
@Table(name = "types", catalog = "phenodcc_context", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"current_type"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Types.findAll", query = "SELECT t FROM Types t"),
    @NamedQuery(name = "Types.findById", query = "SELECT t FROM Types t WHERE t.id = :id"),
    @NamedQuery(name = "Types.findByCurrentType", query = "SELECT t FROM Types t WHERE t.currentType = :currentType")})
public class Types implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "current_type", nullable = false, length = 64)
    private String currentType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currentType")
    private Collection<Context> contextCollection;

    public Types() {
    }

    public Types(Integer id) {
        this.id = id;
    }

    public Types(Integer id, String currentType) {
        this.id = id;
        this.currentType = currentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    @XmlTransient
    public Collection<Context> getContextCollection() {
        return contextCollection;
    }

    public void setContextCollection(Collection<Context> contextCollection) {
        this.contextCollection = contextCollection;
    }
}
