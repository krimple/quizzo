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

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuizQuestionReaderUtil {


  private static QuizService quizService;
  private static QuestionService questionService;
  private static ChoiceService choiceService;

  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext(
        "classpath:META-INF/spring/applicationContext*.xml"
    );

    quizService = context.getBean(QuizService.class);
    questionService = context.getBean(QuestionService.class);
    choiceService = context.getBean(ChoiceService.class);


    parseAndProcess(args[0]);

  }


  /**
   * This method is used to read the data's from an excel file.
   *
   * @param fileName - Name of the excel file.
   */
  private static void parseAndProcess(String fileName) {

    Quiz quiz = new Quiz();
    quiz.setTitle("Chariot Day Quizzo!");
    quiz.setDescription("Because a day without night would be, what would it be?");
    quizService.saveQuiz(quiz);

    try {
      FileInputStream fileInputStream = new FileInputStream(fileName);
      POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
      HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
      HSSFSheet hssfSheet = workBook.getSheetAt(0);
      Iterator rowIterator = hssfSheet.rowIterator();

      rowIterator.next(); // skip top header

      short questionIdx = 0;
      while (rowIterator.hasNext()) {
        questionIdx++;
        HSSFRow hssfRow = (HSSFRow) rowIterator.next();

        // get all quiz rows
        Iterator iterator = hssfRow.cellIterator();
        HSSFCell questionOrderCell = (HSSFCell) iterator.next();
        short order = (short) questionOrderCell.getNumericCellValue();
        String questionText = ((HSSFCell) iterator.next()).getStringCellValue();

        Question question = new Question();
        question.setQuestionText(questionText);
        question.setSortOrder(order);
        questionService.saveQuestion(question);

        HSSFCell correctAnswerCell = (HSSFCell) iterator.next();

        int correctAnswerPosition =
            BigDecimal.valueOf(correctAnswerCell.getNumericCellValue()).intValue() - 1;
        short curIdx = -1;
        while (iterator.hasNext()) {
          String answerText = ((HSSFCell)iterator.next()).getStringCellValue();

          Choice choice = new Choice();
          choice.setQuestion(question);
          choice.setPointValue(new BigDecimal("1.0"));
          choice.setText(answerText);
          curIdx ++;
          choice.setCorrect(curIdx == correctAnswerPosition);

          choiceService.saveChoice(choice);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
