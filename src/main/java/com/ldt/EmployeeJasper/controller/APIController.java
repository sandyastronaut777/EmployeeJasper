package com.ldt.EmployeeJasper.controller;

import com.ldt.EmployeeJasper.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class APIController {

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    String exportReport(@RequestParam("report-format") String reportFormat) throws JRException, FileNotFoundException {
        return reportService.exportReport(reportFormat);
    }

//    @GetMapping("/test")
//    String test(String reportFormat) throws JRException, FileNotFoundException {
//
//        return "s";
//
//    }
}