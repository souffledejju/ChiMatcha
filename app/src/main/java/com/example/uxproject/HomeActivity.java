package com.example.uxproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout layoutDropdownMenu;
    private ImageButton btnMenuHamburger, btnPrev, btnNext;
    private ImageView ivCarousel, ivDot1, ivDot2, ivDot3;
    private TextView tvWelcome, tvViewAll, menuItems, menuBranch, menuLogout;
    private CardView cardProduct1, cardProduct2;
    private RelativeLayout rootLayout;

    private int[] carouselImages = {
            R.drawable.iklan_matcha3,
            R.drawable.iklan_matcha2,
            R.drawable.iklan_matcha1
    };
    private int currentImageIndex = 0;
    private Handler carouselHandler = new Handler();
    private Runnable carouselRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rootLayout = findViewById(R.id.root_home_layout);
        layoutDropdownMenu = findViewById(R.id.layout_dropdown_menu);
        btnMenuHamburger = findViewById(R.id.btn_menu_hamburger);
        tvWelcome = findViewById(R.id.tv_welcome_username);
        ivCarousel = findViewById(R.id.iv_carousel);
        btnPrev = findViewById(R.id.btn_carousel_prev);
        btnNext = findViewById(R.id.btn_carousel_next);
        tvViewAll = findViewById(R.id.tv_view_all);
        menuItems = findViewById(R.id.menu_items);
        menuBranch = findViewById(R.id.menu_branch);
        menuLogout = findViewById(R.id.menu_logout);
        cardProduct1 = findViewById(R.id.card_product_1);
        cardProduct2 = findViewById(R.id.card_product_2);

        ivDot1 = findViewById(R.id.iv_dot_1);
        ivDot2 = findViewById(R.id.iv_dot_2);
        ivDot3 = findViewById(R.id.iv_dot_3);

        tvWelcome.setText(GlobalData.username);

        btnMenuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDropdownMenu.getVisibility() == View.GONE) {
                    layoutDropdownMenu.setVisibility(View.VISIBLE);
                } else {
                    layoutDropdownMenu.setVisibility(View.GONE);
                }
            }
        });

        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDropdownMenu.getVisibility() == View.VISIBLE) {
                    layoutDropdownMenu.setVisibility(View.GONE);
                }
            }
        });

        carouselRunnable = new Runnable() {
            @Override
            public void run() {
                currentImageIndex = (currentImageIndex + 1) % carouselImages.length;
                updateCarouselUI();
                carouselHandler.postDelayed(this, 3000);
            }
        };
        carouselHandler.postDelayed(carouselRunnable, 3000);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex + 1) % carouselImages.length;
                updateCarouselUI();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex - 1 + carouselImages.length) % carouselImages.length;
                updateCarouselUI();
            }
        });

        menuLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        menuBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BranchActivity.class);
                startActivity(intent);
                layoutDropdownMenu.setVisibility(View.GONE);
            }
        });

        View.OnClickListener toItemList = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(intent);

                layoutDropdownMenu.setVisibility(View.GONE);
            }
        };

        tvViewAll.setOnClickListener(toItemList);
        menuItems.setOnClickListener(toItemList);

        cardProduct2.setOnClickListener(v -> openDetail(
                "DRINK",
                "Iced Matcha Latte",
                "Layered. Cold-brewed. Quietly perfect.",
                "Stone-milled ceremonial grade matcha whisked over ice, layered with chilled organic milk. A clean, vegetal sweetness with a lingering umami finish.",
                "Rp 48.000",
                R.drawable.card_product_1
        ));

        cardProduct1.setOnClickListener(v -> openDetail(
                "DRINK",
                "Ceremonial Usucha",
                "The original ritual.",
                "First-harvest tencha from Uji, whisked traditionally with a chasen until silken. Served in a hand-thrown bowl. Pure, unsweetened, deeply contemplative.",
                "Rp 69.000",
                R.drawable.card_product_2
        ));
    }

    private void updateCarouselUI() {
        ivCarousel.setImageResource(carouselImages[currentImageIndex]);

        ivDot1.setImageResource(currentImageIndex == 0 ? R.drawable.carousel_dot_active : R.drawable.carousel_dot_inactive);
        ivDot2.setImageResource(currentImageIndex == 1 ? R.drawable.carousel_dot_active : R.drawable.carousel_dot_inactive);
        ivDot3.setImageResource(currentImageIndex == 2 ? R.drawable.carousel_dot_active : R.drawable.carousel_dot_inactive);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        carouselHandler.removeCallbacks(carouselRunnable);
    }

    private void openDetail(String category, String title, String subtitle, String desc, String price, int imageId) {
        Intent intent = new Intent(HomeActivity.this, OrderDetailActivity.class);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("TITLE", title);
        intent.putExtra("SUBTITLE", subtitle);
        intent.putExtra("DESC", desc);
        intent.putExtra("PRICE", price);
        intent.putExtra("IMAGE", imageId);
        startActivity(intent);
    }
}