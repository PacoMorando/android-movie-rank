package sas.movie_rank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import sas.movie_rank.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding.setMain(this);
        setContentView(this.binding.getRoot());
        //setContentView(R.layout.activity_main);
        this.binding.title.setText(getString(R.string.api_key));

        this.getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.home_container, HomeFragment.class, null)
                .commit();
    }
}