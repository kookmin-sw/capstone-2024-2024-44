package com.example.uploadpost;

import static java.sql.Types.NULL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.example.uploadpost.models.Post;

public class MainActivity extends AppCompatActivity {

    EditText input_theme;
    EditText input_article;
    ImageView photo_section;
    String Tag = "MainActivity";
    private Button post_btn;
    private Button add_btn;
    Uri img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        input_theme = findViewById(R.id.input_theme);
        input_article = findViewById(R.id.input_article);
        input_article.setMovementMethod(new ScrollingMovementMethod());

        post_btn = findViewById(R.id.post_btn);
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(input_theme.getText());
                String content = String.valueOf(input_article.getText());
                Post post = new Post(title, content);
                savePosts(post);
                savePhotos(img, title);
                Intent intent = new Intent(MainActivity.this, Ok.class);
                startActivity(intent);
            }
        });

        photo_section = findViewById(R.id.photo_section);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    public void savePosts(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("post");
        String uid = "admin";
        myRef.child(uid).push().setValue(post);
    }

    public void savePhotos(Uri image, String title) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference myRef = storage.getReference("post");
        String uid = "admin";
        myRef.child(uid).child(title).putFile(image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    photo_section.setImageURI(uri);
                    img = uri;
                }
                break;
        }
    }
}