package com.xunmeng.jpush.ui.fragment.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunmeng.jpush.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/14.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private final LayoutInflater mInflater;
    private ArrayList<Integer> mHeights = new ArrayList<>();
    private OnItemClickLitener mOnItemClickLitener;
    private List<String> mDatas;
    private Context context;


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public HomeAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                mInflater.inflate(R.layout.item_video_summary, parent,
                        false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.img.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.img.setLayoutParams(lp);
        holder.tv.setText(mDatas.get(position));
        holder.img.setImageResource(R.drawable.me);

        // 濡傛灉璁剧疆浜嗗洖璋冿紝鍒欒缃偣鍑讳簨浠�
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
//                    removeData(pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView img;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_video_summary);
            tv = (TextView) view.findViewById(R.id.tv_video_summary);
        }
    }
}



