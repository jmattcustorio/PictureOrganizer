package com.example.admin.picturemanager;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.io.File.separator;


public class MainActivity extends AppCompatActivity {

    Camera camera;
    FrameLayout frameLayout;
    ShowCamera showCamera;

    private String folderName = "SchoolPictures";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);

        Intent intent = getIntent();
        this.folderName = intent.getStringExtra("FOLDER_NAME");

//        Toast.makeText(this, this.folderName, Toast.LENGTH_SHORT).show();
        //open camera
        camera = Camera.open();
        showCamera = new ShowCamera(this, camera);
        frameLayout.addView(showCamera);
    }

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera)
        {
            File picture_file = getOutputMediaFile();

            if (picture_file == null)
            {
                return;
            }else{
                try {
                    FileOutputStream fos = new FileOutputStream(picture_file);
                    fos.write(data);
                    fos.close();

                    camera.startPreview();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    };

    private File getOutputMediaFile()
    {
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED))
        {
            return null;
        }else{
            File folder_gui = new File(Environment.getExternalStorageDirectory()+separator + "School Pictures" + separator + this.folderName);
            if(!folder_gui.exists())
            {
                folder_gui.mkdirs();
            }

            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            String fileName = "picture_" + ts + ".jpg";
            File outputFile = new File (folder_gui, fileName);
            return outputFile;
        }
    }

    public void captureImage(View v)
    {
        if(camera != null)
        {
            camera.takePicture(null,null,mPictureCallback);
        }
    }

}
