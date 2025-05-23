package com.Quiz.mainMaster;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
	
	@Autowired
    private QuestionService questionService;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Question> excelToQuestions(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Question> questions = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row row = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue; // skip header
                }
                
                Integer courseId = (int) row.getCell(0).getNumericCellValue();
                String questionTitle = getString(row.getCell(1));
                String option1 = getString(row.getCell(2));
                String option2 = getString(row.getCell(3));
                String option3 = getString(row.getCell(4));
                String option4 = getString(row.getCell(5));
                Integer answer = (int) row.getCell(6).getNumericCellValue();

                Question question = new Question();
                question.setCourse_id(courseId);
                question.setQuestionTitle(questionTitle);
                question.setOption1(option1);
                question.setOption2(option2);
                question.setOption3(option3);
                question.setOption4(option4);
                question.setAnswer(String.valueOf(answer));

                questions.add(question);
            }

            workbook.close();
            return questions;

        } catch (Exception e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }

    private static String getString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.NUMERIC)
            return String.valueOf(cell.getNumericCellValue());
        if (cell.getCellType() == CellType.BOOLEAN)
            return String.valueOf(cell.getBooleanCellValue());
        return cell.getStringCellValue();
    }
	
//	
//	 public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//
//	    public static boolean hasExcelFormat(MultipartFile file) {
//	        return TYPE.equals(file.getContentType());
//	    }
//
//	    public static List<Question> excelToQuestions(InputStream is) {
//	        try (Workbook workbook = new XSSFWorkbook(is)) {
//	            Sheet sheet = workbook.getSheetAt(0);
//	            Iterator<Row> rows = sheet.iterator();
//
//	            List<Question> questions = new ArrayList<>();
//	            int rowNumber = 0;
//
//	            while (rows.hasNext()) {
//	                Row row = rows.next();
//
//	                if (rowNumber == 0) {
//	                    rowNumber++;
//	                    continue; // skip header
//	                }
//
//	                Integer courseId = (int) row.getCell(0).getNumericCellValue();
//	                String questionTitle = getString(row.getCell(1));
//	                String option1 = getString(row.getCell(2));
//	                String option2 = getString(row.getCell(3));
//	                String option3 = getString(row.getCell(4));
//	                String option4 = getString(row.getCell(5));
//	                Integer answer = (int) row.getCell(6).getNumericCellValue();
//
//	       
//
//	                Question question = new Question();
//	                question.setCourse_id(courseId);
//	                question.setQuestionTitle(questionTitle);
//	                question.setOption1(option1);
//	                question.setOption2(option2);
//	                question.setOption3(option3);
//	                question.setOption4(option4);
//	                question.setAnswer(String.valueOf(answer));
////	                question.setCompany_id(companyId);
////	                question.setEmployee_id(employeeId);
//
//	                questions.add(question);
//	                rowNumber++;
//	            }
//
//	            return questions;
//
//	        } catch (Exception e) {
//	            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
//	        }
//	    }
//
//	    private static String getString(Cell cell) {
//	        if (cell == null) return "";
//	        return switch (cell.getCellType()) {
//	            case STRING -> cell.getStringCellValue().trim();
//	            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
//	            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
//	            case BLANK -> "";
//	            default -> throw new RuntimeException("Unsupported cell type: " + cell.getCellType());
//	        };
//	    }
//	
//	
	
	
}
