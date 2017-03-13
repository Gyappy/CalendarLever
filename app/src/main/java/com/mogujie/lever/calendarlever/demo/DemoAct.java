package com.mogujie.lever.calendarlever.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mogujie.lever.calendarlever.core.ICallBack;
import com.mogujie.lever.calendarlever.core.other.CalendarBuilder;
import com.mogujie.lever.calendarlever.core.CalendarOperator;
import com.mogujie.lever.calendarlever.R;

/**
 * Created by chenhuigu on 17/2/25.
 */

public class DemoAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actlayout);
        Button insert1 = (Button) findViewById(R.id.insert1);
        final CalendarBuilder event = new CalendarBuilder.Builder(DemoAct.this, "33", 1488123906 * 1000L, 1488123906 * 1000L).description("欢迎提出意见多多交流,跪求star").title("我叫股晨晖").build();
        insert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("^^^^^^^^^", "------before1------");
                CalendarOperator.getBlockEngine().insert(event, new ICallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DemoAct.this, "操作成功", Toast.LENGTH_SHORT).show();
                        Log.e("^^^^^^^^^", "------excute1------");
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(DemoAct.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("^^^^^^^^^", "------after1------");
            }
        });

        Button insert2 = (Button) findViewById(R.id.insert2);
        insert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("^^^^^^^^^", "------before2------");
                CalendarOperator.getNonBlockEngine().insert(event, new ICallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DemoAct.this, "操作成功", Toast.LENGTH_SHORT).show();
                        Log.e("^^^^^^^^^", "------excute2------");
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(DemoAct.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("^^^^^^^^^", "------after2------");
            }
        });

        Button delete1 = (Button) findViewById(R.id.delete1);
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("^^^^^^^^^", "------before1------");
                CalendarOperator.getBlockEngine().delete(event, new ICallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DemoAct.this, "操作成功", Toast.LENGTH_SHORT).show();
                        Log.e("^^^^^^^^^", "------excute1------");
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(DemoAct.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("^^^^^^^^^", "------after1------");
            }
        });

        Button delete2 = (Button) findViewById(R.id.delete2);
        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("^^^^^^^^^", "------before2------");
                CalendarOperator.getNonBlockEngine().delete(event, new ICallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DemoAct.this, "操作成功", Toast.LENGTH_SHORT).show();
                        Log.e("^^^^^^^^^", "------excute2------");
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(DemoAct.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("^^^^^^^^^", "------after2------");
            }
        });

        Button query1 = (Button) findViewById(R.id.query1);
        query1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("^^^^^^^^^", "------before1------");
                CalendarOperator.getBlockEngine().query(event, new ICallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DemoAct.this, "操作成功", Toast.LENGTH_SHORT).show();
                        Log.e("^^^^^^^^^", "------excute1------");
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(DemoAct.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("^^^^^^^^^", "------after1------");
            }
        });

        Button query2 = (Button) findViewById(R.id.query2);
        query2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("^^^^^^^^^", "------before2------");
                CalendarOperator.getNonBlockEngine().query(event, new ICallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DemoAct.this, "操作成功", Toast.LENGTH_SHORT).show();
                        Log.e("^^^^^^^^^", "------excute2------");
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(DemoAct.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("^^^^^^^^^", "------after2------");
            }
        });

        Button reverse1 = (Button) findViewById(R.id.reverse1);
        reverse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("^^^^^^^^^", "------before1------");
                CalendarOperator.getBlockEngine().reverse(event, new ICallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DemoAct.this, "操作成功", Toast.LENGTH_SHORT).show();
                        Log.e("^^^^^^^^^", "------excute1------");
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(DemoAct.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("^^^^^^^^^", "------after1------");
            }
        });

        Button reverse2 = (Button) findViewById(R.id.reverse2);
        reverse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("^^^^^^^^^", "------before2------");
                CalendarOperator.getNonBlockEngine().reverse(event, new ICallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DemoAct.this, "操作成功", Toast.LENGTH_SHORT).show();
                        Log.e("^^^^^^^^^", "------excute2------");
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(DemoAct.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("^^^^^^^^^", "------after2------");
            }
        });
    }
}
