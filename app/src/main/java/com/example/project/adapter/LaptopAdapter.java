package com.example.project.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.Laptop;

import java.util.List;

public class LaptopAdapter extends BaseAdapter {
    private List<Laptop> laptopList;
    private LayoutInflater layoutInflater;
    private Context context;

    public LaptopAdapter(List<Laptop> laptopList, Context context) {
        this.laptopList = laptopList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return laptopList.size();
    }

    @Override
    public Object getItem(int position) {
        return laptopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list_product, null);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);


        txtTitle.setText(laptopList.get(position).getTitle());
        txtPrice.setText(laptopList.get(position).getPrice()+" vnđ");
        byte[] image = laptopList.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0, image.length);
        imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
