package com.example.eatsnow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CollectionAdapter extends ArrayAdapter {

    public CollectionAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItemView = inflater.inflate(R.layout.collection_item, null);
        }

        Collection currentCollection = (Collection) getItem(position);

        TextView titleTextView = listItemView.findViewById(R.id.collection_title_textView);
        TextView descriptionTextView = listItemView.findViewById(R.id.collection_description_textView);
        ImageView collectionImageView = listItemView.findViewById(R.id.collection_imageView);

        titleTextView.setText(currentCollection.getTitle());
        descriptionTextView.setText(currentCollection.getDescription());

        if (currentCollection != null) {
            new DownloadImageTask(collectionImageView).execute(currentCollection.getImage_url());
        }

        return listItemView;

    }


}
