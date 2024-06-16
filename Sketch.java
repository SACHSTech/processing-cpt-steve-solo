import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // define variables
  PImage imgBackground;
  PImage imgOpeningBackground;
  PImage imgTopicsBackground;
  PImage imgTimerBackground;
  PImage imgEndingBackground;

  String[] strHistoryQuestions;
  String[][] strHistoryOptions;
  int[] intCorrectAnswersHistory;
  int intQuestionNumber;

  int intScore;

  String[] strGeographyQuestions;
  String[][] strGeographyOptions;
  int[] intCorrectAnswerGeography;

  String[] strPopQuestions;
  String[][] strPopOptions;
  int[] intCorrectAnswerPop;

  String[] strMixedQuestions;
  String[][] strMixedOptions;
  int[] intCorrectAnswerMixed;

  int intPage = 0;
  int intHistoryOrGeography = 0; 
  int intTimer = 2000;
  int intPreviousTime = 0;
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
 
    // array for the history questions 
    strHistoryQuestions = new String[] {
      "What year is was John F. Kennedy assassinated?", 
      "Who was the first President of the United States?",
      "What year did the Titanic sink?",
      "A queen known for her relationships with Julius Caesar",
      "Who was the leader of the Soviet Union during World War II?",
      "Who was the first emperor of Rome?",
      "The Great Wall of China was built to protect against invasions from...",
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

    // array for geography questions
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

    // array for the pop culture questions
    strPopQuestions = new String[] {
      "Who won the Grammy Award for Album of the Year in 2023?",
      "What is the name of the fourth studio album by Taylor Swift, released in 2012?",
      "Who played the role of Iron Man in the Marvel Cinematic Universe?",
      "Which artist released the hit single \"Blinding Lights\" in 2019?",
      "Which book series is written by J.K. Rowling?",
      "What is the highest-grossing film of all time as of 2023?",
      "Which band released the album \"Abbey Road\"?",
      "Who played the character of Katniss Everdeen in \"The Hunger Games\" film series?",
      "Which K-pop group released the hit song \"Dynamite\" in 2020?",
      "Who wrote the novel \"To Kill a Mockingbird\"?"
    };

    // array for the options of pop culture questions
    strPopOptions = new String [][] {
      {"a) Harry Styles", "b) Beyoncé", "c) Taylor Swift", "d) Billie Eilish"},
      {"a) Fearless", "b) Speak Now", "c) Red", "d) 1989"},
      {"a) Chris Hemsworth", "b) Chris Evans", "c) Robert Downey Jr.", "d) Mark Ruffalo"},
      {"a) Post Malone", "b) The Weeknd", "c) Drake", "d) Ed Sheeran"},
      {"a) The Hunger Games", "b) Twilight", "c) Harry Potter", "d) Percy Jackson"},
      {"a) Avatar", "b) Avengers: Endgame", "c) Titanic", "d) Star Wars: The Force Awakens"},
      {"a) The Rolling Stones", "b) The Beatles", "c) Pink Floyd", "d) Led Zeppelin"},
      {"a) Jennifer Lawrence", "b) Emma Watson", "c) Kristen Stewart", "d) Shailene Woodley"},
      {"a) BLACKPINK", "b) BTS", "c) EXO", "d) TWICE"},
      {"a) Harper Lee", "b) F. Scott Fitzgerald", "c) J.D. Salinger", "d) Mark Twain"}
    };

    // array for the correct answer index for the pop culture questions
    intCorrectAnswerPop = new int[] {0, 2, 2, 1, 2, 0, 1, 0, 1, 0};

    // array for the questions of mixed genres
    strMixedQuestions = new String[] {
      "Which country is known as the Land of the Rising Sun?",
      "What is the smallest prime number?",
      "What is the largest organ in the human body?",
      "Which famous scientist developed the theory of relativity?",
      "In which year did World War II end?",
      "What is the currency of the United Kingdom?",
      "What is the main ingredient in traditional Japanese miso soup?",
      "Which element is known for its distinctive yellow-green gas at room temperature?",
      "Which element is used in pencils for writing?",
      "Which country hosted the 2016 Summer Olympics?"
    };

    // array for the options of mixed genres
    strMixedOptions = new String[][] {
      {"a) China", "b) Japan", "c) South Korea", "d) Thailand"},
      {"a) 1", "b) 2", "c) 3", "d) 5"},
      {"a) Heart", "b) Liver", "c) Skin", "d) Lungs"},
      {"a) Isaac Newton", "b) Galileo Galilei", "c) Mr. Fabroa", "d) Albert Einstein"},
      {"a) 1940", "b) 1943", "c) 1945", "d) 1950"},
      {"a) Dollar", "b) Euro", "c) Pound Sterling", "d) Yen"},
      {"a) Tofu", "b) Seaweed", "c) Miso paste", "d) Rice"},
      {"a) Fluorine", "b) Chlorine", "c) Bromine", "d) Iodine"},
      {"a) Graphite", "b) Charcoal", "c) Lead", "d) Carbon"},
      {"a) China", "b) United Kingdom", "c) Brazil", "d) Japan"}
    };
    
    intCorrectAnswerMixed = new int[]{1, 1, 2, 3, 2, 2, 2, 1, 0, 2};

  }

  public void draw() {

    if (intPage == 0){
      drawOpeningPage();
    }
    else if (intPage == 1) {
      drawTopicsPage();
    }
    else if (intPage == 2) {
      drawTimerPage();
    }
    else if (intPage == 3) {
      drawQuestionPage();
    }
    else if (intPage == 4) {
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

    imgTopicsBackground = loadImage ("Mode Page.jpg");

    image(imgTopicsBackground, 0, 0);

  }

  public void drawTimerPage() {

    imgTimerBackground = loadImage ("Difficulty Page.jpg");

    image(imgTimerBackground, 0, 0);

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void drawQuestionPage() {

    // load image
    imgBackground = loadImage("Questions Page.jpg");

    // draw the background
    image(imgBackground, 0, 0);

    if (blnPauseTimer) {
      delay(1000);
      intPreviousTime += 1000;
      blnPauseTimer = false;
    }

    


    if (intTimer > 0) {
      int intCurrentTime = millis();
      int intElapsedSeconds = (int) ((intCurrentTime - intPreviousTime) / 1000); 
      textSize(50);
      text(intElapsedSeconds, 880, 60); 
      if (intElapsedSeconds > intTimer / 1000) { 
        textSize(30);
        text("Time is up, move to next question!", 80, 160);
        blnPauseTimer = true;
        intQuestionNumber++;
        if (areQuestionsFinished()) {
          delay(1000);
          intPage = 4;
        }
        intPreviousTime = millis();
        return;
      }
    }

  

    // print the questions
    fill(255);
    textSize(22);
    if (intHistoryOrGeography == 0) {
      text(strHistoryQuestions[intQuestionNumber], 80, 80);
    } 
    else if(intHistoryOrGeography == 1) {
      text(strGeographyQuestions[intQuestionNumber], 80, 80);
    }
    else if(intHistoryOrGeography == 2) {
      text(strPopQuestions[intQuestionNumber], 80, 80);
    }
    else if(intHistoryOrGeography == 3) {
      text(strMixedQuestions[intQuestionNumber], 80, 80);
    }
    
    // print options
    int intTextPosition = 140;
    textSize(20);
    for (int intColumn = 0; intColumn < strHistoryOptions[0].length; intColumn++) {
      if (intHistoryOrGeography == 0) {
        text(strHistoryOptions[intQuestionNumber][intColumn], 80, intTextPosition);
      } 
      else if (intHistoryOrGeography == 1) {
        text(strGeographyOptions[intQuestionNumber][intColumn], 80, intTextPosition);
      }
      else if(intHistoryOrGeography == 2) {
        text(strPopOptions[intQuestionNumber][intColumn], 80, intTextPosition);
      }
      else if(intHistoryOrGeography == 3) {
        text(strMixedOptions[intQuestionNumber][intColumn], 80, intTextPosition);
      }
      intTextPosition += 30;
    }

  }

  public void drawFinalPage() {

    imgEndingBackground = loadImage ("Ending Page.jpg");
    image(imgEndingBackground, 0, 0);

    intHistoryOrGeography = 0; 
    intTimer = 2000;
    intQuestionNumber = 0;

    // print the questions
    fill(255);
    textSize(50);
    if (intScore == 0) {
      text(intScore + "%", 460, 210);
    }
    else {
    text(intScore + "0%", 460, 210);
    }
  }

  public void mousePressed() {
    
    if (intPage == 0) {
      mousePressedOpeningPage();
    }
    else if (intPage == 1) {
      mousePressedTopicsPage();
    }
    else if (intPage == 2) {
      mousePressedTimerPage();
    }
    else if (intPage == 3) {
      mousePressedQuestionPage();
    }
    else if (intPage == 4) {
      mousePressedFinalPage(); 
    }
      
  }

  public void mousePressedOpeningPage() {

    intPage = 1;
  }

  public void mousePressedTopicsPage(){

    if ((mouseX >= 210 && mouseX <= 310) && (mouseY >= 232 && mouseY <= 332)) {

      intHistoryOrGeography = 0;

      intPage = 2;
    }
    if ((mouseX >= 351 && mouseX <= 451) && (mouseY >= 232 && mouseY <= 332)) {

      intHistoryOrGeography = 1;

      intPage = 2;
    }
    if ((mouseX >= 495 && mouseX <= 595) && (mouseY >= 232 && mouseY <= 332)) {

      intHistoryOrGeography = 2;

      intPage = 2;

    }
    if ((mouseX >= 640 && mouseX <= 740) && (mouseY >= 232 && mouseY <= 332)) {

      intHistoryOrGeography = 3;

      intPage = 2;

    }

  }

  public void mousePressedTimerPage() {

    if ((mouseX >= 248 && mouseX <= 398) && (mouseY >= 265 && mouseY <= 345)){

      intPage = 3;
      intTimer = 5 * 1000;

    }

    if((mouseX >= 424 && mouseX <= 574) && (mouseY >= 265 && mouseY <= 345)) {
      
      intPage = 3;
      intTimer = 10 * 1000;

    }

    if((mouseX >= 600 && mouseX <= 750) && (mouseY >= 265 && mouseY <= 345)) {

      intPage = 3;
      intTimer = -1;

    }
    
    intPreviousTime = millis();

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
      intPreviousTime = millis();
    }
  }

  public void mousePressedFinalPage() {
    // Play again
    if ((mouseX >= 385 && mouseX <= 615) && (mouseY >= 327 && mouseY <= 387)) {
      intPage = 1;
    } 
    // quit
    else if ((mouseX >= 385 && mouseX <= 615) && (mouseY >= 410 && mouseY <= 470)) {
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
