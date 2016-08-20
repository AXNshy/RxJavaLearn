package com.axnshy;

import com.sun.tools.javac.util.Log;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;


/**
 * Created by axnshy on 16/8/20.
 */
public class RxJava1 {


    public static void main(String[] Args) {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext" + s);
            }
        };
        Observer<person> observer1 = new Observer<person>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onNext(person s) {
                System.out.println("onNext" + s.height + "      " + s.weight);
            }
        };
        person xu = new person();
        xu.height = 70;
        xu.weight = 180;

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("RxJava");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(observer);

        Observable observable1 = Observable.just("Hello", "Hi", "Aloha");
        observable1.subscribe(observer);

        Observable<person> observable2 = Observable.create(new Observable.OnSubscribe<person>() {
            @Override
            public void call(Subscriber<? super person> subscriber) {
                subscriber.onNext(xu);
                subscriber.onNext(xu);
                subscriber.onCompleted();
            }
        });

        observable2.subscribe(observer1);


        String[] names = {"ajlskjdlkjals","asdaihwoh","1239u0uajl"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        System.out.println("onNext" + name + "      " + name);
                    }
                });
    }
}
class person {
    int height;
    int weight;

}
