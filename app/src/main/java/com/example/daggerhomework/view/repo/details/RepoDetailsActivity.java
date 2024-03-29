package com.example.daggerhomework.view.repo.details;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.daggerhomework.GitApplication;
import com.example.daggerhomework.R;
import com.example.daggerhomework.contracts.RepoDetailsContract;
import com.example.daggerhomework.model.data.RepoDetailsModel;
import com.example.daggerhomework.presenter.RepoDetailsPresenter;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoDetailsActivity extends AppCompatActivity implements RepoDetailsContract.View {

    private static final String USER_NAME = "extra_user_name";
    private static final String REPO_NAME = "extra_repo_name";

    @BindView(R.id.repo_name)
    TextView name;

    @BindView(R.id.repo_description)
    TextView description;

    @BindView(R.id.repo_issue)
    TextView issue;

    @Inject
    RepoDetailsPresenter presenter;

    public static Intent getIntentInstantce(Context context, String user, String repo) {
        Intent intent = new Intent(context, RepoDetailsActivity.class);
        intent.putExtra(USER_NAME, user);
        intent.putExtra(REPO_NAME, repo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);
        ButterKnife.bind(this);

        GitApplication.getComponentInstance().inject(this);
        getLifecycle().addObserver(presenter);
        String user = getIntent().getStringExtra(USER_NAME);
        String repo = getIntent().getStringExtra(REPO_NAME);
        presenter.setData(user, repo);
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
    public void showRepo(RepoDetailsModel model) {
        name.setText(model.name);
        description.setText(model.description);
        issue.setText(getString(R.string.issue_count, model.issuesCount));
    }
}
