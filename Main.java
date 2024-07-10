import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

class Questions{                        //CLASS FOR STORING VARIOUS THINGS LIKE TEXT,OPTIONS,AND CORRECT OPTIONS.
    private final String Text;
    private final String[] options;
    private final int correct;

    public Questions(String Text,String[] options,int correctoption){
        this.Text = Text;
        this.options = options;
        this.correct = correctoption;

    }
    public String getText(){
        return Text;
    }
    public String[] getoptions(){
        return options;
    }
    public int getcorrect(){
        return correct;
    }
}

class Quiz {            //CLASS FOR STORING DIFFERENT THINGS LIKE SCORE,QUESTION NUMBER AND TOTAL QUESTIONS.
    private final List<Questions> questions;
    private int score;
    private int questionindex;

    public Quiz(List<Questions> questions) {
        this.questions = questions;
        this.score = 0;
        this.questionindex = 0;
        List<Integer> input = new ArrayList<>();
    }

    public void startquiz() {
        Scanner sc = new Scanner(System.in);
        for (Questions question : questions) {               //DISPLAYING THE QUESTION AND OPTIONS FOR THE USER.
            System.out.println("The question " + questionindex + 1 + " : " + question.getText());
            String[] options = question.getoptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + "." + options[i]);
            }
            questionindex++;
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    System.out.println("TIME'S UP!!!");
                    System.exit(0);
                }
            };


            timer.schedule(timerTask, 10000);              //10 seconds timer.
            System.out.println("Enter your answer: (1/2/3/4)");         //INPUTTING ANSWER FROM THE USER.
            int yourans = sc.nextInt();

            timer.cancel();

            if (yourans == question.getcorrect() + 1) {
                score++;                                             //DISPLAYING TOTAL SCORE OF THE USER.
            }
        }

        Result();
    }
    public void Result() {
        System.out.println("QUIZ IS OVER!!");
        System.out.println("Your score is " + score + " out of " + questions.size());
        System.out.println("THANKS FOR PLAYING!!");
    }
}

public class Main {                       //MAIN PROGRAM
    public static void main(String[] args){
        System.out.println("WELCOME TO THE QUIZ: ");
        System.out.println("LET'S START THE QUIZ: ");
        System.out.println("YOU HAVE 10 SECONDS TIME: ");
        List<Questions> questions = new ArrayList<>();
        questions.add(new Questions(" How many wonders are there in the world?",new String[]{"Three","Seven","Six","Five"},1));
        questions.add(new Questions(" What is the capital of Russia?",new String[]{"Rome","Paris","Moscow","New Delhi"},2));
        questions.add(new Questions(" Which is the closest star to Earth?",new String[]{"Mars","Jupiter","Sun","Moon"},2));
        questions.add(new Questions(" How many bones are there in the human body?",new String[]{"200","300","250","206"},3));
        questions.add(new Questions(" Which is the largest democracy in the world?",new String[]{"India","USA","China","Japan"},0));

        Quiz quiz = new Quiz(questions);
        quiz.startquiz();
    }
}