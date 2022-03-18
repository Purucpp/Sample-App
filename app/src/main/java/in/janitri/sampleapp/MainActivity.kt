package `in`.janitri.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yesandroid.kfirst.Api_Response
import com.yesandroid.kfirst.Get_Interface
import com.yesandroid.kfirst.Get_Retrofit_Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val client= Get_Retrofit_Client().getClient();
        val service = client?.create(Get_Interface::class.java)
        val call = service?.getsocial()

        call?.enqueue(object : Callback<List<Api_Response>?>
        {
            override fun onResponse(call: Call<List<Api_Response>?>, response: Response<List<Api_Response>?>)
            {
                val items = response.body()
                if (items != null) {
                    for( i in 0 until items.count())
                    {
                        Log.d("name",items[i].getName().toString())
                    }
                }
            }
            override fun onFailure(call: Call<List<Api_Response>?>, t: Throwable) {
                Log.d("Failed------", t.message.toString());

            }
        })

    }
}