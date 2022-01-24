package com.stock.market.data.remote.services

import com.stock.market.data.remote.response.PanelResponse
import com.stock.market.data.remote.response.TokenResponse
import com.stock.market.domain.model.Price
import io.reactivex.Observable
import retrofit2.http.*

@JvmSuppressWildcards
interface SharesService {

    @FormUrlEncoded
    @POST("/token")
    fun getToken(@Field("username") username: String, @Field("password") password: String,
                 @Field("grant_type") grant_type: String): Observable<TokenResponse>

    @FormUrlEncoded
    @POST("/token")
    fun getTokenRefresh(@Field("refresh_token") refreshToken: String,
                 @Field("grant_type") grant_type: String): Observable<TokenResponse>


    //@GET("/api/v2/Cotizaciones/acciones/merval 25/argentina")
    //@GET("/api/v2/Cotizaciones/acciones/panel general/argentina")
    //@GET("/api/v2/Cotizaciones/acciones/nasdaq 100/estados_unidos")
    @GET("/api/v2/Cotizaciones/acciones/{market}/{country}")
    fun getPanel(@Path("market") market: String, @Path("country") country: String): Observable<PanelResponse>

    //@GET("api/{mercado}/Titulos/{simbolo}/Cotizacion")
    @GET("/api/v2/{mercado}/titulos/{simbolo}/cotizacion")
    fun getPrice(@Path("mercado") mercado: String, @Path("simbolo") simbolo: String,
                 @Query("plazo") plazo: String): Observable<Price>

}