package com.nytimes.articles.view.article;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nytimes.articles.R;
import com.nytimes.articles.data.model.Article;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticalViewHolder> {
    private Context mContext;
    private ArrayList<Article> articles;
    private InteractionListener lintener;

    public ArticleAdapter(Context mContext, ArrayList<Article> articles) {
        this.mContext = mContext;
        this.articles = articles;
    }

    public void setOnInteractionListenerr(InteractionListener lintener) {
        this.lintener = lintener;
    }

    @Override
    public ArticalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ArticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticalViewHolder holder, final int position) {
        final Article article = articles.get(position);
        holder.tvTitle.setText(article.getTitle());

        holder.tvByline.setText(article.getByline());
        holder.tvDate.setText(article.getPublishedDate());
        String url = "";
        if (article.getMediaList() != null && article.getMediaList().size() > 0) {
            url = article.getMediaList().get(0).getMetadataList().get(0).getUrl();
        }
        Glide.with(mContext)
                .load(url)
//                    .placeholder(R.drawable.image_not_found)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .error(R.drawable.image_not_found)
                .into(holder.ivImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lintener != null) {
                    lintener.onItemClicked(position, article);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (articles != null) ? articles.size() : 0;
    }

    public class ArticalViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvByline, tvDate;
        ImageView ivImage;

        public ArticalViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvByline = (TextView) itemView.findViewById(R.id.tv_byline);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            ivImage = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }

    interface InteractionListener {
        void onItemClicked(int position, Article article);
    }
}
