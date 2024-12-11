package com.example.meme_maker;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

//TODO: Meg kell csinálni dik : D 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadFragment(new Main(), "Főoldal", false);
        changeTitle("main");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void loadFragmentAndAddToBackStack(Fragment fragment, String tag) {
        loadFragment(fragment, tag, true);
    }

    private void loadFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                loadFragmentAndAddToBackStack(new Main(), "Főoldal");
                changeTitle("main");
                return true;
            case R.id.btn_creatememe:
                loadFragmentAndAddToBackStack(new CreateMemeFragment(), "Create Meme");
                changeTitle("create");
                return true;
            case R.id.btn_memetemplates:
                loadFragmentAndAddToBackStack(new TemplateFragment(), "Templates");
                changeTitle("template");
                return true;
            case R.id.btn_gallery:
                loadFragmentAndAddToBackStack(new GalleryFragment(), "Gallery");
                changeTitle("gallery");
                return true;
        }
        return true;
    }

    public boolean changeTitle(String title){
        switch (title) {
            case "main":
                setTitle("Főoldal");
                return true;
            case "create":
                setTitle("Create Meme");
                return true;
            case "template":
                setTitle("Templates");
                return true;
            case "gallery":
                setTitle("Gallery");
                return true;
            case "edit":
                setTitle("Edit");
                return true;
        }
        return true;
    }


}