package com.wes.project_1918083_tokopakaian;

import static com.wes.project_1918083_tokopakaian.PER8DBmain.TABLENAME;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class PER8MyAdapter extends RecyclerView.Adapter<PER8MyAdapter.ViewHolder> {
    Context context;
    int singledata;
    ArrayList<PER8Model>modelArrayList;
    SQLiteDatabase sqLiteDatabase;
    //generate constructor
    public PER8MyAdapter(Context context, int singledata, ArrayList<PER8Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.singledata = singledata;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }
    @NonNull
    @Override
    public PER8MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_per8_singledata, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PER8MyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final PER8Model model = modelArrayList.get(position);
        byte[] image = model.getProavatar();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imageavatar.setImageBitmap(bitmap);
        holder.txtname.setText(model.getUsername());
        holder.txtno.setText(model.getUsernomer());
        //flow menu
        holder.flowmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.flowmenu);
                popupMenu.inflate(R.menu.per8flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId()){
                            case R.id.edit_menu:
                                //edit operation
                                Bundle bundle = new Bundle();
                                bundle.putInt("id",model.getId());
                                bundle.putString("name",model.getUsername());
                                bundle.putString("nomer",model.getUsernomer());
                                bundle.putByteArray("avatar",model.getProavatar());
                                Intent intent = new Intent(context, PER8AddContact.class);

                                intent.putExtra("userdata",bundle);
                                context.startActivity(intent);
                                break;
                            case R.id.delete_menu:
                                //delete operation
                                PER8DBmain dBmain = new PER8DBmain(context);
                                sqLiteDatabase = dBmain.getReadableDatabase();
                                long recdelete = sqLiteDatabase.delete(TABLENAME,"id="+model.getId(),null);
                                if (recdelete != -1){
                                    Toast.makeText(context, "Data Deleted",Toast.LENGTH_SHORT).show();
                                    //remove positon after deleted
                                    modelArrayList.remove(position);
                                    //update data
                                    notifyDataSetChanged();
                                }
                                break;
                            default:
                                return false;
                        }
                        return false;
                    }
                });
                //display menu
                popupMenu.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageavatar;
        TextView txtname,txtno;
        ImageButton flowmenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageavatar = (ImageView)itemView.findViewById(R.id.viewavatar);
            txtname = (TextView) itemView.findViewById(R.id.txt_namecontact);
            txtno = (TextView) itemView.findViewById(R.id.txt_nocontact);
            flowmenu = (ImageButton) itemView.findViewById(R.id.flowmenu);
        }
    }
}


