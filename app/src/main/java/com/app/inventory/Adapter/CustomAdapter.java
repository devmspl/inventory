 package com.app.inventory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.inventory.R;
import com.app.inventory.StaticModels.InventoryModels;
import com.app.inventory.StaticModels.SpinnerInventoryYearModel;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final int mResource;
    private final List<SpinnerInventoryYearModel> items;


    public CustomAdapter(@NonNull Context context, @LayoutRes int resource,
                         @NonNull List objects) {
        super(context, resource, 0, objects);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView tvcategory = (TextView) view.findViewById(R.id.tv_category);
        tvcategory.setText(items.get(position).getYear());

        tvcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


//        Offer offerData = items.get(position);
//
//        offTypeTv.setText(offerData.getOfferType());
//        numOffersTv.setText(offerData.getNumberOfCoupons());
//        maxDiscTV.setText(offerData.getMaxDicount());

        return view;
    }
}
