package com.nytimes.articles.view.main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.nytimes.articles.R;
import com.nytimes.articles.view.article.ArticlesFragment;

public class MainActivity extends AppCompatActivity {

    Dialog progressBarDialog;
    private int progressDialogVisibleCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.title);
        setContentView(R.layout.activity_main);
        initializeProgressBar();
        startFragment(ArticlesFragment.newInstance(), false);
    }

    public void startFragment(Fragment fragment, boolean enableBack) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(enableBack);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            finish();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return false;
        }
        return true;
    }

    private void initializeProgressBar() {
        if (progressBarDialog == null)
            createWSLoadingDialog();
    }

    @SuppressLint("InflateParams")
    private void createWSLoadingDialog() {
        progressBarDialog = new Dialog(this, R.style.MaterialTheme_NoTitle);
        progressBarDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.progress_bar, null);
        progressBarDialog.getWindow().setBackgroundDrawableResource(
                R.color.gray_transparant);
        progressBarDialog.setContentView(view);
        progressBarDialog.setCancelable(false);
        progressBarDialog.setCanceledOnTouchOutside(false);
    }

    public void showProgressBar() {
        if (progressBarDialog != null) {
            if (progressBarDialog.isShowing()){
                progressDialogVisibleCount++;
            } else{
                try {
                    progressBarDialog.show();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    public void hideProgressBar() {
        if (progressBarDialog != null) {
            if (progressDialogVisibleCount > 0) {
                progressDialogVisibleCount--;
            } else {
                if (!this.isFinishing() && progressBarDialog.isShowing()){
                    progressBarDialog.cancel();
                }
            }
        }
    }
}
