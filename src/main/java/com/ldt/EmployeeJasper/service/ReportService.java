package com.ldt.EmployeeJasper.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

public interface ReportService {
    String exportReport(String reportFormat) throws FileNotFoundException, JRException;

}
