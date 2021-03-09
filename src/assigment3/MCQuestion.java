package assigment3;

import java.util.ArrayList;

public class MCQuestion extends Question {
    private ArrayList<String> options = new ArrayList<String>();
    private String answer = "";

    public MCQuestion() {
    }

    public MCQuestion(String qText, String options, double point) {
          this.options.add(options);
          super.setqText(qText);
          super.setPoint(point);

    }


    public ArrayList<String> getOptions() {

         ArrayList<String> temp = new ArrayList<String>();

        for (int i = 0; i < options.size(); i++) {
            String apt = options.get(i);
            String[] opt = apt.split("##");
            for(int j =0 ; j < opt.length; j ++) {
                temp.add(opt[j].replace("*", ""));
            }
        }


        return temp;

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


    @Override
    public Double grade(String answer) {
        if (answer.equals(getAnswer())) {
            return super.getPoint();
        }  else {
            return 0.0;
        }

    }

    @Override
    public String getCorrectAnswer() {

        for (int i = 0; i < options.size(); i++) {
            String apt = options.get(i);

            String[] opt = apt.split("##");

            for (int j = 0; j < opt.length; j++) {

                if (opt[j].indexOf("*") == 0) {
                    this.answer = (opt[j].replace("*", ""));
                    setAnswer(this.answer);
                    return this.answer;

                }

            }

        }
        return null;
    }

    @Override
    public String getStringAnswer() {
        return String.join("##", this.options);
    }


}
