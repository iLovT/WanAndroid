package com.jzh.wanandroid.entity.project;

import com.jzh.wanandroid.entity.base.BaseResponse;

import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/24 10:57
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ProjectListResponse extends BaseResponse {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private Integer curPage;
        private Integer offset;
        private boolean over;
        private Integer pageCount;
        private Integer size;
        private Integer total;
        private List<DatasBean> datas;

        public Integer getCurPage() {
            return curPage;
        }

        public void setCurPage(Integer curPage) {
            this.curPage = curPage;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public Integer getPageCount() {
            return pageCount;
        }

        public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * author : ywp0919
             * chapterId : 294
             * chapterName : 完整项目
             * collect : false
             * courseId : 13
             * desc : 这是一个Flutter项目，基于Wan Android提供的api来完成的，非常感谢鸿阳大神，自己经常在这个网站上学习，做这个Flutter项目的话也可以装在自己手机上用来看一些技术文章。
             * 已有功能：用户登录注册、首页文章列表、首页Banner、知识体系、知识体系下的文章、收藏文章管理。
             * 待完成功能：在首页加入热门模块(包括热门搜索和热门网站)、加入搜索文章的功能。
             * <p>
             * <p>
             * envelopePic : http://wanandroid.com/blogimgs/ca28967d-a7cb-48ed-80ef-c63b544f2116.png
             * fresh : false
             * id : 3301
             * link : http://www.wanandroid.com/blog/show/2306
             * niceDate : 1天前
             * origin :
             * projectLink : https://github.com/ywp0919/wan_android_flutter
             * publishTime : 1534915840000
             * superChapterId : 294
             * superChapterName : 开源项目主Tab
             * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
             * title : 玩Android Flutter 版本
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private String author;
            private Integer chapterId;
            private String chapterName;
            private Boolean collect;
            private Integer courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private Long id;
            private String link;
            private String niceDate;
            private String origin;
            private String projectLink;
            private long publishTime;
            private Integer superChapterId;
            private String superChapterName;
            private String title;
            private Integer type;
            private Integer userId;
            private Integer visible;
            private Integer zan;
            private List<TagsBean> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public Integer getChapterId() {
                return chapterId;
            }

            public void setChapterId(Integer chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public Boolean isCollect() {
                return collect;
            }

            public void setCollect(Boolean collect) {
                this.collect = collect;
            }

            public Integer getCourseId() {
                return courseId;
            }

            public void setCourseId(Integer courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public Integer getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(Integer superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Integer getType() {
                return type;
            }

            public void setType(Integer type) {
                this.type = type;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public Integer getVisible() {
                return visible;
            }

            public void setVisible(Integer visible) {
                this.visible = visible;
            }

            public Integer getZan() {
                return zan;
            }

            public void setZan(Integer zan) {
                this.zan = zan;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class TagsBean {
                /**
                 * name : 项目
                 * url : /project/list/1?cid=294
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
