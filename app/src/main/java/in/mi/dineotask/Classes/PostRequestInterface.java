package in.mi.dineotask.Classes;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mi on 14/1/18.
 */

public interface PostRequestInterface {
    @GET("posts")
    Call<Posts[]> getJSON();
}
