package com.example.daggerhomework.view.user;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daggerhomework.GitApplication;
import com.example.daggerhomework.R;
import com.example.daggerhomework.contracts.UserContract;
import com.example.daggerhomework.model.data.UserModel;
import com.example.daggerhomework.presenter.UserPresenter;
import com.example.daggerhomework.view.GlideApp;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsActivity extends AppCompatActivity implements UserContract.View {

    private static final String USER_NAME = "extra_user_name";

    @BindView(R.id.profile_name)
    TextView name;

    @BindView(R.id.profile_image)
    ImageView image;

    @Inject
    UserPresenter presenter;

    public static Intent getIntentInstantce(Context context, String user) {
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra(USER_NAME, user);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        GitApplication.getComponentInstance().inject(this);
        getLifecycle().addObserver(presenter);
        String user = getIntent().getStringExtra(USER_NAME);
        presenter.setUser(user);
        presenter.attach(this);
    }

    @Override
    public void startLoading() {
        //TODO start loading
    }

    @Override
    public void finishLoading() {
        //TODO finish loading
    }

    @Override
    public void showError(String string) {
        //TODO show error
    }

    @Override
    public void showUser(UserModel user) {
        name.setText(user.login);
        GlideApp
                .with(this)
                .load(user.imageUrl)
                .into(image);
    }
}
