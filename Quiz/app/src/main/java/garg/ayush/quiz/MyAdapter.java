package garg.ayush.quiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Points> arrayList;

    public MyAdapter(Context ctx, ArrayList<Points> arrayList) {
        this.ctx = ctx;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("TAG", "onCreateViewHolder: +hhjjhbh");
        LayoutInflater li = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        View inflatedView = li.inflate(R.layout.item_row, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Points points = arrayList.get(position);
//        Log.e("TAG-------------------", "onBindViewHolder: "+points.points );
        holder.points.setText(points.points+"");
        holder.Name.setText(points.getEmail());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name,points;
        public ViewHolder(View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.Name);
            points=itemView.findViewById(R.id.points);
        }
    }
}
