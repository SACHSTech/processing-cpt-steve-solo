import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // define variables
  PImage imgBackground;
  PImage imgOpeningBackground;
  PImage imgTopicsBackground;

  String[] strHistoryQuestions;
  String[][] strHistoryOptions;
  int[] intCorrectAnswersHistory;
  int intQuestionNumber;

  int intScore;

  String[] strGeographyQuestions;
  String[][] strGeographyOptions;
  int[] intCorrectAnswerGeography;

  int intPage = 0;
  int intHistoryOrGeography = 0; // 0 - history; 1 - geography
  int intTimer = 2000;
  int previousTime = 0;
  boolean blnPauseTimer = false;

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

    // array for the answers of the geography questions
    strGeographyQuestions = new String[] {
      "What is the capital city of Australia?",
      "Which river is the longest in the world?",
      "Mount Kilimanjaro is located in which country?",
      "What is the smallest country in the world by land area?",
      "Which desert is the largest in the world?",
      "Which ocean is the deepest in the world?",
      "What is the official language of Brazil?",
      "The Great Barrier Reef is located off the coast of which Australian state?",
      "Which country has the largest population in the world?",
      "What is the highest mountain in North America?"
    };

    // array for the options of the geography questions
    strGeographyOptions = new String[][] {
      {"A) Sydney", "B) Melbourne", "C) Canberra", "D) Brisbane"},
      {"A) Nile", "B) Amazon", "C) Yangtze", "D) Mississippi"},
      {"A) Kenya", "B) Uganda", "C) Tanzania", "D) South Africa"},
      {"A) Monaco", "B) San Marino", "C) Vatican City", "D) Liechtenstein"},
      {"A) Sahara", "B) Gobi", "C) Kalahari", "D) Arabian"},
      {"A) Atlantic Ocean", "B) Indian Ocean", "C) Arctic Ocean", "D) Pacific Ocean"},
      {"A) Spanish", "B) Portuguese", "C) English", "D) French"},
      {"A) New South Wales", "B) Queensland", "C) Victoria", "D) Western Australia"},
      {"A) India", "B) United States", "C) China", "D) Indonesia"},
      {"A) Mount Whitney", "B) Mount McKinley (Denali)", "C) Mount Logan", "D) Mount Elbert"}
    };

    // array for the correct answer index for geography questions
    intCorrectAnswerGeography = new int[]{2, 0, 2, 2, 0, 3, 1, 1, 2, 1};
  }

  public void draw() {

    if (intPage == 0){
      drawOpeningPage();
    }
    if (intPage == 1) {
      drawTopicsPage();
    }
    if (intPage == 2) {
      drawStartingPage();
    }
    if (intPage == 3) {
      drawQuestionPage();
    }
    if (intPage == 4) {
      drawFinalPage();
    }
  
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */

  public void drawOpeningPage() {
    imgOpeningBackground = loadImage("Opening Page.jpg");

    image(imgOpeningBackground, 0, 0);

  }

  public void drawTopicsPage() {

    imgTopicsBackground = loadImage ("History.jpg");

    image(imgTopicsBackground, 0, 0);
  }
  public void drawStartingPage() {

    // draw the background
    image(imgBackground, 0, 0);

    textSize(50);
    text("Select which topic you would like to play", 80, 100);

    // print red button
    fill(255, 0, 0);
    rect(150, 150, 300, 60, 30);
    fill(255, 255, 255);
    textSize(22);
    text("Geography", 250, 190);
 
    // print green button
    fill(0, 255, 0);
    rect(600, 150, 300, 60, 30);
    fill(255, 255, 255);
    textSize(22);
    text("History", 725, 190);
 
    textSize(50);
    text("Select the timer", 80, 280);

    // print 2s button
    fill(0, 0, 0);
    rect(150, 300, 150, 60, 30);
    fill(255, 255, 255);
    textSize(22);
    text("2s", 210, 330);

    // print 5s button
    fill(0, 0, 0);
    rect(350, 300, 150, 60, 30);
    fill(255, 255, 255);
    textSize(22);
    text("5s", 410, 330);

    // print 10 button
    fill(0, 0, 0);
    rect(550, 300, 150, 60, 30);
    fill(255, 255, 255);
    textSize(22);
    text("10s", 600, 330);    

    // print next button
    fill(0, 255, 255);
    rect(400, 500, 300, 60, 30);
    fill(255, 255, 255);
    textSize(22);
    text("Next Page", 500, 535);
    // set timer
  } 

  public void drawFinalPage() {
    intHistoryOrGeography = 0; 
    intTimer = 2000;
    intQuestionNumber = 0;
    image(imgBackground, 0, 0);

    // print the questions
    fill(255);
    textSize(50);
    text("Your score is " + intScore, 200, 220);
    
    // print red button
    fill(255, 0, 0);
    rect(200, 300, 200, 100, 30);
    fill(255, 255, 255);
    textSize(30);
    text("Play again", 230, 350);

    // print green button
    fill(0, 255, 0);
    rect(600, 300, 200, 100, 30);
    fill(255, 255, 255);
    textSize(30);
    text("Quit", 670, 350);

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void drawQuestionPage() {
    // draw the background
    image(imgBackground, 0, 0);

    if (blnPauseTimer == true) {
      delay(1000);
      previousTime = previousTime + 1000;
      blnPauseTimer = false;
    }

    int intCurrentTime = millis();
    textSize(30);
    
    String strTimer = nf(intCurrentTime - previousTime, 4);
    System.out.println("++++"+strTimer);
    textSize(50);
    text(strTimer, 880, 60);
    
    if ((intCurrentTime - previousTime) > intTimer) {
      textSize(20);
      text("Time is up, move to next question", 450, 180);
      blnPauseTimer = true;
      intQuestionNumber++;
      if (areQuestionsFinished() == true) {
        delay(1000);
        intPage = 2;
      }
      previousTime = millis();
      return;
    }

    // print the questions
    fill(255);
    textSize(22);
    if (intHistoryOrGeography == 0) {
      text(strHistoryQuestions[intQuestionNumber], 80, 80);
    } else {
      text(strGeographyQuestions[intQuestionNumber], 80, 80);
    }
    
    // print options
    int intTextPosition = 140;
    textSize(30);
    for (int intColumn = 0; intColumn < strHistoryOptions[0].length; intColumn++) {
      if (intHistoryOrGeography == 0) {
        text(strHistoryOptions[intQuestionNumber][intColumn], 80, intTextPosition);
      } else {
        text(strGeographyOptions[intQuestionNumber][intColumn], 80, intTextPosition);
      }
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

    // set timer
  }

  public void mousePressed() {
    
    if (intPage == 0) {
      mousePressedOpeningPage();
    }
    //if (intPage == 1) {
      
    //}
    if (intPage == 2) {
      mousePressedStartingPage();
    }
    if (intPage == 3) {
      mousePressedQuestionPage();
    }
    if (intPage == 4) {
      mousePressedFinalPage(); 
    }
      
  }

  public void mousePressedOpeningPage() {
    intPage = 1;
  }

  public void mousePressedTopicsPage(){

  }

  public void mousePressedStartingPage() {
    // Georagphy
    if ((mouseX >= 150 && mouseX <= 450) && (mouseY >= 150 && mouseY <= 210)) {
      intHistoryOrGeography = 1;
    } 
    // history
    else if  ((mouseX >= 600 && mouseX <= 900) && (mouseY >= 150 && mouseY <= 210)) {
      intHistoryOrGeography = 0;
    }
    // 2s 
    else if ((mouseX >= 150 && mouseX <= 300) && (mouseY >= 300 && mouseY <= 360)) {
      intTimer = 2*1000;
    } 
    // 5s 
    else if ((mouseX >= 350 && mouseX <= 500) && (mouseY >= 300 && mouseY <= 360)) {
      intTimer = 5*1000;
    }
    // 10s
    else if ((mouseX >= 550 && mouseX <= 700) && (mouseY >= 300 && mouseY <= 360)) {
      intTimer = 10*1000;
    }
    // next page
    else if ((mouseX >= 400 && mouseX <= 700) && (mouseY >= 500 && mouseY <= 560)) {
      intPage = 3;
      previousTime = millis();
    }
  }

  /**
   * Check if buttons are pressed and indicates if the correct is chosen
   * @Author Steve Lin
   * @return
   */
  public void mousePressedQuestionPage() {
    
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

        textSize(20);
        text("CORRECT", 450, 180);

        intQuestionNumber++;
        intScore++;

      }
      else {

        textSize(20);
        text("INCORRECT", 450, 180);
        
        intQuestionNumber++;
      }
      if (areQuestionsFinished() == true) {
        intPage = 4;
      }
      blnPauseTimer = true;
      previousTime = millis();
    }
  }

  public void mousePressedFinalPage() {
    // Play again
    if ((mouseX >= 200 && mouseX <= 400) && (mouseY >= 300 && mouseY <= 400)) {
      intPage = 1;
    } 
    // quit
    else if ((mouseX >= 600 && mouseX <= 800) && (mouseY >= 300 && mouseY <= 400)) {
      exit();
    } 
  }

  public boolean areQuestionsFinished() {
    if (intHistoryOrGeography == 0) {
      if (intQuestionNumber >= strHistoryQuestions.length) {
        return true;
      }
    }
    else {
      if (intQuestionNumber >= strGeographyQuestions.length) {
        return true;
      }
    }
    return false;
  }
}
