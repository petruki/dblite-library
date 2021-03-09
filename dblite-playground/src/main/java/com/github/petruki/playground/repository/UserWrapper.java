package com.github.petruki.playground.repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.petruki.dblite.wrapper.DbLiteWrapper;
import com.github.petruki.dblite.wrapper.EntityWrapper;
import com.github.petruki.playground.model.Plan;
import com.github.petruki.playground.model.User;

@DbLiteWrapper(entityName = "USER", columns = { "id", "name", "email", "plan", "age INT" })
public class UserWrapper implements EntityWrapper<User> {

    @Override
    public User unWrap(Cursor cursor) {
        User user = new User();
        user.setId(getString(cursor, "id"));
        user.setName(getString(cursor, "name"));
        user.setEmail(getString(cursor, "email"));
        user.setAge(getInt(cursor, "age"));
        user.setPlan(getJson(cursor, "plan", Plan.class));
        return user;
    }

    @Override
    public ContentValues wrap(User user) {
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("age", user.getAge());
        values.put("plan", toJson(user.getPlan()));
        return values;
    }

}
