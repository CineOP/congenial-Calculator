package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
class Output {
    String outText = "";

    public void setOut(String outText) {
        this.outText = outText;
        outField.setText(outText);
    }

    TextView outField;
    List<String> compileList = new ArrayList<>();
    public Output(TextView out){
        outField = out;
    }
    public void addSymb(String text) {
        this.outText += text;
        outField.setText(outText);
        if (compileList.isEmpty()){
            compileList.add(text);
        }else {
            try {
                Double.parseDouble(compileList.get(compileList.size() - 1));
                compileList.set(compileList.size() - 1, compileList.get(compileList.size() - 1) + (Double.parseDouble(text)));
            } catch (NumberFormatException e) {
                compileList.add(text);
            }
        }
        Log.i("compileList", compileList.toString());
    }

    public List<String> compile(){
        List<String> tempList = compileList;
        for (int i = 0; i<compileList.size(); i++) {
            if (compileList.get(i).equals("*")|compileList.get(i).equals("/")){
                double res;
                if (compileList.get(i).equals("*")){
                res = Double.parseDouble(compileList.get(i -1)) * Double.parseDouble(compileList.get(i +1));}
                else {res = Double.parseDouble(compileList.get(i -1)) / Integer.parseInt(compileList.get(i +1));}
                tempList.remove(i -1);
                tempList.remove(i -1);
                tempList.remove(i -1);
                tempList.add(i -1, String.valueOf(res));
            }
            Log.i("tempList", tempList.toString());
        }
        for (int i = 0; i<compileList.size(); i++) {
            if (compileList.get(i).equals("+")|compileList.get(i).equals("-")){
                double res;
                if (compileList.get(i).equals("+")){
                    res = Double.parseDouble(compileList.get(i -1)) + Double.parseDouble(compileList.get(i +1));}
                else {res = Double.parseDouble(compileList.get(i -1)) - Double.parseDouble(compileList.get(i +1));}
                tempList.remove(i -1);
                tempList.remove(i -1);
                tempList.remove(i -1);
                tempList.add(i -1, String.valueOf(res));
            }
            Log.i("tempList", tempList.toString());
        }
        this.setOut(tempList.get(0));
        return tempList;
    }
}
public class MainActivity extends AppCompatActivity {
    Button[] numButtons = new Button[14];
    Output res;
    Button resBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = new Output(findViewById(R.id.output_window));
        resBut = findViewById(R.id.butt_result);
        for (int i = 0; i < 10; i++) {
            try {
                numButtons[i] = findViewById(R.id.class.getField("butt"+i).getInt(null));
            } catch (NoSuchFieldException | IllegalAccessException ignored) {}
            numButtons[10] = findViewById(R.id.butt_plus);
            numButtons[11] = findViewById(R.id.butt_minus);
            numButtons[12] = findViewById(R.id.butt_multiply);
            numButtons[13] = findViewById(R.id.butt_divide);

        }
        for (Button b:
                numButtons) {
            b.setOnClickListener(view -> {
                Button butView = (Button) view;
                res.addSymb((String) butView.getText());
            });
        }
        resBut.setOnClickListener(view -> res.compile());

    }


}