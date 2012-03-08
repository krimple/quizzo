package com.chariot.games.quizzo.utils;

import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.service.ChoiceService;
import com.chariot.games.quizzo.service.QuestionService;
import com.chariot.games.quizzo.service.QuizService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuizSlideGeneratorUtil {


  private static QuizService quizService;
  private static QuestionService questionService;
  private static ChoiceService choiceService;
  private static Long quizId;
  private static Quiz quiz;

  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext(
        "classpath:META-INF/spring/applicationContext*.xml"
    );

    quizService = context.getBean(QuizService.class);
    questionService = context.getBean(QuestionService.class);
    choiceService = context.getBean(ChoiceService.class);

    quiz = new Quiz();
    quiz.setTitle("Chariot Day Quizzo!");
    quiz.setDescription("Because a day without night would be, what would it be?");
    quizService.saveQuiz(quiz);
    quizId = quiz.getId();

    new QuizSlideGeneratorUtil().readExcelFile("/Users/kenrimple/Dropbox/QuizzoQuestions.xls");

  }


  /**
   * This method is used to read the data's from an excel file.
   *
   * @param fileName - Name of the excel file.
   */
  private void readExcelFile(String fileName) {
/**
 * Create a new instance for cellDataList
 */
    List cellDataList = new ArrayList();
    try {
/**
 * Create a new instance for FileInputStream class
 */
      FileInputStream fileInputStream = new FileInputStream(fileName);

/**
 * Create a new instance for POIFSFileSystem class
 */
      POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

/*
* Create a new instance for HSSFWorkBook Class
*/
      HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
      HSSFSheet hssfSheet = workBook.getSheetAt(0);

/**
 * Iterate the rows and cells of the spreadsheet
 * to get all the datas.
 */

      PrintWriter printWriter = new PrintWriter("/Users/kenrimple/slides.showoff");

      Iterator rowIterator = hssfSheet.rowIterator();

      rowIterator.next(); // skip top header

      short questionIdx = 0;
      while (rowIterator.hasNext()) {
        questionIdx++;



        HSSFRow hssfRow = (HSSFRow) rowIterator.next();
        Iterator iterator = hssfRow.cellIterator();
        HSSFCell questionOrderCell = (HSSFCell) iterator.next();
        short order = (short) questionOrderCell.getNumericCellValue();
        String questionText = ((HSSFCell) iterator.next()).getStringCellValue();
        HSSFCell correctAnswerCell = (HSSFCell) iterator.next();
        int correctAnswerPosition =
            BigDecimal.valueOf(correctAnswerCell.getNumericCellValue()).intValue();

        printWriter.println("<!SLIDE bullets>");
        printWriter.println(".notes the correct answer is " + (correctAnswerPosition));
        printWriter.println();
        printWriter.println("# Question " + questionIdx + "#");
        printWriter.println("## " + StringUtils.trimWhitespace(questionText) + " ##");
        printWriter.println();

        short curIdx = 0;

        while (iterator.hasNext()) {
          curIdx++;
          String answerText = ((HSSFCell) iterator.next()).getStringCellValue();
          printWriter.println("* (" + (curIdx) + ") " + StringUtils.trimWhitespace(answerText));
        }
        printWriter.println();
      }
      printWriter.flush();
      printWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
/**
 * Call the printToConsole method to print the cell data in the
 * console.
 */
    printToConsole(cellDataList);
  }

  /**
   * This method is used to print the cell data to the console.
   *
   * @param cellDataList - List of the data's in the spreadsheet.
   */
  private void printToConsole(List cellDataList) {
    for (int i = 0; i < cellDataList.size(); i++) {
      List cellTempList = (List) cellDataList.get(i);
      for (int j = 0; j < cellTempList.size(); j++) {
        HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
        String stringCellValue = hssfCell.toString();
        System.out.print(stringCellValue + "\t");
      }
      System.out.println();
    }
  }
}
