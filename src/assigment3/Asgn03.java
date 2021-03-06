package assigment3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

public class Asgn03 {
    public static String db = "jdbc:ucanaccess:///Users/guilhermesonnemann/Desktop/JavaProjects/assigment3/src/Question.accdb";


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
                        System.out.println("read database");


                        break;

                    case "e":
                        close = true;
                        System.out.println("Goodbye!");
                        System.exit(0);
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

        for(int x = 0; x < numberOfOptions; x++){
            System.out.println("Enter Option "+(char)('A' + x) +" (Start with * for correct answer) "+" ");
            options.add(obj.next());
        }
        return options;

    }

    public static Question createAQuestion() {

        Scanner obj = new Scanner(System.in);
        Question question;
        String questionDescription;
        System.out.println("Enter the type of question (MC or TF) >>");
        String type = obj.next() ;

        if(type.equalsIgnoreCase("mc")) {

            System.out.println("Enter the question text >>");
            questionDescription = obj.next();
            System.out.println("How many options?>>");
            int numberOfOptions = obj.nextInt();
            String options = String.join("##", NumberOFOption(numberOfOptions));
            System.out.println(options);
            System.out.println("How many points?");
            double NumberOfPoints = obj.nextDouble();

            question = new MCQuestion(questionDescription, options, NumberOfPoints);


        } else if (type.equalsIgnoreCase("tf")) {

            System.out.println("Enter the question text");
            questionDescription = obj.next();

            System.out.println("Answer is True or False?");
            Boolean answer = obj.nextBoolean();
            System.out.println("How many points?");
            double NumberOfPoints = obj.nextDouble();

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
            statement.setString(2, question.getCorrectAnswer());
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

}

