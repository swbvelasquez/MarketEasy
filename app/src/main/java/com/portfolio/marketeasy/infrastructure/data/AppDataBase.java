package com.portfolio.marketeasy.infrastructure.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.portfolio.marketeasy.core.entities.ProductEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderDetailEntity;
import com.portfolio.marketeasy.core.entities.SaleOrderEntity;
import com.portfolio.marketeasy.core.global.Constants;
import com.portfolio.marketeasy.core.global.Converters;
import com.portfolio.marketeasy.core.interfaces.ProductDAO;
import com.portfolio.marketeasy.core.interfaces.SaleOrderDAO;
import com.portfolio.marketeasy.infrastructure.threads.AppExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
                            .addCallback(roomCallback)
                            .build();

        }
        return INSTANCE;
    }

    //se ejecuta despues de que se crea la bd por primera vez
    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            ProductEntity entity1,entity2,entity3;
            ExecutorService executorService = AppExecutor.getInstance().getExecutorService();

            entity1 = new ProductEntity();
            entity1.setName("Redmi Note 8 PRO");
            entity1.setBrand("Xiaomi");
            entity1.setDescription("Celular marca Xiaomi a precio accesible");
            entity1.setPrice(750);
            entity1.setStock(100);
            entity1.setExpirationDate(null);
            entity1.setUrlImage("https://catalogo.movistar.com.pe/xiaomi-redmi-note-8-pro");

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    INSTANCE.productDAO().insert(entity1);
                }
            });

            entity2 = new ProductEntity();
            entity2.setName("Licuadora Clasica");
            entity2.setBrand("Oster");
            entity2.setDescription("Licuadora clasica 3 velocidades marca Oster");
            entity2.setPrice(345.99);
            entity2.setStock(25);
            entity2.setExpirationDate(null);
            entity2.setUrlImage("https://www.falabella.com.pe/falabella-pe/product/10390529/Licuadora-Oster-clasica-3-velocidades/10390529");

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    INSTANCE.productDAO().insert(entity2);
                }
            });

            entity3 = new ProductEntity();
            entity3.setName("Televisor 75 Pulgadas 4K HD");
            entity3.setBrand("LG");
            entity3.setDescription("Televisor LG de 75 pulgadas nitidez 4k HD");
            entity3.setPrice(1999.99);
            entity3.setStock(155);
            entity3.setExpirationDate(null);
            entity3.setUrlImage("https://www.lg.com/cac/televisores/lg-75UM7100PSA");

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    INSTANCE.productDAO().insert(entity3);
                }
            });

        }
    };
}
