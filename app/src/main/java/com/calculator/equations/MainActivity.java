package com.calculator.equations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.calculator.equations.mxparser.Expression;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.calculator.equations.Utils.canAddChar;
import static com.calculator.equations.Utils.rotateView;

public class MainActivity extends AppCompatActivity {

    /*
     *
     * Calculation Views
     *
     *
     * */
    @BindView(R.id.ID_calculation_main)
    TextView cMainCalculation;
    @BindView(R.id.ID_calculation_answer)
    TextView cAnswerCalculation;


    /*
     *
     * Numbers & Equations Defining
     *
     * Numbers
     * */
    @BindView(R.id.ID_0)
    Button n0Btn;
    @BindView(R.id.ID_1)
    Button n1Btn;
    @BindView(R.id.ID_2)
    Button n2Btn;
    @BindView(R.id.ID_3)
    Button n3Btn;
    @BindView(R.id.ID_4)
    Button n4Btn;
    @BindView(R.id.ID_5)
    Button n5Btn;
    @BindView(R.id.ID_6)
    Button n6Btn;
    @BindView(R.id.ID_7)
    Button n7Btn;
    @BindView(R.id.ID_8)
    Button n8Btn;
    @BindView(R.id.ID_9)
    Button n9Btn;
    @BindView(R.id.ID_dot)
    Button nDotBtn;
    @BindView(R.id.ID_equal)
    Button nEqualBtn;

    /*
     *
     * Equations
     *
     * */
    @BindView(R.id.ID_remove_IMAGEBTN)
    ImageButton eRemoveImageBtn;
    @BindView(R.id.ID_minus)
    Button eMinus;
    @BindView(R.id.ID_plus)
    Button ePlusBtn;
    @BindView(R.id.ID_x)
    Button eTimusBtn;
    @BindView(R.id.ID_divide)
    Button eDivideBtn;


    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.ID_numbers_gridview)
    GridLayout mNumbersGV;
    @BindView(R.id.ID_equations_gridview)
    GridLayout mEquationsGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupWidthWithRootView();
        setupNumbers();
        setupEquations();
    }




    private void setupWidthWithRootView() {
                mNumbersGV.post(new Runnable() {
            @Override
            public void run() {

                int mNumbersGVWidth = mNumbersGV.getMeasuredWidth();
                int mNumbersGVHeight = mNumbersGV.getMeasuredHeight();

                double numbersWidthPortion = (0.34);
                double numberHeightPortion = 0.25;
                int assignedNumbersWidth = (int) (mNumbersGVWidth * numbersWidthPortion);
                int assignedNumbersHeight = (int) (mNumbersGVHeight * numberHeightPortion);

                assignNumberBtn(n0Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(n1Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(n2Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(n3Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(n4Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(n5Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(n6Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(n7Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(n8Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(n9Btn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(nDotBtn, assignedNumbersWidth, assignedNumbersHeight);
                assignNumberBtn(nEqualBtn, assignedNumbersWidth, assignedNumbersHeight);
            }
        });


        mEquationsGV.post(new Runnable() {
            @Override
            public void run() {
                int mEquationsGVWidth = mEquationsGV.getMeasuredWidth();
                int mEquationsGVHeight = mEquationsGV.getMeasuredHeight();

                double equationsWidthPortion = 1;
                double equationsHeightPortion = 0.2;
                int assignedEquationsWidth = (int) (mEquationsGVWidth * equationsWidthPortion);
                int assignedEquationsHeight = (int) (mEquationsGVHeight * equationsHeightPortion);

                assignNumberBtn(eDivideBtn, assignedEquationsWidth, assignedEquationsHeight);
                assignNumberBtn(ePlusBtn, assignedEquationsWidth, assignedEquationsHeight);
                assignNumberBtn(eMinus, assignedEquationsWidth, assignedEquationsHeight);
                assignNumberBtn(eTimusBtn, assignedEquationsWidth, assignedEquationsHeight);
                eRemoveImageBtn.setLayoutParams(new GridLayout.LayoutParams(new LinearLayout.LayoutParams(assignedEquationsWidth, assignedEquationsHeight)));
            }
        });


    }

    private void assignNumberBtn(Button btn, int width, int height) {
        int textSize = (int) (width * 0.25);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        btn.setLayoutParams(new GridLayout.LayoutParams(params));
        btn.setTextSize(textSize);
    }


    private void setupNumbers() {


        n0Btn.setOnClickListener(v -> addToCalculation(0, false));
        n1Btn.setOnClickListener(v -> addToCalculation(1, false));
        n2Btn.setOnClickListener(v -> addToCalculation(2, false));
        n3Btn.setOnClickListener(v -> addToCalculation(3, false));
        n4Btn.setOnClickListener(v -> addToCalculation(4, false));
        n5Btn.setOnClickListener(v -> addToCalculation(5, false));
        n6Btn.setOnClickListener(v -> addToCalculation(6, false));
        n7Btn.setOnClickListener(v -> addToCalculation(7, false));
        n8Btn.setOnClickListener(v -> addToCalculation(8, false));
        n9Btn.setOnClickListener(v -> addToCalculation(9, false));
        nDotBtn.setOnClickListener(v -> addToCalculation(0, true));
        nEqualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cMainCalculation.setText(String.valueOf( attemptRealTimeCalculation()));
            }
        });

        handleLongClick(n0Btn);
        handleLongClick(n1Btn);
        handleLongClick(n2Btn);
        handleLongClick(n3Btn);
        handleLongClick(n4Btn);
        handleLongClick(n5Btn);
        handleLongClick(n6Btn);
        handleLongClick(n7Btn);
        handleLongClick(n8Btn);
        handleLongClick(n9Btn);


    }

    long lastDown;
    private void handleLongClick(Button button)
    {
        final Handler handler = new Handler();
        Runnable mLongPressed = new Runnable() {
            @Override
            public void run() {
                //Do something after 20 seconds
                button.performClick();
                handler.postDelayed(this, 200);
            }
        };


        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    handler.postDelayed(mLongPressed, ViewConfiguration.getLongPressTimeout());
                    lastDown = System.currentTimeMillis();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.removeCallbacksAndMessages(null);
                    if (System.currentTimeMillis() - lastDown < ViewConfiguration.getLongPressTimeout())
                    {
                        button.performClick();
                    }
                }

                return true;
            }
        });

    }

    private void setupEquations() {
        eRemoveImageBtn.setOnClickListener(v -> {
            if (cMainCalculation.length() > 0) {
                cMainCalculation.setText(cMainCalculation.getText().toString().substring(0, cMainCalculation.getText().length() - 1));
                attemptRealTimeCalculation();
            } else {
                cMainCalculation.setText("");
            }
        });

        eRemoveImageBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                rotateView(cMainCalculation,cAnswerCalculation,250);
                return true;
            }
        });

        eDivideBtn.setOnClickListener(v -> {
            if (!canAddChar(cMainCalculation.getText().toString())) {
                cMainCalculation.setText(cMainCalculation.getText().toString() + "/");
            }
        });

        eMinus.setOnClickListener(v -> {
            if (!canAddChar(cMainCalculation.getText().toString())) {
                cMainCalculation.setText(cMainCalculation.getText().toString() + "-");
            }
        });

        eTimusBtn.setOnClickListener(v -> {
            if (!canAddChar(cMainCalculation.getText().toString())) {
                cMainCalculation.setText(cMainCalculation.getText().toString() + "x");
            }
        });

        ePlusBtn.setOnClickListener(v -> {
            if (!canAddChar(cMainCalculation.getText().toString())) {
                cMainCalculation.setText(cMainCalculation.getText().toString() + "+");
            }
        });


    }
    private Double attemptRealTimeCalculation() {
       String fullCalculationText = cMainCalculation.getText().toString().toLowerCase();
        String correctCalculationFormat = fullCalculationText.replaceAll("x","*");

        Expression e = new Expression(correctCalculationFormat);
        double v = e.calculate();

        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(2);

        String value = df.format(v);
        cAnswerCalculation.setText("" + value);
        Utils.scaleView(cAnswerCalculation,0.5f,1.3f, 120);
        return v;

    }


    private void addToCalculation(int number, boolean isDot) {
        StringBuilder stringBuilder = new StringBuilder();
        String currentText = cMainCalculation.getText().toString();
        stringBuilder.append(currentText);
        if (isDot) {
            String lastChar = currentText.substring(currentText.length() - 1).toLowerCase();
            if (!lastChar.equals(".")) {
                stringBuilder.append(".");
                cMainCalculation.setText(stringBuilder);
            }
        } else {
            stringBuilder.append(number);
            cMainCalculation.setText(stringBuilder);
        }

        attemptRealTimeCalculation();
    }

}
