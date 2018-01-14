package in.mi.dineotask.Classes;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mi on 15/1/18.
 */

public interface CommentsRequestInterface {
    @GET("comments")
    Call<Comment[]> getJSON();
}
