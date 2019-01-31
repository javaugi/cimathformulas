/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.rxjava;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * http://www.vogella.com/tutorials/RxJava/article.html
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class RxJava2Ex {

    private static final Logger log = LoggerFactory.getLogger(RxJava2Ex.class);
    List<Todo> todos = getTodos();

    public static void main(String[] args) {
        RxJava2Ex ex = new RxJava2Ex();
        ex.doExample();
    }

    private void doExample() {
        Observable<Todo> todoObservable = Observable.create(new ObservableOnSubscribe<Todo>() {
            @Override
            public void subscribe(ObservableEmitter<Todo> emitter) throws Exception {
                try {
                    for (Todo todo : todos) {
                        emitter.onNext(todo);
                        log.info("todo {}", todo);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
        log.info("1...  {}", todoObservable.subscribe());

        Maybe<List<Todo>> todoMaybe = Maybe.create(emitter -> {
            try {
                if (todos != null && !todos.isEmpty()) {
                    emitter.onSuccess(todos);
                } else {
                    emitter.onComplete();
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        todoMaybe.doOnSuccess(t -> log.info("" + t));
        log.info("2... {} ", todoMaybe.subscribe());

        DisposableObserver<Todo> disposableObserver = todoObservable.subscribeWith(new DisposableObserver<Todo>() {

            @Override
            public void onNext(Todo t) {
                log.info("onNext todo {}", t);
            }

            @Override
            public void onError(Throwable e) {
                log.info("onError ", e);
            }

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        });
        log.info("3... {} ", disposableObserver);

    }

    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        String[] names = {"Todo 1", "Todo 2", "Todo 3", "Todo 4"};
        Todo todo;
        for (String name : names) {
            todo = new Todo(name);
            todos.add(todo);
        }
        return todos;
    }

    public class Todo {

        String todoName;

        public Todo(String todoName) {
            this.todoName = todoName;
        }

        @Override
        public String toString() {
            return "Todo{" + "todoName=" + todoName + '}';
        }

    }
}
