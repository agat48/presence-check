package pl.agat.app;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "Obecnosci";
 
    // Table names
    private static final String TABLE_MEMBERS = "Chorzysci";
    private static final String TABLE_PRESENCE = "Obecnosci";
    private static final String TABLE_OTHER = "Cos";
    
    //Table Chorzysci column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_VOICE = "voice";
    private static final String KEY_PH_NO = "phone_number";
 
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
	@Override
	public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMBERS_TABLE = "CREATE TABLE " + TABLE_MEMBERS + "("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_SURNAME + " TEXT," + KEY_VOICE + " INTEGER," + KEY_PH_NO + " TEXT" + ");";
        String CREATE_PRESENCE_TABLE = "CREATE TABLE " + TABLE_PRESENCE + "("+ KEY_ID + " INTEGER," + KEY_NAME + " TEXT," + KEY_SURNAME + " TEXT," + KEY_VOICE + " INTEGER," + KEY_PH_NO + " TEXT" + ");";
        String CREATE_OTHER_TABLE = "CREATE TABLE " + TABLE_OTHER + "("+ KEY_ID + " INTEGER," + KEY_NAME + " TEXT," + KEY_SURNAME + " TEXT," + KEY_VOICE + " INTEGER," + KEY_PH_NO + " TEXT" + ");";
        db.execSQL(CREATE_MEMBERS_TABLE);
        db.execSQL(CREATE_PRESENCE_TABLE);
        db.execSQL(CREATE_OTHER_TABLE);
    }
	
	@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS);

        // Create tables again
        onCreate(db);
    }
	
    // Adding new member
    void addMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, member.getName()); // Contact Name
        values.put(KEY_SURNAME, member.getSurname()); // Contact Name
        values.put(KEY_VOICE, member.getVoice()); // Contact Name
        values.put(KEY_PH_NO, member.getPhoneNumber()); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_MEMBERS, null, values);
        db.close(); // Closing database connection
    }
    void deleteMember(int id) {
    	SQLiteDatabase db=this.getWritableDatabase();
    	db.delete(TABLE_MEMBERS, KEY_ID+"="+id, null);
    	db.close();
    }
    void deleteMember() {
    	SQLiteDatabase db=this.getWritableDatabase();
    	db.delete(TABLE_MEMBERS, KEY_NAME+"=\"Ala\"", null);
    	db.close();
    }
    public List<Member> getAllMembers() {
    	List<Member> contactList = new ArrayList<Member>();
    	    // Select All Query
    	    String selectQuery = "SELECT  * FROM " + TABLE_MEMBERS;
    	    SQLiteDatabase db = this.getWritableDatabase();
    	    Cursor cursor = db.rawQuery(selectQuery, null);
    	 
    	    // looping through all rows and adding to list
    	    if (cursor.moveToFirst()) {
    	        do {
    	            Member member = new Member();
    	            member.setID(Integer.parseInt(cursor.getString(0)));
    	            member.setName(cursor.getString(1));
    	            member.setSurname(cursor.getString(2));
    	            member.setVoice(Integer.parseInt(cursor.getString(3)));
    	            member.setPhoneNumber(cursor.getString(4));
    	            // Adding contact to list
    	            contactList.add(member);
    	        } while (cursor.moveToNext());
    	    }
    	 db.close();
    	    // return contact list
    	    return contactList;
    }
    List<String> getAllMemberNames() {
    	List<String> list = new ArrayList<String>();
    	// Get members
    	List<Member> members = this.getAllMembers();
    	String content;
    	Member currMember;
    	for(int i=0;i<members.size();i++)
    	{
    		currMember=members.get(i);
    		content=currMember.getID()+" "+currMember.getName()+" "+currMember.getSurname()+" "+currMember.getVoice()+" "+currMember.getPhoneNumber();
    		list.add(content);
    	}
    	return list;
    }
}