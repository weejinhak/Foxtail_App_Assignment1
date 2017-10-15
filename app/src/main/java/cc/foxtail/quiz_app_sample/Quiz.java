package cc.foxtail.quiz_app_sample;


public class Quiz {
    private int stringResourceId;
    private boolean answer;

    public Quiz(int stringResourceId, boolean answer) {
        this.stringResourceId = stringResourceId;
        this.answer = answer;
    }

    public int getStringResourceId() {
        return stringResourceId;
    }

    public boolean isAnswer() {
        return answer;
    }
}
