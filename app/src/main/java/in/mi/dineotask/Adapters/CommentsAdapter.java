package in.mi.dineotask.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.mi.dineotask.Classes.Comment;
import in.mi.dineotask.R;

/**
 * Created by mi on 14/1/18.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private ArrayList<Comment> comments;

    public CommentsAdapter(ArrayList<Comment> comments){
        this.comments=comments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.comment_list_element,parent,false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.body.setText(comments.get( position ).getBody());
        holder.email.setText( comments.get( position ).getEmail() );
        holder.name.setText( comments.get( position ).getName() );

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, body;
        public ViewHolder(View itemView) {
            super( itemView );
            name = itemView.findViewById( R.id.name );
            email = itemView.findViewById( R.id.email );
            body = itemView.findViewById( R.id.commentBody );
        }
    }
}
