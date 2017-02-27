package com.mogujie.lever.calendarlever.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mogujie.lever.calendarlever.core.CalendarBuilder;
import com.mogujie.lever.calendarlever.core.CalendarOperator;
import com.mogujie.lever.calendarlever.impl.NonBlockCalendar;
import com.mogujie.lever.calendarlever.R;
import com.mogujie.lever.calendarlever.ICallBack;

/**
 * Created by chenhuigu on 17/2/25.
 */

public class mainAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actlayout);
        Button b1 = (Button) findViewById(R.id.t1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("^^^^^^^^^", "------before------");
                CalendarOperator.getInstance().getNonBlockEngine().addEvent(new CalendarBuilder.Builder(mainAct.this, "33", 1488123906 * 1000L, 1488123906 * 1000L).description("试试a 描述").title("plus版本").build(), new ICallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(mainAct.this, "操作成功", Toast.LENGTH_SHORT).show();
                        Log.e("^^^^^^^^^", "------excute------");
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(mainAct.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("^^^^^^^^^", "------after------");
            }
        });
    }
}
