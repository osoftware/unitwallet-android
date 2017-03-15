package com.breadwallet.presenter.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.breadwallet.R;
import com.breadwallet.presenter.activities.IntroSetPitActivity;
import com.breadwallet.presenter.activities.IntroWriteDownActivity;
import com.breadwallet.presenter.activities.UpdatePitActivity;
import com.breadwallet.presenter.entities.BRMenuItem;
import com.breadwallet.presenter.entities.BRSecurityCenterItem;

import java.util.ArrayList;
import java.util.List;

/**
 * BreadWallet
 * <p>
 * Created by Mihail Gutan <mihail@breadwallet.com> on 6/29/15.
 * Copyright (c) 2016 breadwallet LLC
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

public class FragmentSecurityCenter extends Fragment {
    private static final String TAG = FragmentSecurityCenter.class.getName();

    public ListView mListView;
    public RelativeLayout layout;
    public List<BRSecurityCenterItem> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.

        View rootView = inflater.inflate(R.layout.fragment_security_center, container, false);

        itemList = new ArrayList<>();
        itemList.add(new BRSecurityCenterItem("6-Digit PIN", "Unlocks your Bread, authorizes send money.", R.drawable.ic_check_mark_blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: 6-Digit PIN");
                Intent intent = new Intent(getActivity(), UpdatePitActivity.class);
                startActivity(intent);
            }
        }));
        itemList.add(new BRSecurityCenterItem("FingerPrint", "Unlocks your Bread, authorizes send money to set limit.", R.drawable.ic_check_mark_grey, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: Touch ID");
            }
        }));
        itemList.add(new BRSecurityCenterItem("Paper Key", "Restores your Bread on new devices and after software updates.", R.drawable.ic_check_mark_grey, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: Paper Key");
                Intent intent = new Intent(getActivity(), IntroWriteDownActivity.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_right);
            }
        }));

        mListView = (ListView) rootView.findViewById(R.id.menu_listview);
        mListView.setAdapter(new SecurityCenterListAdapter(getContext(), R.layout.menu_list_item, itemList));

        return rootView;
    }

    public RelativeLayout getMainLayout() {
        return layout;
    }

    public class SecurityCenterListAdapter extends ArrayAdapter<BRSecurityCenterItem> {

        private List<BRSecurityCenterItem> items;
        private Context mContext;
        private int defaultLayoutResource = R.layout.security_center_list_item;

        public SecurityCenterListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<BRSecurityCenterItem> items) {
            super(context, resource);
            this.items = items;
            this.mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//            Log.e(TAG, "getView: pos: " + position + ", item: " + items.get(position));
            if (convertView == null) {
                // inflate the layout
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(defaultLayoutResource, parent, false);
            }
            TextView title = (TextView) convertView.findViewById(R.id.item_title);
            TextView text = (TextView) convertView.findViewById(R.id.item_text);
            ImageView checkMark = (ImageView) convertView.findViewById(R.id.check_mark);

            title.setText(items.get(position).title);
            text.setText(items.get(position).text);
            checkMark.setImageResource(items.get(position).checkMarkResId);
            convertView.setOnClickListener(items.get(position).listener);
            return convertView;

        }

        @Override
        public int getCount() {
            return items == null ? 0 : items.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}