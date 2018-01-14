package in.mi.dineotask.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import in.mi.dineotask.Adapters.CommentsAdapter;
import in.mi.dineotask.Classes.Comment;
import in.mi.dineotask.Classes.CommentsRequestInterface;
import in.mi.dineotask.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsFragment extends Fragment {

    int postID;
    RecyclerView recyclerView;
    CommentsAdapter commentsAdapter;
    ArrayList<Comment> commentsArrayList;

    public CommentsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate( R.layout.fragment_comments, container, false );
        recyclerView = rootView.findViewById( R.id.commentsList );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager( layoutManager );
        commentsArrayList = new ArrayList<>(  );
        postID = getArguments().getInt( "ID" );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://jsonplaceholder.typicode.com" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        CommentsRequestInterface commentsRequestInterface = retrofit
                .create( CommentsRequestInterface.class );

        Call<Comment[]> call = commentsRequestInterface.getJSON();

        call.enqueue( new Callback<Comment[]>() {
            @Override
            public void onResponse(Call<Comment[]> call, Response<Comment[]> response) {
                Comment[] comments = response.body();
                loadComments( comments );
            }

            @Override
            public void onFailure(Call<Comment[]> call, Throwable t) {
                Log.e( "LOOK HERE:-", t.getMessage() );
            }
        } );

        return rootView;
    }
//loads comments for current post only
    public void loadComments(Comment[] comments){
        for(int x =0; x<comments.length; x++){
            if(comments[x].getPostId()==postID){
                commentsArrayList.add( comments[x] );
            }
            if(comments[x].getPostId()>postID){
                    break;
            }
        }
        commentsAdapter = new CommentsAdapter(commentsArrayList);
        recyclerView.setAdapter( commentsAdapter );
    }

}
