package com.example.chatwithme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chatwithme.Adepter.FragmentAdapter;
import com.example.chatwithme.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

         mAuth = FirebaseAuth.getInstance();

         setSupportActionBar(binding.Toolbar);

         binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
         binding.tabLayout.setupWithViewPager(binding.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId==R.id.setting){
            Toast.makeText(this, "Enter setting", Toast.LENGTH_SHORT).show();
        } else if (itemId==R.id.groupChat) {
            Toast.makeText(this, "Enter Group chat", Toast.LENGTH_SHORT).show();
        } else if (itemId==R.id.logout) {
            mAuth.signOut();
            Toast.makeText(this, " Logout", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,Sign_In.class);
            startActivity(intent);

        }
        else{
            Toast.makeText(this, "Enter back button", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}