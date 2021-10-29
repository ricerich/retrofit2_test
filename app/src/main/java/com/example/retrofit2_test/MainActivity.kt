package com.example.retrofit2_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity()
{
    lateinit var tv1 : TextView;
    lateinit var btn1 : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1 = findViewById<TextView>(R.id.tv1)
        btn1 = findViewById<Button>(R.id.btn1)

       btn1.setOnClickListener {
           request1()
       }
    }

    private fun request1()
    {
        val baseURL = "http://10.100.204.53:9999"
        var gson1 : Gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit
            .Builder()
            .baseUrl(baseURL)
//            .addConverterFactory(GsonConverterFactory.create(gson1))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserInfoService::class.java)

        service.getPeople().enqueue(object: Callback<UserInfoList>
        {
            override fun onResponse(call: Call<UserInfoList>, response: Response<UserInfoList>)
            {
               var userList1 = response.body()
                var str1=""
                for(i in 0..userList1!!.datas.size-1)
                    str1 += "," + userList1!!.datas.get(i).ID //ID만 출력해 봄~!
                    tv1.text = str1
            }

            override fun onFailure(call: Call<UserInfoList>, t: Throwable)
            {
                tv1.text = "request 에 실패하였습니다."
                // 에러 메세지를 표시하는 Toast 를 추가하는 등의 에러 처리를 추가하는 것도 가능 !
            }
        })
    }
}