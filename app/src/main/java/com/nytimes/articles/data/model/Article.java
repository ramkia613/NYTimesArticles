package com.nytimes.articles.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Article implements Parcelable {
    @SerializedName("url")
    private String url;
    @SerializedName("adx_keywords")
    private String keywords;
    @SerializedName("column")
    private String column;
    @SerializedName("section")
    private String section;
    @SerializedName("byline")
    private String byline;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("abstract")
    private String abstracts;
    @SerializedName("published_date")
    private String publishedDate;
    @SerializedName("source")
    private String source;
    @SerializedName("id")
    private long id;
    @SerializedName("asset_id")
    private long asset_id;
    @SerializedName("views")
    private long views;
    @SerializedName("media")
    private List<Media> mediaList;

    public Article() {
    }

    protected Article(Parcel in) {
        url = in.readString();
        keywords = in.readString();
        column = in.readString();
        section = in.readString();
        byline = in.readString();
        type = in.readString();
        title = in.readString();
        abstracts = in.readString();
        publishedDate = in.readString();
        source = in.readString();
        id = in.readLong();
        asset_id = in.readLong();
        views = in.readLong();
        mediaList = in.createTypedArrayList(Media.CREATOR);
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String published_date) {
        this.publishedDate = published_date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(long asset_id) {
        this.asset_id = asset_id;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    /*public List<String> getDesFacetList() {
        return desFacetList;
    }

    public void setDesFacetList(List<String> desFacetList) {
        this.desFacetList = desFacetList;
    }

    public List<String> getOrgFacetList() {
        return orgFacetList;
    }

    public void setOrgFacetList(List<String> orgFacetList) {
        this.orgFacetList = orgFacetList;
    }

    public List<String> getPerFacetList() {
        return perFacetList;
    }

    public void setPerFacetList(List<String> perFacetList) {
        this.perFacetList = perFacetList;
    }

    public List<String> getGeoFacetList() {
        return geoFacetList;
    }

    public void setGeoFacetList(List<String> geoFacetList) {
        this.geoFacetList = geoFacetList;
    }*/

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(keywords);
        dest.writeString(column);
        dest.writeString(section);
        dest.writeString(byline);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(abstracts);
        dest.writeString(publishedDate);
        dest.writeString(source);
        dest.writeLong(id);
        dest.writeLong(asset_id);
        dest.writeLong(views);
        dest.writeTypedList(mediaList);
    }
}
