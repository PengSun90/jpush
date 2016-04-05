package com.xunmeng.jpush.config;

import com.xunmeng.jpush.entity.NeteastNewsDetail;
import com.xunmeng.jpush.entity.NeteastNewsSummary;
import com.xunmeng.jpush.entity.NeteastVideoSummary;
import com.xunmeng.jpush.entity.SinaPhotoDetail;
import com.xunmeng.jpush.entity.SinaPhotoList;
import com.xunmeng.jpush.entity.WeatherInfo;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：mywangyi
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2016/3/24 11:59
 * 修改人：Administrator
 * 修改时间：2016/3/24 11:59
 * 修改备注：
 */
public class http {

    /**
     * 网易新闻列表 例子：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     * <p/>
     * 对API调用了observeOn(MainThread)之后，线程会跑在主线程上，包括onComplete也是，
     * unsubscribe也在主线程，然后如果这时候调用call.cancel会导致NetworkOnMainThreadException
     * 加一句unsubscribeOn(io)
     *
     * @param type      新闻类别：headline为头条,list为其他
     * @param id        新闻类别id
     * @param startPage 开始的页码
     * @return 被观察对象
     */
    public Map<String, List<NeteastNewsSummary>> getNewsListObservable(String type, String id, int startPage) {
        return null;
    }

    /**
     * 网易新闻详情：例子：http://c.m.163.com/nc/article/BG6CGA9M00264N2N/full.html
     *
     * @param postId 新闻详情的id
     * @return 被观察对象
     */
    public Map<String, NeteastNewsDetail> getNewsDetailObservable(String postId) {
        return null;
    }

    /**
     * 新浪图片新闻列表 例子：http://api.sina.cn/sinago/list.json?channel=hdpic_pretty&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=1
     *
     * @param page 页码
     * @return 被观察对象
     */
    public SinaPhotoList getSinaPhotoListObservable(String photoTypeId, int page) {
        return null;
    }

    /**
     * 新浪图片详情 例子：http://api.sina.cn/sinago/article.json?postt=hdpic_hdpic_toutiao_4&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&id=20550-66955-hdpic
     *
     * @param id 图片的id
     * @return 被观察者
     */
    public SinaPhotoDetail getSinaPhotoDetailObservable(String id) {
        return null;
    }

    /**
     * 网易视频列表 例子：http://c.m.163.com/nc/video/list/V9LG4B3A0/n/0-10.html
     *
     * @param id        视频类别id
     * @param startPage 开始的页码
     * @return 被观察者
     */
    public Map<String, List<NeteastVideoSummary>> getVideoListObservable(String id, int startPage) {
        return null;
    }

    /**
     * 天气情况 例子：http://wthrcdn.etouch.cn/weather_mini?city=%E5%8C%97%E4%BA%AC
     *
     * @param city 城市名称
     * @return 被观察者
     */
    public WeatherInfo getWeatherInfoObservable(String city) {
        return null;
    }


}
