package com.nytimes.articles.view.article;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nytimes.articles.R;
import com.nytimes.articles.data.model.Article;
import com.nytimes.articles.data.model.Response;
import com.nytimes.articles.data.model.Result;
import com.nytimes.articles.view.details.ArticleDetailsFragment;
import com.nytimes.articles.view.main.MainActivity;
import com.nytimes.articles.viewmodel.ArticlesFragmentViewModel;

import java.util.ArrayList;

public class ArticlesFragment extends Fragment {

    private View view;
    ArticlesFragmentViewModel viewModule;

    private RecyclerView articleView;

    ArticleAdapter mAdapter;
    ArrayList<Article> articles = new ArrayList<>();

    public ArticlesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment
     *
     * @return A new instance of fragment ArticlesFragment.
     */
    public static ArticlesFragment newInstance() {
        ArticlesFragment fragment = new ArticlesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.fragment_articles, container, false);
            viewModule = ViewModelProviders.of(this).get(ArticlesFragmentViewModel.class);
            initView();
            getArticles();
        }
        return view;
    }

    private void initView() {
        articleView = (RecyclerView) view.findViewById(R.id.rv_article);

        //RecyclerView setup as ListView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        articleView.setLayoutManager(linearLayoutManager);

        //Add row divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(articleView.getContext(),
                linearLayoutManager.getOrientation());
//        articleView.addItemDecoration(dividerItemDecoration);

        mAdapter = new ArticleAdapter(getActivity(), articles);
        articleView.setAdapter(mAdapter);

        mAdapter.setOnInteractionListenerr(new ArticleAdapter.InteractionListener() {
            @Override
            public void onItemClicked(int position, Article article) {
                ((MainActivity) getActivity()).startFragment(ArticleDetailsFragment.newInstance(article), true);
            }
        });
    }

    /**
     * Load the articles information from ViewModule
     */
    //TODO section and period will be changed by the criteria
    void getArticles() {
        //FIXME ClassCastException while running testcase
        try {
            ((MainActivity) getActivity()).showProgressBar();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        viewModule.getArticles("all-sections", "7").observeForever(new Observer<Response>() {
            @Override
            public void onChanged(@Nullable Response response) {
                //FIXME ClassCastException while running testcase
                try {
                    ((MainActivity) getActivity()).hideProgressBar();
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
                if (response.status == Response.SUCCESS) {
                    Result result = (Result) response.data;
                    articles.clear();
                    articles.addAll(result.getArticleList());
                    mAdapter.notifyDataSetChanged();
                } else if (response.status == Response.FAILURE) {
                    Toast.makeText(getActivity(), response.error.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
