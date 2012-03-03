package com.chariot.games.quizzo.utils;

import com.chariot.games.quizzo.model.Choice;
import com.chariot.games.quizzo.model.Question;
import com.chariot.games.quizzo.model.Quiz;
import com.chariot.games.quizzo.service.ChoiceService;
import com.chariot.games.quizzo.service.QuestionService;
import com.chariot.games.quizzo.service.QuizService;
import org.apache.poi.POIDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kenrimple
 * Date: 3/2/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuizQuestionReaderUtil {


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

    new QuizQuestionReaderUtil().readExcelFile("/Users/kenrimple/Downloads/QuizzoQuestions.xls");

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
        System.out.println("Question # " + questionIdx);
        System.out.println();
        System.out.println("--------------");
        System.out.println(questionText);

        Question question = new Question();
        question.setQuestionText(questionText);
        question.setSortOrder(order);
        questionService.saveQuestion(question);

        System.out.println();
        System.out.println("Choices...");
        System.out.println();

        HSSFCell correctAnswerCell = (HSSFCell) iterator.next();
        int correctAnswerPosition =
            BigDecimal.valueOf(correctAnswerCell.getNumericCellValue()).intValue() - 1;
        List<String> choices = new ArrayList<String>();
        short curIdx = -1;
        while (iterator.hasNext()) {
          String answerText = ((HSSFCell)iterator.next()).getStringCellValue();

          Choice choice = new Choice();
          choice.setQuestion(question);
          choice.setPointValue(new BigDecimal("1.0"));
          choice.setText(answerText);
          curIdx ++;

          if (curIdx == correctAnswerPosition) {
            System.out.print("CORRECT: ---> ");
            choice.setCorrect(true);
          } else {
            choice.setCorrect(false);
          }
          choiceService.saveChoice(choice);
          System.out.println(answerText);
        }

        // persist 'em

      }
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
