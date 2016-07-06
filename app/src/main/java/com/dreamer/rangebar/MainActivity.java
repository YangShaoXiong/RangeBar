package com.dreamer.rangebar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dreamer.library.RangeBar;

public class MainActivity extends AppCompatActivity {

    private RangeBar mRangeBar;
    private TextView mTv;
    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv = (TextView) findViewById(R.id.tv);
        mBtn = (Button) findViewById(R.id.btn);

        mRangeBar = (RangeBar) findViewById(R.id.rangeBar);
        mRangeBar.setOnRangeBarListener(new RangeBar.OnRangeBarListener() {
            @Override
            public void onClick(int position) {
                mTv.setText("点击位置=" + (position + 1));
                // do something
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRangeBar.changeRangeNum(1);
            }
        });
    }
}
