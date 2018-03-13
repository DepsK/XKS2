package com.dream.xukuan.xk2stu2;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class HWJsonTask extends AsyncTask<String,Void,List<HWContentBean.InfoEntity>> {

    public interface CallBack{
        void getData(List<HWContentBean.InfoEntity> result);
    }

    CallBack cb;
    public HWJsonTask(CallBack cb){
        this.cb = cb;
    }
    @Override
    protected List<HWContentBean.InfoEntity> doInBackground(String... params) {
        List<HWContentBean.InfoEntity> entityList = new ArrayList<>();

        String url = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String tmp = null;
            if((tmp = br.readLine())!=null ){
                sb.append(tmp);
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("infos");
            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject infosObject = jsonArray.getJSONObject(i);
                HWContentBean.InfoEntity entity = new HWContentBean.InfoEntity();
                entity.setTitle(infosObject.getString("title"));
                entity.setContent(infosObject.getString("content"));
                entity.setPhoto(infosObject.getString("photo"));
                entity.setCommentCount(infosObject.getString("commentCount"));
                entity.setIfc(infosObject.getString("ifc"));
                entity.setIpc(infosObject.getString("ipc"));
                entityList.add(entity);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entityList;
    }

    @Override
    protected void onPostExecute(List<HWContentBean.InfoEntity> result) {
        super.onPostExecute(result);
        if(cb!=null){
            cb.getData(result);
        }
    }
}