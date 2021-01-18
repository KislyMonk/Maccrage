package ru.Pegov.Macragge.reportDownload;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TT_ReportDownloader {
    private String url = "http:///api/CrmOnyma/TT/TT";
    private MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
    private String region = "Урал";
    private String dateStringFrom = "";
    private String dateStringTo = "";

    public TT_ReportDownloader() {
    }

    public byte[] getReportUral(String dateStringFrom, String dateStringTo){
        this.dateStringFrom = dateStringFrom;
        this.dateStringTo = dateStringTo;

        return getReport();
    }

    public byte[] getReportFromOtherRegion(String region, String dateStringFrom, String dateStringTo){
        this.dateStringFrom = dateStringFrom;
        this.dateStringTo = dateStringTo;
        this.region = region;

        return getReport();
    }

    private byte[] getReport(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        uriVariables.add("export_data", "{" +
                " \"filters\": [" +
                "  {" +
                "   \"name\": \"filter\"," +
                "   \"val\": \"Contains([macroregion], '"+ region +"')\"" +
                "  }," +
                "  {" +
                "   \"name\": \"start\"," +
                "   \"val\": \""+ dateStringFrom +"\"" +
                "  }," +
                "  {" +
                "   \"name\": \"end\"," +
                "   \"val\": \""+ dateStringTo +"\"" +
                "  }" +
                " ]" +
                "}");


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(uriVariables, headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity( url, request , byte[].class).getBody();
    }
}
