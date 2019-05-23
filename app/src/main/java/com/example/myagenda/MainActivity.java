package com.example.myagenda;

import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myagenda.databaseClasses.Agenda_Class;
import com.example.myagenda.databaseClasses.AppDataBase;

public class MainActivity extends AppCompatActivity {
    public static AppDataBase appDataBase;
    public static Agenda_Class task;
    public static BottomNavigationView bottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("My Agenda");
        appDataBase = Room.databaseBuilder(this, AppDataBase.class, "Agenda").allowMainThreadQueries().build();
        task = new Agenda_Class();



        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        // Lorsque le main est appelé en met le fragment Agenda par défaut
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Agenda_Fragment()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_agenda :
                            selectedFragment = new Agenda_Fragment();
                            break;

                        case R.id.nav_event:
                            selectedFragment = new Event_Fragment();
                            break;

                        case R.id.nav_add :
                            selectedFragment = new Add_Fragment();
                            break;

                        case R.id.nav_account :
                            selectedFragment = new Account_Fragment();
                            break;

                            default:
                                selectedFragment = new Agenda_Fragment();
                                break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
