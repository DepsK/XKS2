package com.dream.xukuan.xk2stu2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author XK
 * @date 2018/3/13.
 */
public class HWAdapter extends BaseAdapter {

    private final Context context;
    private final List<HWContentBean.InfoEntity> entityList;
    private LayoutInflater inflater;

    public HWAdapter(Context context, List<HWContentBean.InfoEntity> entityList) {
        this.context = context;
        this.entityList = entityList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return entityList.size();
    }

    @Override
    public Object getItem(int position) {
        return entityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_hw_item_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        HWContentBean.InfoEntity entity = entityList.get(position);
        holder.tv_title.setText(entity.getTitle());
        holder.tv_content.setText(entity.getContent());
        holder.tv_pingLun.setText(entity.getCommentCount()+"");
        holder.tv_ifc.setText(entity.getIfc()+"");
        holder.tv_ipc.setText(entity.getIpc()+"");
        String imgUrl = entity.getPhoto();
        Picasso.with(context).load("http://ft-info.fit-time.cn/" + imgUrl.substring(imgUrl.indexOf("/") + 1)).into(holder.iv_head);
        return convertView;
    }

    class ViewHolder{
        ImageView iv_head;
        TextView tv_title, tv_content, tv_pingLun, tv_ipc, tv_ifc;
        public ViewHolder(View view) {
            iv_head = (ImageView) view.findViewById(R.id.iv_head);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_pingLun = (TextView) view.findViewById(R.id.tv_pingLun);
            tv_ipc = (TextView) view.findViewById(R.id.tv_ipc);
            tv_ifc = (TextView) view.findViewById(R.id.tv_ifc);
        }
    }
}