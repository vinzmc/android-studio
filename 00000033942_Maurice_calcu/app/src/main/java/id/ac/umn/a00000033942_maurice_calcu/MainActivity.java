package id.ac.umn.a00000033942_maurice_calcu;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    double result;
    char operator;
    boolean negative, decimal, calculated;
    TextView input, entry;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnBagi, btnKali, btnTambah, btnKurang, btnResult;
    Button btnCE, btnC, btnBack, btnNegative, btnDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //text view
        input = (TextView) this.findViewById(R.id.input);
        entry = (TextView) this.findViewById(R.id.entry);

        //extra
        btnCE = (Button) this.findViewById(R.id.btnCE);
        btnC = (Button) this.findViewById(R.id.btnC);
        btnNegative = (Button) this.findViewById(R.id.btnNegative);
        btnDecimal = (Button) this.findViewById(R.id.btnDecimal);
        btnBack = (Button) this.findViewById(R.id.btnBack);
        btnBack.setText("\u232b");

        btnBagi = (Button) this.findViewById(R.id.btnBagi);
        btnKali = (Button) this.findViewById(R.id.btnKali);
        btnTambah = (Button) this.findViewById(R.id.btnTambah);
        btnKurang = (Button) this.findViewById(R.id.btnKurang);
        btnResult = (Button) this.findViewById(R.id.btnResult);

        //button angka
        btn0 = (Button) this.findViewById(R.id.btn0);
        btn1 = (Button) this.findViewById(R.id.btn1);
        btn2 = (Button) this.findViewById(R.id.btn2);
        btn3 = (Button) this.findViewById(R.id.btn3);
        btn4 = (Button) this.findViewById(R.id.btn4);
        btn5 = (Button) this.findViewById(R.id.btn5);
        btn6 = (Button) this.findViewById(R.id.btn6);
        btn7 = (Button) this.findViewById(R.id.btn7);
        btn8 = (Button) this.findViewById(R.id.btn8);
        btn9 = (Button) this.findViewById(R.id.btn9);
        //resizing button biar persegi
        buttonResize();

        //inisiasi variable
        negative = decimal = calculated = false;
        result = 0;
        operator = ' ';

        //angka onclick listener
        btn0.setOnClickListener(v -> buttonListener('0'));
        btn1.setOnClickListener(v -> buttonListener('1'));
        btn2.setOnClickListener(v -> buttonListener('2'));
        btn3.setOnClickListener(v -> buttonListener('3'));
        btn4.setOnClickListener(v -> buttonListener('4'));
        btn5.setOnClickListener(v -> buttonListener('5'));
        btn6.setOnClickListener(v -> buttonListener('6'));
        btn7.setOnClickListener(v -> buttonListener('7'));
        btn8.setOnClickListener(v -> buttonListener('8'));
        btn9.setOnClickListener(v -> buttonListener('9'));

        //extra
        btnBack.setOnClickListener(v -> backspace());
        btnC.setOnClickListener(v -> clear());
        btnCE.setOnClickListener(v -> clearEntry());
        btnNegative.setOnClickListener(v -> DN('!'));
        btnDecimal.setOnClickListener(v -> DN('.'));

//        operator
        btnTambah.setOnClickListener(v -> hitung('+'));
        btnKurang.setOnClickListener(v -> hitung('-'));
        btnKali.setOnClickListener(v -> hitung('x'));
        btnBagi.setOnClickListener(v -> hitung('/'));
        btnResult.setOnClickListener(v -> hitung('='));
    }

    protected void buttonResize(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int screenHeight = displaymetrics.widthPixels;

        int btnHeight = (int) (screenHeight * 0.25);
        int btnWidth = (int) (screenHeight * 0.25);
        Button[] buttonTemp = {
                btnCE, btnC, btnBack, btnBagi,
                btn7, btn8, btn9, btnKali,
                btn4, btn5, btn6, btnKurang,
                btn1, btn2, btn3, btnTambah,
                btnNegative, btn0, btnDecimal, btnResult
        };

        for (Button button : buttonTemp) {
            button.getLayoutParams().height = btnHeight;
            button.getLayoutParams().width = btnWidth;
        }
    }
    
//button input
    public void buttonListener(char inputC){
        if(!calculated){
            input.setText(String.format("%s%c", input.getText().toString(), inputC));
        }else{
            clearEntry();
            input.setText(String.format("%s%c", input.getText().toString(), inputC));
        }
    }

    //decimal n negative
    public void DN(char inputC){
        if(calculated){
            clearEntry();
        }
        switch(inputC){
            case ('.'):
                if(!decimal){
                    if(input.length()==0){//inisiasi 0.#
                        input.setText(String.format("%s%c", "0", inputC));
                    }else{
                        input.setText(String.format("%s%c", input.getText().toString(), inputC));
                    }
                    decimal = true;
                }
                break;
            case ('!') :
                int length = input.length();
                if(!negative){
                    input.setText(String.format("%c%s", '-', input.getText().toString()));
                    negative = true;
                }else{
                    if(length == 1){
                        input.setText("");
                    }else{
                        input.setText(input.getText().toString().substring(1, length));
                    }
                    negative = false;
                }
                break;
        }
    }

    public void backspace(){
        int length = input.length();
        if(calculated){
            clearEntry();
        }
        else if(length > 0){
            if(input.getText().charAt(length-1) == '.'){decimal = false;}
            if(input.getText().charAt(length-1) == '-'){negative = false;}

            input.setText(input.getText().toString().substring(0, length - 1));
        }
    }

    public void hitung(char inputC){
        String temp;
        double numberedInput;

        if(input.length()==0){
            //mencegah operator dengan input kosong
            if(operator == ' '){
                return;
            }else{//mengganti operator
                temp = new Double(result).toString().replaceAll("\\.?0*$", "");
                entry.setHint(String.format("%s %s ", temp, inputC));
                operator = inputC;
                return;
            }
        }else{
            numberedInput = Double.parseDouble(input.getText().toString());
        }

        switch (operator) {
            case ('+'):
                result += numberedInput;
                break;
            case ('-'):
                result -= numberedInput;
                break;
            case ('x'):
                result *= numberedInput;
                break;
            case ('/'):
                result /= numberedInput;
                break;
            default:
                result = numberedInput;
                break;
        }
        //jangan diubah
        temp = new Double(result).toString().replaceAll("\\.?0*$", "");

        //mencegah input direset saat belum ada entry
        if(operator == ' ' && inputC =='='){
            return;
        }

        operator = inputC;
        if(operator=='='){
            input.setText(String.format("%s", temp));
            entry.setHint("");
            calculated = true;
        }else{
            entry.setHint(String.format("%s %s ", temp, inputC));
            input.setText("");
            calculated = decimal = negative = false;
        }
    }
    //clear input field
    public void clear(){
        if(calculated){
            clearEntry();
            return;
        }
        input.setText("");
        decimal = negative = false;
    }
    //reset
    public void clearEntry(){
        input.setText("");
        entry.setHint("");
        result = 0;
        operator = ' ';
        calculated = decimal = negative = false;
    }
}