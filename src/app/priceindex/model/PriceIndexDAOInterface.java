package app.priceindex.model;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.Map;

/**
 * Created by renan on 11/22/2016.
 */
public interface PriceIndexDAOInterface {

    @GET("bpi/historical/close.json")
    Call<JsonObject> fetchAll(@Query("start") String start, @Query("end") String end);
}
