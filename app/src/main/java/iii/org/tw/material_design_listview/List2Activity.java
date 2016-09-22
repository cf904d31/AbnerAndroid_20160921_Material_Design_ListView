package iii.org.tw.material_design_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import io.github.codefalling.recyclerviewswipedismiss.SwipeDismissRecyclerViewTouchListener;

public class List2Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String[] dataset = {"Item 1","Item 2","Item 3","Item 4","Item 5","Item 6","Item 7"};
    private LinkedList<String> data;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        data = new LinkedList<>();

        for (String s : dataset) {
            data.add(s);
        }
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);




        //-----https://github.com/CodeFalling/RecyclerViewSwipeDismiss




        SwipeDismissRecyclerViewTouchListener listener = new SwipeDismissRecyclerViewTouchListener.Builder(
                recyclerView,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view) {
                        // Do what you want when dismiss
                        int p = recyclerView.getChildPosition(view);
                        data.remove(p);
                        mAdapter.notifyDataSetChanged();

                    }
                })
                .setIsVertical(false)
                .setItemTouchCallback(
                        new SwipeDismissRecyclerViewTouchListener.OnItemTouchCallBack() {
                            @Override
                            public void onTouch(int index) {
                                // Do what you want when item be touched
                            }
                        })
                .setItemClickCallback(new SwipeDismissRecyclerViewTouchListener.OnItemClickCallBack() {
                    @Override
                    public void onClick(int position) {
                        // Do what you want when item be clicked                    }
                    }})
                            //.setBackgroundId(R.drawable.bg_item_normal, R.drawable.bg_item_selected)
                    .create();


        recyclerView.setOnTouchListener(listener);


    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public MyViewHolder(View v) {
                super(v);
                mTextView =(TextView) v.findViewById(R.id.recycler_item_title);
            }
        }

//        public MyAdapter() {
//        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            //-----老師寫的版本
//            View view = View.inflate(List2Activity.this,R.layout.layout_recycler_item_title,null);
//            MyViewHolder holder = new MyViewHolder(view);
            View view = View.inflate(List2Activity.this,R.layout.layout_recycler_item_title,null);
            MyViewHolder holder = new MyViewHolder(view);

            //-- google的版本
//            //--https://developer.android.com/training/material/lists-cards.html?hl=zh-tw
//            View view = LayoutInflater.from(List2Activity.this)
//                    .inflate(R.layout.layout_recycler_item_title, parent, false);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            holder.mTextView.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
