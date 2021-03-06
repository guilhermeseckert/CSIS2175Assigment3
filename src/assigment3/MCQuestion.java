package assigment3;

import java.util.ArrayList;

public class MCQuestion extends Question {
    private ArrayList<String> options = new ArrayList<String>();
    private String answer = "";

    public MCQuestion() {
    }

    @Override
    public Double grade(String answer) {
        return null;
    }

    @Override
    public String getCorrectAnswer() {
        return null;
    }

    @Override
    public String getStringAnswer() {
        return null;
    }

    public MCQuestion(String qText, String options, double point) {
        this.options.add(options);
          this.answer = answer;
    }

    public ArrayList<String> getOptions() {

        return options;
    }

    public void setOptions(ArrayList<String> options){

    }

    public void setOptions(String options) {
        this.options.add(options);

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }





}
