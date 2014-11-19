package com.jxxy.tableshow.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLiteUtils extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "tableDb";
	private static final int DATABASE_VERSION = 1;
	private static final String KEY_ROWID = "_id";
	private static SQLiteUtils instance;
	private Cursor cursor = null;
	private SQLiteUtils(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public static SQLiteUtils getInstance(Context context) {
		if (instance == null) {
			instance = new SQLiteUtils(context);
		}
		return instance;
	}

	/**
	 * TODO ??��????��??�?
	 * @param mDb
	 */
	public void Close(SQLiteDatabase mDb) {
		if (mDb.isOpen() && !mDb.isDbLockedByOtherThreads()
				&& !mDb.isDbLockedByCurrentThread()) {
			/* TODO: 添�??游�????��?? BY-2014-1-13 L. */
			if(cursor!=null){
				if(!cursor.isClosed()){
					try{
						cursor.close();
					}catch (Exception e) {
						e.printStackTrace();
					}finally{
						if (cursor != null)
							cursor.close();
						cursor = null;
					}
				}else{
					cursor = null;
				}
			}
			mDb.close();
		} else {
			
		}
	}

	/**
	 * TODO �???��?��??�??????��?��??�?list??��??
	 * @param mDb
	 * @param list
	 */
	public void SavaData(SQLiteDatabase mDb, List list) {
		if (list == null || list.size() == 0) {
			return;
		}
		Class clazz = list.get(0).getClass();
		checkTableExist(mDb, clazz);
		String table = clazz.getSimpleName();
		Field[] fields = clazz.getDeclaredFields();

		try {
			for (int i = 0; i < list.size(); i++) {
				ContentValues initialValues = new ContentValues();
				Object o = list.get(i);

				for (int j = 0; j < fields.length; j++) {
					fields[j].setAccessible(true);
					Class<?> dataType = fields[j].getType();
					Object value = fields[j].get(o);

					if (value != null) {
						if (!KEY_ROWID.equals(fields[j].getName())) {
							if (dataType == String.class) {
								initialValues.put(fields[j].getName(),
										String.valueOf(value));
							} else if (dataType == int.class
									|| dataType == Integer.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Integer) null
												: Integer.parseInt(value
														.toString()));
							} else if (dataType == float.class
									|| dataType == Float.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Float) null : Float
												.parseFloat(value.toString()));
							} else if (dataType == double.class
									|| dataType == Double.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Double) null : Double
												.parseDouble(value.toString()));
							} else if (dataType == long.class
									|| dataType == Long.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Long) null : Long
												.parseLong(value.toString()));
							} else if (dataType == boolean.class
									|| dataType == Boolean.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Boolean) null : "1"
												.equals(value.toString()));
							} else {
								initialValues.put(fields[j].getName(),
										String.valueOf(value));
							}
						}

					}

				}
				mDb.insert(table, null, initialValues);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO ?????��?��????????,�???��??�?Bean对象
	 * @param mDb
	 * @param o
	 * @return
	 */
	public long SavaDataSingle(SQLiteDatabase mDb, Object o) {
		if ( o == null ) {
			return -1;
		}
		checkTableExist(mDb,  o.getClass());
		String table =  o.getClass().getSimpleName();
		Field[] fields =  o.getClass().getDeclaredFields();
		try {
				ContentValues initialValues = new ContentValues();
				for (int j = 0; j < fields.length; j++) {
					fields[j].setAccessible(true);
					Class<?> dataType = fields[j].getType();
					Object value = fields[j].get(o);
					if (value != null) {
						if (!KEY_ROWID.equals(fields[j].getName())) {
							if (dataType == String.class) {
								initialValues.put(fields[j].getName(),
										String.valueOf(value));
							} else if (dataType == int.class
									|| dataType == Integer.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Integer) null
												: Integer.parseInt(value
														.toString()));
							} else if (dataType == float.class
									|| dataType == Float.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Float) null : Float
												.parseFloat(value.toString()));
							} else if (dataType == double.class
									|| dataType == Double.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Double) null : Double
												.parseDouble(value.toString()));
							} else if (dataType == long.class
									|| dataType == Long.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Long) null : Long
												.parseLong(value.toString()));
							} else if (dataType == boolean.class
									|| dataType == Boolean.class) {
								initialValues.put(
										fields[j].getName(),
										value == null ? (Boolean) null : "1"
												.equals(value.toString()));
							} else {
								initialValues.put(fields[j].getName(),
										String.valueOf(value));
							}
						}
					}
				}
				long _id = mDb.insert(table, null, initialValues);
				return _id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * TODO �???��?????已�??�????table
	 * @param mDb
	 * @param clazz
	 */
	private void checkTableExist(SQLiteDatabase mDb, Class<?> clazz) {
		if (!tableIsExist(mDb, clazz)) {
			Log.e("GGG", "�???�建�?");
			CreateTable(mDb, clazz);
		}
	}

	/**
	 * TODO ???�?�???��??�???????已�??�????table
	 * @param mDb
	 * @param clazz
	 * @return
	 */
	private boolean tableIsExist(SQLiteDatabase mDb, Class<?> clazz) {
		if(cursor!=null){
			cursor = null;
		}
		try {
			String sql = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='"
					+ clazz.getSimpleName() + "' ";
			cursor = mDb.rawQuery(sql, null);
			if (cursor != null && cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
			cursor = null;
		}
		return false;
	}

	/**
	 * TODO ??????table�????????????��??
	 * @param mDb
	 * @param clazz
	 */
	public void DeleteData(SQLiteDatabase mDb, Class clazz) {
		checkTableExist(mDb, clazz);
		try {
			String table = clazz.getSimpleName();
			mDb.delete(table, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO ??��??????????��??
	 * @param mDb
	 * @param clazz
	 * @return
	 */
	public Object GetAllData(SQLiteDatabase mDb, Class clazz) {
		if(cursor!=null){
			cursor = null;
		}
		checkTableExist(mDb, clazz);
		List obList = new ArrayList();
		Field[] fields = clazz.getDeclaredFields();
		String[] names = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			names[i] = fields[i].getName();
		}
		cursor = mDb.query(clazz.getSimpleName(), null, null, null, null, null, null);
		if (cursor != null) {
			try {
				for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
						.moveToNext()) {
					Object obj = clazz.newInstance();
					for (Field f : fields) {
						f.setAccessible(true);
						// /??��??�???��????��?��???????��??
						// /???????????��?��????????�?
						String CursorGettypeName = f.getType().getSimpleName();
						if ("Integer".equals(CursorGettypeName)) {
							CursorGettypeName = "int";
						} else if ("boolean".equals(CursorGettypeName)) {
							CursorGettypeName = "String";
						}
						CursorGettypeName = CursorGettypeName.substring(0, 1)
								.toUpperCase() + CursorGettypeName.substring(1);
						String CursorGetName = "get" + CursorGettypeName;
						String BeanSetType = f.getName();
						String BeanSetName = "set"
								+ BeanSetType.substring(0, 1).toUpperCase()
								+ BeanSetType.substring(1);
						Method BeanSet;
						if (f.getType() == boolean.class) {
							BeanSet = getBooleanFieldSetMethod(clazz, f);
						} else {
							BeanSet = clazz.getMethod(BeanSetName, f.getType());
						}
						Object retValue = cursor.getString(cursor
								.getColumnIndex(f.getName()));
						Class<?> dataType = f.getType();
						if(retValue != null){
							if (dataType == String.class) {
								if(retValue != null){
									BeanSet.invoke(obj, retValue.toString());
								}else{
									BeanSet.invoke(obj, "");
								}
							} else if (dataType == int.class
									|| dataType == Integer.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Integer) null
												: Integer.parseInt(retValue
														.toString()));
							} else if (dataType == float.class
									|| dataType == Float.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Float) null
												: Float.parseFloat(retValue
														.toString()));
							} else if (dataType == double.class
									|| dataType == Double.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Double) null
												: Double.parseDouble(retValue
														.toString()));
							} else if (dataType == long.class
									|| dataType == Long.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Long) null : Long
												.parseLong(retValue.toString()));
							} else if (dataType == boolean.class
									|| dataType == Boolean.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Boolean) null : "1"
												.equals(retValue.toString()));
							} else {
								BeanSet.invoke(obj, retValue);
							}
						}
					}
					obList.add(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (cursor != null)
					cursor.close();
				cursor = null;
			}
			return obList;
		} else {
			return null;
		}
	}

	/**
	 * TODO ???页�?��??
	 * 
	 * @param mDb
	 * @param clazz
	 * @param num  �?页�?��?��??�?�?
	 * @param currentPage �?次�?��?��??页�??
	 * @param where 模�????��?��?????
	 * @return
	 */
	public Object GetDataByPage(SQLiteDatabase mDb, Class clazz, int num,
			int currentPage,String where) {
		if(cursor!=null){
			cursor = null;
		}
		checkTableExist(mDb, clazz);
		List obList = new ArrayList();
		Field[] fields = clazz.getDeclaredFields();
		String[] names = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			names[i] = fields[i].getName();
		}
//		cursor = mDb.rawQuery("select * from " + table + " limit " + num
//				+ " offset " + (num * (currentPage - 1)), null);
		cursor = mDb.rawQuery("select * from "+ clazz.getSimpleName() + " where "+ where + " limit " + num
				+ " offset " + (num * (currentPage - 1)), null);
		if (cursor != null) {
			try {
				for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
						.moveToNext()) {
					Object obj = clazz.newInstance();
					for (Field f : fields) {
						f.setAccessible(true);
						// /??��??�???��????��?��???????��??
						// /???????????��?��????????�?
						String CursorGettypeName = f.getType().getSimpleName();
						if ("Integer".equals(CursorGettypeName)) {
							CursorGettypeName = "int";
						} else if ("boolean".equals(CursorGettypeName)) {
							CursorGettypeName = "String";
						}
						CursorGettypeName = CursorGettypeName.substring(0, 1)
								.toUpperCase() + CursorGettypeName.substring(1);
						String CursorGetName = "get" + CursorGettypeName;
						String BeanSetType = f.getName();
						String BeanSetName = "set"
								+ BeanSetType.substring(0, 1).toUpperCase()
								+ BeanSetType.substring(1);
						Method BeanSet;
						if (f.getType() == boolean.class) {
							BeanSet = getBooleanFieldSetMethod(clazz, f);
						} else {
							BeanSet = clazz.getMethod(BeanSetName, f.getType());
						}
						Object retValue = cursor.getString(cursor
								.getColumnIndex(f.getName()));
						Class<?> dataType = f.getType();
						if(retValue != null){
							if (dataType == String.class) {
								if(retValue != null){
									BeanSet.invoke(obj, retValue.toString());
								}else{
									BeanSet.invoke(obj, "");
								}
							} else if (dataType == int.class
									|| dataType == Integer.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Integer) null
												: Integer.parseInt(retValue
														.toString()));
							} else if (dataType == float.class
									|| dataType == Float.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Float) null
												: Float.parseFloat(retValue
														.toString()));
							} else if (dataType == double.class
									|| dataType == Double.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Double) null
												: Double.parseDouble(retValue
														.toString()));
							} else if (dataType == long.class
									|| dataType == Long.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Long) null : Long
												.parseLong(retValue.toString()));
							} else if (dataType == boolean.class
									|| dataType == Boolean.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Boolean) null : "1"
												.equals(retValue.toString()));
							} else {
								BeanSet.invoke(obj, retValue);
							}
						}
					}
					obList.add(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (cursor != null)
					cursor.close();
				cursor = null;
			}
			return obList;
		} else {
			return null;
		}
	}

	/** 
	 * TODO ??��??主�????????
	 * @param mDb
	 * @param clazz
	 * @param id
	 */
	public void DeleteById(SQLiteDatabase mDb, Class clazz, String id) {
		try {
			String tableName = clazz.getSimpleName();
			mDb.delete(tableName, KEY_ROWID + " = " + id, null);
		} catch (Exception e) {

		}
	}

	/**
	 * TODO ??��??SQL�???�中where??????
	 * @param mDb
	 * @param clazz
	 * @param where
	 * ??��??�?�? �?DeleteByWhere(db,Bean.class,"spmc like '�?�????'")
	 */
	public void DeleteByWhere(SQLiteDatabase mDb, Class clazz, String where) {
		try {
			//Demo SQL  mDb.delete(tableName, "spmc like '�?�????'", null);
			mDb.delete(clazz.getSimpleName(), where, null);
		} catch (Exception e) {

		}
	}

	/**
	 * TODO ??��??�????SQL�???�模�???��??
	 * @param mDb
	 * @param clazz
	 * @param where
	 * @return
	 * ??��??�?�?  �?GetDataByWhere(db , Bean.class , "name like '%" + searcherFilter + "%'");
	 */
	public Object GetDataByWhere(SQLiteDatabase mDb, Class clazz, String where,String groupBy) {
		if(cursor!=null){
			cursor = null;
		}
		try {
			//Demo SQL  Cursor cursor = db.query(TABLE_NAME, null, "name like '%" + searcherFilter + "%'", null, null, null, null);
			cursor = mDb.query(clazz.getSimpleName(), null, where, null, groupBy,
					null, null);
			List obList = new ArrayList<Object>();
			if (cursor != null) {
				for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
						.moveToNext()) {
					Object obj = clazz.newInstance();
					for (Field f : clazz.getDeclaredFields()) {
						f.setAccessible(true);
						// /??��??�???��????��?��???????��??
						// /???????????��?��????????�?
						String CursorGettypeName = f.getType().getSimpleName();
						if ("Integer".equals(CursorGettypeName)) {
							CursorGettypeName = "int";
						} else if ("boolean".equals(CursorGettypeName)) {
							CursorGettypeName = "String";
						}
						CursorGettypeName = CursorGettypeName.substring(0, 1)
								.toUpperCase() + CursorGettypeName.substring(1);
						String CursorGetName = "get" + CursorGettypeName;
						String BeanSetType = f.getName();
						String BeanSetName = "set"
								+ BeanSetType.substring(0, 1).toUpperCase()
								+ BeanSetType.substring(1);
						Method BeanSet;
						if (f.getType() == boolean.class) {
							BeanSet = getBooleanFieldSetMethod(clazz, f);
						} else {
							BeanSet = clazz.getMethod(BeanSetName, f.getType());
						}
						Object retValue = cursor.getString(cursor
								.getColumnIndex(f.getName()));
						Class<?> dataType = f.getType();
						if(retValue != null){
							if (dataType == String.class) {
								if(retValue != null){
									BeanSet.invoke(obj, retValue.toString());
								}else{
									BeanSet.invoke(obj, "");
								}
							} else if (dataType == int.class
									|| dataType == Integer.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Integer) null
												: Integer.parseInt(retValue
														.toString()));
							} else if (dataType == float.class
									|| dataType == Float.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Float) null
												: Float.parseFloat(retValue
														.toString()));
							} else if (dataType == double.class
									|| dataType == Double.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Double) null
												: Double.parseDouble(retValue
														.toString()));
							} else if (dataType == long.class
									|| dataType == Long.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Long) null : Long
												.parseLong(retValue.toString()));
							} else if (dataType == boolean.class
									|| dataType == Boolean.class) {
								BeanSet.invoke(
										obj,
										retValue == null ? (Boolean) null : "1"
												.equals(retValue.toString()));
							} else {
								BeanSet.invoke(obj, retValue);
							}
						}
					}
					obList.add(obj);
					obj = null;
				}
				return obList;
			}
		} catch (Exception e) {
			return null;
		} finally {
			if (cursor != null)
				cursor.close();
			cursor = null;
		}
		return null;
	}

	/**
	 * TODO ??��?��?��??
	 * @param mDb
	 * @param o
	 */
	public void Update(SQLiteDatabase mDb, Object o) {
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			String where = "";
			ContentValues initialValues = new ContentValues();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				Class<?> dataType = fields[i].getType();
				Object value = fields[i].get(o);
				if (value != null) {
					if (KEY_ROWID.equals(fields[i].getName())) {
						where = KEY_ROWID + " = " + value;
					} else {
						if (dataType == String.class) {
							initialValues.put(fields[i].getName(),
									String.valueOf(value));
						} else if (dataType == int.class
								|| dataType == Integer.class) {
							initialValues.put(
									fields[i].getName(),
									value == null ? (Integer) null : Integer
											.parseInt(value.toString()));
						} else if (dataType == float.class
								|| dataType == Float.class) {
							initialValues.put(
									fields[i].getName(),
									value == null ? (Float) null : Float
											.parseFloat(value.toString()));
						} else if (dataType == double.class
								|| dataType == Double.class) {
							initialValues.put(
									fields[i].getName(),
									value == null ? (Double) null : Double
											.parseDouble(value.toString()));
						} else if (dataType == long.class
								|| dataType == Long.class) {

							initialValues.put(
									fields[i].getName(),
									value == null ? (Long) null : Long
											.parseLong(value.toString()));
						} else if (dataType == boolean.class
								|| dataType == Boolean.class) {

							initialValues.put(
									fields[i].getName(),
									value == null ? (Boolean) null : "1"
											.equals(value.toString()));
						} else {

							initialValues.put(fields[i].getName(),
									String.valueOf(value));
						}
					}
				}
			}
			mDb.update(o.getClass().getSimpleName(), initialValues, where, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO ??��??SQL�????
	 * @param mDb
	 * @param strSQL
	 */
	public void execSQL(SQLiteDatabase mDb, String strSQL) {
		try {
			mDb.execSQL(strSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * boolean类�??�???��?��??
	 * @param num
	 * @return
	 */
	public boolean changeBoolean(int num) {
		if (num == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ??��?????�?table???SQL�????
	 * 
	 * @param clazz
	 */
	public void CreateTable(SQLiteDatabase mDb, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("create table if not exists ");
		strSQL.append(clazz.getSimpleName() + " ( " + KEY_ROWID
				+ " integer primary key autoincrement");
		for (int i = 0; i < fields.length; i++) {
			if (!KEY_ROWID.equals(fields[i].getName())) {
				strSQL.append("," + fields[i].getName());
			}
		}
		strSQL.append(" ) ;");
		mDb.execSQL(strSQL.toString());
		Log.e("GGG", "建表�???��??-----" + strSQL.toString());
	}

	public static Method getBooleanFieldSetMethod(Class<?> clazz, Field f) {
		String fn = f.getName();
		String mn = "set" + fn.substring(0, 1).toUpperCase() + fn.substring(1);
		if (isISStart(f.getName())) {
			mn = "set" + fn.substring(2, 3).toUpperCase() + fn.substring(3);
		}
		try {
			return clazz.getDeclaredMethod(mn, f.getType());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean isISStart(String fieldName) {
		if (fieldName == null || fieldName.trim().length() == 0)
			return false;
		// is�?头�??并�??is�????�?�?�?�?�????大�?? �?�? isAdmin
		return fieldName.startsWith("is")
				&& !Character.isLowerCase(fieldName.charAt(2));
	}
}
