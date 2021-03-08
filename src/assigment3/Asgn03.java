package assigment3;

import java.sql.*;
import java.util.*;

public class Asgn03 {
    public static String db = "jdbc:ucanaccess:///Users/guilhermesonnemann/Desktop/JavaProjects/assigment3/src/Question.accdb";
    private static Double Score;

    public static void main(String[] args) {



        Scanner obj = new Scanner(System.in);


        try {
            Connection con = DriverManager.getConnection(db);
            boolean close = false;
            while (!close) {
                System.out.println("Please choose (c)reate a question, (p)review or (e)xit ");
                String opt = obj.next().toLowerCase(Locale.ROOT);
                switch (opt) {
                    case "c":
                        Question question = createAQuestion();
                        addQuestion(question);
                        break;

                    case "p":
                        readQuestions();
                        break;

                    case "e":
                        close = true;
                        System.out.println("Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Incorrect option");
                }
            }

            con.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }


    }


    public static ArrayList NumberOFOption(int numberOfOptions) {
        Scanner obj = new Scanner(System.in);
        ArrayList<String> options = new ArrayList<String>();
        boolean doagain = false;

        while (!doagain) {

            for(int x = 0; x < numberOfOptions; x++){
                System.out.println("Enter Option "+(char)('A' + x) +" (Start with * for correct answer) "+" ");
                options.add(obj.next());
            }

            for(int j = 0; j< options.size(); j ++) {

                if (options.get(j).indexOf("*") == 0) {
                    doagain =true;
                }
            }

            if(!doagain) {
                System.out.println("Please time * for correct answer");
            }

        }


        return options;


    }

    public static Question createAQuestion() {

        Scanner obj = new Scanner(System.in);
        Question question;
        String options;
        double NumberOfPoints;
        String questionDescription = "";
        System.out.println("Enter the type of question (MC or TF) >>");
        String type = obj.next() ;

        if(type.equalsIgnoreCase("mc")) {

            System.out.println("Enter the question text >>");
            questionDescription = obj.next();
            System.out.println("How many options?>>");
            int numberOfOptions = obj.nextInt();
            options = String.join("##", NumberOFOption(numberOfOptions));
            System.out.println("How many points?");
            NumberOfPoints = obj.nextDouble();

            question = new MCQuestion(questionDescription, options, NumberOfPoints);

        } else if (type.equalsIgnoreCase("tf")) {

            System.out.println("Enter the question text");
            questionDescription = obj.next();
            System.out.println("Answer is True or False?");
            Boolean answer = obj.nextBoolean();
            System.out.println("How many points?");
            NumberOfPoints = obj.nextDouble();

            question = new TFQuestion(questionDescription, answer,NumberOfPoints);

        } else {
            System.out.println("Question type wrong");
            question = new MCQuestion();
        }

        return question;


    }


    public static void addQuestion(Question question) {
        try {

           Connection con = DriverManager.getConnection(db);

           String sql = "INSERT INTO Questions (QText, Answer, Point, Type) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, question.getqText());
            statement.setString(2, question.getStringAnswer());
            statement.setDouble(3, question.getPoint());

            if(question instanceof MCQuestion){
                statement.setString(4, "MC");
            } else if (question instanceof TFQuestion) {
                statement.setString(4, "TF");
            }
            statement.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);

        }

    }


    public static void readQuestions() {

     try {
         Scanner obj = new Scanner(System.in);
         Connection con = DriverManager.getConnection(db);
         Statement statement = con.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM Questions");
         String answer;


         while(resultSet.next()) {
             String QText = resultSet.getString("QText");
             String Answer = resultSet.getString("Answer");
             Double Point = resultSet.getDouble("Point");
             String Type = resultSet.getString("Type");


             if (Type.equals("MC") ) {
                 MCQuestion question = new MCQuestion();
                 question.setqText(QText);
                 question.setAnswer(Answer);
                 question.setOptions(Answer);
                 question.setPoint(Point);

                 System.out.println("Question" +question.getqText() +" (" + question.getPoint() +")");

                 for(int x = 0; x < question.getOptions().size(); x++){
                     System.out.println((char)('A' + x) +": " + question.getOptions().get(x));

                 }
                 System.out.println(question.getCorrectAnswer());

                 System.out.println("Enter your choice >> ");
                 answer = obj.next().toLowerCase(Locale.ROOT);
                 switch (answer) {
                     case "a":
                         if (question.getOptions().get(0).equals(question.getCorrectAnswer())) {
                             System.out.println("You are correct!");

                         } else {
                             System.out.println("You are wrong. The correct answer is " + question.getCorrectAnswer());
                         }
                         break;

                     case "b":
                         if (question.getOptions().get(1).equals(question.getCorrectAnswer())) {
                             System.out.println("You are correct!");

                         } else {
                             System.out.println("You are wrong. The correct answer is " + question.getCorrectAnswer());
                         }
                         break;

                     case "c":
                         if (question.getOptions().get(2).equals(question.getCorrectAnswer())) {
                             System.out.println("You are correct!");

                         } else {
                             System.out.println("You are wrong. The correct answer is " + question.getCorrectAnswer());
                         }
                         break;
                     case "d":
                         if (question.getOptions().get(3).equals(question.getCorrectAnswer())) {
                             System.out.println("You are correct!");

                         } else {
                             System.out.println("You are wrong. The correct answer is " + question.getCorrectAnswer());
                         }
                         break;
                     case "e":
                         if (question.getOptions().get(4).equals(question.getCorrectAnswer())) {
                             System.out.println("You are correct!");

                         } else {
                             System.out.println("You are wrong. The correct answer is " + question.getCorrectAnswer());
                         }
                         break;

                     default:
                         System.out.println("wrong option");
                         break;
                 }
//                 Score += question.grade(answer);


             } else  {

                 TFQuestion question = new TFQuestion();
                 question.setqText(QText);
                 question.setAnswer(Boolean.parseBoolean(Answer));
                 question.setPoint(Point);

                 System.out.println(question.getqText());
                 System.out.println("True(T) or False(F)");
                 answer = obj.nextLine().toLowerCase(Locale.ROOT);

                 switch (answer) {
                     case "t":
                     case "f":
                         answer = "true";
                         if(answer.equals(question.getCorrectAnswer())) {
                             System.out.println("You are correct!");
                         } else {
                             System.out.println("You are wrong. The correct answer is " + question.getAnswer());
                         }
                     break;
                 }

//                 Score += question.grade(answer);



             }
         }



     } catch (Exception ex) {
         ex.printStackTrace();

     }



    }




}

