package com.example.notificationeg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import static com.example.notificationeg2.App.CHANNEL_ID_1;
import static com.example.notificationeg2.App.CHANNEL_ID_2;

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
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine("This is line 1")
                            .addLine("This is line 2")
                            .addLine("This is line 3")
                            .addLine("This is line 4")
                            .addLine("This is line 5")
                            .addLine("This is line 6")
                            .addLine("This is line 7")
                            .setBigContentTitle("Big Content Title")
                            .setSummaryText("Summary Text"))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();

            managerCompat.notify(2, notification);
        });
    }

    private void forChannel1() {
        channel1.setOnClickListener(view -> {
            String title = et_title.getText().toString();
            String message = et_msg.getText().toString();

            Intent activityIntent = new Intent(this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this,
                    0, activityIntent, 0);

            Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
            broadcastIntent.putExtra("toastMessage", message);
            PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                    0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.hisoka5);

            Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_1)
                    .setSmallIcon(R.drawable.ic_one)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setLargeIcon(largeIcon)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(getString(R.string.long_dummy_text))
                            .setBigContentTitle("Big Content Title")
                            .setSummaryText("Summary Text"))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setColor(Color.BLUE)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true)
                    .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
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