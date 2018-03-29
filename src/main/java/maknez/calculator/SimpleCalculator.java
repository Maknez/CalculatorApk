package maknez.calculator;

import android.annotation.SuppressLint;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;
import java.util.Objects;

import static java.lang.Double.isInfinite;

public class SimpleCalculator extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonAdd, buttonSub, buttonMul, buttonDiv, buttonEqual,
            buttonReverse, buttonClear, buttonBack, buttonComa;
    TextView results;
    String text, tempText, value;
    private double firstValue, secondValue;
    boolean wasClicked = false;

    public void pressNumberButton(String number) {
        wasClicked = false;
        if(!results.getText().toString().endsWith(")")){
            if(results.getText().equals("0") && results.getText().length() == 1)
                results.setText(number);
            else if (results.getText().toString().endsWith(" 0") && results.getText().toString().length() < 30){
                results.setText((String.valueOf(results.getText().toString().substring(0,results.length() - 1) + number)));
            }
            else {
                results.setText((String.valueOf(results.getText() + number)));
            }
        }
    }

    public void saveFirstValue() {
        //Czy text jest pusty
        if(!text.equals("")) {
            //Czy to pierwsza czy druga zmienna
            if(!text.contains(" ")) {
                //Czy pierwsza liczba jest dodatnia
                if (Character.isDigit(text.charAt(0)) && Character.isDigit(text.charAt(text.length() - 1))) {
                    firstValue = Double.parseDouble(text);
                }
                //Czy pierwsza liczba jest ujemna
                else if (text.startsWith("(-") && text.endsWith(")")) {
                    value = text.substring(1, text.length() - 1);
                    firstValue = Double.parseDouble(value);
                }
            }
        }
    }

    public void saveSecondValue() {
        //Czy text jest pusty
        if(!text.equals("")) {
            //Czy to pierwsza czy druga zmienna
            if(text.contains(" ") && (Character.isDigit(text.charAt(text.length() - 1)) || text.endsWith(")"))) {
                value = text.substring(text.indexOf(" ") + 3, text.length());
                //Czy druga liczba jest dodatnia
                if (Character.isDigit(value.charAt(0)) && Character.isDigit(value.charAt(value.length() - 1))) {
                    secondValue = Double.parseDouble(value);
                }
                //Czy druga liczba jest ujemna
                else if (value.startsWith("(-") && value.endsWith(")")) {
                    value = value.substring(1, value.length() - 1);
                    secondValue = Double.parseDouble(value);
                }
            }
        }
    }

    public boolean isPossibleToShow() {
        if (isInfinite(firstValue)) {
            Toast.makeText(SimpleCalculator.this, "The result is too long to show!", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }



    public void doTheOperation (String operator) {
        //Czy brak danych
        if(!text.equals("")){
            //Czy konczy sie .
            if(text.endsWith(".")) {
                text = text.substring(0, text.length() - 1);
            }
            //Czy operator =
            if(!Objects.equals(operator, " = ")) {
                //Czy podmieniamy operator
                if (text.endsWith(" + ") || text.endsWith(" - ") || text.endsWith(" * ") || text.endsWith(" / ")) {
                    text = text.substring(0, text.length() - 3) + operator;
                }
                //Czy dodajemy operator
                else if (!text.contains(" ") && (Character.isDigit(text.charAt(text.length() - 1)) || text.endsWith(")"))) {
                    text = text + operator;
                }
                //Czy wykonujemy operacje
                else if (text.contains(" ") && (Character.isDigit(text.charAt(text.length() - 1)) || text.endsWith(")"))) {
                    if (text.contains(" + ")) {
                        firstValue = firstValue + secondValue;
                        if(isPossibleToShow()) {
                            text = String.valueOf(new BigDecimal(Double.toString(firstValue)).stripTrailingZeros().toPlainString());
                        }
                        else {
                            text = "0";
                        }
                        if(firstValue < 0) {
                            text = "(" + text + ")" + operator;
                        }
                        else {
                            text = text + operator;
                        }
                    }
                    else if (text.contains(" - ")) {
                        firstValue = firstValue - secondValue;
                        if(isPossibleToShow()) {
                            text = String.valueOf(new BigDecimal(Double.toString(firstValue)).stripTrailingZeros().toPlainString());
                        }
                        else {
                            text = "0";
                        }
                        if(firstValue < 0) {
                            text = "(" + text + ")" + operator;
                        }
                        else {
                            text = text + operator;
                        }
                    }
                    else if (text.contains(" * ")) {
                        firstValue = firstValue * secondValue;
                        if(isPossibleToShow()) {
                            text = String.valueOf(new BigDecimal(Double.toString(firstValue)).stripTrailingZeros().toPlainString());
                        }
                        else {
                            text = "0";
                        }
                        if(firstValue < 0) {
                            text = "(" + text + ")" + operator;
                        }
                        else {
                            text = text + operator;
                        }
                    }
                    else if (text.contains(" / ")) {
                        if(secondValue != 0) {
                            firstValue = firstValue / secondValue;
                            if(isPossibleToShow()) {
                                text = String.valueOf(new BigDecimal(Double.toString(firstValue)).stripTrailingZeros().toPlainString());
                            }
                            else {
                                text = "0";
                            }
                            if(firstValue < 0) {
                                text = "(" + text + ")" + operator;
                            }
                            else {
                                text = text + operator;
                            }
                        }
                        else {
                            Toast.makeText(SimpleCalculator.this, "You cannot divide by 0!", Toast.LENGTH_LONG).show();
                            text = "0";
                        }
                    }
                }
            }
            //Czy operator to =
            else {
                //Czy podmieniamy znak
                if (text.endsWith(" + ") || text.endsWith(" - ") || text.endsWith(" * ") || text.endsWith(" / ")) {
                    text = text.substring(0, text.length() - 3);
                }
                //Czy wyswietlamy sama liczbe
                /*else if (!text.contains(" ") && (Character.isDigit(text.charAt(text.length() - 1)) || text.endsWith(")"))) {
                }*/
                //Czy wykonujemy operacje
                else if (text.contains(" ") && (Character.isDigit(text.charAt(text.length() - 1)) || text.endsWith(")"))) {
                    if (text.contains(" + ")) {
                        firstValue = firstValue + secondValue;
                        if(isPossibleToShow()) {
                            text = String.valueOf(new BigDecimal(Double.toString(firstValue)).stripTrailingZeros().toPlainString());
                        }
                        else {
                            text = "0";
                        }
                        if(firstValue < 0) {
                            text = "(" + text + ")";
                        }
                    }
                    else if (text.contains(" - ")) {
                        firstValue = firstValue - secondValue;
                        if(isPossibleToShow()) {
                            text = String.valueOf(new BigDecimal(Double.toString(firstValue)).stripTrailingZeros().toPlainString());
                        }
                        else {
                            text = "0";
                        }
                        if(firstValue < 0) {
                            text = "(" + text + ")";
                        }
                    }
                    else if (text.contains(" * ")) {
                        firstValue = firstValue * secondValue;
                        if(isPossibleToShow()) {
                            text = String.valueOf(new BigDecimal(Double.toString(firstValue)).stripTrailingZeros().toPlainString());
                        }
                        else {
                            text = "0";
                        }
                        if(firstValue < 0) {
                            text = "(" + text + ")";
                        }
                    }
                    else if (text.contains(" / ")) {
                        if(secondValue != 0) {
                            firstValue = firstValue / secondValue;
                            if(isPossibleToShow()) {
                                text = String.valueOf(new BigDecimal(Double.toString(firstValue)).stripTrailingZeros().toPlainString());
                            }
                            else {
                                text = "0";
                            }
                            if(firstValue < 0) {
                                text = "(" + text + ")";
                            }
                        }
                        else {
                            Toast.makeText(SimpleCalculator.this, "You cannot divide by 0!", Toast.LENGTH_LONG).show();
                            text = "0";
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", String.valueOf(results.getText()));
        outState.putDouble("first", firstValue);
        outState.putDouble("second", secondValue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simple_calculator);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonReverse = findViewById(R.id.buttonReverse);
        buttonClear = findViewById(R.id.buttonClear);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonBack = findViewById(R.id.buttonBack);
        buttonComa = findViewById(R.id.buttonComa);
        results = findViewById(R.id.results);

        TextViewCompat.setAutoSizeTextTypeWithDefaults(
                results,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
        );

        if(savedInstanceState != null){
            results.setText(savedInstanceState.getString("value"));
            firstValue = savedInstanceState.getDouble("first");
            secondValue = savedInstanceState.getDouble("second");
        }

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasClicked = false;
                results.setText(String.valueOf(""));
                firstValue = 0;
                secondValue = 0;
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasClicked = false;
                text = results.getText().toString();
                if(text.length() > 0) {
                    if(text.endsWith(" ")) {
                        results.setText(String.valueOf(text.substring(0, text.length() - 3)));
                    }
                    else if (!text.contains(" ")) {
                        if(text.endsWith(")")) {
                            results.setText(String.valueOf(text.substring(2, text.length() - 1)));
                        }
                        else {
                            results.setText(String.valueOf(text.substring(0, text.length() - 1)));
                        }
                    }
                    else if (text.contains(" ")) {
                        if(text.endsWith(")")) {
                            results.setText(String.valueOf(text.substring(0, text.indexOf(" ") + 3) + text.substring(text.indexOf(" ") + 5, text.length() - 1)));
                        }
                        else {
                            results.setText(String.valueOf(text.substring(0, text.length() - 1)));
                        }
                    }
                }
            }
        });

        buttonComa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasClicked = false;
                text = results.getText().toString();
                if(text.equals("")) {
                    results.setText(String.valueOf("0."));
                }
                else if(!text.endsWith(")")){
                    if(!text.contains(".") && !text.contains(" ")) {
                        results.setText(String.valueOf(results.getText() + "."));
                    }
                    else if(text.contains(" ")) {
                        if(!text.endsWith(" ")){
                            value = text.substring(text.indexOf(" ") + 3, text.length());
                            if(!value.contains(".")) {
                                results.setText(String.valueOf(results.getText() + "."));
                            }
                        }
                        else {
                            results.setText(String.valueOf(results.getText() + "0."));
                        }
                    }
                }
            }
        });


        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("2");            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("3");            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("4");            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("5");            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("6");            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("7");            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("8");            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressNumberButton("9");            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasClicked = false;
                text = results.getText().toString();
                saveFirstValue();
                saveSecondValue();
                doTheOperation(" + ");
                results.setText(String.valueOf(text));
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasClicked = false;
                text = results.getText().toString();
                saveFirstValue();
                saveSecondValue();
                doTheOperation(" - ");
                results.setText(String.valueOf(text));
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasClicked = false;
                text = results.getText().toString();
                saveFirstValue();
                saveSecondValue();
                doTheOperation(" * ");
                results.setText(String.valueOf(text));
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasClicked = false;
                text = results.getText().toString();
                saveFirstValue();
                saveSecondValue();
                doTheOperation(" / ");
                results.setText(String.valueOf(text));
            }
        });

        buttonReverse.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                wasClicked = false;
                text = results.getText().toString();
                //Czy wynik jest pusty
                if (text.equals("")) {
                    results.setText("(-");
                }
                else if(text.length() > 0 && text.startsWith("(-") && Character.isDigit(text.charAt(text.length() - 1))) {
                    results.setText(results.getText().toString() + ")");
                }
                //Czy jest tylko jedna zmienna
                else if(!text.contains(" ")) {
                    //Czy jest to zmienna ujemna
                    if (text.startsWith("(-") && text.endsWith(")")) {
                        text = text.substring(2, text.length() - 1);
                        results.setText(String.valueOf(text));
                    }
                    //Czy jest to zmienna dodatnia
                    else if (Character.isDigit(text.charAt(0)) && Character.isDigit(text.charAt(text.length() - 1))) {
                        results.setText(String.valueOf("(-" + text + ")"));
                    }
                    else if (Character.isDigit(text.charAt(0)) && text.endsWith(".")) {
                        results.setText("(-" + text.substring(0, text.length() - 1) + ")");
                    }
                }
                else if(text.contains(" ")) {
                    if (!text.endsWith(" ")) {
                        value = text.substring(text.indexOf(" ") + 3, text.length());
                        if (value.startsWith("(-") && value.endsWith(")")) {
                            value = value.substring(2, value.length() - 1);
                            results.setText(String.valueOf(text.substring(0, text.indexOf(" ") + 3) + value));
                        }
                        else if (Character.isDigit(value.charAt(0)) && Character.isDigit(value.charAt(value.length() - 1))) {
                            results.setText(String.valueOf(text.substring(0, text.indexOf(" ") + 3) + "(-" + value + ")"));
                        }
                        else if (Character.isDigit(value.charAt(0)) && value.endsWith(".")) {
                            results.setText(String.valueOf(text.substring(0, text.indexOf(" ") + 3) + "(-" + value.substring(0, value.length() - 1) + ")"));
                        }
                    }
                }

            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!wasClicked) {
                    text = results.getText().toString();
                    tempText = text;
                    saveFirstValue();
                    saveSecondValue();
                    doTheOperation(" = ");
                    results.setText(String.valueOf(text));
                    wasClicked = true;
                }
                else {
                    text = tempText;
                    doTheOperation(" = ");
                    results.setText(String.valueOf(text));
                }
            }
        });
    }
}
