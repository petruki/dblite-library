package com.github.petruki.dblite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbLite {

    /**
     * @return Wrapper classes
     */
    Class<?>[] wrappers();

    /**
     * @return Database name
     */
    String dbName();

    /**
     * @return Database version
     */
    int version();
}
