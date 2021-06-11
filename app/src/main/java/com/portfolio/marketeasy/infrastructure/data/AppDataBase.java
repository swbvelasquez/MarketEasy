package com.portfolio.marketeasy.infrastructure.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.global.Constants;
import com.portfolio.marketeasy.core.global.Converters;
import com.portfolio.marketeasy.core.interfaces.ProductDAO;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;

@Database(
        entities = {
                ProductEntity.class,
                SaleOrderEntity.class,
                SaleOrderDetailEntity.class
        },
        version = Constants.DB_VERSION
)
@TypeConverters(
        {Converters.class}
)
public abstract class AppDataBase extends RoomDatabase {
    public static AppDataBase INSTANCE;

    public abstract SaleOrderDAO saleOrderDAO();
    public abstract ProductDAO productDAO();

    public static synchronized AppDataBase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context,AppDataBase.class, Constants.DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();

        }
        return INSTANCE;
    }
}
