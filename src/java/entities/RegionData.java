/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "RegionData", catalog = "careermove", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegionData.findAll", query = "SELECT r FROM RegionData r"),
    @NamedQuery(name = "RegionData.findByIndustry", query = "SELECT r FROM RegionData r WHERE r.regionDataPK.industry = :industry"),
    @NamedQuery(name = "RegionData.findByAnzsco", query = "SELECT r FROM RegionData r WHERE r.regionDataPK.anzsco = :anzsco"),
    @NamedQuery(name = "RegionData.findBySa4code", query = "SELECT r FROM RegionData r WHERE r.regionDataPK.sa4code = :sa4code"),
    @NamedQuery(name = "RegionData.findBySa4name", query = "SELECT r FROM RegionData r WHERE r.sa4name = :sa4name"),
    @NamedQuery(name = "RegionData.findByTotalScore", query = "SELECT r FROM RegionData r WHERE r.totalScore = :totalScore"),
    @NamedQuery(name = "RegionData.findByRank", query = "SELECT r FROM RegionData r WHERE r.rank = :rank"),
    @NamedQuery(name = "RegionData.findByUnempRateMvmt", query = "SELECT r FROM RegionData r WHERE r.unempRateMvmt = :unempRateMvmt"),
    @NamedQuery(name = "RegionData.findByUnempRateScore", query = "SELECT r FROM RegionData r WHERE r.unempRateScore = :unempRateScore"),
    @NamedQuery(name = "RegionData.findByAverageAnnualMovement", query = "SELECT r FROM RegionData r WHERE r.averageAnnualMovement = :averageAnnualMovement"),
    @NamedQuery(name = "RegionData.findByAverageAnnualMovementScore", query = "SELECT r FROM RegionData r WHERE r.averageAnnualMovementScore = :averageAnnualMovementScore"),
    @NamedQuery(name = "RegionData.findByHousePrice", query = "SELECT r FROM RegionData r WHERE r.housePrice = :housePrice"),
    @NamedQuery(name = "RegionData.findByHousePriceScore", query = "SELECT r FROM RegionData r WHERE r.housePriceScore = :housePriceScore"),
    @NamedQuery(name = "RegionData.findByRentPrice", query = "SELECT r FROM RegionData r WHERE r.rentPrice = :rentPrice"),
    @NamedQuery(name = "RegionData.findByRentPriceScore", query = "SELECT r FROM RegionData r WHERE r.rentPriceScore = :rentPriceScore"),
    @NamedQuery(name = "RegionData.findByMedianIncomel", query = "SELECT r FROM RegionData r WHERE r.medianIncomel = :medianIncomel"),
    @NamedQuery(name = "RegionData.findByMedianIncomeScore", query = "SELECT r FROM RegionData r WHERE r.medianIncomeScore = :medianIncomeScore"),
    @NamedQuery(name = "RegionData.findByPatentIncrease", query = "SELECT r FROM RegionData r WHERE r.patentIncrease = :patentIncrease"),
    @NamedQuery(name = "RegionData.findByPatentIncreaseScore", query = "SELECT r FROM RegionData r WHERE r.patentIncreaseScore = :patentIncreaseScore"),
    @NamedQuery(name = "RegionData.findByTradeMarksIncrease", query = "SELECT r FROM RegionData r WHERE r.tradeMarksIncrease = :tradeMarksIncrease"),
    @NamedQuery(name = "RegionData.findByTradeMarksScore", query = "SELECT r FROM RegionData r WHERE r.tradeMarksScore = :tradeMarksScore"),
    @NamedQuery(name = "RegionData.findByNewBusinessIncrease", query = "SELECT r FROM RegionData r WHERE r.newBusinessIncrease = :newBusinessIncrease"),
    @NamedQuery(name = "RegionData.findByNewBusinessScore", query = "SELECT r FROM RegionData r WHERE r.newBusinessScore = :newBusinessScore"),
    @NamedQuery(name = "RegionData.findBySA4UnempRate", query = "SELECT r FROM RegionData r WHERE r.sA4UnempRate = :sA4UnempRate"),
    @NamedQuery(name = "RegionData.findByOccUnemp1315", query = "SELECT r FROM RegionData r WHERE r.occUnemp1315 = :occUnemp1315"),
    @NamedQuery(name = "RegionData.findByOccUnemp1315Score", query = "SELECT r FROM RegionData r WHERE r.occUnemp1315Score = :occUnemp1315Score"),
    @NamedQuery(name = "RegionData.findBySA4UnempRateScore", query = "SELECT r FROM RegionData r WHERE r.sA4UnempRateScore = :sA4UnempRateScore"),
    @NamedQuery(name = "RegionData.findByTotalBusinesses13", query = "SELECT r FROM RegionData r WHERE r.totalBusinesses13 = :totalBusinesses13"),
    @NamedQuery(name = "RegionData.findByTotalBusinesses14", query = "SELECT r FROM RegionData r WHERE r.totalBusinesses14 = :totalBusinesses14"),
    @NamedQuery(name = "RegionData.findByIncreaseBusinesses", query = "SELECT r FROM RegionData r WHERE r.increaseBusinesses = :increaseBusinesses"),
    @NamedQuery(name = "RegionData.findByIncreaseBusScore", query = "SELECT r FROM RegionData r WHERE r.increaseBusScore = :increaseBusScore"),
    @NamedQuery(name = "RegionData.findByJobVacancyMvmt", query = "SELECT r FROM RegionData r WHERE r.jobVacancyMvmt = :jobVacancyMvmt"),
    @NamedQuery(name = "RegionData.findByJobVacancyScore", query = "SELECT r FROM RegionData r WHERE r.jobVacancyScore = :jobVacancyScore"),
    @NamedQuery(name = "RegionData.findByNumBus1315Mvmt", query = "SELECT r FROM RegionData r WHERE r.numBus1315Mvmt = :numBus1315Mvmt"),
    @NamedQuery(name = "RegionData.findByNumBus1315Score", query = "SELECT r FROM RegionData r WHERE r.numBus1315Score = :numBus1315Score")})
public class RegionData implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegionDataPK regionDataPK;
    @Size(max = 150)
    @Column(name = "SA4NAME", length = 150)
    private String sa4name;
    @Column(name = "TotalScore")
    private Integer totalScore;
    @Column(name = "rank")
    private Integer rank;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "UnempRateMvmt", precision = 22)
    private Double unempRateMvmt;
    @Column(name = "UnempRateScore")
    private Integer unempRateScore;
    @Column(name = "AverageAnnualMovement", precision = 22)
    private Double averageAnnualMovement;
    @Column(name = "AverageAnnualMovementScore")
    private Integer averageAnnualMovementScore;
    @Column(name = "HousePrice", precision = 22)
    private Double housePrice;
    @Column(name = "HousePriceScore")
    private Integer housePriceScore;
    @Column(name = "RentPrice", precision = 22)
    private Double rentPrice;
    @Column(name = "RentPriceScore")
    private Integer rentPriceScore;
    @Column(name = "MedianIncomel", precision = 22)
    private Double medianIncomel;
    @Column(name = "MedianIncomeScore")
    private Integer medianIncomeScore;
    @Column(name = "PatentIncrease", precision = 22)
    private Double patentIncrease;
    @Column(name = "PatentIncreaseScore")
    private Integer patentIncreaseScore;
    @Column(name = "TradeMarksIncrease", precision = 22)
    private Double tradeMarksIncrease;
    @Column(name = "TradeMarksScore")
    private Integer tradeMarksScore;
    @Column(name = "NewBusinessIncrease", precision = 22)
    private Double newBusinessIncrease;
    @Column(name = "NewBusinessScore")
    private Integer newBusinessScore;
    @Column(name = "SA4UnempRate", precision = 22)
    private Double sA4UnempRate;
    @Column(name = "OccUnemp1315", precision = 22)
    private Double occUnemp1315;
    @Column(name = "OccUnemp1315Score")
    private Integer occUnemp1315Score;
    @Column(name = "SA4UnempRateScore")
    private Integer sA4UnempRateScore;
    @Column(name = "TotalBusinesses13", precision = 22)
    private Double totalBusinesses13;
    @Column(name = "TotalBusinesses14", precision = 22)
    private Double totalBusinesses14;
    @Column(name = "IncreaseBusinesses", precision = 22)
    private Double increaseBusinesses;
    @Column(name = "IncreaseBusScore")
    private Integer increaseBusScore;
    @Column(name = "JobVacancyMvmt", precision = 22)
    private Double jobVacancyMvmt;
    @Column(name = "JobVacancyScore")
    private Integer jobVacancyScore;
    @Column(name = "NumBus1315Mvmt", precision = 22)
    private Double numBus1315Mvmt;
    @Column(name = "NumBus1315Score")
    private Integer numBus1315Score;

    public RegionData() {
    }

    public RegionData(RegionDataPK regionDataPK) {
        this.regionDataPK = regionDataPK;
    }

    public RegionData(String industry, String anzsco, String sa4code) {
        this.regionDataPK = new RegionDataPK(industry, anzsco, sa4code);
    }

    public RegionDataPK getRegionDataPK() {
        return regionDataPK;
    }

    public void setRegionDataPK(RegionDataPK regionDataPK) {
        this.regionDataPK = regionDataPK;
    }

    public String getSa4name() {
        return sa4name;
    }

    public void setSa4name(String sa4name) {
        this.sa4name = sa4name;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getUnempRateMvmt() {
        return unempRateMvmt;
    }

    public void setUnempRateMvmt(Double unempRateMvmt) {
        this.unempRateMvmt = unempRateMvmt;
    }

    public Integer getUnempRateScore() {
        return unempRateScore;
    }

    public void setUnempRateScore(Integer unempRateScore) {
        this.unempRateScore = unempRateScore;
    }

    public Double getAverageAnnualMovement() {
        return averageAnnualMovement;
    }

    public void setAverageAnnualMovement(Double averageAnnualMovement) {
        this.averageAnnualMovement = averageAnnualMovement;
    }

    public Integer getAverageAnnualMovementScore() {
        return averageAnnualMovementScore;
    }

    public void setAverageAnnualMovementScore(Integer averageAnnualMovementScore) {
        this.averageAnnualMovementScore = averageAnnualMovementScore;
    }

    public Double getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(Double housePrice) {
        this.housePrice = housePrice;
    }

    public Integer getHousePriceScore() {
        return housePriceScore;
    }

    public void setHousePriceScore(Integer housePriceScore) {
        this.housePriceScore = housePriceScore;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Integer getRentPriceScore() {
        return rentPriceScore;
    }

    public void setRentPriceScore(Integer rentPriceScore) {
        this.rentPriceScore = rentPriceScore;
    }

    public Double getMedianIncomel() {
        return medianIncomel;
    }

    public void setMedianIncomel(Double medianIncomel) {
        this.medianIncomel = medianIncomel;
    }

    public Integer getMedianIncomeScore() {
        return medianIncomeScore;
    }

    public void setMedianIncomeScore(Integer medianIncomeScore) {
        this.medianIncomeScore = medianIncomeScore;
    }

    public Double getPatentIncrease() {
        return patentIncrease;
    }

    public void setPatentIncrease(Double patentIncrease) {
        this.patentIncrease = patentIncrease;
    }

    public Integer getPatentIncreaseScore() {
        return patentIncreaseScore;
    }

    public void setPatentIncreaseScore(Integer patentIncreaseScore) {
        this.patentIncreaseScore = patentIncreaseScore;
    }

    public Double getTradeMarksIncrease() {
        return tradeMarksIncrease;
    }

    public void setTradeMarksIncrease(Double tradeMarksIncrease) {
        this.tradeMarksIncrease = tradeMarksIncrease;
    }

    public Integer getTradeMarksScore() {
        return tradeMarksScore;
    }

    public void setTradeMarksScore(Integer tradeMarksScore) {
        this.tradeMarksScore = tradeMarksScore;
    }

    public Double getNewBusinessIncrease() {
        return newBusinessIncrease;
    }

    public void setNewBusinessIncrease(Double newBusinessIncrease) {
        this.newBusinessIncrease = newBusinessIncrease;
    }

    public Integer getNewBusinessScore() {
        return newBusinessScore;
    }

    public void setNewBusinessScore(Integer newBusinessScore) {
        this.newBusinessScore = newBusinessScore;
    }

    public Double getSA4UnempRate() {
        return sA4UnempRate;
    }

    public void setSA4UnempRate(Double sA4UnempRate) {
        this.sA4UnempRate = sA4UnempRate;
    }

    public Double getOccUnemp1315() {
        return occUnemp1315;
    }

    public void setOccUnemp1315(Double occUnemp1315) {
        this.occUnemp1315 = occUnemp1315;
    }

    public Integer getOccUnemp1315Score() {
        return occUnemp1315Score;
    }

    public void setOccUnemp1315Score(Integer occUnemp1315Score) {
        this.occUnemp1315Score = occUnemp1315Score;
    }

    public Integer getSA4UnempRateScore() {
        return sA4UnempRateScore;
    }

    public void setSA4UnempRateScore(Integer sA4UnempRateScore) {
        this.sA4UnempRateScore = sA4UnempRateScore;
    }

    public Double getTotalBusinesses13() {
        return totalBusinesses13;
    }

    public void setTotalBusinesses13(Double totalBusinesses13) {
        this.totalBusinesses13 = totalBusinesses13;
    }

    public Double getTotalBusinesses14() {
        return totalBusinesses14;
    }

    public void setTotalBusinesses14(Double totalBusinesses14) {
        this.totalBusinesses14 = totalBusinesses14;
    }

    public Double getIncreaseBusinesses() {
        return increaseBusinesses;
    }

    public void setIncreaseBusinesses(Double increaseBusinesses) {
        this.increaseBusinesses = increaseBusinesses;
    }

    public Integer getIncreaseBusScore() {
        return increaseBusScore;
    }

    public void setIncreaseBusScore(Integer increaseBusScore) {
        this.increaseBusScore = increaseBusScore;
    }

    public Double getJobVacancyMvmt() {
        return jobVacancyMvmt;
    }

    public void setJobVacancyMvmt(Double jobVacancyMvmt) {
        this.jobVacancyMvmt = jobVacancyMvmt;
    }

    public Integer getJobVacancyScore() {
        return jobVacancyScore;
    }

    public void setJobVacancyScore(Integer jobVacancyScore) {
        this.jobVacancyScore = jobVacancyScore;
    }

    public Double getNumBus1315Mvmt() {
        return numBus1315Mvmt;
    }

    public void setNumBus1315Mvmt(Double numBus1315Mvmt) {
        this.numBus1315Mvmt = numBus1315Mvmt;
    }

    public Integer getNumBus1315Score() {
        return numBus1315Score;
    }

    public void setNumBus1315Score(Integer numBus1315Score) {
        this.numBus1315Score = numBus1315Score;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regionDataPK != null ? regionDataPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegionData)) {
            return false;
        }
        RegionData other = (RegionData) object;
        if ((this.regionDataPK == null && other.regionDataPK != null) || (this.regionDataPK != null && !this.regionDataPK.equals(other.regionDataPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RegionData[ regionDataPK=" + regionDataPK + " ]";
    }
    
}
