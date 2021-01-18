package ru.Pegov.test;

import org.junit.Ignore;
import org.junit.Test;
import ru.Pegov.Macragge.DB.TroubleTicket;
import ru.Pegov.Macragge.SigmaParser.SigmaReportParser;
import ru.Pegov.Macragge.reportDownload.TT_ReportDownloader;

import java.io.ByteArrayInputStream;
import java.util.List;

public class GetFileFromPost_Test {

    public GetFileFromPost_Test() {
    }

    @Test
    public void gettingTest(){
        TT_ReportDownloader TTRD = new TT_ReportDownloader();

        SigmaReportParser srp = new SigmaReportParser();

        String dateFrom = "11.1.2021";
        String dateTo = "13.1.2021";

        List<TroubleTicket> ttList = srp.getTTsList(new ByteArrayInputStream(TTRD.getReportUral(dateFrom, dateTo)));

        System.out.println(ttList.size());
    }
}