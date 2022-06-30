package com.ourappsworld.example.roomdbcrud.data.factory;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ourappsworld.example.roomdbcrud.data.GoodsDatabase;
import com.ourappsworld.example.roomdbcrud.model.ItemDataM;


@Database(entities = {ItemDataM.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GoodsDatabase mGoodsDatabase();

}
