package src;

import java.util.Scanner;


public class GradeStudent {

    private static double WeightedMidtermScore;
    private static double WeightedFinalExamScore;
    private static double WeightedHomeworkScore;

    private static int WeightMidterm = 0;
    private static int WeightFinalExam = 0;
    private static int WeightHomework = 0;
    private static Scanner sc;
    public static void main(String[] args) {
        begin();

        midTerm();

        finalTerm();

        homework();

        report();
    }// end main

    // show welcome message
    public static void begin() {
        System.out.println("This program reads exam/homework scores");
        System.out.println("and reports your overall course grade.");
        System.out.println();
    } // end begin()

    // Enter and calculate midterm exam scores.
    public static void midTerm() {
        // enter weighted score
        System.out.println("Midterm:");
        System.out.print("Weight (0-100)? ");
        sc = new Scanner(System.in);
        WeightMidterm = sc.nextInt();

        // check the total weight score does not exceed 100 points
        WeightMidterm = checkTotalWeight(WeightMidterm, WeightFinalExam, WeightHomework);

        // enter midterm exam scores
        System.out.print("Score earned? ");
        int testScore = sc.nextInt();

        // Enter the option whether test scores will be increased or not
        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int choose = sc.nextInt();

        // If you choose 1, enter plus points
        if(choose == 1) {
            System.out.print("Shift amount? ");
            int plusPoint = sc.nextInt();
            testScore += plusPoint;
        }

        // if test score + plus point = maximum score
        if(testScore > 100) {
            testScore = 100;
        }

        // Print total test scores + bonus points / maximum score
        System.out.println("Total points = " + testScore + " / 100");

        // Calculate and print total score based on weighted score
        double weightedScore =((double)testScore/100) * WeightMidterm;
        WeightedMidtermScore = weightedScore;
        System.out.println("Weighted score = " + (double)Math.round(weightedScore * 10) / 10 + " / " + WeightMidterm);
        System.out.println();

    } // end midTerm()

    // Enter and calculate final exam scores.
    public static void finalTerm() {
        // enter weighted score
        System.out.println("Final:");
        System.out.print("Weight (0-100)? ");
        sc = new Scanner(System.in);
        WeightFinalExam = sc.nextInt();

        // check the total weight score does not exceed 100 points
        WeightFinalExam = checkTotalWeight(WeightFinalExam, WeightMidterm, WeightHomework);

        // enter midterm exam scores
        System.out.print("Score earned? ");
        int testScore = sc.nextInt();

        // Enter the option whether test scores will be increased or not
        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int choose = sc.nextInt();

        // If you choose 1, enter plus points
        if(choose == 1) {
            System.out.print("Shift amount? ");
            int plusPoint = sc.nextInt();
            testScore += plusPoint;
        }

        // if test score + plus point = maximum score
        if(testScore > 100) {
            testScore = 100;
        }

        // Print total test scores + bonus points / maximum score
        System.out.println("Total points = " + testScore + " / 100");

        // Calculate and print total score based on weighted score
        double weightedScore =((double)testScore/100) * WeightFinalExam;
        WeightedFinalExamScore = weightedScore;
        System.out.println("Weighted score = " + (double)Math.round(weightedScore * 10) / 10 + " / " + WeightFinalExam);
        System.out.println();

    } // end finalTerm()
    
    // Enter and calculate homework scores.
    public static void homework() {

        // enter weighted score
        System.out.println("Homework:");
        System.out.print("Weight (0-100)? ");
        sc = new Scanner(System.in);
        WeightHomework = sc.nextInt();

        // check the total weight score does not exceed 100 points
        WeightHomework = checkTotalWeight(WeightHomework, WeightMidterm, WeightFinalExam);

        /* 
        Enter the total number of homework to do and for each assignment, you need
        to enter both parameters: score and max
        */
        System.out.print("Number of assignments? ");
        int num = sc.nextInt();
        int score = 0;
        int max = 0;
        for(int i=1; i<=num; i++) {
            System.out.print("Assignment " + i + " score and max? ");
            score += sc.nextInt();
            max += sc.nextInt();
        }

        /* Considering the maximum score of the assignment is 150, if it is exceeded,
         it will only be counted as 150 points.
        */
        if(score > 150) {
            score = 150;
        }
        if(max > 150) {
            max = 150;
        }

        // Enter the number of sessions the student attended and got attendance
        System.out.print("How many sections did you attend? ");
        int attend = sc.nextInt();
        int sectionPoints = attend*5;

        // The maximum score of attend is 30, if it exceeds 30, it will still be counted as 30
        if(sectionPoints > 30) {
            sectionPoints = 30;
        }

        // Update student's total homework score and attendance score
        score += sectionPoints;
        max += 30;

        // Print attendance score on max scale and total homework score on max scale
        System.out.println("Section points = " + sectionPoints + " / 30");
        System.out.println("Total points = " + score + " / " + max);

        // Calculate and print total score based on weighted score
        double weightedScore =((double)score/max) * WeightHomework;
        WeightedHomeworkScore = weightedScore;
        System.out.println("Weighted score = " + (double)Math.round(weightedScore * 10) / 10 + " / " + WeightHomework);
        System.out.println();

        sc.close();
    } // end homework()
    
    /* calculation of the display of GPA results as well
     as the corresponding comment message. */
    public static void report() {
        double overall = WeightedMidtermScore + WeightedFinalExamScore + WeightedHomeworkScore;
        System.out.println("Overall percentage = " + (double)Math.round(overall * 10) / 10);
        double grade = grade(overall);
        System.out.println("Your grade will be at least: " + grade);
        System.out.println("<< your custom grade message here >>");
    } // end report()

    /* Check that the total weighted score does not exceed 100 points
     and return the position value a */
    public static int checkTotalWeight(int a, int b, int c) {
        int sum = a + b + c;
        if(sum > 100) {
            return a - (sum - 100);
        } else {
            return a;
        }
        
    } // end checkTotalWeight()


    // Returns the min GPA a student can achieve based on grade point average
    public static double grade(double a) {

        if(a == 100) {
            return 4.0;
        } else if(a >= 85) {
            return 3.0;
        } else if(a < 85 && a >= 75) {
            return 2.0;
        } else if(a < 75 && a >= 60) {
            return 0.7;
        } else {
            return 0.0;
        }

    } //end grade()

    


}
