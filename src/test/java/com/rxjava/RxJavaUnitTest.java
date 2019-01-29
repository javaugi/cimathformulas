/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.rxjava;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class RxJavaUnitTest {

    private static final Logger log = LoggerFactory.getLogger(RxJavaUnitTest.class);

    String result = "";

    // Simple subscription to a fix value
    @Test
    public void returnAValue() {
        result = "";
        Observable<String> observer = Observable.just("Hello"); // provides datea
        observer.subscribe(s -> result = s); // Callable as subscriber
        assertTrue(result.equals("Hello"));
    }

    @Test
    public void expectNPE() {
        Observable<Todo> todoObservable = Observable.create(new ObservableOnSubscribe<Todo>() {
            @Override
            public void subscribe(ObservableEmitter<Todo> emitter) throws Exception {
                try {
                    List<Todo> todos = RxJavaUnitTest.this.getTodos();
                    if (todos == null) {
                        throw new NullPointerException("todos was null");
                    }
                    for (Todo todo : todos) {
                        emitter.onNext(todo);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
        TestObserver<Object> testObserver = new TestObserver<>();
        todoObservable.subscribeWith(testObserver);

        // expect a NPE by using the TestObserver
        testObserver.assertError(NullPointerException.class);
    }

    private List<Todo> getTodos() {
        return null;
    }

    public class Todo {
    }

// https://www.baeldung.com/rxjava-2-flowable
    @Test
    public void thenAllValuesAreBufferedAndReceived() {
        List testList = IntStream.range(0, 100000)
                .boxed()
                .collect(Collectors.toList());

        Observable observable = Observable.fromIterable(testList);
        TestSubscriber<Integer> testSubscriber = observable
                .toFlowable(BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.computation()).test();

        testSubscriber.awaitTerminalEvent();

        List<Integer> receivedInts = testSubscriber.getEvents()
                .get(0)
                .stream()
                .mapToInt(object -> (int) object)
                .boxed()
                .collect(Collectors.toList());

        assertEquals(testList, receivedInts);
    }

    @Test
    public void whenDropStrategyUsed_thenOnBackpressureDropped() {
        List testList = IntStream.range(0, 100000)
                .boxed()
                .collect(Collectors.toList());

        Observable observable = Observable.fromIterable(testList);
        TestSubscriber<Integer> testSubscriber = observable
                .toFlowable(BackpressureStrategy.DROP)
                .observeOn(Schedulers.computation())
                .test();
        testSubscriber.awaitTerminalEvent();
        List<Integer> receivedInts = testSubscriber.getEvents()
                .get(0)
                .stream()
                .mapToInt(object -> (int) object)
                .boxed()
                .collect(Collectors.toList());

        //assertThat(receivedInts.size() < testList.size());
        //assertThat(!receivedInts.contains(100000));
    }

    @Test
    public void whenLatestStrategyUsed_thenTheLastElementReceived() {
        List testList = IntStream.range(0, 100000)
                .boxed()
                .collect(Collectors.toList());

        Observable observable = Observable.fromIterable(testList);
        TestSubscriber<Integer> testSubscriber = observable
                .toFlowable(BackpressureStrategy.LATEST)
                .observeOn(Schedulers.computation())
                .test();

        testSubscriber.awaitTerminalEvent();
        List<Integer> receivedInts = testSubscriber.getEvents()
                .get(0)
                .stream()
                .mapToInt(object -> (int) object)
                .boxed()
                .collect(Collectors.toList());

        //assertThat(receivedInts.size() < testList.size());
        //assertThat(receivedInts.contains(100000));
    }

    @Test
    public void whenErrorStrategyUsed_thenExceptionIsThrown() {
        Observable observable = Observable.range(1, 100000);
        TestSubscriber subscriber = observable
                .toFlowable(BackpressureStrategy.ERROR)
                .observeOn(Schedulers.computation())
                .test();

        subscriber.awaitTerminalEvent();
        subscriber.assertError(MissingBackpressureException.class);
    }

    @Test
    public void whenMissingStrategyUsed_thenException() {
        Observable observable = Observable.range(1, 100000);
        TestSubscriber subscriber = observable
                .toFlowable(BackpressureStrategy.MISSING)
                .observeOn(Schedulers.computation())
                .test();
        subscriber.awaitTerminalEvent();
        subscriber.assertError(MissingBackpressureException.class);
    }
}
