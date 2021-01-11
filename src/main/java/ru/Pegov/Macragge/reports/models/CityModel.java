/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.reports.models;


/**
 *
 * @author Андрей
 */
public class CityModel {
    private String name;
    private String regionName = "";
    private int countL2TP = 0;
    private int countL3TP = 0;
    private double totalTimeL3TP = 0.0; 
    private double totalTimeL2TP = 0.0; 
    private double totalTime = 0.0;
    private int atWorkCount = 0;
    private int atWorkCountL2 = 0;
    private int atWorkCountL3 = 0;
    private int compleateAt36h = 0;
    private int compleateAt48h = 0;
    private int compleateAt72h = 0;
    private int compleateAtMore72h = 0;
    private int repeatedTTL2 = 0;
    private int repeatedTTL3 = 0;
    private int compleateAt6hL2 = 0;
    private int compleateAt8hL2 = 0;
    private int compleateAt24hL2 = 0;
    private int compleateAtMore24hL2 = 0;
    private int compleateAt24hL3 = 0;
    private int compleateAt36hL3 = 0;
    private int compleateAt48hL3 = 0;
    private int compleateAt72hL3 = 0;
    private int compleateAtMore72hL3 = 0;
    private long decisionToTransfer = 0;

    public CityModel(String name) {
        this.name = name;
    }
    
    public CityModel(String name, String regionName) {
        this.name = name;
        this.regionName = regionName;
    }
    
//-------------Increase methods-----------------    
    
    /**
     * increase count of TT from L2 Technical Support
     */
    public void incCountL2TP(){
        countL2TP++;
    }
    
    /**
     * increase count of TT from L3 Technical Support
     */
    public void incCountL3TP(){
        countL3TP++;
    }
    
    /**
     * increases l3tp total work time on time (hours)
     * @param time - is a time of single TT that was on L3TP
     */
    public void incTotalTimeL3TP(double time){
        totalTimeL3TP = totalTimeL3TP + time;
        totalTime = totalTime + time;
    }
    
    /**
     * increases l2tp total work time on time (hours)
     * @param time - is a lifetime of single TT that was on L3TP
     */
    public void incTotalTimeL2TP(double time){
        totalTimeL2TP = totalTimeL2TP + time;
        totalTime = totalTime + time;
    }
    
    /**
     * increases count of TT that have at work state
     */
    public void incAtWork(){
        atWorkCount++;
    }
    
    /**
     * increases count of TT that have at work state, at L2
     */
    public void incAtWorkL2(){
        atWorkCountL2++;
    }
    
    /**
     * increases count of TT that have at work state, at L3
     */
    public void incAtWorkL3(){
        atWorkCountL3++;
    }
    
    /**
     * increases count of TT that compleat or continues,
     * but lifetime less 36 hours
     */
    public void incCompleateAt36h(){
        compleateAt36h++;
    }
    
    /**
     * increases count of TT that compleat or continues,
     * but lifetime less 48 hours
     */
    public void incCompleateAt48h(){
        compleateAt48h++;
    }
    
    /**
     * increases count of TT that compleat or continues,
     * but lifetime less 72 hours
     */
    public void incCompleateAt72h(){
        compleateAt72h++;
    }
    
    /**
     * increases count of TT that compleat or continues,
     * but lifetime more 72 hours
     */
    public void incCompleateAtMore72h(){
        compleateAtMore72h++;
    }
    
    /**
     * increases count of repeated treatmentto L2TP
     */
    public void incRepeatedTTL2(){
        repeatedTTL2++;
    }
    
    /**
     * increases count of repeated treatmentto L3TP
     */
    public void incRepeatedTTL3(){
        repeatedTTL3++;
    }
    
    /**
     * increases count of TT that compleat or continues at L2,
     * but lifetime less 6 hours
     */
    public void incCompleateAt6hL2(){
        compleateAt6hL2++;
    }
    
    /**
     * increases count of TT that compleat or continues at L2,
     * but lifetime less 8 hours
     */
    public void incCompleateAt8hL2(){
        compleateAt8hL2++;
    }
    
    /**
     * increases count of TT that compleat or continues at L2,
     * but lifetime less 24 hours
     */
    public void incCompleateAt24hL2(){
        compleateAt24hL2++;
    }
    
    /**
     * increases count of TT that compleat or continues at L2,
     * but lifetime more 24 hours
     */
    public void incCompleateAtMore24hL2(){
        compleateAtMore24hL2++;
    }
    
    /**
     * increases count of TT that compleat or continues on L3,
     * but lifetime less 24 hours
     */
    public void incCompleateAt24hL3(){
        compleateAt24hL3++;
    }
    
    /**
     * increases count of TT that compleat or continues on L3,
     * but lifetime less 36 hours
     */
    public void incCompleateAt36hL3(){
        compleateAt36hL3++;
    }
    
    /**
     * increases count of TT that compleat or continues on L3,
     * but lifetime less 48 hours
     */
    public void incCompleateAt48hL3(){
        compleateAt48hL3++;
    }
    
    /**
     * increases count of TT that compleat or continues on L3,
     * but lifetime less 72 hours
     */
    public void incCompleateAt72hL3(){
        compleateAt72hL3++;
    }
    
    /**
     * increases count of TT that compleat or continues on L3,
     * but lifetime more 72 hours
     */
    public void incCompleateAtMore72hL3(){
        compleateAtMore72hL3++;
    }
    
    /**
     * add time to all decisionToTransfer, need for calculate avg decision time
     */
    public void incDecisionToTransfer(long time){
        decisionToTransfer += time;
    }
    
//-------------Setters-----------------  
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRegionName(String regionName){
        this.regionName = regionName;
    }
    
    public void setCountL2TP(int countL2TP) {
        this.countL2TP = countL2TP;
    }

    public void setCountL3TP(int countL3TP) {
        this.countL3TP = countL3TP;
    }

    public String getName() {
        return name;
    }

//-------------Getters-----------------  
    
    public int getCountL2TP() {
        return countL2TP;
    }

    public int getCountL3TP() {
        return countL3TP;
    }

    public double getTotalTimeL3TP() {
        return totalTimeL3TP;
    }

    public double getTotalTimeL2TP() {
        return totalTimeL2TP;
    }
    
    public String getRegionName(){
        return regionName;
    }

    public int getAtWorkCountL2() {
        return atWorkCountL2;
    }

    public int getAtWorkCount() {
        return atWorkCount;
    }
    
    public int getCompleateAt36h() {
        return compleateAt36h;
    }

    public int getCompleateAt48h() {
        return compleateAt48h;
    }

    public int getCompleateAt72h() {
        return compleateAt72h;
    }

    public int getCompleateAtMore72h() {
        return compleateAtMore72h;
    }

    public int getRepeatedTTL2() {
        return repeatedTTL2;
    }

    public int getRepeatedTTL3() {
        return repeatedTTL3;
    }

    public int getCompleateAt6hL2() {
        return compleateAt6hL2;
    }

    public int getCompleateAt8hL2() {
        return compleateAt8hL2;
    }

    public int getCompleateAt24hL2() {
        return compleateAt24hL2;
    }

    public int getCompleateAtMore24hL2() {
        return compleateAtMore24hL2;
    }
    
    public int getCompleateAt24hL3() {
        return compleateAt24hL3;
    }

    public int getCompleateAt36hL3() {
        return compleateAt36hL3;
    }

    public int getCompleateAt48hL3() {
        return compleateAt48hL3;
    }

    public int getCompleateAt72hL3() {
        return compleateAt72hL3;
    }

    public int getCompleateAtMore72hL3() {
        return compleateAtMore72hL3;
    }

    public int getAtWorkCountL3() {
        return atWorkCountL3;
    }
    
    public int getWorkCount() {
        return atWorkCount;
    }

    public long getDecisionToTransfer() {
        return decisionToTransfer;
    }
    
    
    
    /**
     * 
     * @return Averege Time of all TT was on L3TP in hours.
     */
    public double getAveregeTimeL3TP(){
        //по просьбе Белоусовой среднее считается без учета тех что в работе, разница не сильно велика
        if((countL3TP + atWorkCountL3) != 0){
            return totalTimeL3TP/(countL3TP + atWorkCountL3);
        }else{
            return -0.0;
        }
    }
    
    /**
     * 
     * @return Averege Time of all TT was on L2TP in hours.
     */
    public double getAveregeTimeL2TP(){
        if(countL2TP != 0 || atWorkCountL2 != 0){
            return totalTimeL2TP/(countL2TP + atWorkCountL2);
        }else{
            return -0.0;
        }
    }
    
    /**
     * 
     * @return Averege Time of all TT was on L2TP in hours.
     */
    public double getAveregeTimeL23TP(){
        if(countL2TP != 0){
            return totalTime/countL2TP;
        }else{
            return -0.0;
        }
    }
    
    /**
     * 
     * @return Averege Time of all TT was on L2TP in hours.
     */
    public double getDecisionTime(){
        if((countL2TP + atWorkCountL2) != 0){
            return ((double)decisionToTransfer/(countL2TP + atWorkCountL2))/1000/60/60;
        }else{
            return -0.0;
        }
    }

    @Override
    public String toString() {
        return "CityModel{" + "name=" + name 
                + ", countL2TP=" + countL2TP
                + ", countL3TP=" + countL3TP 
                + ", AveregeTimeL3TP()" + this.getAveregeTimeL3TP() 
                + ", AveregeTimeL2TP()" + this.getAveregeTimeL2TP()
                + ", totalTime" + totalTime
                +'}';
    }
    
    
}
