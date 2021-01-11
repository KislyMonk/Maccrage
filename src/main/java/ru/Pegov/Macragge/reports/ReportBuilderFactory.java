package ru.Pegov.Macragge.reports;

/**
 * 
 * @author Андрей
 */
public class ReportBuilderFactory {
    public ReportBuilder getReportBuilder (String reportName) throws UnknownDataExeption{
        switch(reportName){
            case("EmployeeReport"):
                return new EmployeeReportBuilder();
            case("BuildingsReport"):
                return new MostTTsByBuilding();
            case("TTMainReport"):
                return new TT_ReportBuilder();
            case("MaxDate"):
                return new LastClosedDateBuilder();
            default: throw new UnknownDataExeption("Unknown parameter reportName:" + reportName);
        }
    }
}
