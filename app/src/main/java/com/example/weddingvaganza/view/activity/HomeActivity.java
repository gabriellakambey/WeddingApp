package com.example.weddingvaganza.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.ScheduleModel;
import com.example.weddingvaganza.view.fragment.FinalHomeFragment;
import com.example.weddingvaganza.view.fragment.FirstHomeFragment;
import com.example.weddingvaganza.view.fragment.FirstTodoListFragment;
import com.example.weddingvaganza.view.fragment.ProfileFragment;
import com.example.weddingvaganza.view.fragment.TodoListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView menu_bawah;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int userId = Prefs.getInt("user_id", 0);
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // BOTTOM NAVIGATION MENU
        menu_bawah = findViewById(R.id.menu_bawah);
        menu_bawah.setOnNavigationItemSelectedListener(this);
        
        // SET HOME AS MAIN FRAGMENT
        setHomeAsMain();
    }

    private void setHomeAsMain() {
        Call<List<ScheduleModel>> call = weddingService.getSchedule(userId);
        call.enqueue(new Callback<List<ScheduleModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleModel>> call, Response<List<ScheduleModel>> response) {
                List<ScheduleModel> scheduleModels = response.body();
                if (scheduleModels.size() == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FirstHomeFragment()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FinalHomeFragment()).commit();
                }
            }

            @Override
            public void onFailure(Call<List<ScheduleModel>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    Fragment fragment = null;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Call<List<ScheduleModel>> call = weddingService.getSchedule(userId);
                call.enqueue(new Callback<List<ScheduleModel>>() {
                    @Override
                    public void onResponse(Call<List<ScheduleModel>> call, Response<List<ScheduleModel>> response) {
                        List<ScheduleModel> scheduleModels = response.body();
                        if (scheduleModels.size()==0) {
                            fragment = new FirstHomeFragment();
                        } else {
                            fragment = new FinalHomeFragment();
                        }
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, fragment);
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onFailure(Call<List<ScheduleModel>> call, Throwable t) {

                        Toast.makeText(HomeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
                

            case R.id.todoList:
                Call<List<ScheduleModel>> call1 = weddingService.getSchedule(userId);
                call1.enqueue(new Callback<List<ScheduleModel>>() {
                    @Override
                    public void onResponse(Call<List<ScheduleModel>> call, Response<List<ScheduleModel>> response) {
                        List<ScheduleModel> scheduleModels = response.body();
                        if (scheduleModels.size()==0) {
                            fragment = new FirstTodoListFragment();
                        } else {
                            fragment = new TodoListFragment();
                        }
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, fragment);
                        fragmentTransaction.commit();

                    }

                    @Override
                    public void onFailure(Call<List<ScheduleModel>> call, Throwable t) {

                    }
                });
                break;


            case R.id.profile:
                fragment = new ProfileFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.commit();
                break;
        }
        return true;
    }
}