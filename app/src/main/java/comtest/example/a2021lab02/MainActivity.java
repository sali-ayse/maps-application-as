package comtest.example.a2021lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    public void openMapsActivity(){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

    public void openMapsActivity2(){
        Intent intent = new Intent(this,MapsActivity2.class);
        startActivity(intent);
    }

    public void openMapsActivity3(){
        Intent intent = new Intent(this,MapsActivity3.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                openMapsActivity();
                break;
            case R.id.button2:
                openMapsActivity2();
                break;
            case R.id.button3:
                openMapsActivity3();
                break;

        }

    }
}