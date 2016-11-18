package com.liyudong.home.myrxjava;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySeventhRxMarvel();
    }

    public void mySeventhRxMarvel(){
        List<String> abilities = new ArrayList<>();
        abilities.add("magic");
        abilities.add("science");
        abilities.add("power");
        Marvel marvel = new Marvel("StephenStrange",abilities);

        Marvel[] marvels = new Marvel[]{marvel};

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("MainActivity", s);
            }
        };

        Observable.from(marvels)
                .flatMap(new Func1<Marvel, Observable<String>>() {
                    @Override
                    public Observable<String> call(Marvel marvel) {
                        return Observable.from(marvel.getAbilities());
                    }
                }).subscribe(subscriber);

    }

    public void mySixthRxMarvel(){
        List<String> abilities = new ArrayList<>();
        abilities.add("magic");
        abilities.add("science");
        abilities.add("power");
        Marvel marvel = new Marvel("StephenStrange",abilities);

        Marvel[] marvels = new Marvel[]{marvel};

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d("MainActivity", "EveryThingsIsDone");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("MainActivity", s);
            }
        };

        Observable.from(marvels)
                .map(new Func1<Marvel,String>(){
                    @Override
                    public String call(Marvel marvel) {
                        return marvel.getName();
                    }
                }).subscribe(subscriber);
    }

    private ImageView imageView;
    public void myFifthRx(){
        final int drawableRes = R.mipmap.ic_launcher;
        imageView = (ImageView) findViewById(R.id.imageView);
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Drawable>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        });
    }

    public void myForthRx(){
        String[] works = {"Java","Android","C++","C#","Unity3D","HTML5","CSS","JAVASCRIP"};
        Observable<String> observable = Observable.from(works);
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("MainActivity", s);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };

        Action0 action0 = new Action0() {
            @Override
            public void call() {
                Log.d("MainActivity", "EveryThingsDone");
            }
        };

        observable.subscribe(action1);
        observable.subscribe(action1,onErrorAction,action0);
    }

    public void myThirdRx(){
        String[] works = {"Java","Android","C++","C#","Unity3D","HTML5","CSS","JAVASCRIP"};
        Observable<String> observable = Observable.from(works);
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("MainActivity", "EveryThing is Done");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("MainActivity", s);
            }
        };
        observable.subscribe(observer);
    }

    public void mySecondRx(){
        //Observable adj 看的见的
        //Observable看得见，所以该类负责观察变化，从而调用订阅在他身上的事件
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Begin");//调用订阅的事件的onNext方法
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d("MainActivity", "EveryThingDone");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("MainActivity", s);
            }
        };
        observable.subscribe(subscriber);
    }

    public void myFirstRx(){
        String[] names = {"David","Tom","Jack","Mary"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("MainActivity", s);
            }
        });
    }
}
