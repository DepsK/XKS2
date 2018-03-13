package com.dream.xukuan.xk2stu2;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class ThirdTopBean {

    String subject;
    String imageUrl;

    public ThirdTopBean(){

    }

    public ThirdTopBean(String subject, String imageUrl) {
        this.subject = subject;
        this.imageUrl = imageUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "FirstTopBean{" +
                "subject='" + subject + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}