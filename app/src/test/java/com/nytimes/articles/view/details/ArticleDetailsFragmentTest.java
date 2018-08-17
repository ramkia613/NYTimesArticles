package com.nytimes.articles.view.details;

import android.view.View;
import android.widget.TextView;

import com.nytimes.articles.BuildConfig;
import com.nytimes.articles.R;
import com.nytimes.articles.data.model.Article;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ArticleDetailsFragmentTest {

    ArticleDetailsFragment fragment;
    @Mock
    Article article;

    @Before
    public void setUp() throws Exception {
        article = mock(Article.class);
        fragment = ArticleDetailsFragment.newInstance(article);
        SupportFragmentTestUtil.startFragment(fragment);
    }

    @Test
    public void testArticleDetails() {
        when(article.getTitle()).thenReturn("Title");
        when(article.getByline()).thenReturn("ByLine");
        when(article.getSection()).thenReturn("Section");
        when(article.getType()).thenReturn("Type");
        when(article.getSource()).thenReturn("Source");
        when(article.getPublishedDate()).thenReturn("28-08-2017");

        fragment.initView();
        View view = fragment.getView();
        assertNotNull(view);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        TextView byLine = (TextView) view.findViewById(R.id.tv_byline);
        TextView section = (TextView) view.findViewById(R.id.tv_section_type);
        TextView source = (TextView) view.findViewById(R.id.tv_source);
        TextView date = (TextView) view.findViewById(R.id.tv_date);

        assertEquals("Title", title.getText().toString());
        assertEquals("ByLine", byLine.getText().toString());
        assertEquals("Section , Type", section.getText().toString());
        assertEquals("Source", source.getText().toString());
        assertEquals("28-08-2017", date.getText().toString());
    }


}