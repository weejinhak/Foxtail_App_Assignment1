package cc.foxtail.quiz_app_sample;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private TextView answerTextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerTextview=findViewById(R.id.answer_text_view);

        Button answerViewButton = findViewById(R.id.show_answer_button);
        final Intent intent=getIntent();
        final boolean quizAnswer=intent.getBooleanExtra("quizAnswer",false);


        answerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quizHint= quizAnswer? R.string.correct_hint :R.string.incorrect_hint;
                answerTextview.setText(quizHint);
            }
        });


    }

}
