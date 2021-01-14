package ru.Pegov.test;

import org.junit.Ignore;
import org.junit.Test;
import ru.Pegov.Macragge.reportDownload.TT_ReportDownloader;

public class GetFileFromPost_Test {

    public GetFileFromPost_Test() {
    }

    @Test
    public void gettingTest(){
        TT_ReportDownloader TTRD = new TT_ReportDownloader();

        System.out.println(TTRD.getReport());
    }
}