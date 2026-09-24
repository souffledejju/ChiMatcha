package com.example.uxproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MenuActivity extends AppCompatActivity {

    private LinearLayout layoutDropdown;
    private ImageButton btnHamburger;
    private RelativeLayout rootLayout;
    private TextView menuHome, menuBranch, menuLogout;
    private CardView cardMatchaLatte, cardCeremonialUsucha, cardStrawberryMatcha, cardMatchaTiramisu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        rootLayout = findViewById(R.id.root_menu_layout);
        layoutDropdown = findViewById(R.id.layout_dropdown_menu_menu_page);
        btnHamburger = findViewById(R.id.btn_menu_hamburger_menu);
        menuHome = findViewById(R.id.menu_home_from_menu);
        menuBranch = findViewById(R.id.menu_branch_from_menu);
        menuLogout = findViewById(R.id.menu_logout_from_menu);
        cardMatchaLatte = findViewById(R.id.menu_item_matcha_latte);
        cardCeremonialUsucha = findViewById(R.id.menu_item_ceremonial_usucha);
        cardStrawberryMatcha = findViewById(R.id.menu_item_stawberry_matcha);
        cardMatchaTiramisu = findViewById(R.id.menu_item_matcha_tiramisu);

        btnHamburger.setOnClickListener(v -> layoutDropdown.setVisibility(layoutDropdown.getVisibility() == View.GONE ? View.VISIBLE : View.GONE));
        rootLayout.setOnClickListener(v -> { if (layoutDropdown.getVisibility() == View.VISIBLE) layoutDropdown.setVisibility(View.GONE); });
        menuHome.setOnClickListener(v -> finish());
        menuBranch.setOnClickListener(v -> { startActivity(new Intent(MenuActivity.this, BranchActivity.class)); layoutDropdown.setVisibility(View.GONE); });
        menuLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent); finish();
        });

        cardMatchaLatte.setOnClickListener(v -> openDetail("DRINK", "Iced Matcha Latte", "Layered. Cold-brewed. Quietly perfect.", "Stone-milled ceremonial grade matcha whisked over ice, layered with chilled organic milk. A clean, vegetal sweetness with a lingering umami finish.", "Rp 48.000", R.drawable.card_product_1));

        cardCeremonialUsucha.setOnClickListener(v -> openDetail("DRINK", "Ceremonial Usucha", "The original ritual.", "First-harvest tencha from Uji, whisked traditionally with a chasen until silken. Served in a hand-thrown bowl. Pure, unsweetened, deeply contemplative.", "Rp 69.000", R.drawable.card_product_2));

        cardStrawberryMatcha.setOnClickListener(v -> openDetail("DRINK", "Strawberry Matcha", "A brief, blushing romance.", "Crushed seasonal strawberries layered beneath cold matcha and silk milk. A quiet conversation between bright fruit and earthy tea.", "Rp 52.000", R.drawable.stawberry_matcha));

        cardMatchaTiramisu.setOnClickListener(v -> openDetail("DESSERT", "Matcha Tiramisu", "Italian classic, kyoto soul.", "House-made mascarpone layered with matcha-soaked ladyfingers, finished with a snow of premium matcha powder and 24K edible gold dust.", "Rp 55.000", R.drawable.matcha_tiramisu));
    }

    private void openDetail(String category, String title, String subtitle, String desc, String price, int imageId) {
        Intent intent = new Intent(MenuActivity.this, OrderDetailActivity.class);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("TITLE", title);
        intent.putExtra("SUBTITLE", subtitle);
        intent.putExtra("DESC", desc);
        intent.putExtra("PRICE", price);
        intent.putExtra("IMAGE", imageId);
        startActivity(intent);
    }
}