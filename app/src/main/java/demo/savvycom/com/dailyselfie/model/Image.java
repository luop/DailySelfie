package demo.savvycom.com.dailyselfie.model;

import android.net.Uri;

/**
 * Created by Bui The Nam on 7/22/2015.
 */
public class Image {
    private long mTimeStamp;
    private String mImgName;
    private Uri mUri;

    public Image(long timeStamp, String imgName, Uri uri) {
        mTimeStamp = timeStamp;
        mImgName = imgName;
        mUri = uri;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    public String getImgName() {
        return mImgName;
    }

    public void setImgName(String imgName) {
        mImgName = imgName;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }
}
