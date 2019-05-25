package tech.shipr.devlists;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.yhdesai.appname.R;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        ImageView photoImageView =  convertView.findViewById(R.id.photoImageView);
        TextView titleTextView =  convertView.findViewById(R.id.titleTextView);
        TextView urlTextView = convertView.findViewById(R.id.urlTextView);
        TextView descTextView = convertView.findViewById(R.id.descTextView);
        TextView authorTextView =  convertView.findViewById(R.id.nameTextView);

        FriendlyMessage message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            titleTextView.setVisibility(View.GONE);

            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(photoImageView);
        } else {
            titleTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            titleTextView.setText(message.getTitle());
            urlTextView.setText(message.getUrl());
            descTextView.setText(message.getDesc());

        }
        authorTextView.setText(message.getName());

        return convertView;
    }
}
