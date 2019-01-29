/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.rxjava;

import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Code1 {

    private static final Logger log = LoggerFactory.getLogger(Code2.class);

    private static List<String> SUPER_HEROES = Arrays.asList(
            "Superman",
            "Batman",
            "Aquaman",
            "Asterix",
            "Captain America"
    );

    public static void main(String... args) {
        Observable<String> stream = Observable.fromIterable(SUPER_HEROES);
        stream.subscribe(
                name -> System.out.println(name)
        );
    }
}
