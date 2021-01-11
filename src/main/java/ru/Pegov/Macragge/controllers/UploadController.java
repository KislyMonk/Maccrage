/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.Pegov.Macragge.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.Pegov.Macragge.SigmaParser.SigmaReportParser;
import ru.Pegov.Macragge.models.*;

/**
 *
 * @author Андрей
 */

@RestController
@RequestMapping(value= "/uploadReport")
public class UploadController {
    
    //default getter, nothing to do
    @GetMapping()
    public Reply startState(){        
        return new ErrorMessage("Use POST-method");
    }
    
    @PostMapping()
    public Reply upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request){
        ArrayList<HashMap<String,String>> result = new ArrayList();
        
        SigmaReportParser srp = new SigmaReportParser();
        for(int i = 0; i < file.length; i++){
            try {
                srp.setInputStream(file[i].getInputStream());
            } catch (IOException ex) {
                System.out.println("[" + (new GregorianCalendar()) + 
                        "]ru.Pegov.Macragge.controllers.UploadController.upload() IOException " + ex);
            }
            HashMap<String,String> preResult = new HashMap<>();
            preResult.put("name", file[i].getOriginalFilename());
            preResult.put("add", srp.getAdded()+"");
            preResult.put("notAdd", srp.getNotAdd()+"");
            result.add(preResult);
        }
        
        return new Regular("ok", result);
    }
}
