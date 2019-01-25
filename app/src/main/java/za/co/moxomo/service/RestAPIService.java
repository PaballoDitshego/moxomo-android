package za.co.moxomo.service;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestAPIService {

    @GET("/vacancies")
    Observable<JsonElement> fetchVacancies(@Query("searchString") String searchString,
                                           @Query("offset") int offset,
                                           @Query("limit") int limit);

}