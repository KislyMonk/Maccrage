/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.DB;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity class of macragge_main.tt table
 * @author Андрей
 */

@Entity
@Table(name = "TT", schema = "macragge_main")
public class TroubleTicket implements Serializable {
    @Id
    @Column(name = "ID", nullable=false)
    private String id;
    
    @Column(name = "MR", nullable=false)
    private String MR;
    
    @Column(name = "City", nullable=false)
    private String city;
    
    @Column(name = "Reason", nullable=false)
    private String reason;
    
    @Column(name = "DesTime")
    private Timestamp desdecisionTime;
    
    @Column(name = "OpeningDate", nullable=false)
    private Timestamp openingDate;
    
    @Column(name = "EndingDate")
    private Timestamp endingDate;
    
    @Column(name = "Status", nullable=false)
    private String status;
    
    @Column(name = "Employee", nullable=false)
    private String employee;
    
    @Column(name = "Start3LTP")
    private Timestamp start3LTP;
    
    @Column(name = "End3LTP")
    private Timestamp end3LTP;
    
    @Column(name = "RepeatedCount")
    private Integer repeatedCount;
    
    @Column(name = "LastTTFrom")
    private String lastTTFrom;
    
    @Column(name = "AppealOpening", nullable=false)
    private Timestamp appealOpening;
    
    @Column(name = "LastTransferOn2LTP", nullable=false)
    private Timestamp lastTransferOn2LTP;
    
    @Column(name = "Client")
    private Integer clientId;
    
    @Column(name = "timeOn3LTP")
    private Integer timeOn3LTP;
    
    @Column(name = "timeOn2LTP")
    private Long timeOn2LTP;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "comment")
    private String comment;

    @Column(name = "service")
    private String service;
    
    @Column(name = "group2ltp")
    private String group2ltp;

    @Column(name = "subgroup2ltp")
    private String subgroup2ltp;

    @Column(name = "reason2ltp")
    private String reason2ltp;

    @Column(name = "subreason2ltp")
    private String subreason2ltp;

    @Column(name = "group3ltp")
    private String group3ltp;

    @Column(name = "subgroup3ltp")
    private String subgroup3ltp;

    @Column(name = "reason3ltp")
    private String reason3ltp;

    @Column(name = "subreason3ltp")
    private String subreason3ltp;


    /**
     * Create new blank entity
     */
    public TroubleTicket() {
    }
    
    /**
     * Create new TroubleTicket with required parameters: 
     * @param id Number of TT
     * @param MR McroRegion name
     * @param city City name in MR
     * @param reason Reason to create TT
     * @param desdecisionTime Date when make desdecision transfer to 2ltp
     * @param status Status of TT
     * @param clientId Client's account number
     * @param appealOpening Date of opening appeal
     * @param lastTransferOn2LTP Date of last transfer on 2LTP
     * @param employee Employee's name who last updated TT
     */
    public TroubleTicket(String id, String MR, String city, String reason, Timestamp desdecisionTime, Timestamp openingDate, String status, String employee, Timestamp appealOpening, Timestamp lastTransferOn2LTP, Integer clientId) {
        this.id = id;
        this.MR = MR;
        this.city = city;
        this.reason = reason;
        this.desdecisionTime = desdecisionTime;
        this.openingDate = openingDate;
        this.status = status;
        this.employee = employee;
        this.appealOpening = appealOpening;
        this.lastTransferOn2LTP = lastTransferOn2LTP;
        this.clientId = clientId;
    }
    
    

    /**
     * Number of TT
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * McroRegion name
     * @return String MR
     */
    public String getMR() {
        return MR;
    }

    /**
     * City name in MR
     * @return String city
     */
    public String getCity() {
        return city;
    }

    /**
     * Reason to create TT
     * @return String reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Date when make desdecision transfer to 2ltp
     * @return Timestamp desdecisionTime
     */
    public Timestamp getDesdecisionTime() {
        return desdecisionTime;
    }

    /**
     * Date of opened Appeal
     * @return Timestamp openingDate
     */
    public Timestamp getOpeningDate() {
        return openingDate;
    }

    /**
     * Date of cloased TT
     * @return Timestamp endingDate
     */
    public Timestamp getEndingDate() {
        return endingDate;
    }

    /**
     * Status of TT
     * @return Timestamp status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Employee's name who last updated TT
     * @return String employee
     */
    public String getEmployee() {
        return employee;
    }

    /**
     * Date of tranfer TT to 3LTP
     * @return Timestamp start3LTP
     */
    public Timestamp getStart3LTP() {
        return start3LTP;
    }

    /**
     * Date of closed TT on 3LTP
     * @return Timestamp end3LTP
     */
    public Timestamp getEnd3LTP() {
        return end3LTP;
    }

    /**
     * Count of TT that was befor this, closed on 2LTP
     * @return Integer repeatedCount
     */
    public Integer getRepeatedCount() {
        return repeatedCount;
    }

    /**
     * Department generated repeated TT
     * @return String lastTTFrom
     */
    public String getLastTTFrom() {
        return lastTTFrom;
    }

    /**
     * Date of opening appeal
     * @return Timestamp appealOpening
     */
    public Timestamp getAppealOpening() {
        return appealOpening;
    }

    /**
     * Date of last transfer on 2LTP
     * @return Timestamp lastTransferOn2LTP
     */
    public Timestamp getLastTransferOn2LTP() {
        return lastTransferOn2LTP;
    }

    /**
     * Client's account number
     * @return Integer clientId
     */
    public Integer getClientId() {
        return clientId;
    }
    
    /**
     * 
     * @return Integer timeOn3LTP
     */
    public Integer getTimeOn3LTP() {
        if(timeOn3LTP != null) {
            return timeOn3LTP;
        }else{
            return 0;
        }
    }
    
    /**
     * 
     * @return timeOn2LTP
     */
    public Long getTimeOn2LTP() {
        return timeOn2LTP;
    }
    
    /**
     * 
     * @return client address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @return 1LTP comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Clients problem service
     * @return service
     */
    public String getService() {
        return service;
    }

    public String getGroup2ltp() {
        return group2ltp;
    }

    public String getSubgroup2ltp() {
        return subgroup2ltp;
    }

    public String getReason2ltp() {
        return reason2ltp;
    }

    public String getSubreason2ltp() {
        return subreason2ltp;
    }

    public String getGroup3ltp() {
        return group3ltp;
    }

    public String getSubgroup3ltp() {
        return subgroup3ltp;
    }

    public String getReason3ltp() {
        return reason3ltp;
    }

    public String getSubreason3ltp() {
        return subreason3ltp;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setGroup2ltp(String group2ltp) {
        this.group2ltp = group2ltp;
    }

    public void setSubgroup2ltp(String subgroup2ltp) {
        this.subgroup2ltp = subgroup2ltp;
    }

    public void setReason2ltp(String reason2ltp) {
        this.reason2ltp = reason2ltp;
    }

    public void setSubreason2ltp(String subreason2ltp) {
        this.subreason2ltp = subreason2ltp;
    }

    public void setGroup3ltp(String group3ltp) {
        this.group3ltp = group3ltp;
    }

    public void setSubgroup3ltp(String subgroup3ltp) {
        this.subgroup3ltp = subgroup3ltp;
    }

    public void setReason3ltp(String reason3ltp) {
        this.reason3ltp = reason3ltp;
    }

    public void setSubreason3ltp(String subreason3ltp) {
        this.subreason3ltp = subreason3ltp;
    }


    /**
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @param timeOn2LTP
     */
    public void setTimeOn2LTP(Long timeOn2LTP) {
        this.timeOn2LTP = timeOn2LTP;
    }
    
    
    
    /**
     *
     * @param timeOn3LTP
     */
    public void setTimeOn3LTP(Integer timeOn3LTP) {
        this.timeOn3LTP = timeOn3LTP;
    }
    
    

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @param MR
     */
    public void setMR(String MR) {
        this.MR = MR;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     *
     * @param desdecisionTime
     */
    public void setDesdecisionTime(Timestamp desdecisionTime) {
        this.desdecisionTime = desdecisionTime;
    }

    /**
     *
     * @param openingDate
     */
    public void setOpeningDate(Timestamp openingDate) {
        this.openingDate = openingDate;
    }

    /**
     *
     * @param endingDate
     */
    public void setEndingDate(Timestamp endingDate) {
        this.endingDate = endingDate;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(String employee) {
        this.employee = employee;
    }

    /**
     *
     * @param start3LTP
     */
    public void setStart3LTP(Timestamp start3LTP) {
        this.start3LTP = start3LTP;
    }

    /**
     *
     * @param end3LTP
     */
    public void setEnd3LTP(Timestamp end3LTP) {
        this.end3LTP = end3LTP;
    }

    /**
     *
     * @param repeatedCount
     */
    public void setRepeatedCount(Integer repeatedCount) {
        this.repeatedCount = repeatedCount;
    }

    /**
     *
     * @param lastTTFrom
     */
    public void setLastTTFrom(String lastTTFrom) {
        this.lastTTFrom = lastTTFrom;
    }

    /**
     *
     * @param appealOpening
     */
    public void setAppealOpening(Timestamp appealOpening) {
        this.appealOpening = appealOpening;
    }

    /**
     *
     * @param lastTransferOn2LTP
     */
    public void setLastTransferOn2LTP(Timestamp lastTransferOn2LTP) {
        this.lastTransferOn2LTP = lastTransferOn2LTP;
    }

    /**
     *
     * @param clientId
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TroubleTicket other = (TroubleTicket) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.MR, other.MR)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.openingDate, other.openingDate)) {
            return false;
        }
        if (!Objects.equals(this.endingDate, other.endingDate)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.end3LTP, other.end3LTP)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TroubleTicket{" + "id=" + id + ", MR=" + MR + ", city=" + city + ", reason=" + reason + ", desdecisionTime=" + desdecisionTime + ", openingDate=" + openingDate + ", endingDate=" + endingDate + ", status=" + status + ", employee=" + employee + ", start3LTP=" + start3LTP + ", end3LTP=" + end3LTP + ", repeatedCount=" + repeatedCount + ", lastTTFrom=" + lastTTFrom + ", appealOpening=" + appealOpening + ", lastTransferOn2LTP=" + lastTransferOn2LTP + ", clientId=" + clientId + ", timeOn3LTP=" + timeOn3LTP + '}';
    }

    
    
    
}
