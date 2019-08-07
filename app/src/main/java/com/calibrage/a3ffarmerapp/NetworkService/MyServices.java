package com.calibrage.a3ffarmerapp.NetworkService;

import com.calibrage.a3ffarmerapp.Model.GetLookUpModel;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface MyServices {


    @GET
    Observable<GetLookUpModel> GetActiveLookUp(@Url String url);


}
