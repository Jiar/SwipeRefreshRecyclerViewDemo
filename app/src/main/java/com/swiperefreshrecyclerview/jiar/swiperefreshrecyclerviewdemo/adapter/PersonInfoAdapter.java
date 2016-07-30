package com.swiperefreshrecyclerview.jiar.swiperefreshrecyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swiperefreshrecyclerview.jiar.swiperefreshrecyclerviewdemo.R;
import com.swiperefreshrecyclerview.jiar.swiperefreshrecyclerviewdemo.model.PersonInfo;

import java.util.List;

/**
 * Created by Jiar on 16/7/30.
 */
public class PersonInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<PersonInfo> personInfoList;
    private final static String TAG = "PersonInfoAdapter";
    private OnClickListener onClickListener;

    public PersonInfoAdapter(Context context, List<PersonInfo> personInfoList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.personInfoList = personInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.person_info_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        PersonInfo personInfo = personInfoList.get(position);
        viewHolder.person_info_firstName.setText(personInfo.getFirstName());
        viewHolder.person_info_lastName.setText(personInfo.getLastName());
        viewHolder.person_info_motto.setText(personInfo.getMotto());

        viewHolder.person_info_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.getPersonInfoItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout person_info_item;
        TextView person_info_firstName;
        TextView person_info_lastName;
        TextView person_info_motto;

        public ViewHolder(View itemView) {
            super(itemView);
            person_info_item = (LinearLayout)itemView.findViewById(R.id.person_info_item);
            person_info_firstName = (TextView)itemView.findViewById(R.id.person_info_firstName);
            person_info_lastName = (TextView)itemView.findViewById(R.id.person_info_lastName);
            person_info_motto = (TextView)itemView.findViewById(R.id.person_info_motto);
        }

    }

    public interface OnClickListener {
        void getPersonInfoItem(int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
