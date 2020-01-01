package io.gourd.java.webmagic.csdn;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author Li.Wei by 2019/12/28
 */
@Slf4j
public class GithubRepoPageProcessorBak implements PageProcessor {
    private static String username = "u011278496";// 设置csdn用户名
    private static int size = 0;// 共抓取到的文章数量

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        if (!page.getUrl().regex("http://blog.csdn.net/" + username + "/article/details/\\d+").match()) {
            //获取当前页码
            // String number =page.getHtml().xpath("//li[@class='page-item active']//a[@class='page-link']/text()").toString();
            String number = "1";
            //匹配当前页码+1的页码也就是下一页，加入爬取列表中
            String regex = "http://blog.csdn.net/" + username + "/article/details/\\d+";

            log.info("regex={}", regex);
            String targetUrls = page.getHtml().links().regex(regex).get();
            page.addTargetRequest(targetUrls);

            List<String> detailUrls = page.getHtml().xpath("//li[@class='blog-unit']//a/@href").all();
            for (String list : detailUrls) {
                System.out.println(list);
            }
            page.addTargetRequests(detailUrls);
        } else {
            size++;// 文章数量加1
            CsdnBlog csdnBlog = new CsdnBlog();
            String path = page.getUrl().get();
            int id = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            String title = page.getHtml().xpath("//h1[@class='csdn_top']/text()").get();
            String date = page.getHtml().xpath("//div[@class='artical_tag']//span[@class='time']/text()").get();
            String copyright = page.getHtml().xpath("//div[@class='artical_tag']//span[@class='original']/text()").get();
            int view = Integer.parseInt(page.getHtml().xpath("//button[@class='btn-noborder']//span[@class='txt']/text()").get());
            csdnBlog.id(id).title(title).date(date).copyright(copyright).view(view);
            System.out.println(csdnBlog);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new GithubRepoPageProcessorBak())
            .addUrl("http://blog.csdn.net/" + username)
            .thread(5).run();
        System.out.println("文章总数为" + size);
    }
}

