package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button sch_job, cancel_job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sch_job = findViewById(R.id.sch_job);
        cancel_job = findViewById(R.id.cancel_job);

        sch_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComponentName componentName = new ComponentName(MainActivity.this, JobServiceEg.class);
                JobInfo info = new JobInfo.Builder(1001, componentName)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                        .setPersisted(true)
                        .setPeriodic(15 * 60 * 1000)
                        .build();

                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                int resultCode = scheduler.schedule(info);
                if (resultCode == JobScheduler.RESULT_SUCCESS) {
                    Log.d("JobService", "Job scheduled");
                } else {
                    Log.d("JobService", "Job scheduling failed");
                }
            }
        });

        cancel_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                scheduler.cancel(1001);
                Log.d("JobService", "Job cancelled");
            }
        });
    }
}