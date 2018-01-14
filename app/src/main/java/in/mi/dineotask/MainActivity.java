package in.mi.dineotask;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.mi.dineotask.Fragments.CommentsFragment;
import in.mi.dineotask.Fragments.PostsFragment;

public class MainActivity extends AppCompatActivity {
    PostsFragment postsFragment = new PostsFragment();

    @Override
    public void onBackPressed() {
        if(postsFragment.isVisible()){
        super.onBackPressed();}
        else {
            getSupportFragmentManager().beginTransaction().replace( R.id.container,postsFragment )
                    .commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        getSupportFragmentManager().beginTransaction()
                .replace( R.id.container,postsFragment).commit();
    }
}
