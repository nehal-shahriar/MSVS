package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class user extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button osmanycaptbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //tabbed activity
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new votevents(),"event");
        viewPagerAdapter.addFragment(new result(),"result");
        viewPagerAdapter.addFragment(new candidate(),"candidate");
        viewPagerAdapter.addFragment(new userprofile(),"profile");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //tabLayout.getTabAt(0).setIcon(R.drawable.event);
        //tabLayout.getTabAt(1).setIcon(R.drawable.result);
        //tabLayout.getTabAt(2).setIcon(R.drawable.profile);
        tabLayout.getTabAt(0).setText("events");
        tabLayout.getTabAt(1).setText("result");
        tabLayout.getTabAt(2).setText("candidate");
        tabLayout.getTabAt(3).setText("profile");
        //osmanycaptbtn=findViewById(R.id.osmanycaptbtn);
        /*if(tabLayout.getSelectedTabPosition()==0){
        osmanycaptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(user.this,MainActivity.class);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    osmanycaptbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(user.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
        }


    }

