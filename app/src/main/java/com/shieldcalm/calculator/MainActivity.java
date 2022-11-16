package com.shieldcalm.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution_tv,result_tv;

    MaterialButton buttonC,buttonBrackopen,buttonBrackclose;
    MaterialButton buttonDivide,buttonMultiplye,buttonPlus,buttonMinus,buttonEquals,buttonAc,buttonPoint;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonAc,R.id.button_AC);
        assignId(buttonPoint,R.id.button_point);
        assignId(buttonBrackopen,R.id.button_open_bracket);
        assignId(buttonBrackclose,R.id.button_clese_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiplye,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_mince);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(button0,R.id.button_0);
        assignId(button0,R.id.button_0);







    }

    void assignId(MaterialButton btn, int id){

        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalcullate = solution_tv.getText().toString();

       if (buttonText.equals("AC")){
           solution_tv.setText("");
           result_tv.setText("0");
           return;
       }

        if (buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }

        if (buttonText.equals("C")){
            dataToCalcullate = dataToCalcullate.substring(0,dataToCalcullate.length()-1);
            solution_tv.setText(result_tv.getText());
            return;
        }else{
            dataToCalcullate = dataToCalcullate+buttonText;
        }
        solution_tv.setText(dataToCalcullate);


        String finalResult = getResult(dataToCalcullate);

        if (!finalResult.equals("error")){
            result_tv.setText(finalResult);
        }
    }

    String getResult(String data){
      try {
          Context context= Context.enter();
          context.setOptimizationLevel(-1);
          Scriptable scriptable = context.initStandardObjects();
          String finalResult = context.evaluateString(scriptable,data,"Javascript",1, null).toString();
          return finalResult;
      }catch (Exception e){
          return "error";
      }
    }

}