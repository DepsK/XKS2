package com.dream.xukuan.xk2stu2;

import java.util.List;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class HWContentBean {

    private List<InfoEntity> infos;

    public List<InfoEntity> getInfos() {
        return infos;
    }

    public void setInfos(List<InfoEntity> infos) {
        this.infos = infos;
    }

    public static class InfoEntity{
        private String title;
        private String content;
        private String photo;
        private String commentCount;
        private String ipc;
        private String ifc;


        @Override
        public String toString() {
            return "InfoEntity{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", photo='" + photo + '\'' +
                    ", commentCount='" + commentCount + '\'' +
                    ", ipc='" + ipc + '\'' +
                    ", ifc='" + ifc + '\'' +
                    '}';
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getIpc() {
            return ipc;
        }

        public void setIpc(String ipc) {
            this.ipc = ipc;
        }

        public String getIfc() {
            return ifc;
        }

        public void setIfc(String ifc) {
            this.ifc = ifc;
        }
    }

} 