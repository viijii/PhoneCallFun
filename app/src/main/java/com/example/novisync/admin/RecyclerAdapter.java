package com.example.novisync.admin;

import java.text.BreakIterator;
import java.util.List;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import android.support.v7.widget.RecyclerView;



public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;

    List<GetDataAdapter> getDataAdapter;


    public RecyclerAdapter(List<GetDataAdapter> getDataAdapter, Context context) {

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.addcustrecycler, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {


        GetDataAdapter getDataAdapter1 = getDataAdapter.get(position);


        final String call_name = getDataAdapter1.getCall_name();
        final String phnumber = getDataAdapter1.getPhnumber();


        Viewholder.tv.setText(call_name);
        Viewholder.b.setText(phnumber);


        Viewholder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, EventDesc.class);
                intent.putExtra("call_name", call_name);
                intent.putExtra("phnumber", phnumber);
                context.startActivity(intent);
            }

        });
        Viewholder.b.setOnClickListener(new View.OnClickListener(){

      // @SuppressLint("MissingPermission")
       @Override
      public void onClick (View v){
      Intent intent1 = new Intent(context, RecyclerAdapter.class);
       intent1.putExtra("call_name", call_name);
       intent1.putExtra("phnumber", phnumber);
      Intent intent = new Intent(Intent.ACTION_CALL);
     intent.getStringExtra("call_name");
       intent.setData(Uri.parse("tel:" + phnumber));
           if(ContextCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
           {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 0); context.startActivity(intent);
    }

               context.startActivity(intent);
   }

  });
        }
    //Performing action on button click
    // Viewholder.b.setOnClickListener(new View.OnClickListener()

    // {
    // @Override
    // public void onClick (View v){
//        Intent intent1 = new Intent(context, RecyclerAdapter.class);
//        intent1.putExtra("call_name", call_name);
//        intent1.putExtra("phnumber", phnumber);
//
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        intent.setData(Uri.parse("tel:" + phnumber));
//        intent.getStringExtra("call_name");
//        //intent.setData(Uri.parse("tel:" + call_name));
//    }
//    });
//               if(ContextCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
//
//    {
//        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 0);
//        context.startActivity(intent);
//    }



        @Override
        public int getItemCount () {

            return getDataAdapter.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {


            public TextView tv;
            //        public  TextView tv_to;
            public LinearLayout ll;
            public Button b;


            public ViewHolder(View itemView) {

                super(itemView);

                tv = (TextView) itemView.findViewById(R.id.name);
                b = (Button) itemView.findViewById(R.id.button);
                ll = (LinearLayout) itemView.findViewById(R.id.llayout);


            }
        }
    }





