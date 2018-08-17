package com.nytimes.articles.view.article;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nytimes.articles.BuildConfig;
import com.nytimes.articles.data.model.Article;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class ArticleAdapterTest {
    private Context context;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;
    }

    @Test
    public void testAdapterView() {
        // Set up input
        Article article = new Article();
        article.setTitle("Title");
        article.setByline("Byline");
        article.setSection("Section");
        article.setType("Type");
        article.setSource("Source");
        article.setPublishedDate("2018-08-11");
        article.setAbstracts("Abstract");

        ArrayList<Article> articles = new ArrayList<>();
        articles.add(article);
        articles.add(article);


        ArticleAdapter adapter = new ArticleAdapter(context, articles);

        RecyclerView rvParent = new RecyclerView(context);
        rvParent.setLayoutManager(new LinearLayoutManager(context));

        ArticleAdapter.ArticalViewHolder viewHolder =
                adapter.onCreateViewHolder(rvParent, 0);

        adapter.onBindViewHolder(viewHolder, 0);

        assertEquals("Byline", viewHolder.tvByline.getText().toString());
        assertEquals(2, adapter.getItemCount());
    }
}