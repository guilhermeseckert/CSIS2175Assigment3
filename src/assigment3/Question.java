package assigment3;

public abstract class  Question {

    private String qText;
    private Double point;

    public Question() { }

    public Question(String qText) {
        this.qText = qText;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }


    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    abstract public Double grade(String answer );
    abstract public String getCorrectAnswer();
    abstract public  String getStringAnswer();


}
