package com.example.masterbasicmaths;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    TextView q,plus,minus,multiply,div,s1,s2,s3,four,two,nine,four2;
    float xCoOrdinate,yCoOrdinate;
    Button b1;

    String c1,c2,c3,c4,S1,S2,S3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        q = findViewById( R.id.q );

        plus = findViewById( R.id.plus );
        minus = findViewById( R.id.minus );
        multiply = findViewById( R.id.multiply );
        div = findViewById( R.id.divide );
        s1 = findViewById( R.id.s1 );
        s2 = findViewById( R.id.s2 );
        s3 = findViewById( R.id.s3 );
        two = findViewById( R.id.two );
        four = findViewById( R.id.four );
        nine = findViewById( R.id.nine );
        four2 = findViewById( R.id.four2 );
        b1 = findViewById( R.id.b1 );

        two.setOnTouchListener( new MyTouchListener() );
        two.setOnDragListener( new MyDragListener() );
        four.setOnTouchListener( new MyTouchListener() );
        four.setOnDragListener( new MyDragListener() );
        nine.setOnTouchListener( new MyTouchListener() );
        nine.setOnDragListener( new MyDragListener() );
        four2.setOnTouchListener( new MyTouchListener() );
        four2.setOnDragListener( new MyDragListener() );



        plus.setOnTouchListener( new MyTouchListener() );
        plus.setOnDragListener( new MyDragListener() );
        minus.setOnTouchListener( new MyTouchListener() );
        minus.setOnDragListener( new MyDragListener() );
        multiply.setOnTouchListener( new MyTouchListener() );
        multiply.setOnDragListener( new MyDragListener() );
        div.setOnTouchListener( new MyTouchListener() );
        div.setOnDragListener( new MyDragListener() );

        s1.setOnTouchListener( new MyTouchListener() );
        s1.setOnDragListener( new MyDragListener() );
        s2.setOnTouchListener( new MyTouchListener() );
        s2.setOnDragListener( new MyDragListener() );
        s3.setOnTouchListener( new MyTouchListener() );
        s3.setOnDragListener( new MyDragListener() );



        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                calculate( c1, S1, c2, S2, c3, S3, c4 );
            }
        } );

      /*  Log.d("vlaue of s1",S1);

        if(c1!=null && c2!=null && c3 != null && c4 != null && S1!=null && S2 != null && S3!= null) {
            calculate( c1, S1, c2, S2, c3, S3, c4 );
        } */



    }

    private void calculate(String c1, String S1, String c2, String S2, String c3, String S3, String c4) {
        c1 =  four.getText().toString();
        S1 =  s1.getText().toString() ;
        c2 =  two.getText().toString();
        S2 =  s2.getText().toString();
        c3 =  nine.getText().toString();
        S3 =  s3.getText().toString();
        c4 =  four2.getText().toString();

        if (!S1.equals( "_" )  && !S2.equals( "_" )  && !S3.equals( "_")) {
            String sum1 = c1 + S1 + c2 + S2 + c3 + S3 + c4;

            Expression e = new ExpressionBuilder(sum1).build();

            int sum = (int) e.evaluate();

            if (sum == 10) {
                q.setText( "10" );
                Toast.makeText( this, "Congrats correct ans "+sum, Toast.LENGTH_LONG ).show();
            } else {
                Toast.makeText( this, "Sorry Wrong answer "+sum+" try again  ", Toast.LENGTH_LONG ).show();
            }


        }else{
            Toast.makeText( this, "please arrange the numbers and symbols properly , try again  ", Toast.LENGTH_LONG ).show();
        }
    }


    private class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                ClipData data = ClipData.newPlainText( "","" );
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag( data,shadowBuilder,v,0 );

                return true;
            }else{
                return false;
            }

        }
    }

    private class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();

            View dragView = (View) event.getLocalState();
            switch(event.getAction()){
                case DragEvent.ACTION_DROP:
                   View view = (View) event.getLocalState();

                   TextView dropTarget = (TextView) v;

                   TextView dropped = (TextView) view;

                   String droppedTarget = dropTarget.getText().toString();
                   dropTarget.setText( dropped.getText().toString() );
                   dropped.setText( droppedTarget );
                    break;
            }
            return true;
        }
    }
}