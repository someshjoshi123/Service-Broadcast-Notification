package com.example.notificationeg1;

import static com.example.notificationeg1.App.CHANNEL_ID_1;
import static com.example.notificationeg1.App.CHANNEL_ID_2;

import android.app.Notification;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    AppCompatEditText et_title, et_msg;
    AppCompatButton channel1, channel2;
    NotificationManagerCompat managerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        managerCompat = NotificationManagerCompat.from(this);
        
        forChannel1();
        forChannel2();
    }

    private void forChannel2() {
        channel2.setOnClickListener(view -> {
            String title = et_title.getText().toString();
            String message = et_msg.getText().toString();

            Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_2)
                    .setSmallIcon(R.drawable.ic_two)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();

            managerCompat.notify(2, notification);
        });
    }

    private void forChannel1() {
        channel1.setOnClickListener(view -> {
            String title = et_title.getText().toString();
            String message = et_msg.getText().toString();

            Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_1)
                    .setSmallIcon(R.drawable.ic_one)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();

            managerCompat.notify(1, notification);
        });
    }

    private void initUI() {
        et_title = findViewById(R.id.et_title);
        et_msg = findViewById(R.id.et_msg);
        channel1 = findViewById(R.id.channel1);
        channel2 = findViewById(R.id.channel2);
    }
    
}