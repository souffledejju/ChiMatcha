package com.example.uxproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BranchActivity extends AppCompatActivity {

    private LinearLayout layoutDropdown;
    private ImageButton btnHamburger;
    private RelativeLayout rootLayout;
    private TextView menuHome, menuItems, menuLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        rootLayout = findViewById(R.id.root_branch_layout);
        layoutDropdown = findViewById(R.id.layout_dropdown_menu_branch);
        btnHamburger = findViewById(R.id.btn_menu_hamburger_branch);

        menuHome = findViewById(R.id.menu_home_from_branch);
        menuItems = findViewById(R.id.menu_items_from_branch);
        menuLogout = findViewById(R.id.menu_logout_from_branch);

        btnHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDropdown.getVisibility() == View.GONE) {
                    layoutDropdown.setVisibility(View.VISIBLE);
                } else {
                    layoutDropdown.setVisibility(View.GONE);
                }
            }
        });

        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDropdown.getVisibility() == View.VISIBLE) {
                    layoutDropdown.setVisibility(View.GONE);
                }
            }
        });

        menuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        menuItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        menuLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}