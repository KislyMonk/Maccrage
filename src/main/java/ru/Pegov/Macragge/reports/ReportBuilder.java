/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.reports;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 *
 * @author Андрей
 */
public interface ReportBuilder {
    public void setDateRange(Timestamp from, Timestamp to);
    public void setAdditionalData(HashMap<String, Object> data) throws UnknownDataExeption;
    public Report getReport();
}
