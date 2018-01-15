package com.example.testzedkz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by nurik on 13.01.2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    TextView txtF, emailT, genT, ipT, workT, posT, title;
    ImageView photo;
    private List<DataList> dataListList;
    private Context context;

    public CustomAdapter(List<DataList> dataLists, Context context){
        this.context = context;
        this.dataListList = dataLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.items_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataList listData = dataListList.get(position);

        holder.nameText.setText(listData.getFirstName());
        holder.emailText.setText(listData.getEmail());

        String imgUrl = listData.getPhoto();
        final String newUrl = imgUrl.replace("http", "https");

        Picasso.with(context)
                .load(newUrl)
                .into(holder.img);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, listData.getFirstName(), Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                View v = LayoutInflater.from(context).inflate(R.layout.dialog, null);

                txtF = (TextView) v.findViewById(R.id.textName);
                emailT = (TextView) v.findViewById(R.id.emailText);
                genT = (TextView) v.findViewById(R.id.genderText);
                ipT = (TextView) v.findViewById(R.id.ipAdressText);
                workT = (TextView) v.findViewById(R.id.workText);
                posT = (TextView) v.findViewById(R.id.posText);
                title = (TextView) v.findViewById(R.id.title);
                photo = (ImageView) v.findViewById(R.id.image_id);

                title.setText(listData.getFirstName());
                txtF.setText("Name: " + listData.getFirstName());
                emailT.setText("Email: " + listData.getEmail());
                genT.setText("Gender: " + listData.getGender());
                ipT.setText("IP adress: " + listData.getIpAdress());
                workT.setText("Employment name: " + listData.getWork());
                posT.setText("Position: " + listData.getWorkPosition());
                Picasso.with(context)
                        .load(newUrl)
                        .into(photo);

                builder.setCancelable(false);
                builder.setView(v);
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataListList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText;
        public TextView emailText;
        public ImageView img;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            nameText = (TextView) itemView.findViewById(R.id.txtName);
            emailText = (TextView) itemView.findViewById(R.id.txtEmail);
            img = (ImageView) itemView.findViewById(R.id.imgId);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.liner);
        }
    }
}
