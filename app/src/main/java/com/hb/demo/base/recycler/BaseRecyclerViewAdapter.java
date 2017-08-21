package com.hb.demo.base.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hb on 15/12/10.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    /**
     * click listener
     */
    protected OnItemClickListener     mOnItemClickListener;
    /**
     * long click listener
     */
    protected OnItemLongClickListener mOnItemLongClickListener;
    /**
     * data
     */
    protected List<T>                 mList;
    protected Context                 mContext;
    private int                       mLayoutId;

    /**
     * @param list the datas to attach the adapter
     */
    public BaseRecyclerViewAdapter(Context context, List<T> list, int layoutId) {
        mContext = context;
        mList = list;
        mLayoutId = layoutId;
    }

    /**
     * get a item by index
     *
     * @param position
     * @return
     */
    protected T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * set a long click listener
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * set a click listener
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * inflate a view by viewgroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    /**
     * inflate a view by viewgroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @param attach
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, attach);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(inflateItemView(parent, mLayoutId));
    }

    /**
     * a final function to avoid you override use template design pattern
     *
     * @param vh
     * @param position
     */
    @Override
    public final void onBindViewHolder(RecyclerViewHolder vh, int position) {
        final T item = getItem(position);
        bindDataToItemView(vh, item);
        bindItemViewClickListener(vh, item);
    }

    /**
     * bind data to itemview
     *
     * @param vh viewholder
     * @param item item
     */
    protected abstract void bindDataToItemView(RecyclerViewHolder vh, T item);

    /**
     * bind click listner to itemview
     *
     * @param vh viewholder
     * @param item item
     */
    protected final void bindItemViewClickListener(RecyclerViewHolder vh, final T item) {
        if (mOnItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(view, item);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onLongClick(v, item);
                    return true;
                }
            });
        }
    }

    /**
     * BaseViewHolder using bindViewById(View view,int id) function to handle
     * the relations between view and viewId
     */
    public abstract static class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
            findView();
        }

        /**
         * you need to override this method to bind view in the viewholder you'd
         * better use bindViewById(View view,int id)
         */
        protected abstract void findView();

        /**
         * generic function to findViewById
         *
         * @param id viewId
         * @return the view found
         */
        protected <T extends View> T findViewById(int id) {
            return (T) itemView.findViewById(id);
        }

    }
}
