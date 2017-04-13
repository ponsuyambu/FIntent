FIntent
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
 7. Auto handling of Fragment commit when app is not foreground.
 
 
Implementation
--------
**MainActivity**
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