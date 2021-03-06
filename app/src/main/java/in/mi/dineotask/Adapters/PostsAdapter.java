package in.mi.dineotask.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.mi.dineotask.Classes.Posts;
import in.mi.dineotask.R;

/**
 * Created by mi on 14/1/18.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private ArrayList<Posts> posts;
    private ItemClickListener mClicklistener;

    public PostsAdapter(ArrayList<Posts> posts,ItemClickListener mClicklistener){
        this.posts = posts;
        this.mClicklistener = mClicklistener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext())
                .inflate( R.layout.post_list_element,parent,false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleText.setText( posts.get( position ).getTitle() );
        holder.bodyText.setText( posts.get( position ).getBody() );
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleText, bodyText;
        public ViewHolder(View itemView) {
            super( itemView );
            titleText = itemView.findViewById( R.id.postTitle );
            bodyText = itemView.findViewById( R.id.postBody );
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View v) {
            if(mClicklistener!=null){
                mClicklistener.onItemClick( v,
                        getAdapterPosition(),
                        posts.get(getAdapterPosition()).getId());
            }
            Log.e( "LISTENER IS NULL","####" );
        }
    }

//parent fragment will implement this method to handle click events
    public interface ItemClickListener{
        void onItemClick(View view,int position, int postID);
    }
}
