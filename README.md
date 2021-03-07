[![](https://jitpack.io/v/petruki/dblite-library.svg)](https://jitpack.io/#petruki/dblite-library)

### About

DbLite is a simple Android library that allows you to interact with SQLite DB.
Most of the libraries provide better and clean implementaion to interact with SQLite, but they lack of features when the data can be both relational and non-relational. This common scenario happens when you consume JSON data from external APIs and need to store it on a local DB.

This solution provides with a versatile way to implement and resolve model object dependencies easier.

Here are some of the features:
- Independent object persistence implementation (Wrappers)
- CRUD operations out-of-the-box
- Entity resolver for creating relationship from non-relational data

### Installation

> Step 1 - Add Jitpack to the project build.gradle file

```
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

> Step 2 - Add DbLite dependency to the module app of your project

```
dependencies {
	implementation 'com.github.petruki:dblite-library:0.1.1-rc'
}
``` 


### How-to (Sample usage)

Below is the step-by-step you must follow to implement new Repository classes.
You can also check the playground demo included to this library source code for more detail.

1. Create model class: model/User.java
```java
public class User {
    String _id;
    String name;
    String email;
}
```


2. Create wrapper class: repository/UserWrapper.java
```java
@DbLiteWrapper(entityName = "USER", columns = { "id", "name", "email" })
public class UserWrapper implements EntityWrapper<User> {

    @Override
    public User unWrap(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getString(cursor.getColumnIndex("id")));
        user.setName(cursor.getString(cursor.getColumnIndex("name")));
        user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        return user;
    }

    @Override
    public ContentValues wrap(User user) {
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        return values;
    }
}
```

3. Create repository class: repository/UserRepository.java
```java
public class UserRepository extends AbstractRepository<User> {

    public UserRepository(Context context) {
        super(context, new UserWrapper(), MyDatabase.class);
    }
}
```


4. Create DB class that configures wrappers and DB arguments (repository/MyDatabase.java)
```java
@DbLite(dbName = "BOOKING_DB", version = 1, wrappers = {
        UserWrapper.class,
        BookingWrapper.class
})
abstract class MyDatabase extends DbLiteFactory {

    protected MyDatabase(Context context) {
        super(context);
    }
}
```