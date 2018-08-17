package com.nytimes.articles.view.article;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nytimes.articles.BuildConfig;
import com.nytimes.articles.R;
import com.nytimes.articles.data.model.Article;
import com.nytimes.articles.data.model.Response;
import com.nytimes.articles.data.model.Result;
import com.nytimes.articles.viewmodel.ArticlesFragmentViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ArticlesFragmentTest {
    ArticlesFragment fragment;
    ArticlesFragmentViewModel viewModel;

    @Mock
    ArticleAdapter adapter;
    @Mock
    ArrayList<Article> articles;

    @Mock
    Article article;

    @Mock
    LinearLayoutManager linearLayoutManagerl;

    @Before
    public void setUp() throws Exception {
        viewModel = new ArticlesFragmentViewModel();
        articles = mock(ArrayList.class);
        article = mock(Article.class);
        adapter = mock(ArticleAdapter.class);
        linearLayoutManagerl = mock(LinearLayoutManager.class);
        fragment = ArticlesFragment.newInstance();
        fragment.viewModule = viewModel;
        fragment.articles = articles;
        fragment.mAdapter = adapter;
        SupportFragmentTestUtil.startFragment(fragment);
    }

    @Test
    public void testAriclesSucess() {
        Result res = mock(Result.class);
        ArrayList<Article> articleList = new ArrayList<>();
        articleList.add(new Article());
        articleList.add(new Article());


        when(article.getTitle()).thenReturn("Title");
        when(article.getByline()).thenReturn("ByLine");
        when(article.getSource()).thenReturn("Source");
        when(article.getType()).thenReturn("Type");
        when(article.getPublishedDate()).thenReturn("2018-08-10");

        when(res.getArticleList()).thenReturn(articleList);

        fragment.viewModule.getArticles("all-section", "7").postValue(Response.success(res));

        assertEquals(articles.size(), adapter.getItemCount());
    }

    @Test
    public void testAriclesFailed() {
        Throwable throwable = mock(Throwable.class);
        when(throwable.getMessage()).thenReturn("Failed");
        fragment.viewModule.getArticles("all-section", "7").postValue(Response.error(null, throwable));
        assertEquals("Failed", ShadowToast.getTextOfLatestToast());
    }
}