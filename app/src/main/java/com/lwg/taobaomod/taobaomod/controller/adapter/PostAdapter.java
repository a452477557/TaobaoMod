package com.lwg.taobaomod.taobaomod.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lwg.taobaomod.taobaomod.R;
import com.lwg.taobaomod.taobaomod.controller.Constans;
import com.lwg.taobaomod.taobaomod.controller.entity.Post;

import java.util.List;

/**
 * 主贴列表的适配器
 */
public class PostAdapter extends BaseAdapter{
    LayoutInflater inflater;
    List<Post> list;
/*

    public PostAdapter(Fragment_one fragment_one) {
    }
*/

    public void setList(List<Post> list) {
        this.list = list;
    }

    BitmapUtils bitmapUtils;

    public PostAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        bitmapUtils = new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return (list == null)? 0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder  holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_post, null);
            holder = new ViewHolder();
            holder.subject = (TextView) convertView.findViewById(R.id.subject);
            holder.str1 = (TextView) convertView.findViewById(R.id.str1);
            holder.num1 = (TextView) convertView.findViewById(R.id.num1);
            holder.num2 = (TextView) convertView.findViewById(R.id.num2 );
            holder.pic = (ImageView) convertView.findViewById(R.id.pic);
            holder.tags = (TextView) convertView.findViewById(R.id.tags);
            holder.rate = (TextView) convertView.findViewById(R.id.rate);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final Post item = list.get(position);

        holder.subject.setText(item.getSubject());
        holder.tags.setText(item.getTags());
        holder.str1.setText("$"+item.getStr1());
        holder.num1.setText(item.getNum1());
        holder.num2.setText(item.getNum2());
        holder.rate.setText(item.getRate()+"人付款");
        holder.num2.setText(item.getNum2());

       // 判断是否有图片附件
        if (item.getPic().equals("")){
            holder.pic_box.setVisibility(View.GONE);
        }else{
          // holder.pic_box.setVisibility(View.VISIBLE);
            String pic = Constans.URL_BASE+item.getPic();
            bitmapUtils.display(holder.pic, pic);
        }
        // 编写图片的点击事件
        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control.handleImageClick(item);
            }
        });
        return convertView;
    }

    public void setControl(IControl control) {
        this.control = control;
    }

    IControl control;
    public interface IControl{
        void handleImageClick(Post item);
    }

    public static class ViewHolder{
        TextView subject;
        TextView str1;//价格
         TextView rate; //付款人数
        TextView num1; //金币抵:
        TextView num2;//包邮:    1-是; 0-否
        TextView  tags; //天猫:	1-是; 0-否
        RelativeLayout pic_box;
        ImageView pic; //upload/ps1.jpg
    }


}
