package com.booboomx.gank.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by booboomx on 17/1/18.
 */

public class MeizhiBean implements Serializable {

    /**
     * error : false
     * results : [{"_id":"587c6073421aa91194ca0e2c","source":"chrome","who":"daimajia","publishedAt":"2017-01-16T14:12:18.71Z","used":true,"createdAt":"2017-01-16T13:56:03.417Z","type":"福利","desc":"1-16","url":"http://ww3.sinaimg.cn/large/610dc034gw1fbsfgssfrwj20u011h48y.jpg"},{"_id":"5878471d421aa9119735ac13","source":"chrome","who":"daimajia","publishedAt":"2017-01-13T11:58:16.991Z","used":true,"createdAt":"2017-01-13T11:18:53.183Z","type":"福利","desc":"1-13","url":"http://ww3.sinaimg.cn/large/610dc034gw1fbou2xsqpaj20u00u0q4h.jpg"},{"_id":"5876e811421aa9315bfbe85f","source":"chrome","who":"daimajia","publishedAt":"2017-01-12T11:30:59.369Z","used":true,"createdAt":"2017-01-12T10:21:05.74Z","type":"福利","desc":"1-12","url":"http://ww2.sinaimg.cn/large/0060lm7Tgw1fbnmsjogt9j30u00u0jvv.jpg"},{"_id":"58758a6e421aa9315bfbe854","source":"chrome","who":"daimajia","publishedAt":"2017-01-11T12:05:20.787Z","used":true,"createdAt":"2017-01-11T09:29:18.702Z","type":"福利","desc":"1-11","url":"http://ww4.sinaimg.cn/large/0060lm7Tgw1fbmfo9is9hj30u00u0ai3.jpg"},{"_id":"58745425421aa93161103dd7","source":"chrome","who":"daimajia","publishedAt":"2017-01-10T11:33:19.525Z","used":true,"createdAt":"2017-01-10T11:25:25.871Z","type":"福利","desc":"1-10","url":"http://ww3.sinaimg.cn/large/610dc034gw1fbldexdog4j20u011h41b.jpg"},{"_id":"5872f7f2421aa9316407fb84","source":"chrome","who":"daunahu","publishedAt":"2017-01-09T11:46:59.821Z","used":true,"createdAt":"2017-01-09T10:39:46.599Z","type":"福利","desc":"1-9","url":"http://ww1.sinaimg.cn/large/610dc034gw1fbk6h23k3ij20u00jymyw.jpg"},{"_id":"586e1aad421aa9315ea79905","source":"chrome","who":"daimajia","publishedAt":"2017-01-06T13:20:19.591Z","used":true,"createdAt":"2017-01-05T18:06:37.810Z","type":"福利","desc":"1-5","url":"http://ww4.sinaimg.cn/large/610dc034gw1fbfwwsjh3zj20u00u00w1.jpg"},{"_id":"586d8f74421aa9316407fb56","source":"chrome","who":"daimajia ","publishedAt":"2017-01-05T13:18:10.185Z","used":true,"createdAt":"2017-01-05T08:12:36.360Z","type":"福利","desc":"1-5","url":"http://ww4.sinaimg.cn/large/610dc034jw1fbffqo6jjoj20u011hgpx.jpg"},{"_id":"586c63a6421aa94dc1ac0b02","source":"chrome","who":"daimajia","publishedAt":"2017-01-04T11:39:01.326Z","used":true,"createdAt":"2017-01-04T10:53:26.957Z","type":"福利","desc":"1-4","url":"http://ww4.sinaimg.cn/large/610dc034jw1fbeerrs7aqj20u011htec.jpg"},{"_id":"586b0915421aa94dbbd82bcf","source":"chrome","who":"daimajia","publishedAt":"2017-01-03T11:51:31.742Z","used":true,"createdAt":"2017-01-03T10:14:45.467Z","type":"福利","desc":"1-3","url":"http://ww3.sinaimg.cn/large/610dc034jw1fbd818kkwjj20u011hjup.jpg"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 587c6073421aa91194ca0e2c
         * source : chrome
         * who : daimajia
         * publishedAt : 2017-01-16T14:12:18.71Z
         * used : true
         * createdAt : 2017-01-16T13:56:03.417Z
         * type : 福利
         * desc : 1-16
         * url : http://ww3.sinaimg.cn/large/610dc034gw1fbsfgssfrwj20u011h48y.jpg
         */

        private String _id;
        private String source;
        private String who;
        private String publishedAt;
        private boolean used;
        private String createdAt;
        private String type;
        private String desc;
        private String url;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
