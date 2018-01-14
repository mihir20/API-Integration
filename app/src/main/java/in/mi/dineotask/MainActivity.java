package in.mi.dineotask;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.mi.dineotask.Fragments.PostsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        getSupportFragmentManager().beginTransaction()
                .replace( R.id.container,new PostsFragment()).commit();
    }
}
