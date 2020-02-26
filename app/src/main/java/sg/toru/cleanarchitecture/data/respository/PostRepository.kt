package sg.toru.cleanarchitecture.data.respository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sg.toru.cleanarchitecture.data.api.PostApi
import sg.toru.cleanarchitecture.data.util.NetworkUtil
import java.util.concurrent.TimeUnit

// test
object NetworkClient {
    private lateinit var okHttpClient:OkHttpClient
    lateinit var retrofit:Retrofit
    init {
        okHttpClient = okHttp3()
        retrofit = retrofit(okHttpClient)
    }

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
}
// test end

class PostRepository {

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

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkUtil.endpoint)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getPostService(): PostApi = retrofit().create(PostApi::class.java)
}