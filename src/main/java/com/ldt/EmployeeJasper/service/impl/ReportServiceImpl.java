package com.ldt.EmployeeJasper.service.impl;

import com.ldt.EmployeeJasper.entity.Employee;
import com.ldt.EmployeeJasper.repository.EmployeeRepository;
import com.ldt.EmployeeJasper.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "//Users//ldttechnology//Documents";
        System.out.println(reportFormat);
        List<Employee> employee = employeeRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:employee.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employee);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "LDT");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "//employee.html");
        }
        return "report generated in path : " + path;
    }
}
