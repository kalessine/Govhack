/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Owner
 */
@Embeddable
public class RegionDataPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "Industry")
    private String industry;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ANZSCO")
    private String anzsco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SA4CODE")
    private String sa4code;

    public RegionDataPK() {
    }

    public RegionDataPK(String industry, String anzsco, String sa4code) {
        this.industry = industry;
        this.anzsco = anzsco;
        this.sa4code = sa4code;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAnzsco() {
        return anzsco;
    }

    public void setAnzsco(String anzsco) {
        this.anzsco = anzsco;
    }

    public String getSa4code() {
        return sa4code;
    }

    public void setSa4code(String sa4code) {
        this.sa4code = sa4code;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (industry != null ? industry.hashCode() : 0);
        hash += (anzsco != null ? anzsco.hashCode() : 0);
        hash += (sa4code != null ? sa4code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegionDataPK)) {
            return false;
        }
        RegionDataPK other = (RegionDataPK) object;
        if ((this.industry == null && other.industry != null) || (this.industry != null && !this.industry.equals(other.industry))) {
            return false;
        }
        if ((this.anzsco == null && other.anzsco != null) || (this.anzsco != null && !this.anzsco.equals(other.anzsco))) {
            return false;
        }
        if ((this.sa4code == null && other.sa4code != null) || (this.sa4code != null && !this.sa4code.equals(other.sa4code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RegionDataPK[ industry=" + industry + ", anzsco=" + anzsco + ", sa4code=" + sa4code + " ]";
    }
    
}
