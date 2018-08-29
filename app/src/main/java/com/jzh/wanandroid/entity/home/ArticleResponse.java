package com.jzh.wanandroid.entity.home;

import com.jzh.wanandroid.entity.base.BaseResponse;

import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/20 16:45
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ArticleResponse extends BaseResponse {

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
        private Boolean over;
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

        public Boolean isOver() {
            return over;
        }

        public void setOver(Boolean over) {
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
             * author : kelinZhou
             * chapterId : 400
             * chapterName : ViewPager
             * collect : false
             * courseId : 13
             * desc : 基于ViewPger封装的轮播图，支持无限轮播、支持自定义动画、支持自定义时长。
             * <p>
             * envelopePic : http://www.wanandroid.com/blogimgs/b3ac93c6-b7c2-4987-93a7-96c15ada71e4.png
             * fresh : false
             * id : 3271
             * link : http://www.wanandroid.com/blog/show/2293
             * niceDate : 2018-08-16
             * origin :
             * projectLink : https://github.com/kelinZhou/Banner
             * publishTime : 1534397931000
             * superChapterId : 294
             * superChapterName : 开源项目主Tab
             * tags : [{"name":"项目","url":"/project/list/1?cid=400"}]
             * title : 基于ViewPger封装的轮播图 Banner
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
                 * url : /project/list/1?cid=400
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
