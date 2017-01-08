package com.angcyo.recyclerviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static Toast T;

    List<String> datas = new ArrayList<>();
    private MyAdatper mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        T = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);


        for (int i = 0; i < 20; i++) {
            datas.add("  [" + i + "]  ");
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new MyAdatper();
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                final int size = datas.size();
                for (int i = 0; i < 1; i++) {
                    //datas.add(2, "add:" + i);
                    datas.remove(2);
//                    datas.add(2, "new 2");
                }
//                mAdapter.notifyItemRangeInserted(2, 3);
//                mAdapter.notifyDataSetChanged();
//                mAdapter.notifyItemInserted(size);
//                mAdapter.notifyItemRangeChanged(5, datas.size() - 5);
                try {
//                    mAdapter.notifyItemRangeChanged(2, 1000);
//                    mAdapter.notifyDataSetChanged();

                    mAdapter.notifyItemRemoved(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyAdatper extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RelativeLayout layout = new RelativeLayout(MainActivity.this);

            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(R.mipmap.ic_launcher);

            TextView textView = new MyTextView(MainActivity.this);
            textView.setTextSize(16);

            layout.addView(imageView);
            layout.addView(textView);

            final MyViewHolder myViewHolder = new MyViewHolder(layout);
            myViewHolder.mTextView = textView;
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.mTextView.setText(datas.get(position) + " 测试--->" + position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.setText(datas.get(position) + " Position:" + position);
                    T.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
