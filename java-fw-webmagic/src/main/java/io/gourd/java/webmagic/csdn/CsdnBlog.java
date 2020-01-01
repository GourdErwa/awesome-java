package io.gourd.java.webmagic.csdn;

import lombok.AllArgsConstructor;

/**
 * @author Li.Wei by 2019/12/28
 */
class CsdnBlog {

    private int id;// 编号
    private String title;// 标题
    private String date;// 日期
    private String category;// 分类
    private int view;// 阅读人数
    private int comments;// 评论人数
    private String copyright;// 是否原创

    public CsdnBlog id(int id) {
        this.id = id;
        return this;
    }

    public CsdnBlog date(String date) {
        this.date = date;
        return this;
    }

    public CsdnBlog title(String title) {
        this.title = title;
        return this;
    }

    public CsdnBlog category(String category) {
        this.category = category;
        return this;
    }

    public CsdnBlog view(int view) {
        this.view = view;
        return this;
    }

    public CsdnBlog comments(int comments) {
        this.comments = comments;
        return this;
    }

    public CsdnBlog copyright(String copyright) {
        this.copyright = copyright;
        return this;
    }

    @Override
    public String toString() {
        return "CsdnBlog{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", date='" + date + '\'' +
            ", category='" + category + '\'' +
            ", view=" + view +
            ", comments=" + comments +
            ", copyright='" + copyright + '\'' +
            '}';
    }
}
