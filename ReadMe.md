FIntent [ ![Download](https://api.bintray.com/packages/suyambu/android/in.ponshere.fintent/images/download.svg) ](https://bintray.com/suyambu/android/in.ponshere.fintent/_latestVersion) [![Coverage Status](https://coveralls.io/repos/github/suyambu/FIntent/badge.svg?branch=master)](https://coveralls.io/github/suyambu/FIntent?branch=master)
===================
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
 
 
Implementation
--------

**1. Activity changes**
```java
public class MainActivity extends AppCompatActivity implements IFIntentActivity {
    FIntentController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = Factory.getInstance().getController(this);
        controller.setContainerId(R.id.rlContainer);
        controller.startFragment(new FIntent(FragmentA.class));

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

2. **Extend FIFragment class**

3. **Directly start using the FIntent APIs.**
```java
public class FragmentA extends FIFragment<BindingFragmentA> implements View.OnClickListener{

    public static final String FINTENT_TAG = "FragmentA";

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        binding.btnNext.setOnClickListener(this);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_a;
    }

    @Override
    public void onClick(View view) {
        startFragment(new FIntent(US1FragmentB.class));
    }

//TODO: This method has been removed. Update readme.
    @Override
    public String uniqueFIntentTag() {
        return FINTENT_TAG;
    }
}
```

Contributing
=============
Please fork this repository and contribute back using pull requests. Features can be requested using issues. All code, comments, and critiques are greatly appreciated.