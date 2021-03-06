package com.example.lee.recyclerview_revised;

import com.example.lee.floatbuttonlibrary.FloatingActionButton;
import com.example.lee.floatbuttonlibrary.FloatingActionsMenu;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.GestureDetector;
//import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    ItemData itemData;
    ArrayList<ItemData> Tasklist = new ArrayList<ItemData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindData();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("CircleLife");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        //toolbar.setBackgroundColor(getResources().getColor(R.color.trivial_color));
        //action button
        final View actionB = findViewById(R.id.action_b);
       /* final View actionD = findViewById(R.id.action_d);
        final View actionE = findViewById(R.id.action_e);
*/

        SwipeDismissRecyclerViewTouchListener touchListener =
                new SwipeDismissRecyclerViewTouchListener(
                        recyclerView,
                        new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    myAdapter.Carrylist.remove(position);
                                }
                                // do not call notifyItemRemoved for every item, it will cause gaps on deleting items
                                myAdapter.notifyDataSetChanged();
                            }
                        });

        recyclerView.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        recyclerView.setOnScrollListener(touchListener.makeScrollListener());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView,
                new OnItemRecycleViewClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                       // Toast.makeText(MainActivity.this, "Clicked " + myAdapter.Carrylist.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this,CaldroidSampleActivity.class);
                        startActivity(intent);
                    }
                }));

        FloatingActionButton actionC = new FloatingActionButton(getBaseContext());
        FloatingActionButton actionD = new FloatingActionButton(getBaseContext());
        actionD.setTitle("Health");
        actionD.setColorNormalResId(R.color.health_color);
       // actionD.setColorPressedResId(R.color.pink_pressed);
        //actionD.setIcon(R.drawable.health);
        FloatingActionButton actionE = new FloatingActionButton(getBaseContext());
        actionE.setTitle("Work");
        actionE.setColorNormalResId(R.color.Work_color);
        // actionD.setColorPressedResId(R.color.pink_pressed);
        //actionD.setIcon(R.drawable.health);

        actionC.setTitle("Trivial");
        actionC.setColorNormalResId(R.color.trivial_color);
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);

                itemData = new ItemData("Task", R.drawable.rating_good);
                Tasklist.add(itemData);
                BindData();
            }
        });
        //�s�WButton��Button Menu
        ((FloatingActionsMenu) findViewById(R.id.multiple_actions)).addButton(actionC);
        ((FloatingActionsMenu) findViewById(R.id.multiple_actions)).addButton(actionD);
        ((FloatingActionsMenu) findViewById(R.id.multiple_actions)).addButton(actionE);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "buttonB", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TaskPage.class);
                intent.putExtra("primarycolor", R.color.Bhabits_color);
                intent.putExtra("title","Bad Habits");
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        actionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "buttonB", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TaskPage.class);
                intent.putExtra("primarycolor", R.color.health_color);
                intent.putExtra("title","Health");
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        actionE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "buttonB", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TaskPage.class);
                intent.putExtra("primarycolor", R.color.Work_color);
                intent.putExtra("title","Works");
                startActivity(intent);
                MainActivity.this.finish();
            }
        });


    }
    protected void BindData(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // cardView = (CardView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // MyAdapter mAdapter = new MyAdapter(itemsData);
        //myAdapter = new MyAdapter(Tasklist,this);
        myAdapter = new MyAdapter(Tasklist);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }
    //ToolBar Listener
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_share:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    //RecyclerView Listener
    public void onItemClicked(int position, MyAdapter mAdapter) {
        // TODO Auto-generated method stub

        //Toast.makeText(this, myAdapter.Carrylist.get(position).getTitle(), Toast.LENGTH_LONG).show();

    }


    public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemRecycleViewClickListener mListener;

        private static final long DELAY_MILLIS = 100;

        private RecyclerView mRecyclerView;
        private GestureDetector mGestureDetector;
        private boolean mIsPrepressed = false;
        private boolean mIsShowPress = false;
        private View mPressedView = null;

        public RecyclerItemClickListener(RecyclerView recyclerView, OnItemRecycleViewClickListener listener) {
            mListener = listener;
            mRecyclerView = recyclerView;
            mGestureDetector = new GestureDetector(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    mIsPrepressed = true;
                    mPressedView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                    super.onDown(e);
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent e) {
                    if (mIsPrepressed && mPressedView != null) {
                        mPressedView.setPressed(true);
                        mIsShowPress = true;
                    }
                    super.onShowPress(e);
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    if (mIsPrepressed && mPressedView != null) {
                        mPressedView.setPressed(true);
                        final View pressedView = mPressedView;
                        pressedView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pressedView.setPressed(false);
                            }
                        }, DELAY_MILLIS);
                        mIsPrepressed = false;
                        mPressedView = null;
                    }
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClicked(childView, view.getChildPosition(childView));
            } else if (e.getActionMasked() == MotionEvent.ACTION_UP && mPressedView != null && mIsShowPress) {
                mPressedView.setPressed(false);
                mIsShowPress = false;
                mIsPrepressed = false;
                mPressedView = null;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        public void onRequestDisallowInterceptTouchEvent(boolean b){
            //return false;
        }
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
}
