package com.example.consumptionapicomments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.consumptionapicomments.model.Comments;
import com.example.consumptionapicomments.service.JsonPlaceHolderService;
import com.example.consumptionapicomments.service.RestApiBuilder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTextView = findViewById(R.id.jsonText);
        getComments();
    }

    public void getComments(){
        RestApiBuilder retrofit = new RestApiBuilder();
        JsonPlaceHolderService jsonPlaceHolderService = retrofit.getService();
        Call<List<Comments>> call = jsonPlaceHolderService.getComments();
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if(!response.isSuccessful()){
                    mJsonTextView.setText("Codigo: "+response.code());
                    return;
                }
                List<Comments> commentsList = response.body();
                for (Comments comments: commentsList){
                    String content = "";
                    content += "postId: "+comments.getPostId() + "\n";
                    content += "id: "+comments.getId() + "\n";
                    content += "name: "+comments.getName() + "\n";
                    content += "email: "+comments.getEmail() + "\n";
                    content += "body: "+comments.getBody() + "\n\n";
                    mJsonTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                mJsonTextView.setText(t.getMessage());
            }
        });
    }

}