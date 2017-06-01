package wz.cifz.zx.moudle.bean;

import java.util.List;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/29.
 */

public class AumsePic {

    /**
     * status : true
     * tngou : [{"count":25535,"fcount":0,"galleryclass":4,"id":1036,"img":"/ext/161223/7083a1fde72448a62e477c5aab0721c8.jpg","rcount":0,"size":11,"time":1482494705000,"title":"大胸美女性感爆乳丰满胸围性感图片"},{"count":79048,"fcount":0,"galleryclass":1,"id":1035,"img":"/ext/161223/395b860c06ccaf5b35cde317ff082c18.jpg","rcount":0,"size":9,"time":1482494660000,"title":"蕾丝透视装美女性感包臀裙极品私房照"},{"count":52221,"fcount":0,"galleryclass":3,"id":1034,"img":"/ext/161223/905b7784c0aeb91870fb40d34defae5d.jpg","rcount":0,"size":11,"time":1482494627000,"title":"风骚迷人性感美女凌凌美腿丝袜诱惑写真"},{"count":32842,"fcount":0,"galleryclass":6,"id":1033,"img":"/ext/161213/c5f1416b4feb857b8d711f83dc692885.jpg","rcount":0,"size":18,"time":1481628679000,"title":"亚洲美女菲儿火辣身材白皙肌肤性感人体艺术"},{"count":41643,"fcount":0,"galleryclass":1,"id":1032,"img":"/ext/161213/a94ead894d0d0e4e5b3b807626eeab4d.jpg","rcount":0,"size":10,"time":1481628573000,"title":"大胸美女御姐酥胸事业线美腿妖娆性感"}]
     * total : 1022
     */

    private boolean status;
    private int total;
    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        /**
         * count : 25535
         * fcount : 0
         * galleryclass : 4
         * id : 1036
         * img : /ext/161223/7083a1fde72448a62e477c5aab0721c8.jpg
         * rcount : 0
         * size : 11
         * time : 1482494705000
         * title : 大胸美女性感爆乳丰满胸围性感图片
         */

        private int count;
        private int fcount;
        private int galleryclass;
        private long id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
