package com.github.petruki.dblite.wrapper;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;

/**
 * EntityWrappers are used to transitioning application model objects to SQLite DB objects
 */
public interface EntityWrapper<T> {

    /**
     * Unwrap object given the object model type
     */
    T unWrap(Cursor cursor);

    /**
     * Wrap object to be contained into a valida DB model object
     */
    ContentValues wrap(T entity);

    default <Y> Y getJson(Cursor cursor, String column, Class<Y> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(getString(cursor, column), clazz);
    }

    default <Y> String toJson(Y entity) {
        Gson gson = new Gson();
        return gson.toJson(entity);
    }

    default String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }

    default Integer getInt(Cursor cursor, String column) {
        return cursor.getInt(cursor.getColumnIndex(column));
    }

    default Float getFloat(Cursor cursor, String column) {
        return cursor.getFloat(cursor.getColumnIndex(column));
    }

    default Long getLong(Cursor cursor, String column) {
        return cursor.getLong(cursor.getColumnIndex(column));
    }

    default Double getDouble(Cursor cursor, String column) {
        return cursor.getDouble(cursor.getColumnIndex(column));
    }

    default Boolean getBool(Cursor cursor, String column) {
        return cursor.getInt(cursor.getColumnIndex(column)) > 0;
    }

    default byte[] getBlob(Cursor cursor, String column) {
        return cursor.getBlob(cursor.getColumnIndex(column));
    }

    default short getShort(Cursor cursor, String column) {
        return cursor.getShort(cursor.getColumnIndex(column));
    }
}
