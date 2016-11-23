package app.priceindex.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by renan on 11/22/2016.
 */
public class PriceIndexDAO  {
    PriceIndexDAOInterface service;
    public PriceIndexDAO() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.coindesk.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PriceIndexDAOInterface.class);
    }
    //api.coindesk.com/v1/bpi/historical/close.json?start=2013-09-01&end=2013-09-05
    public List<PriceIndex> fetchAll(String start, String end) {
        Call<JsonObject> call = service.fetchAll(start, end);
        try {
            List<PriceIndex> priceIndexList = new ArrayList<>();

            Response<JsonObject> response = call.execute();

            if (response.code() != 200) {
                return new ArrayList<>();
            }

            Gson gson = new Gson();
            JsonObject responseBodyObject = gson.fromJson(response.body().toString(), JsonObject.class);
            JsonObject bpiObject = responseBodyObject.getAsJsonObject("bpi");
            for (Map.Entry<String,JsonElement> item : bpiObject.entrySet()) {
                priceIndexList.add(new PriceIndex(item.getKey(), item.getValue().getAsDouble()));
            }

            return priceIndexList;
        } catch (IOException e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }
}
