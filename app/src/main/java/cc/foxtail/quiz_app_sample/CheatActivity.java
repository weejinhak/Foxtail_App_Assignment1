package cc.foxtail.quiz_app_sample;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private TextView answerTextView;
    boolean isHintView=false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerTextView =findViewById(R.id.answer_text_view);

        Button answerViewButton = findViewById(R.id.show_answer_button);
        Button chatBackButton= findViewById(R.id.cheat_back_button);

        final Intent intent=getIntent();
        final boolean quizAnswer=intent.getBooleanExtra("quizAnswer",false);


        answerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quizHint= quizAnswer? R.string.correct_hint :R.string.incorrect_hint;
                answerTextView.setText(quizHint);
                isHintView=true;
            }
        });


        chatBackButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheatActivity.this, MainActivity.class);
                intent.putExtra("isCheatView",isHintView);
                startActivity(intent);
            }
        });

    }

}
