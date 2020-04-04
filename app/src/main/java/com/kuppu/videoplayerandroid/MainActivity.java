package com.kuppu.videoplayerandroid;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Declare variables
private VideoView videoView;
private Button expand_btn;
private FrameLayout frameLayout;
private RecyclerView recyclerView;
// video paths
String videoPathone,videoPath,videoPathtwo,videoPaththree;

private List<Video> videoList=new ArrayList<>();
    private VideoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.video_view);
        expand_btn=(Button) findViewById(R.id.lanscape_btn);
        frameLayout=findViewById(R.id.framelayout);
        recyclerView=findViewById(R.id.recycler_video);

         videoPath = "android.resource://" + getPackageName() + "/" + R.raw.atoms;
        videoPathone = "android.resource://" + getPackageName() + "/" + R.raw.bigbunny;
        videoPathtwo = "android.resource://" + getPackageName() + "/" + R.raw.kaleidoscope;
        videoPaththree = "android.resource://" + getPackageName() + "/" + R.raw.earth;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
//Initialize mediacontroller
        MediaController mediaController = new MediaController(this);

        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        mAdapter = new VideoListAdapter(videoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

//add data to list
        prepareVideoData();


        expand_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int orientation = MainActivity.this.getResources().getConfiguration().orientation;
                if(orientation== Configuration.ORIENTATION_PORTRAIT){
                    LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);


frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                }else if(orientation==Configuration.ORIENTATION_LANDSCAPE){
                    LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);

                    frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400));

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }

            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Video videos = videoList.get(position);
                Toast.makeText(getApplicationContext(), videos.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                if(videos.getUripath().equals("video")){
                    Uri uri = Uri.parse(videoPath);
                    videoView.setVideoURI(uri);
                    videoView.start();
                }else if(videos.getUripath().equals("video1")){
                    Uri uri = Uri.parse(videoPathone);
                    videoView.setVideoURI(uri);
                    videoView.start();
                }else if(videos.getUripath().equals("video2")){
                    Uri uri = Uri.parse(videoPathtwo);
                    videoView.setVideoURI(uri);
                    videoView.start();
                }else if(videos.getUripath().equals("video3")){
                    Uri uri = Uri.parse(videoPaththree);
                    videoView.setVideoURI(uri);
                    videoView.start();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    private void prepareVideoData() {

        Video video1=new Video();
        video1.setTitle("The Big bunny");
        video1.setUripath("video1");
        videoList.add(video1);

        Video video2=new Video();
        video2.setTitle("The Atom");
        video2.setUripath("video");
        videoList.add(video2);

        Video video3=new Video();
        video3.setTitle("The kaleidoscope ");
        video3.setUripath("video2");
        videoList.add(video3);

        Video video4=new Video();
        video4.setTitle("The Earth");
        video4.setUripath("video3");
        videoList.add(video4);

mAdapter.notifyDataSetChanged();

    }




    }


