package in.ponshere.fintent.sample.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import in.ponshere.fintent.FIntent;
import in.ponshere.fintent.FIntentController;
import in.ponshere.fintent.FIFactory;
import in.ponshere.fintent.IFIntentActivity;
import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.fragments.animations.US5FragmentA;
import in.ponshere.fintent.sample.fragments.basic.US1FragmentA;
import in.ponshere.fintent.sample.fragments.clear_history.US2FragmentA;
import in.ponshere.fintent.sample.fragments.navigate_to.US3FragmentA;
import in.ponshere.fintent.sample.fragments.reuse_instance.US4FragmentA;
import in.ponshere.fintent.sample.fragments.start_for_result.US6FragmentA;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,IFIntentActivity {

    FIntentController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //FIntent controller setup
        if(savedInstanceState == null || controller == null){
            controller = FIFactory.getInstance().createFIntentController(this,R.id.rlContainer);
        }



        if(savedInstanceState == null){
            navigationView.setCheckedItem(R.id.navStartForResult);
            controller.startFragment(new FIntent(US6FragmentA.class,"top_fragment"));
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            controller.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navBasic) {
            controller.startFragment(new FIntent(US1FragmentA.class,"top_fragment")
            .addFlag(FIntent.FLAGS.CLEAR_HISTORY)
            .setAnimationType(FIntent.AnimationType.NONE));
        } else if (id == R.id.navClearHistory) {
            controller.startFragment(new FIntent(US2FragmentA.class,"top_fragment")
                    .addFlag(FIntent.FLAGS.CLEAR_HISTORY)
                    .setAnimationType(FIntent.AnimationType.NONE));
        } else if (id == R.id.navNavigateTo) {
            controller.startFragment(new FIntent(US3FragmentA.class,"top_fragment")
                    .addFlag(FIntent.FLAGS.CLEAR_HISTORY)
                    .setAnimationType(FIntent.AnimationType.NONE));
        } else if (id == R.id.navReuseFragInstance) {
            controller.startFragment(new FIntent(US4FragmentA.class,"top_fragment")
                    .addFlag(FIntent.FLAGS.CLEAR_HISTORY)
                    .setAnimationType(FIntent.AnimationType.NONE));
        } else if (id == R.id.navAnimations) {
            controller.startFragment(new FIntent(US5FragmentA.class,"top_fragment")
                    .addFlag(FIntent.FLAGS.CLEAR_HISTORY)
                    .setAnimationType(FIntent.AnimationType.NONE));
        } else if (id == R.id.navStartForResult) {
            controller.startFragment(new FIntent(US6FragmentA.class,"top_fragment")
                    .addFlag(FIntent.FLAGS.CLEAR_HISTORY)
                    .setAnimationType(FIntent.AnimationType.NONE));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void callSuperBackPressed() {
        super.onBackPressed();
    }
}
