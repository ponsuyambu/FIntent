FIntent [![API](https://img.shields.io/badge/API-14%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=14) [ ![Download](https://api.bintray.com/packages/suyambu/android/fintent/images/download.svg) ](https://bintray.com/suyambu/android/fintent/_latestVersion) [![Build Status](https://travis-ci.org/suyambu/FIntent.svg?branch=master)](https://travis-ci.org/suyambu/FIntent) [![Coverage Status](https://coveralls.io/repos/github/suyambu/FIntent/badge.svg?branch=master)](https://coveralls.io/github/suyambu/FIntent?branch=master)
===================
[![Join the chat at https://gitter.im/ConsenSys/btcrelay-fetchd](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/fintent/fintent?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This library helps you to implement fragment navigation easily.

Features
--------

 1. Easy APIs
 2. Clear History
 3. NavigateTo fragment APIs
 4. Reuse fragment instance
 5. Animation support
 6. Start fragment for Result
 7. Auto handling of Fragment commit when the app is not in foreground.
 
Installation
---------------
**1. Add repo**
```groovy
repositories {
    maven {
        url  "http://dl.bintray.com/suyambu/android" 
    }
}
```
**2. Dependencies**
```groovy
dependencies {
    compile 'in.ponshere:fintent:<version>'
}
```
 
Usage
--------

**1. Setup FIntentController**
```java
public class MainActivity extends AppCompatActivity implements IFIntentActivity {
    FIntentController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = FIntentFactory.getInstance().createFIntentController(this,R.id.rlContainer);
        controller.startFragment(new FIntent(US1FragmentB.class,"top_fragment"));
    }

    @Override
    public void onBackPressed() {
        controller.onBackPressed();
    }

    @Override
    public void callSuperBackPressed() {
        super.onBackPressed();
    }
}
```

2. **Extend FIntentFragment class and directly start using its APIs**
```java
public class US1FragmentA extends FIntentFragment<BindingUS1FragmentA> implements View.OnClickListener{

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.us1_fragment_a;
    }

    @Override
    public void onClick(View view) {
        startFragment(new FIntent(US1FragmentB.class,"AtoB"));
    }

}   
```

Examples
==========
**1. Starting next fragment**
```java
startFragment(new FIntent(US1FragmentB.class,"FragA"));
```

**2. Exit the current fragment**
```java
finish()
```

**3. Clear History**
```java
startFragment(new FIntent(US2FragmentD.class,"FragC").addFlag(FIntent.FLAGS.CLEAR_HISTORY));
```

**4. Transition Animation**
```java
startFragment(new FIntent(US1FragmentB.class,"FragA").setAnimationType(FIntent.AnimationType.SLIDE_UP_DOWN));
```

**5. Start fragment for result**
```java
    static final int REQUEST_CODE = 1000;

    public void onClick(View view) {
        startFragmentForResult(new FIntent(US6FragmentB.class,US6FragmentA),REQUEST_CODE);
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == OK){

            }else if(resultCode == CANCELLED){
                
            }
        }

    }

```

Contributing
=============
Please fork this repository and contribute back using pull requests. Features can be requested using issues. All code, comments, and critiques are greatly appreciated.