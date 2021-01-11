package ru.Pegov.Macragge.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * A model for request reportName
 * @author Андрей
 */
public class ReportRequest {
    private String reportName;
    private long from;
    private long to;
    private Map<String,String> additionalData;
    
    @JsonCreator
    public ReportRequest(@JsonProperty("report") String reportName, 
            @JsonProperty("from")long from,
            @JsonProperty("to") long to,
            @JsonProperty("additionalData") Map<String,String> additionalData) {
        this.reportName = reportName;
        this.from = from;
        this.to = to;
        this.additionalData = additionalData;
    }

    public String getReportName() {
        return reportName;
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }

    public Map<String, String> getAdditionalData() {
        return additionalData;
    }

    @Override
    public String toString() {
        return "ReportRequest{" + "reportName=" + reportName + ", from=" + from + ", to=" + to + ", additionalData=" + additionalData + '}';
    }
}
