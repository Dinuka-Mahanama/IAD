package com.creedtech.project.iad.iadproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CHAdapter extends BaseAdapter {
    Context context;

    List<CHClass> subject_list;

    public CHAdapter(List<CHClass> listValue, Context context)
    {
        this.context = context;
        this.subject_list = listValue;
    }

    @Override
    public int getCount()
    {
        return this.subject_list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.subject_list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;
        if(convertView == null)
        {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.ch_listview_items, null);

            viewItem.Claim_IDTextView = (TextView)convertView.findViewById(R.id.Claim_IDTextView);

            viewItem.Vehicle_IDTextView = (TextView)convertView.findViewById(R.id.Vehicle_IDTextView);

            viewItem.Customer_IDTextView = (TextView)convertView.findViewById(R.id.Customer_IDTextView);

            viewItem.Claim_StatusTextView = (TextView)convertView.findViewById(R.id.Claim_StatusTextView);

            viewItem.Amount_AssignedTextView = (TextView)convertView.findViewById(R.id.Amount_AssignedTextView);

            viewItem.Amount_PayedTextView = (TextView)convertView.findViewById(R.id.Amount_PayedTextView);

            viewItem.Transaction_IDTextView = (TextView)convertView.findViewById(R.id.Transaction_IDTextView);

            viewItem.Claim_Details_IDTextView = (TextView)convertView.findViewById(R.id.Claim_Details_IDTextView);
            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.Claim_IDTextView.setText(subject_list.get(position).Claim_ID);

        viewItem.Vehicle_IDTextView.setText(subject_list.get(position).Vehicle_ID);

        viewItem.Customer_IDTextView.setText(subject_list.get(position).Customer_ID);

        viewItem.Vehicle_IDTextView.setText(subject_list.get(position).Vehicle_ID);

        viewItem.Claim_StatusTextView.setText(subject_list.get(position).Claim_Status);

        viewItem.Amount_AssignedTextView.setText(subject_list.get(position).Amount_Assigned);

        viewItem.Amount_PayedTextView.setText(subject_list.get(position).Amount_Payed);

        viewItem.Transaction_IDTextView.setText(subject_list.get(position).Transaction_ID);

        viewItem.Claim_Details_IDTextView.setText(subject_list.get(position).Claim_Details_ID);

        return convertView;
    }
}

class ViewItem
{
    TextView Claim_IDTextView;
    TextView Vehicle_IDTextView;
    TextView Customer_IDTextView;
    TextView Claim_StatusTextView;
    TextView Amount_AssignedTextView;
    TextView Amount_PayedTextView;
    TextView Transaction_IDTextView;
    TextView Claim_Details_IDTextView;
}
