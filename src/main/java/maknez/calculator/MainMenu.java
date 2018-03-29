package maknez.calculator;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button SimpleCalc, ScienCalc, Credits, Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu);

        SimpleCalc = findViewById(R.id.SimpleCalc);
        ScienCalc = findViewById(R.id.ScienCalc);
        Credits = findViewById(R.id.Credits);
        Exit = findViewById(R.id.Exit);

        SimpleCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent simpleCalculator = new Intent(MainMenu.this, SimpleCalculator.class);
                startActivity(simpleCalculator);
            }
        });

        ScienCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scienCalculator = new Intent(MainMenu.this, ScienCalculator.class);
                startActivity(scienCalculator);

            }
        });
        Credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent credits = new Intent(MainMenu.this, Credits.class);
                startActivity(credits);
            }
        });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }
}
