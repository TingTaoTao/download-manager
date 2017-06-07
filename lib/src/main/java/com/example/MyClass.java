package com.example;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class MyClass {
    public static void main(String[] args) {
        //每隔两秒产生一个数字
        Observable.interval(2,2, TimeUnit.SECONDS)
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong > 10;//返回true就停止了
                    }
                })
                .subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Long value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
        while (true){
            try {
                Thread.sleep(10000);
                System.out.println("--");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
