package codeninjas.musicakinator.di.modules

import codeninjas.musicakinator.domain.providers.DizzerGlobalDataProvider
import codeninjas.musicakinator.other.custom.constants.ApiConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class DizzerModule {

    @Singleton
    @Provides
    @Named("BASE_URL_Dizzer")
    fun provideBaseUrl(): String {
        return ApiConstants.BASE_URL_DIZZER
    }


    @Singleton
    @Provides
    @Named("provideRetrofitDizzer")
    fun provideRetrofit(
        @Named("BASE_URL_Dizzer") baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("provideRetrofitDizzer") retrofit: Retrofit): DizzerGlobalDataProvider {
        return retrofit.create(DizzerGlobalDataProvider::class.java)
    }
}