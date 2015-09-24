package demo.savvycom.com.dailyselfie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import demo.savvycom.com.dailyselfie.model.Image;

/**
 * Created by Bui The Nam on 7/22/2015.
 */
public class SelfieItemAdapter extends RecyclerView.Adapter<SelfieItemAdapter.SelfieItemViewHolder> {
    private ArrayList<Image> mArrayListImage;
    private Context mContext;

    public SelfieItemAdapter(ArrayList arrayListImage, Context context) {
        mArrayListImage = arrayListImage;
        mContext = context;
    }

    @Override
    public SelfieItemAdapter.SelfieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        SelfieItemViewHolder selfieItemViewHolder = new SelfieItemViewHolder(v);
        return selfieItemViewHolder;
    }

    @Override
    public void onBindViewHolder(SelfieItemAdapter.SelfieItemViewHolder holder, int position) {
       final Image image = mArrayListImage.get(position);
        holder.mImageViewAvatar.setImageURI(image.getUri());
        holder.mTextViewTimeStamp.setText(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date(image.getTimeStamp())));
        holder.mTextViewImageName.setText(image.getImgName());
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW, image.getUri());
//
//                mContext.startActivity(i);
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(image.getUri(), "image/png");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayListImage.size();
    }


    class SelfieItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewAvatar;
        private TextView mTextViewImageName;
        private TextView mTextViewTimeStamp;
        private RelativeLayout mRelativeLayout;

        public SelfieItemViewHolder(View itemView) {
            super(itemView);
            mImageViewAvatar = (ImageView) itemView.findViewById(R.id.imageView);
            mTextViewImageName = (TextView) itemView.findViewById(R.id.tv_imgName);
            mTextViewTimeStamp = (TextView) itemView.findViewById(R.id.tv_timeStamp);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.root_item);
        }


    }

}
