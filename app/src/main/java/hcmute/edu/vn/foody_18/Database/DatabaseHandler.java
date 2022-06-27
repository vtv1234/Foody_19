package hcmute.edu.vn.foody_18.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import hcmute.edu.vn.foody_18.Model.Food;
import hcmute.edu.vn.foody_18.Model.Menu;
import hcmute.edu.vn.foody_18.Model.Orders;
import hcmute.edu.vn.foody_18.Model.Restaurant;
import hcmute.edu.vn.foody_18.Model.User;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler mInstance = null;
    private final Context context;

    public static String DATABASE_NAME = "foody_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RESTAURANTS = "restaurants";
    private static final String TABLE_FOODS_MENU = "foods_menu";
    private static final String TABLE_FOODS = "foods";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_COVER_IMAGE = "cover_image";
    private static final String KEY_THUMB_IMAGE = "thumb_image";
    private static final String KEY_VOTE = "vote";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";
    private static final String KEY_SOLD = "sold";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE = "phone";

    private static final String FOREIGN_KEY_REF_RESTAURANT = "restaurant_id";
    private static final String FOREIGN_KEY_REF_FOOD_MENU = "foods_menu_id";

    private static final String CREATE_TABLE_RESTAURANTS = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s INT, %s TEXT, %s TEXT);", TABLE_RESTAURANTS,KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_VOTE, KEY_COVER_IMAGE, KEY_THUMB_IMAGE);
    private static final String CREATE_TABLE_FOODS_MENU = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s INT, %s TEXT, FOREIGN KEY(%s) REFERENCES %s(%s));", TABLE_FOODS_MENU, KEY_ID, FOREIGN_KEY_REF_RESTAURANT, KEY_TITLE, FOREIGN_KEY_REF_RESTAURANT, TABLE_RESTAURANTS, KEY_ID);
    private static final String CREATE_TABLE_FOODS = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s INT, %s TEXT, %s INT, %s INT, %s TEXT, %s TEXT, %s TEXT, FOREIGN KEY(%s) REFERENCES %s(%s));", TABLE_FOODS, KEY_ID, FOREIGN_KEY_REF_FOOD_MENU, KEY_NAME, KEY_PRICE, KEY_SOLD, KEY_DESCRIPTION, KEY_COVER_IMAGE, KEY_THUMB_IMAGE, FOREIGN_KEY_REF_FOOD_MENU, TABLE_FOODS_MENU, KEY_ID);
    private static final String CREATE_TABLE_USERS = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT);", TABLE_USERS, KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_ADDRESS, KEY_PHONE);
    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE IF NOT EXISTS orders\n" +
            "Id Integer Primary key autoincrement, \n"+
            "RestaurantId INTEGER,\n" +
            "RestaurantName text,\n" +
            "FoodName text,\n" +
            "FoodId text,\n" +
            "Image text,\n" +
            "Price integer,\n" +
            "Quantity integer,\n" +
            "DateOrder text,\n" +
            "TotalPrice integer,\n";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Log.d("table", CREATE_TABLE_RESTAURANTS);
    }

    public static synchronized DatabaseHandler getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHandler(ctx.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_RESTAURANTS);
        sqLiteDatabase.execSQL(CREATE_TABLE_FOODS_MENU);
        sqLiteDatabase.execSQL(CREATE_TABLE_FOODS);
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_RESTAURANTS + " VALUES(1,'Song Hân - Bún Bò Huế - Nguyễn Bặc','71/2/107/33 Nguyễn Bặc, P. 3, Tân Bình, TP. HCM', 100, 'https://trivietphat.net/wp-content/uploads/2021/08/bun-bo-1.jpg', 'https://trivietphat.net/wp-content/uploads/2021/08/bun-bo-1.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_RESTAURANTS + " VALUES(2,'Thanh Ngân - Phở - Hồng Bàng','493 Hồng Bàng, P. 14,  Quận 5, TP. HCM', 100, 'https://bepcuatoi.com/wp-content/uploads/2020/08/mon-pho-bo-thom-ngon-1200x675.png','https://bepcuatoi.com/wp-content/uploads/2020/08/mon-pho-bo-thom-ngon-1200x675.png')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_RESTAURANTS + " VALUES(3,'Hẻm Tea & Milktea', '490 Lê Văn Sỹ, P. 14.,  Quận 3, TP. HCM', 100, 'https://firebasestorage.googleapis.com/v0/b/androidfoody-53620.appspot.com/o/file_restaurant_photo_hdv2_16239-14cf3da8-210618002512.jpg?alt=media&token=038260ef-1bcc-4a52-a592-eb417786134e', 'https://firebasestorage.googleapis.com/v0/b/androidfoody-53620.appspot.com/o/file_restaurant_photo_hdv2_16239-14cf3da8-210618002512.jpg?alt=media&token=038260ef-1bcc-4a52-a592-eb417786134e')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_RESTAURANTS + " VALUES(4,'Cơm tấm Ngô Quyền','33 Dân Chủ, P. Bình Thọ,  Tp. Thủ Đức, TP. HCM', 100, 'https://upload.wikimedia.org/wikipedia/commons/b/b0/C%C6%A1m_T%E1%BA%A5m%2C_Da_Nang%2C_Vietnam.jpg', 'https://upload.wikimedia.org/wikipedia/commons/b/b0/C%C6%A1m_T%E1%BA%A5m%2C_Da_Nang%2C_Vietnam.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_RESTAURANTS + " VALUES(5,'Artisan Baked - Bánh Biscotti - Nguyễn Duy','522/525 Nguyễn Duy, P. 10,  Quận 8, TP. HCM', 100, 'https://firebasestorage.googleapis.com/v0/b/androidfoody-53620.appspot.com/o/file_restaurant_photo_pruz_16331-ed3d1bb3-211002195408.jpeg?alt=media&token=52b3a7ce-26c0-4b80-8a0e-a42edb05e263', 'https://firebasestorage.googleapis.com/v0/b/androidfoody-53620.appspot.com/o/file_restaurant_photo_pruz_16331-ed3d1bb3-211002195408.jpeg?alt=media&token=52b3a7ce-26c0-4b80-8a0e-a42edb05e263')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_RESTAURANTS + " VALUES(6,'KFC Lê Văn Việt','Lê Văn Việt, TP. HCM', 100, 'https://cdn.tgdd.vn/Files/2021/01/13/1320026/cach-lam-ga-ran-kfc-bang-noi-chien-khong-dau-gion-rum-an-khong-ngay-202202231031137197.jpg', 'https://cdn.tgdd.vn/Files/2021/01/13/1320026/cach-lam-ga-ran-kfc-bang-noi-chien-khong-dau-gion-rum-an-khong-ngay-202202231031137197.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_RESTAURANTS + " VALUES(7,'SaSin - Mì Cay 7 Cấp Độ Hàn Quốc - D2','11 Đường D2, P. 25,  Quận Bình Thạnh, TP. HCM', 100, 'https://firebasestorage.googleapis.com/v0/b/androidfoody-53620.appspot.com/o/foody-upload-api-foody-mobile-2-200408094158.jpg?alt=media&token=ba381caf-70c1-4fdf-b6fe-32ff6b58eb54', 'https://firebasestorage.googleapis.com/v0/b/androidfoody-53620.appspot.com/o/foody-upload-api-foody-mobile-2-200408094158.jpg?alt=media&token=ba381caf-70c1-4fdf-b6fe-32ff6b58eb54')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(1, 1, 'Bún bò')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(1, 1, 'Bún bò Huế', 40000, 300, 'Bún bò Huế', 'https://monngonbamien.org/wp-content/uploads/2019/10/cach-nau-bun-bo-hue-mien-nam-de-ban-don-gia-chuan-vi-ngon-nhat.jpg', 'https://monngonbamien.org/wp-content/uploads/2019/10/cach-nau-bun-bo-hue-mien-nam-de-ban-don-gia-chuan-vi-ngon-nhat.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(2, 1, 'Bún giò', 30000, 300, 'Bún giò', 'https://cdn.beptruong.edu.vn/wp-content/uploads/2018/10/bun-gio-heo.jpg', 'https://cdn.beptruong.edu.vn/wp-content/uploads/2018/10/bun-gio-heo.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(3, 1, 'Bún măng vịt', 65000, 299, 'Hộp 250g, siêu ngon, kèm nước chấm', 'https://noinauphodien.vn/wp-content/uploads/2018/10/C%C3%A1ch-n%E1%BA%A5u-b%C3%BAn-m%C4%83ng-v%E1%BB%8Bt-mi%E1%BB%85n-ch%C3%AA.jpg', 'https://noinauphodien.vn/wp-content/uploads/2018/10/C%C3%A1ch-n%E1%BA%A5u-b%C3%BAn-m%C4%83ng-v%E1%BB%8Bt-mi%E1%BB%85n-ch%C3%AA.jpg')");


        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(2, 2, 'THỨC UỐNG GIÁNG SINH 2021 - SEASONAL')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(5, 2, 'Trà sữa dâu cacao (M) (Hot)', 75000, 399, '', 'https://images.foody.vn/res/g71/700458/s120x120/074c89b8-81d4-4810-8c85-0172b801-76e1e5e7-211118220655.jpeg', 'https://images.foody.vn/res/g71/700458/s120x120/074c89b8-81d4-4810-8c85-0172b801-76e1e5e7-211118220655.jpeg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(6, 2, 'Dâu Tuyết Đá Xay (M)', 69000, 200, 'Không topping', 'https://images.foody.vn/res/g71/700458/s120x120/8a56bd0e-e480-4397-a550-42a9180f-4232657f-211118220557.jpeg', 'https://images.foody.vn/res/g71/700458/s120x120/8a56bd0e-e480-4397-a550-42a9180f-4232657f-211118220557.jpeg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(3, 2, 'PHỞ')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(7, 3, 'Phở gà', 65000, 199, 'Phở gà', 'https://www.thatlangon.com/wp-content/uploads/2021/06/cong-thuc-cach-nau-pho-ga-ngon-nhat.jpg', 'https://www.thatlangon.com/wp-content/uploads/2021/06/cong-thuc-cach-nau-pho-ga-ngon-nhat.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(8, 3, 'Phở bò', 62000, 199, 'Phở bò','https://anh.eva.vn/upload/2-2019/images/2019-06-28/1561709538-885-thumbnail_schema_article.jpg', 'https://anh.eva.vn/upload/2-2019/images/2019-06-28/1561709538-885-thumbnail_schema_article.jpg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(4, 3, 'MÓN MỚI - ĐÁ XAY - THANH TRÀ')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(9, 4, 'Olong Dâu Phúc Long', 35000, 99, 'Size S (500ml)', 'https://images.foody.vn/res/g31/300546/s120x120/10251c47-c44f-4745-bcca-e6c83489-e53dc1ef-220103034515.jpeg', 'https://images.foody.vn/res/g31/300546/s120x120/10251c47-c44f-4745-bcca-e6c83489-e53dc1ef-220103034515.jpeg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(10, 4, 'SỮA CHUA VIỆT QUẤT ĐÁC CAM', 45000, 199, 'Size S : 500ml Size M : 700ml Các món đá xay không thể làm “Không đá”', 'https://images.foody.vn/res/g31/300546/s120x120/71f1d483-5072-4935-8120-7ddf7f98-21658b7f-220227205841.jpeg', 'https://images.foody.vn/res/g31/300546/s120x120/71f1d483-5072-4935-8120-7ddf7f98-21658b7f-220227205841.jpeg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(5, 3, 'BEST SELLER')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(11, 5, 'TRÀ LÀI VỊ VẢI + 3 trái vải', 35000, 499, 'Trà pha sẵn ko giảm được độ ngọt size M : 700ml size L : 1000ml', 'https://images.foody.vn/res/g31/300546/s120x120/ee24133d-8ee1-4b3a-2458-4518a9576154.jpg', 'https://images.foody.vn/res/g31/300546/s120x120/ee24133d-8ee1-4b3a-2458-4518a9576154.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(12, 5, 'TRÀ SỮA OLONG', 30000, 200, 'Giá chưa bao gồm TOPPING size M 700ml - size Jumbo 1lit', 'https://images.foody.vn/res/g31/300546/s120x120/899a692f-ac2d-4aa8-4417-67dbbe509491.jpg', 'https://images.foody.vn/res/g31/300546/s120x120/899a692f-ac2d-4aa8-4417-67dbbe509491.jpg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(6, 4, 'CƠM TẤM')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(13, 6, 'Cơm sườn', 65000, 299, 'Com sườn', 'https://cdn.beptruong.edu.vn/wp-content/uploads/2018/06/cach-uop-thit-nuong-com-tam.jpg', 'https://cdn.beptruong.edu.vn/wp-content/uploads/2018/06/cach-uop-thit-nuong-com-tam.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(14, 6, 'Cơm chả trứng', 25000, 199, 'Chả trứng', 'https://www.hoidaubepaau.com/wp-content/uploads/2018/07/cha-trung-an-com-tam.jpg', 'https://www.hoidaubepaau.com/wp-content/uploads/2018/07/cha-trung-an-com-tam.jpg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(7, 5, 'BÁNH BISCOTTI')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(15, 7, 'Biscotti Nam Việt Quất - Hộp 195gr', 90000, 100, 'Nam Việt Quất kết hợp với Hạnh Nhân và được nướng hai lần mang đến cho bạn cảm giác dai giòn ngon tuyệt đỉnh.', 'https://images.foody.vn/res/g105/1044820/s120x120/8d804da0-cdd2-4376-a274-a536e7dc-c3df0599-201007123321.jpeg', 'https://images.foody.vn/res/g105/1044820/s120x120/8d804da0-cdd2-4376-a274-a536e7dc-c3df0599-201007123321.jpeg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(16, 7, 'Bánh Biscotti Truyền Thống - Hộp 195gr', 74250, 123, 'Hương vị truyền thống kết hợp hạt hạnh nhân & óc chó với một chút hương vị hạt tiểu hồi.', 'https://images.foody.vn/res/g105/1044820/s120x120/72ce1d39-c8fd-47f9-88b0-d8a9b85a-2ced17ea-201007122133.jpeg', 'https://images.foody.vn/res/g105/1044820/s120x120/72ce1d39-c8fd-47f9-88b0-d8a9b85a-2ced17ea-201007122133.jpeg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(17, 7, 'Bánh Biscotti Dark Chocolate - Hộp 195gr', 74250, 99, 'Bánh Biscotti hạnh nhân & socola nguyên chất, dành cho những người sành ăn tạo ra một sự kết hợp ngon miệng.', 'https://images.foody.vn/res/g105/1044820/s120x120/b4181034-6bc9-4eb6-8759-ad34fd76-37d537ed-201007122820.jpeg', 'https://images.foody.vn/res/g105/1044820/s120x120/b4181034-6bc9-4eb6-8759-ad34fd76-37d537ed-201007122820.jpeg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(8, 6, 'CƠM SƯỜN')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(18, 8, 'Cơm Tấm Sườn Trứng Ốp La', 42400, 299, 'cơm tấm, 1 canh thêm, sườn và trứng ốp la', 'https://images.foody.vn/res/g114/1134239/s120x120/55d061c2-d3f5-4870-9841-670a4f13-7e312258-220423171708.jpeg', 'https://images.foody.vn/res/g114/1134239/s120x120/55d061c2-d3f5-4870-9841-670a4f13-7e312258-220423171708.jpeg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(19, 8, ' Cơm tấm sườn', 39200, 219, 'Cơm tấm, 1 canh thêm và sườn nướng', 'https://images.foody.vn/res/g114/1134239/s120x120/16b0c3d1-8000-4797-a2c2-c3509e8b-5a6dcf5a-220423171746.jpeg', 'https://images.foody.vn/res/g114/1134239/s120x120/16b0c3d1-8000-4797-a2c2-c3509e8b-5a6dcf5a-220423171746.jpeg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(9, 6, 'KHOAI TÂY CHIÊN')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(20, 9, 'Khoai tây chiên', 42400, 312, 'Cơm tấm, 1 Canh thêm, Ba rọi nướng và Trứng ốp la', 'https://images.foody.vn/res/g114/1134239/s120x120/222edad5-5094-438b-8568-08c040c3-251e5b24-220423172345.jpeg', 'https://images.foody.vn/res/g114/1134239/s120x120/222edad5-5094-438b-8568-08c040c3-251e5b24-220423172345.jpeg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(10, 6, 'GÀ RÁN')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(21, 10, 'Gà rán', 42400, 100, 'Gà rán', 'https://cdn.tgdd.vn/Files/2021/01/13/1320026/cach-lam-ga-ran-kfc-bang-noi-chien-khong-dau-gion-rum-an-khong-ngay-202202231031137197.jpg', 'https://cdn.tgdd.vn/Files/2021/01/13/1320026/cach-lam-ga-ran-kfc-bang-noi-chien-khong-dau-gion-rum-an-khong-ngay-202202231031137197.jpg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(11, 7, 'LẨU 4 NGƯỜI')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(22, 11, 'Lẩu thái bò Úc (4 người)', 395000, 100, '', 'https://images.foody.vn/res/g24/233099/s120x120/81e91d7c-e3f9-4827-9c85-2c02073a-831925c7-220504114812.jpeg', 'https://images.foody.vn/res/g24/233099/s120x120/81e91d7c-e3f9-4827-9c85-2c02073a-831925c7-220504114812.jpeg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(23, 11, 'Lẩu tomyum bò Mỹ (4 người)', 419000, 100, '', 'https://images.foody.vn/res/g24/233099/s120x120/81e91d7c-e3f9-4827-9c85-2c02073a-831925c7-220504114812.jpeg', 'https://images.foody.vn/res/g24/233099/s120x120/81e91d7c-e3f9-4827-9c85-2c02073a-831925c7-220504114812.jpeg')");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS_MENU + " VALUES(12, 7, 'MÌ CAY')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(24, 12, 'Mì kim chi hải sản', 68000, 100, '', 'https://images.foody.vn/res/g24/233099/s120x120/af75c208-98d2-4549-97b6-5a161842f80e.jpg', 'https://images.foody.vn/res/g24/233099/s120x120/af75c208-98d2-4549-97b6-5a161842f80e.jpg')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_FOODS + " VALUES(25, 12, 'Mì kim chi bò Mỹ', 68000, 100, '', 'https://images.foody.vn/res/g24/233099/s120x120/5ea49d3d-c85e-4b6f-9701-65645305bfcc.jpg', 'https://images.foody.vn/res/g24/233099/s120x120/5ea49d3d-c85e-4b6f-9701-65645305bfcc.jpg')");


        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USERS + " VALUES(null, 'QuangVinh', 'admin@', '123', null, null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_USERS + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_FOODS + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_FOODS_MENU + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_RESTAURANTS + "'");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> restaurantArrayList = new ArrayList<Restaurant>();
        String selectQuery = "SELECT * FROM "+ TABLE_RESTAURANTS + " ORDER BY " + KEY_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(cursor.getInt(0));
            restaurant.setName(cursor.getString(1));
            restaurant.setAddress(cursor.getString(2));
            restaurant.setVote(cursor.getInt(3));
            restaurant.setCoverImage(cursor.getString(4));
            restaurant.setThumbImage(cursor.getString(5));

            restaurantArrayList.add(restaurant);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return restaurantArrayList;
    }

    public ArrayList<Menu> getAllMenuFoodsByIdRestaurant(int id) {
        ArrayList<Menu> menuArrayList = new ArrayList<Menu>();
        String selectQuery = "SELECT * FROM " + TABLE_FOODS_MENU + " WHERE " + FOREIGN_KEY_REF_RESTAURANT + "=? ORDER BY " + KEY_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {Integer.toString(id)});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Menu menu = new Menu();
            menu.setId(cursor.getInt(0));
            menu.setTitle(cursor.getString(2));
            menu.setFoods(getAllFoodByIdFoodsMenu(cursor.getInt(0)));

            menuArrayList.add(menu);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return menuArrayList;
    }

    public ArrayList<Food> getAllFoodByIdFoodsMenu(int id) {
        ArrayList<Food> foodArrayList = new ArrayList<Food>();
        String selectQuery = "SELECT * FROM " + TABLE_FOODS + " WHERE " + FOREIGN_KEY_REF_FOOD_MENU + "=? ORDER BY " + KEY_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {Integer.toString(id)});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Food food = new Food();
            food.setFoodId(cursor.getInt(0));
            food.setName(cursor.getString(2));
            food.setPrice(Integer.toString(cursor.getInt(3)));
            food.setSold(cursor.getInt(4));
            food.setDescription(cursor.getString(5));
            food.setCoverImage(cursor.getString(6));
            food.setThumbImage(cursor.getString(7));

            foodArrayList.add(food);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return foodArrayList;
    }

    public Restaurant getRestaurant(int restaurantId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RESTAURANTS, null, KEY_ID + "=?", new String[] { String.valueOf(restaurantId)}, null, null, null);
        Restaurant restaurant = null;
        if(cursor.moveToFirst()) {
            restaurant = new Restaurant();
            restaurant.setId(cursor.getInt(0));
            restaurant.setName(cursor.getString(1));
            restaurant.setAddress(cursor.getString(2));
            restaurant.setVote(cursor.getInt(3));
            restaurant.setCoverImage(cursor.getString(4));
            restaurant.setThumbImage(cursor.getString(5));
        }
        cursor.close();
        db.close();
        return restaurant;
    }

    public Boolean checkExistUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean addUser(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, 0);
        contentValues.put(KEY_NAME, "");
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_PASSWORD, pass);
        contentValues.put(KEY_ADDRESS, "");
        contentValues.put(KEY_PHONE, "");
        long result = db.insert(TABLE_USERS, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean loginUser(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = ? AND " + KEY_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email, pass});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public User getInfoUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[] {email});
        User user = new User();

        if(cursor.moveToFirst()) {
            user.setUserId(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            user.setAddress(cursor.getString(4));
            user.setPhone(cursor.getString(5));
        }

        cursor.close();
        db.close();
        return user;
    }
    public boolean insertOrder(int Id, int RestaurantId, String RestaurantName, String FoodName, int FoodId, String Image,int Price,int Quantity,String DateOrder,int TotalPrice) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id", Id);
        values.put("RestaurantId", RestaurantId);
        values.put("RestaurantName", RestaurantName);
        values.put("FoodName", FoodName);
        values.put("FoodId", FoodId);
        values.put("Image", Image);
        values.put("Price", Price);
        values.put("Quantity",Quantity);
        values.put("DateOrder",DateOrder);
        values.put("TotalPrice",TotalPrice);

        long id = database.insert("orders", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Orders> getOrders() {
        ArrayList<Orders> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select Id,FoodName,RestaurantName,Image, Price, Quantity, DateOrders, TotalPrice from orders", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                Orders ordersModel = new Orders();
                ordersModel.setId(cursor.getInt(0));
                ordersModel.setFoodName(cursor.getString(1));
                ordersModel.setRestaurantName(cursor.getString(2));
                ordersModel.setImage(cursor.getString(3));
                ordersModel.setPrice(cursor.getInt(4));
                ordersModel.setQuantity(cursor.getInt(5));
                ordersModel.setDateOrder(cursor.getString(6));
                ordersModel.setTotalPrice(cursor.getInt(7));
                orders.add(ordersModel);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

}