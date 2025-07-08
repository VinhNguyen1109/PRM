package com.example.btvn_recyclerview;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    Toolbar toolbar;

    List<String> cartItems;

    private static final String CHANNEL_ID = "cart_channel";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onBinding();
        onAction();
        fakeCartData();
        checkCartAndNotify();
    }

    private void onBinding() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tabLayout = findViewById(R.id.tabLayout);
    }

    private void onAction() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 2) {
                    ArrayList<Category> categoryList = new ArrayList<>();
                    categoryList.add(new Category(R.drawable.pharmacy, "pharmacy"));
                    categoryList.add(new Category(R.drawable.registry, "registry"));
                    categoryList.add(new Category(R.drawable.cartwheel, "cartwheel"));
                    categoryList.add(new Category(R.drawable.clothing, "clothing"));
                    categoryList.add(new Category(R.drawable.shoes, "shoes"));
                    categoryList.add(new Category(R.drawable.accessories, "accessories"));
                    categoryList.add(new Category(R.drawable.baby, "baby"));
                    categoryList.add(new Category(R.drawable.home, "home"));
                    categoryList.add(new Category(R.drawable.patio_and_garden, "patio"));

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("categoryList", categoryList);

                    ListProductFragment fragment = new ListProductFragment();
                    fragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new EmptyFragment())
                            .commit();                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void fakeCartData() {
        cartItems = new ArrayList<>();
        cartItems.add("9 điểm PE");
        cartItems.add("Chúc thầy Khang nhiều sức khỏe, thành công trong cuộc sống ạ!");
    }

    private void checkCartAndNotify() {
        if (cartItems != null && !cartItems.isEmpty()) {
            createNotificationChannel();

            Intent intent = new Intent(this, CartActivity.class);
            intent.putStringArrayListExtra("cartItems", new ArrayList<>(cartItems));

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_cart)
                    .setContentTitle("Giỏ hàng của bạn")
                    .setContentText("Bạn có sản phẩm đang chờ trong giỏ hàng")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Cart Channel";
            String description = "Thông báo giỏ hàng";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            PopupMenu popupMenu = new PopupMenu(this, toolbar);
            popupMenu.getMenuInflater().inflate(R.menu.menu_popup_list, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.popup_search) {
                    Toast.makeText(this, "Bạn đã chọn Tìm kiếm (popup)", Toast.LENGTH_SHORT).show();
                } else if (menuItem.getItemId() == R.id.popup_cart) {
                    Intent intent = new Intent(this, CartActivity.class);
                    intent.putStringArrayListExtra("cartItems", new ArrayList<>(cartItems));
                    startActivity(intent);
                }
                return true;
            });

            popupMenu.show();
            return true;
        } else if (id == R.id.action_search) {
            Toast.makeText(this, "Bạn đã chọn Tìm kiếm (top bar)", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            intent.putStringArrayListExtra("cartItems", new ArrayList<>(cartItems));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
