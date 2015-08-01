package AuntBertha;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface AuntBerthaApi {

    @GET("/{zipcode}/programs?api_key=b30f1b9f41161c0fb3b39cb49aff8104&serviceTag=mentoring&attributeTag=young adults")
    public void getFeed(@Path("zipcode") String zip, Callback<ABModel> response);

}
