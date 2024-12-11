package com.example.meme_maker;

import android.content.SharedPreferences;
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

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//TODO: Meg kell csinálni dik : D

    private List<Item> templateList;

    private TemplateDatabase templatesDb;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        templateList = new ArrayList<>();

        templateList.add(new Item(R.drawable.temp_1, "Dawg"));
        templateList.add(new Item(R.drawable.temp_2, "Dog"));
        templateList.add(new Item(R.drawable.temp_3, "Allstars"));
        templateList.add(new Item(R.drawable.temp_4, "Kife Dog"));
        templateList.add(new Item(R.drawable.temp_5, "Face"));
        templateList.add(new Item(R.drawable.temp_6, "Gundog"));
        templateList.add(new Item(R.drawable.temp_7, "Finger"));
        templateList.add(new Item(R.drawable.temp_8, "Pingui"));
        templateList.add(new Item(R.drawable.temp_9, "Bunny"));
        templateList.add(new Item(R.drawable.temp_10, "Reload Cat"));
        templateList.add(new Item(R.drawable.temp_11, "Fist"));
        templateList.add(new Item(R.drawable.temp_12, "Gunda"));
        templateList.add(new Item(R.drawable.temp_13, "Sponge Pant"));
        templateList.add(new Item(R.drawable.temp_14, "Angry Bob"));
        templateList.add(new Item(R.drawable.temp_15, "Giga Bob"));
        templateList.add(new Item(R.drawable.temp_16, "Angrystein"));
        templateList.add(new Item(R.drawable.temp_17, "Shake"));

        //populateDatabase();

        loadFragment(new Main(), "Főoldal", false);
        changeTitle("main");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showCount(templatesDb.templatesDao().getTemplateCount());



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void showCount(Integer message) {
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
    private void populateDatabase() {

        templatesDb = TemplateDatabase.getDatabase(getApplicationContext());

        int count = templatesDb.templatesDao().getTemplateCount();

        if (count == 0) {
            // Feltöltjük a táblát előre megadott adatokkal

            for (int i = 0; i < templateList.size();i++) {

                LocalDate currentDate = LocalDate.now();
                java.util.Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                String title = String.valueOf(templateList.get(i));

                Templates template = new Templates(0, templateFileNameRemove(title), date, 1);
                templatesDb.templatesDao().insert(template);
            }
        }
    }

    /*private void populateDatabase() {

        templatesDb = TemplateDatabase.getDatabase(getApplicationContext());

        int count = templatesDb.templatesDao().getTemplateCount();

        if (count == 0) {
            // Feltöltjük a táblát előre megadott adatokkal

            for (int i = 0; i < templateList.size();i++) {

                LocalDate currentDate = LocalDate.now();
                java.util.Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                String title = String.valueOf(templateList.get(i));

                Templates template = new Templates(0, templateFileNameRemove(title), date, 1);
                templatesDb.templatesDao().insert(template);
            }
        }
    }*/

    private String templateFileNameRemove(String file){
        String originalString = file;
        int numberOfCharactersToRemove = 5; // Példa: eltávolítani az első 5 karaktert

        if (originalString.length() > numberOfCharactersToRemove) {
            String result = originalString.substring(numberOfCharactersToRemove);
            return result;
        }
        return originalString;
    }

}