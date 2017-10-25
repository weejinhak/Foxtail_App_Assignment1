package cc.foxtail.quiz_app_sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static Context mContext;
    public boolean isCheatView = false;


    private Quiz[] quizArray = new Quiz[]{
            new Quiz(R.string.question_iu, false, false),
            new Quiz(R.string.question_americas, true, false),
            new Quiz(R.string.question_asia, true, false),
            new Quiz(R.string.question_constraint, true, false),
            new Quiz(R.string.question_life_cycle, true, false)
    };

    private int currentIndex = 0;
    private TextView questionTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");
        mContext = this;

        questionTextView = findViewById(R.id.question_text_view);
        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);
        Button nextButton = findViewById(R.id.next_button);
        Button preButton = findViewById(R.id.pre_button);
        Button cheatButton = findViewById(R.id.cheat_button);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheatView||quizArray[currentIndex].isHintChecker()) {
                    checkCheat(true);
                } else {
                    checkAnswer(true);
                }
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheatView||quizArray[currentIndex].isHintChecker()) {
                    checkCheat(true);
                } else {
                    checkAnswer(false);
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % quizArray.length;
                isCheatView = false;
                updateQuestion();
            }
        });
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex - 1) % quizArray.length;
                isCheatView = false;
                if (currentIndex < 0)
                    currentIndex = quizArray.length + currentIndex;
                updateQuestion();
            }
        });

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("INDEX", 0);
        }

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                intent.putExtra("quizAnswer", quizArray[currentIndex].isAnswer());
                startActivityForResult(intent, 0);
            }
        });


        updateQuestion();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 0) {
            Boolean result = data.getBooleanExtra("isCheatView", false);
            if (result) {
                isCheatView = true;
                quizArray[currentIndex].setHintChecker(true);
                Log.d("result_true", "true");
            } else {
                isCheatView = false;
                Log.d("result_true", "false");
            }
        }

    }

    private void updateQuestion() {
        int questionResourceId = quizArray[currentIndex].getStringResourceId();
        questionTextView.setText(questionResourceId);
    }

    public void checkAnswer(boolean answer) {
        boolean answerIsTrue = quizArray[currentIndex].isAnswer();
        int toastMessageId = answer == answerIsTrue ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();
    }

    private void checkCheat(boolean check) {
        if (check)
            Toast.makeText(this, "정답 봤잖아!", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("MainActivity", "onSave");
        outState.putInt("INDEX", currentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }

}
