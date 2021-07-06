package sg.edu.rp.c346.id20006949.c346assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {
TextView intro;
Button website;
Button settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intro = findViewById(R.id.intro);
        website = findViewById(R.id.website);
        settings = findViewById(R.id.settings);
        website.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url)));
                startActivity(intent);
        }
    });
        settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                startActivity(intent);
            }
        });


    }

}