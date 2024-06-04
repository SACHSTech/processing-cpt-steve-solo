import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // define variables
  PImage imgBackground;

  String[] strHistoryQuestions;
  String[][] strHistoryOptions;
  int[] intCorrectAnswersHistory;
  int intQuestionNumber;

  /**
   * Called once at the beginning of execution, put your size call here
   */
  public void settings() {
    // put your size call here
    size(1000, 600);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    // load image
    imgBackground = loadImage("f3989e431422a23123fc19fcc43f8d2e.jpeg");

    // resize the image to fit the background
    imgBackground.resize(width, height);
    
    // array for the history questions 
    strHistoryQuestions = new String[] {
      "What year is was John F. Kennedy assassinated?", 
      "Who was the first President of the United States?",
      "What year did the Titanic sink?",
      "Who was the Egyptian queen known for her relationships with Julius Caesar and Mark Antony?",
      "Who was the leader of the Soviet Union during World War II?",
      "Who was the first emperor of Rome?",
      "The Great Wall of China was primarily built to protect against invasions from which group?",
      "Which treaty ended World War I?",
      "Which empire was ruled by Genghis Khan?",
      "Who was the principal author of the Declaration of Independence?"
    };

    // array for the options of the history question
    strHistoryOptions = new String[][] {
      {"A) 1963", "B) 1924", "C) 1784", "D) 2001"},
      {"A) Thomas Jefferson", "B) John Adams", "C) George Washington", "D) James Madison"},
      {"A) 1905", "B) 1912", "C) 1918", "D) 1923"},
      {"A) Nefertiti", "B) Hatshepsut", "C) Cleopatra", "D) Anck-Su-Namun"},
      {"A) Vladimir Lenin", "B) Nikita Khrushchev", "C) Joseph Stalin", "D) Leonid Brezhnev"},
      {"A) Julius Caesar", "B) Augustus", "C) Nero", "D) Caligula"},
      {"A) The Japanese", "B) The Mongols", "C) The Koreans", "D) The Vietnamese"},
      {"A) Treaty of Versailles", "B) Treaty of Tordesillas", "C) Treaty of Paris", "D) Treaty of Ghent"},
      {"A) Persian Empire", "B) Mongol Empire", "C) Ottoman Empire", "D) Byzantine Empire"},
      {"A) Benjamin Franklin", "B) John Adams", "C) Thomas Jefferson", "D) James Madison"}
    };

    // array of list of correct answers in accordance to index
    intCorrectAnswersHistory = new int[]{0, 2, 1, 2, 2, 1, 1, 0, 1, 2};

    // set question number to 0
    intQuestionNumber = 0;
  }


  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // draw the background
    image(imgBackground, 0, 0);

    // print the questions
    fill(255);
    textSize(30);
    text(strHistoryQuestions[intQuestionNumber], 80, 80);
    
    // print options
    int intTextPosition = 140;
    textSize(30);
    for (int intColumn = 0; intColumn < strHistoryOptions[0].length; intColumn++) {
      text(strHistoryOptions[0][intColumn], 80, intTextPosition);
      intTextPosition += 30;
    }
    

    // print red button
    fill(255, 0, 0);
    rect(0, 300, 500, 150, 30);
    fill(255, 255, 255);
    textSize(75);
    text("A", 225, 400);

    // print green button
    fill(0, 255, 0);
    rect(500, 300, 500, 150, 30);
    fill(255, 255, 255);
    textSize(75);
    text("B", 725, 400);

    // print blue button
    fill(0, 0, 255);
    rect(0, 450, 500, 150, 30);
    fill(255, 255, 255);
    textSize(75);
    text("C", 225, 550);

    // print yellow button
    fill(240, 224, 58);
    rect(500, 450, 500, 150, 30);
    fill(255, 255, 255);
    textSize(75);
    text("D", 725, 550);

  }

  /**
   * Check if buttons are pressed and indicates if the correct is chosen
   * @Author Steve Lin
   * @return
   */
  public void mousePressed() {
    
    int intSelectedOption = 4;

    // chained conditionals that indicates which button is pressed
    if ((mouseX >= 0 && mouseX <= 500) && (mouseY >= 300 && mouseY <= 450)) {
      intSelectedOption = 0; // A
    } 
    
    else if ((mouseX >= 500 && mouseX <= 1000) && (mouseY >= 300 && mouseY <= 450)) {
      intSelectedOption = 1; // B
    }
     
    else if ((mouseX >= 0 && mouseX <= 500) && (mouseY >= 450 && mouseY <= 600)) {
      intSelectedOption = 2; // C
    } 
    
    else if ((mouseX >= 500 && mouseX <= 1000) && (mouseY >= 450 && mouseY <= 600)) {
      intSelectedOption = 3; // D
    }

    if (intSelectedOption != 4){
      if (intSelectedOption == intCorrectAnswersHistory[intQuestionNumber]){

        textSize(100);
        text("CORRECT", 80, 80);

      }
      else {

        textSize(30);
        text("INCORRECT", 80, 80);
      }
    }

    
  }

  // define other methods down here.
}
