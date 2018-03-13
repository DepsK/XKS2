package com.dream.xukuan.xk2stu2;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class FirstTopBean {

    String subject;
    String imageUrl;

    public FirstTopBean(){

    }

    public FirstTopBean(String subject, String imageUrl) {
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