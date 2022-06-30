package com.ourappsworld.example.roomdbcrud.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.ourappsworld.example.roomdbcrud.R;
import com.ourappsworld.example.roomdbcrud.RoomCreateActivity;
import com.ourappsworld.example.roomdbcrud.RoomReadSingleActivity;
import com.ourappsworld.example.roomdbcrud.data.factory.AppDatabase;
import com.ourappsworld.example.roomdbcrud.model.ItemDataM;

import java.util.ArrayList;


public class AdapterBarangRecyclerView extends RecyclerView.Adapter<AdapterBarangRecyclerView.ViewHolder> {

    private final ArrayList<ItemDataM> daftarBarang;
    private final Context context;
    private final AppDatabase db;

    public AdapterBarangRecyclerView(ArrayList<ItemDataM> barangs, Context ctx) {
        daftarBarang = barangs;
        context = ctx;
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "barangdb").allowMainThreadQueries().build();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final String name = daftarBarang.get(position).getGoodsName();
        holder.cvMain.setOnClickListener(view -> {
            ItemDataM barang = db.mGoodsDatabase().mSelectRecordDetail(daftarBarang.get(position).getGoodsId());
            context.startActivity(RoomReadSingleActivity.getActIntent((Activity) context).putExtra("data", barang));
        });
        holder.cvMain.setOnLongClickListener(view -> {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.view_dialog);
            dialog.setTitle("Pilih Aksi");
            dialog.show();

            Button editButton = dialog.findViewById(R.id.bt_edit_data);
            Button delButton = dialog.findViewById(R.id.bt_delete_data);

            editButton.setOnClickListener(
                    view1 -> {
                        dialog.dismiss();
                        onEditBarang(position);
                    }
            );

            delButton.setOnClickListener(
                    view12 -> {
                        dialog.dismiss();
                        onDeteleBarang(position);
                    }
            );
            return true;
        });
        holder.tvTitle.setText(name);
    }

    private void onDeteleBarang(int position) {
        db.mGoodsDatabase().mDeleteRecord(daftarBarang.get(position));
        daftarBarang.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarBarang.size());
    }

    private void onEditBarang(int position) {
        context.startActivity(RoomCreateActivity.getActIntent((Activity) context).putExtra("data", daftarBarang.get(position)));
    }

    @Override
    public int getItemCount() {

        return daftarBarang.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_namabarang);
            cvMain = v.findViewById(R.id.cv_main);
        }
    }
}
