package com.vladglush.lab5;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.vladglush.lab5.fragments.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frameContainer)
    protected FrameLayout frameContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setFragment(new ListFragment());
    }

    public void setFragment(final Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).addToBackStack(null).commit();
    }
}
