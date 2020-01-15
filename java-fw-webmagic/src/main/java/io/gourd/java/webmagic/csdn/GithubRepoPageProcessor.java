package io.gourd.java.webmagic.csdn;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Li.Wei by 2019/12/28
 */
@Slf4j
public class GithubRepoPageProcessor implements PageProcessor {

    private static final String USERNAME = "u011278496";// 设置 csdn 用户名
    private static final int PAGE_NUMBER = 1;

    private static final String PAGE_LIST = "https://blog.csdn.net/" + USERNAME + "/article/list/%d";

    private static final String PAGE_DETAILS = "https://blog.csdn.net/" + USERNAME + "/article/details/\\d+";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000 * 2).setTimeOut(20000)
        .addHeader("accept", "*/*")
        .addHeader("referer", "https://blog.csdn.net/xiaohulunb?t=1")
        .addHeader("accept-encoding", "gzip, deflate, br")
        .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8")
        .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");

    private static final String[] URLS = new String[PAGE_NUMBER];

    static {
        for (int i = 0; i < PAGE_NUMBER; i++) {
            URLS[i] = String.format(PAGE_LIST, i + 1);
        }
    }

    @Override
    public void process(Page page) {
        final String pageUrl = page.getUrl().get();
        final Html html = page.getHtml();
        if (pageUrl.contains("/article/list/")) {

            final List<String> all = html.links().regex(PAGE_DETAILS).all();
            final TreeSet<String> treeSet = new TreeSet<>(all);

            treeSet.forEach(s -> {
                log.info("page.link = {}", s);
                page.addTargetRequest(s);
            });

        } else if (pageUrl.contains("/article/details/")) {

            log.info("html={}", html);

        } else {
            log.error("page url error , url = {}", pageUrl);
        }


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
            .addUrl(URLS)
            .thread(3).run();
    }
}

