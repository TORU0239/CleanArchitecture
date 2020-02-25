package sg.toru.cleanarchitecture.data.respository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sg.toru.cleanarchitecture.data.util.NetworkUtil
import sg.toru.cleanarchitecture.data.api.PostApi
import java.util.concurrent.TimeUnit

class PostRepository {

    private val okHttpClient = okHttp3()

    private fun okHttp3(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .writeTimeout(3000, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkUtil.endpoint)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getPostService(): PostApi = retrofit(okHttpClient).create(PostApi::class.java)
}