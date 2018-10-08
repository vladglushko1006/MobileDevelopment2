package com.vladglush.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listViewUfcFighter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<UfcFighter>> call = api.getUfcFighter();
        call.enqueue(new Callback<List<UfcFighter>>() {

            @Override
            public void onResponse(Call<List<UfcFighter>> call, Response<List<UfcFighter>> response) {

                List<UfcFighter>lastNameList = response.body();
                //Creating an String array for the ListView
                String[] lastName = new String[lastNameList.size()];
                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < lastNameList.size(); i++) {
                    Log.d("last_name", lastNameList.get(i).getLastName());
                    lastName[i] = lastNameList.get(i).getLastName();
                }
                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,lastName));
            }

            @Override
            public void onFailure(Call<List<UfcFighter>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
