package assigment3;


public class TFQuestion extends Question {
    private Boolean answer;

    public TFQuestion() {

    }


    public TFQuestion(String qText, boolean answer, double point) {
        this.answer = answer;
        super.setqText(qText);
        super.setPoint(point);

    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    @Override
    public Double grade(String answer) {
        if (Boolean.parseBoolean(answer) == this.answer) {
            return super.getPoint();
        }

        return 0.0;
    }

    @Override
    public String getCorrectAnswer() {
        if (this.answer) {
            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public String getStringAnswer() {
        return String.valueOf(this.answer);
    }


}
