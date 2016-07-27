package com.example.fanpian.config;

/**
 *      图片全部缓存在"mnt/sdcard/android/data/(package)/cache",全部采用MD5或者Base64命名
 */
public class InternetConfig {

   //////////////////////////////////////////////////////////////////////////////////////////

    /**
     *  程序启动时即请求的数据
     *  启动界面的图片,GET请求;(获得json数据,解析出图片名称,拼接在域名(http://morguo.com/)后面,和缓存做对比)
     */
    public static final String GET_LAUNCH_IMAGE_NEW ="http://morguo.com/api/appxq/getlaunchimagenew.php?type=android&androidflag=1";

    /**
     *  程序启动时即请求的数据
     *  首页,也就是"推荐"一栏;
     *  GET请求,分页加载,按页请求(首页page=1,最后一个字段pdateline第一页没有值,其它页都有值,但我看没有值请求所得数据也是一样的)
     *  (获得json数据,解析出图片名称,拼接在域名(http://morguo.com/)后面,和缓存做对比)
     *  每一页的item个数和顺序都不一样
     *  item类型共有 种:例如 "type": "threadarticle",
     *   "type": "threadarticle",杂粮
     *   "type": "collection", 影单
     *   "type": "threadvideo",短片
     *   "type": "acinecism",影评
     *   "type": "movielines",(电影线)-不显示标题
     *   "type": "threadmusic",音乐-不显示标题
     *   "type": "threadgallery",画报,---------------这是每页的最后一项
     *
     *
     */
    public static final String MOVIE_EXPLORER ="http://morguo.com/forum.php?mod=movieexplorer&v=3&androidflag=1&page=1&pdateline=";
    public static final String MOVIE_EXPLORER2 ="http://morguo.com/forum.php?mod=movieexplorer&v=3&androidflag=1&page=2&pdateline=1469422559";
    public static final String MOVIE_EXPLORER3 ="http://morguo.com/forum.php?mod=movieexplorer&v=3&androidflag=1&page=3&pdateline=1469424967";



    /**
     *   程序启动时即请求的数据
     *  搜索页面的分类和地区
     *  GET请求,
     */
    public static final String SEARCH ="http://morguo.com/search.php?mod=forum&androidflag=1";

    /**
     *  按分类或地区展示电影的列表
     *  按id进行请求数据,例如"动作"的id是2,"喜剧"的id是3,"美国"的id是5
     *  GET请求,
     *  分页加载,按页请求,第一页page=1,第二页page=2依次类推
     */
    public static final String SEARCH_BY_ID="http://morguo.com/forum.php?mod=threadbytag&id=2&androidflag=1&page=1";

    /**
     *  电影列表页点击某一项查看电影详情
     *  按tid请求数据,例如"霍比特人3：五军之战"的tid是703;注意page=1字段是不变的;
     *  GET请求,
     *  电影详情
     *
     */
    public static final String ViEW_DETAILS_BY_TID="http://morguo.com/forum.php?mod=viewthread&tid=703&androidflag=1&noticetype=&noticeid=&page=1";

//    /**
//     *  播放电影,在字段 "trailerurl": "http://vfx.mtime.cn/Video/2014/11/07/mp4/141107092713358923.mp4",
//     *   电影2分钟长一点时,不缓存
//     */
//    public static final String ="http://124.202.164.9/mp4files/2225000008BC0C1B/vf1.mtime.cn/Video/2014/11/07/mp4/141107092713358923.mp4";




}
