package com.ourappsworld.example.roomdbcrud.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ourappsworld.example.roomdbcrud.model.ItemDataM;


@Dao
public interface BarangDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBarang(ItemDataM barang);

    @Update
    int updateBarang(ItemDataM barang);

    @Delete
    int deleteBarang(ItemDataM barang);

    @Query("SELECT * FROM tbarang")
    ItemDataM[] selectAllBarangs();

    @Query("SELECT * FROM tbarang WHERE mId = :id LIMIT 1")
    ItemDataM selectBarangDetail(int id);
}
