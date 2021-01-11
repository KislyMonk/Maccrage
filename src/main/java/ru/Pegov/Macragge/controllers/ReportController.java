package ru.Pegov.Macragge.controllers;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Pegov.Macragge.models.ErrorMessage;
import ru.Pegov.Macragge.models.Regular;
import ru.Pegov.Macragge.models.Reply;
import ru.Pegov.Macragge.models.ReportRequest;
import ru.Pegov.Macragge.reports.ReportBuilder;
import ru.Pegov.Macragge.reports.ReportBuilderFactory;
import ru.Pegov.Macragge.reports.UnknownDataExeption;

/**
 *
 * @author Андрей
 */

@RestController
@RequestMapping(value= "/getReport")
public class ReportController {
    
    //default getter, nothing to do
    @GetMapping()
    public Reply startState(){        
        return new ErrorMessage("Use POST-method");
    }
    
    @PostMapping()
    public Reply getReport(@RequestBody ReportRequest request){
        ReportBuilderFactory rbf = new ReportBuilderFactory();
        
        if(request != null){
            System.err.println(request);
            ReportBuilder rb;
            try {
                rb = rbf.getReportBuilder(request.getReportName());
            } catch (UnknownDataExeption ex) {
                return new ErrorMessage("Wrong reportName:\'" + request.getReportName() + "\', " + ex.getMessage());
            }
            rb.setDateRange(new Timestamp(request.getFrom())
                          , new Timestamp(request.getTo()));
            
            return new Regular(request.getReportName(), rb.getReport());
            
        }
        return new ErrorMessage("In work");
    }
}
