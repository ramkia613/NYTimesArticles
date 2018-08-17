package com.nytimes.articles.view.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nytimes.articles.R;
import com.nytimes.articles.data.model.Article;


public class ArticleDetailsFragment extends Fragment {
    private static final String ARG_ARTICLE = "param_article";

    private Article article;
    private View view;

    public ArticleDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param article Parameter 1.
     * @return A new instance of fragment ArticleDetailsFragment.
     */
    public static ArticleDetailsFragment newInstance(Article article) {
        ArticleDetailsFragment fragment = new ArticleDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ARTICLE, article);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            article = (Article) getArguments().getParcelable(ARG_ARTICLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_article_details, container, false);
        initView();
        return view;
    }

    void initView() {
        ((TextView) view.findViewById(R.id.tv_title)).setText(article.getTitle());
        ((TextView) view.findViewById(R.id.tv_byline)).setText(article.getByline());
        ((TextView) view.findViewById(R.id.tv_section_type)).setText(article.getSection() + " , " + article.getType());
        ((TextView) view.findViewById(R.id.tv_source)).setText(article.getSource());
        ((TextView) view.findViewById(R.id.tv_date)).setText(article.getPublishedDate());
        ((TextView) view.findViewById(R.id.tv_abstract)).setText(article.getAbstracts());
        String url = "";
        if (article.getMediaList() != null && article.getMediaList().size() > 0) {
            url = article.getMediaList().get(0).getMetadataList().get(0).getUrl();
        }
        Glide.with(getActivity())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(((ImageView) view.findViewById(R.id.imageView)));
    }

}
