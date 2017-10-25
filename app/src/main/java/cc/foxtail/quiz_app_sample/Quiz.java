package cc.foxtail.quiz_app_sample;


public class Quiz {
    private int stringResourceId;
    private boolean answer;
    private boolean hintChecker;

    public Quiz(int stringResourceId, boolean answer, boolean hintChecker) {
        this.stringResourceId = stringResourceId;
        this.answer = answer;
        this.hintChecker = hintChecker;

    }

    public int getStringResourceId() {
        return stringResourceId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setHintChecker(boolean hintChecker) {
        this.hintChecker = hintChecker;
    }

    public boolean isHintChecker() {
        return hintChecker;
    }
}
