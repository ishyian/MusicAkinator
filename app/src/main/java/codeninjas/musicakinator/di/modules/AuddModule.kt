package codeninjas.musicakinator.di.modules

import codeninjas.musicakinator.domain.providers.AuddGlobalDataProvider
import codeninjas.musicakinator.other.custom.constants.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AuddModule {

    @Singleton
    @Provides
    @Named("BASE_URL_Audd")
    fun provideBaseUrl(): String {
        return ApiConstants.BASE_URL_AUDD
    }


    @Singleton
    @Provides
    @Named("provideRetrofitAudd")
    fun provideRetrofit(
        @Named("BASE_URL_Audd") baseUrl: String,
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
    fun provideApiService(@Named("provideRetrofitAudd") retrofit: Retrofit): AuddGlobalDataProvider {
        return retrofit.create(AuddGlobalDataProvider::class.java)
    }
}