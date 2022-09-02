package com.example.postcreator.database;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.postcreator.pojo.Post;
import com.example.postcreator.pojo.Task;
import com.example.postcreator.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 16;
    public static final String DATABASE_NAME = "posts"+Constants.SOURCE_ID+".db";
    private static final String POSTS_TABLE_NAME = "posts";
    private static DbHelper myInstance = null;

    private interface PostColumns{
        String USER_ID = "userId";
        String MEDIA_ID = "mediaId";
        String TITLE = "title";
        String DESCRIPTION = "description";
        String CREATE_AT = "create_at";
        String UPDATE_AT = "update_at";
    }

    private static final String SQL_CREATE_TABLE_POSTS = "CREATE TABLE posts (" +
            "_id TEXT PRIMARY KEY, userId INTEGER, mediaId INTEGER, title TEXT, description TEXT, create_at TEXT, update_at TEXT)";

  private static final String SQL_CREATE_TABLE_TASKS = "CREATE TABLE TASKS ( id TEXT PRIMARY KEY , " +
          " name TEXT )";




        public static synchronized DbHelper getMyInstance(Application application) {
            if(myInstance == null){
                myInstance = new DbHelper(application);
            }
            return myInstance;
        }

        private DbHelper(Context context) {
            super(context, DATABASE_NAME , null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE_POSTS);
            db.execSQL(SQL_CREATE_TABLE_TASKS);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onDelete(db);
            onCreate(db);
        }
        public void onDelete(SQLiteDatabase db){
            db.execSQL("DROP TABLE IF EXISTS posts");
            db.execSQL("DROP TABLE IF EXISTS TASKS");
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        public LiveData<List<Post>> getPosts(){
            MutableLiveData<List<Post>> listPost = new MutableLiveData<>();
            SQLiteDatabase db = getReadableDatabase();
            String query = "SELECT * FROM "+POSTS_TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);

            //getIndexes
            int idIndex = cursor.getColumnIndex(BaseColumns._ID);
            int userIdIndex = cursor.getColumnIndex(PostColumns.USER_ID);
            int mediaIdIndex = cursor.getColumnIndex(PostColumns.MEDIA_ID);
            int titleIndex = cursor.getColumnIndex(PostColumns.TITLE);
            int descriptionIndex = cursor.getColumnIndex(PostColumns.DESCRIPTION);
            //int create_atIndex = cursor.getColumnIndex(PostColumns.CREATE_AT);
            //int update_atIndex = cursor.getColumnIndex(PostColumns.UPDATE_AT);
            List<Post> posts = new ArrayList<>();
            while(cursor.moveToNext()){
              int id = cursor.getInt(idIndex);
              int userId = cursor.getInt(userIdIndex);
              int mediaId = cursor.getInt(mediaIdIndex);
              String title = cursor.getString(titleIndex);
              String description = cursor.getString(descriptionIndex);
              //String create_at = cursor.getString(create_atIndex);
              //String update_at = cursor.getString(update_atIndex);
              posts.add(new Post(id, userId, mediaId, title, description));
            }
            cursor.close();
            //if(posts.size() > 0 )
            listPost.setValue(posts);
            return listPost;
        }

        public LiveData<Long> addPost(Post post){
            SQLiteDatabase db = getWritableDatabase();
            MutableLiveData<Long> mCount = new MutableLiveData<>();

            ContentValues values = new ContentValues();
            //values.put(BaseColumns._ID, 1);
            values.put(PostColumns.USER_ID, post.getUserId());
            values.put(PostColumns.MEDIA_ID, post.getMediaId());
            values.put(PostColumns.TITLE, post.getTitle());
            values.put(PostColumns.DESCRIPTION, post.getDescription());
            values.put(PostColumns.CREATE_AT, "17.04.2022");
            values.put(PostColumns.UPDATE_AT, "15.05.2022");

            long count  = db.insert(POSTS_TABLE_NAME, null, values);
            mCount.setValue(count);
            return mCount;
        }



     public long addTask(Task task){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", task.getName());
            long count = db.insert("Tasks", null, values);

            return count;
     }







}
