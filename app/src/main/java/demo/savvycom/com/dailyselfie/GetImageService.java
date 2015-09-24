package demo.savvycom.com.dailyselfie;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

import demo.savvycom.com.dailyselfie.model.Image;

/**
 * Created by Bui The Nam on 7/22/2015.
 */
public class GetImageService {

    public static ArrayList<Image> getImageList() {
        ArrayList<Image> arrayListImage = new ArrayList<>();
        File folderImage = new File(Environment.getExternalStorageDirectory(), "MyImages");
        File[] files = folderImage.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            arrayListImage.add(new Image(file.lastModified(), file.getName(), Uri.fromFile(file)));
        }
        return arrayListImage;
    }
}
