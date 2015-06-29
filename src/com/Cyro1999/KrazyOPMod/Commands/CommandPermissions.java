package com.Cyro1999.KrazyOPMod.Commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandPermissions
{
    AdminLevel level();

    SourceType source();

    boolean blockHostConsole() default false;
}