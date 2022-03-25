package ru.matthew_shtyasek.atm_system.orm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnString {
    int value() default 0;
    String name() default "";
    Constraints constraints() default @Constraints;
}
