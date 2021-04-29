package id.sch.smktelkom.www.movieproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listPo)
    RecyclerView listMovie;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MovieAdapter();
        listMovie.setLayoutManager(new LinearLayoutManager(this));
        listMovie.setAdapter(adapter);
        listMovie.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        RestClient.getService().getMo().enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                adapter.listMovie.addAll(response.body().getSearch());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}