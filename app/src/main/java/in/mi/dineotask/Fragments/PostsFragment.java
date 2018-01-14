package in.mi.dineotask.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import in.mi.dineotask.Adapters.PostsAdapter;
import in.mi.dineotask.Classes.PostRequestInterface;
import in.mi.dineotask.Classes.Posts;
import in.mi.dineotask.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {


    private RecyclerView recyclerView;
    private ArrayList<Posts> postsArrayList;
    private PostsAdapter postsAdapter;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate( R.layout.fragment_posts, container, false );

        //initializing views
        recyclerView = rootView.findViewById( R.id.postList );
        recyclerView.setHasFixedSize( true );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getActivity().getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );

        //loading JSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://jsonplaceholder.typicode.com" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        PostRequestInterface postRequestInterface = retrofit.create( PostRequestInterface.class );
        Call<Posts[]> call = postRequestInterface.getJSON();

        call.enqueue( new Callback<Posts[]>() {
            @Override
            public void onResponse(Call<Posts[]> call, Response<Posts[]> response) {
                Posts[] posts = response.body();
                postsArrayList = new ArrayList<>( Arrays.asList(posts) );
                postsAdapter = new PostsAdapter( postsArrayList );
                recyclerView.setAdapter( postsAdapter );
            }

            @Override
            public void onFailure(Call<Posts[]> call, Throwable t) {
                Log.e("MIHIR SEE HERE:-",t.getMessage());
            }
        } );

        return rootView;
    }



}
