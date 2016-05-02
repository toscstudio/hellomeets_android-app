package com.hellomeets.android.fragment.upcoming;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellomeets.android.EventDetailActivity;
import com.hellomeets.android.R;
import com.hellomeets.android.utils.DummyContent.DummyItem;

import java.util.List;

public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private Activity mActivity;

    public UpcomingEventAdapter(List<DummyItem> items, Activity mActivity) {
        mValues = items;
        this.mActivity = mActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        /*holder.mIdView.setText(mValues.get(position).id);
        holder.mEventView.setText(mValues.get(position).content);*/

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, EventDetailActivity.class);
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String transitionName = context.getString(R.string.transition_string);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, holder.mBannerImageView, transitionName);
                    context.startActivity(intent, options.toBundle());
                } else {
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mBannerImageView;
        public final TextView mDate;
        public final TextView mEventView;
        public final TextView mEventPrice;
        public final Button mBookingButton;
        public final TextView mSpeakers;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mDate = (TextView) view.findViewById(R.id.date);
            mEventView = (TextView) view.findViewById(R.id.event_title);
            mEventPrice = (TextView) view.findViewById(R.id.price);
            mBookingButton = (Button) view.findViewById(R.id.book);
            mSpeakers = (TextView) view.findViewById(R.id.speakers);
            mBannerImageView = (ImageView) view.findViewById(R.id.event_thumbnail);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mEventView.getText() + "'";
        }
    }
}
