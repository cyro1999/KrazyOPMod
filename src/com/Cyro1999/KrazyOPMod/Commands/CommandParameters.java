package com.Cyro1999.KrazyOPMod.Commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParameters
{
    String description();

    String usage();

    String aliases() default ""; // "alias1,alias2,alias3" - no spaces
}