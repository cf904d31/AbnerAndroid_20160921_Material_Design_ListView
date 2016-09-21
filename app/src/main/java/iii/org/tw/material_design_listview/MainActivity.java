package iii.org.tw.material_design_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listView1(View v) {
        Intent it = new Intent(this,List1Activity.class);
        startActivity(it);
    }

    public void listView2(View v) {
        Intent it = new Intent(this,List2Activity.class);
        startActivity(it);
    }

}
