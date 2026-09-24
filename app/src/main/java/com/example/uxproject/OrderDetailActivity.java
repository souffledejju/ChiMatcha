package com.example.uxproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        String category = getIntent().getStringExtra("CATEGORY");
        String title = getIntent().getStringExtra("TITLE");
        String subtitle = getIntent().getStringExtra("SUBTITLE");
        String desc = getIntent().getStringExtra("DESC");
        String price = getIntent().getStringExtra("PRICE");
        int imageId = getIntent().getIntExtra("IMAGE", R.drawable.card_product_1);

        TextView tvCategory = findViewById(R.id.tv_item_category);
        TextView tvTitle = findViewById(R.id.tv_item_title);
        TextView tvSubtitle = findViewById(R.id.tv_item_subtitle);
        TextView tvDesc = findViewById(R.id.tv_item_desc);
        TextView tvPrice = findViewById(R.id.tv_item_price);
        TextView tvTotalPrice = findViewById(R.id.tv_total_price);
        ImageView ivImage = findViewById(R.id.iv_product_image);
        LinearLayout layoutIceSweet = findViewById(R.id.layout_ice_sweet);

        EditText etQuantity = findViewById(R.id.et_quantity);

        tvCategory.setText(category);
        tvTitle.setText(title);
        tvSubtitle.setText(subtitle);
        tvDesc.setText(desc);
        tvPrice.setText(price);
        tvTotalPrice.setText(price);
        ivImage.setImageResource(imageId);

        if ("DESSERT".equals(category)) {
            layoutIceSweet.setVisibility(View.GONE);
        } else {
            layoutIceSweet.setVisibility(View.VISIBLE);
        }

        ImageButton btnBack = findViewById(R.id.btn_back_to_menu);
        btnBack.setOnClickListener(v -> finish());

        Spinner spinnerIce = findViewById(R.id.spinner_ice_level);
        Spinner spinnerSweet = findViewById(R.id.spinner_sweet_level);

        String[] iceLevels = {"--Ice Level--", "No Ice", "Less Ice", "Normal", "Extra Ice"};
        ArrayAdapter<String> adapterIce = new ArrayAdapter<>(this, R.layout.item_spinner_text, iceLevels);
        spinnerIce.setAdapter(adapterIce);

        String[] sweetLevels = {"--Sweet Level--", "Unsweetened", "Less Sweet", "Normal", "Extra Sweet"};
        ArrayAdapter<String> adapterSweet = new ArrayAdapter<>(this, R.layout.item_spinner_text, sweetLevels);
        spinnerSweet.setAdapter(adapterSweet);

        Button btnPlaceOrder = findViewById(R.id.btn_place_order);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityInput = etQuantity.getText().toString().trim();

                if (quantityInput.isEmpty() || quantityInput.equals("0")) {
                    new MaterialAlertDialogBuilder(OrderDetailActivity.this, R.style.Theme_UXProject)
                            .setTitle("Order Failed")
                            .setMessage("Quantity must be filled and cannot be 0.")
                            .setPositiveButton("OK", null)
                            .show();
                } else {

                    new MaterialAlertDialogBuilder(OrderDetailActivity.this)
                            .setTitle("Order Confirmed!")
                            .setMessage("A confirmation email has been sent to your email.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .show();
                }
            }
        });
    }
}