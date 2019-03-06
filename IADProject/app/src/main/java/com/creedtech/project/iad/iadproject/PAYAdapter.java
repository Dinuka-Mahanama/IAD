package com.creedtech.project.iad.iadproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PAYAdapter extends BaseAdapter {
    Context context;

    List<PAYClass> subject_list;

    public PAYAdapter(List<PAYClass> listValue, Context context)
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
        payViewItem payViewItem = null;
        if(convertView == null)
        {
            payViewItem = new payViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.pay_listview_items, null);

            payViewItem.Transaction_IDTextView = (TextView)convertView.findViewById(R.id.Transaction_IDTextView);

            payViewItem.AmountTextView = (TextView)convertView.findViewById(R.id.AmountTextView);

            payViewItem.BankTextView = (TextView)convertView.findViewById(R.id.BankTextView);

            payViewItem.Account_NoTextView = (TextView)convertView.findViewById(R.id.Account_NoTextView);

            payViewItem.Customer_IDTextView = (TextView)convertView.findViewById(R.id.Customer_IDTextView);

            payViewItem.Pay_TypeTextView = (TextView)convertView.findViewById(R.id.Pay_TypeTextView);
            convertView.setTag(payViewItem);
        }
        else
        {
            payViewItem = (payViewItem) convertView.getTag();
        }

        payViewItem.Transaction_IDTextView.setText(subject_list.get(position).Transaction_ID);

        payViewItem.AmountTextView.setText(subject_list.get(position).Amount);

        payViewItem.Customer_IDTextView.setText(subject_list.get(position).Customer_ID);

        payViewItem.BankTextView.setText(subject_list.get(position).Bank);

        payViewItem.Account_NoTextView.setText(subject_list.get(position).Account_No);

        payViewItem.Customer_IDTextView.setText(subject_list.get(position).Customer_ID);

        payViewItem.Pay_TypeTextView.setText(subject_list.get(position).PayType);

        return convertView;
    }
}

class payViewItem
{
    TextView Transaction_IDTextView;
    TextView AmountTextView;
    TextView BankTextView;
    TextView Account_NoTextView;
    TextView Customer_IDTextView;
    TextView Pay_TypeTextView;
}
