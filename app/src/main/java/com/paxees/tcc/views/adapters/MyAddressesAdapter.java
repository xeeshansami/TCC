//package com.paxees.tcc.views.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.paxees.tcc.R;
//import com.paxees.tcc.network.networkmodels.response.models.MyAddressesListResponse;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//public class MyAddressesAdapter extends RecyclerView.Adapter<MyAddressesAdapter.ViewHolder> {
//    private int row_index;
//    private MyAddressesListResponse mData;
//    private LayoutInflater mInflater;
//    private ItemClickListener mClickListener;
//    private Context context;
//    private String branchImage;
//
//    // data is passed into the constructor
//    public MyAddressesAdapter(Context context, MyAddressesListResponse data) {
//        this.mInflater = LayoutInflater.from(context);
//        this.mData = data;
//        this.context = context;
//    }
//
//    // inflates the row layout from xml when needed
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.item_views_myaddresses, parent, false);
//        return new ViewHolder(view);
//    }
//
//    // binds the data to the TextView in each row
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        MyAddressesListResponse data = mData;
//        holder.titleLbl.setText("Billing Address");
//        holder.titleValue.setText(data.getShipping().getAddress1());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData    }
//
//
//    // stores and recycles views as they are scrolled off screen
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView titleLbl, titleValue;
//        ImageView imageView;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            titleLbl = itemView.findViewById(R.id.txtCardNum);
//            titleValue = itemView.findViewById(R.id.visaCardTv);
//            imageView = itemView.findViewById(R.id.payment_method_done);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
//    }
//
//    // convenience method for getting data at click position
//    MyAddressesListResponse getItem(int id) {
//        return mData;
//    }
//
//    // allows clicks events to be caught
//    public void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
//}