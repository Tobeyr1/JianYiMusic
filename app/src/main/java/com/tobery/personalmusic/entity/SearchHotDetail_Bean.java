package com.tobery.personalmusic.entity;

import java.util.List;

public class SearchHotDetail_Bean {

    /**
     * code : 200
     * data : [{"searchWord":"永不失联的爱","score":3793979,"content":"你是我 这一辈子都不想失联的爱","source":0,"iconType":1,"iconUrl":"https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png","url":"","alg":"alg_statistics"},{"searchWord":"是想你的声音啊","score":3027346,"content":"我在哼你最爱听的旋律","source":0,"iconType":1,"iconUrl":"https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png","url":"","alg":"alg_statistics"},{"searchWord":"BMG官宣合作啦","score":2784738,"content":"网易云音乐与BMG达成战略合作","source":1,"iconType":0,"iconUrl":"http://p4.music.126.net/P4mXkx6VKXLFqVo5ohHxDg==/109951163992439900.jpg","url":"https://music.163.com/m/at/5f28ccbeb3710ab67ae26fe8","alg":"featured"},{"searchWord":"他只是经过","score":2577025,"content":"他曾说的话 有没有兑现？\n","source":0,"iconType":1,"iconUrl":"https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png","url":"","alg":"alg_statistics"},{"searchWord":"薛之谦","score":2097819,"content":"老薛一发歌就能掀起狂潮！","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"波西米亚狂想曲","score":1958368,"content":"Queen的金曲，越听越上瘾","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"画画的baby","score":1839992,"content":"奔驰的小野马 和带刺的玫瑰","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"猪猪侠","score":1767625,"content":"噜啦噜啦咧 猪猪侠来了！","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"中国新说唱","score":1647295,"content":"这个夏天有新说唱才圆满！","source":1,"iconType":0,"iconUrl":"http://p4.music.126.net/P4mXkx6VKXLFqVo5ohHxDg==/109951163992439900.jpg","url":"","alg":"featured"},{"searchWord":"街灯晚餐","score":1558973,"content":"不羡烛光 只想与你街灯下晚餐","source":0,"iconType":5,"iconUrl":"https://p1.music.126.net/PtgUJbcvDAY0HKWpmOB2HA==/109951163967988470.png","url":"","alg":"alg_statistics"},{"searchWord":"Wake Me Up","score":982576,"content":"穿越黑暗，寻找自己的方向！","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"Your Man","score":921262,"content":"I wanna be your man","source":0,"iconType":5,"iconUrl":"https://p1.music.126.net/PtgUJbcvDAY0HKWpmOB2HA==/109951163967988470.png","url":"","alg":"alg_statistics"},{"searchWord":"林俊杰","score":876895,"content":"当之无愧的行走CD机！","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"漫步人生路","score":814513,"content":"邓丽君最后一张以广东话演唱的专辑\n","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"我爱你","score":813136,"content":"做我最后一个爱人 清晨到黄昏","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"鲸落","score":775387,"content":"巨鲸落 万物生","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"皇后乐队","score":767560,"content":"20世纪70年代摇滚乐的模范代表","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"伤心太平洋","score":763711,"content":"听这歌 就想叫姑姑","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"张杰","score":760442,"content":"张杰带你穿越人海","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"疑心病","score":758404,"content":"爱情里的疑心病，每个人都会犯吧~","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"}]
     * message : success
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchWord : 永不失联的爱
         * score : 3793979
         * content : 你是我 这一辈子都不想失联的爱
         * source : 0
         * iconType : 1
         * iconUrl : https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png
         * url :
         * alg : alg_statistics
         */

        private String searchWord;
        private int score;
        private String content;
        private int source;
        private int iconType;
        private String iconUrl;
        private String url;
        private String alg;

        public String getSearchWord() {
            return searchWord;
        }

        public void setSearchWord(String searchWord) {
            this.searchWord = searchWord;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getIconType() {
            return iconType;
        }

        public void setIconType(int iconType) {
            this.iconType = iconType;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }
    }
}
