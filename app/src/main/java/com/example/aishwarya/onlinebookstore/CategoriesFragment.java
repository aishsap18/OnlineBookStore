package com.example.aishwarya.onlinebookstore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aishwarya on 02-Apr-17.
 */

public class CategoriesFragment extends Fragment {

    GridView grid,grid2,grid3,grid4,grid5;
    public static final String JSON_ARRAY = "result";
    public static final String TAG_bookId = "book_id";
    public static final String TAG_bookName = "book_name";
    public static final String TAG_authorName = "author_name";
    public static final String TAG_publication = "publication";
    public static final String TAG_category = "category";
    public static final String TAG_price = "price";
    public static final String TAG_description = "description";
    public static final String TAG_bookImagePath = "book_image_path";
    public static String key;
    EditText searchet;
    Button searchbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.categories_fragment, container, false);
        UserHomeActivity.title.setText("Book Store");

        grid = (GridView) mainView.findViewById(R.id.xCategoryGridView);
        grid2 = (GridView) mainView.findViewById(R.id.xCategoryGridView2);
        grid3 = (GridView) mainView.findViewById(R.id.xCategoryGridView3);
        grid4 = (GridView) mainView.findViewById(R.id.xCategoryGridView4);
        grid5 = (GridView) mainView.findViewById(R.id.xCategoryGridView5);
        searchet=(EditText)mainView.findViewById(R.id.xSearchEditText);
        searchbtn=(Button)mainView.findViewById(R.id.xSearchButton);

        //Toast.makeText(getActivity(), UserHomeActivity.userid+"", Toast.LENGTH_SHORT).show();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();

                    JSONArray eventsArray = null;
                    JSONObject jsonObject = null;

                    jsonObject = new JSONObject(response);
                    eventsArray = jsonObject.getJSONArray(JSON_ARRAY);

                    //For category = Art
                    final ArrayList<DataCategoryWise> art = new ArrayList<>();

                    //For category = Fiction
                    final ArrayList<DataCategoryWise> fiction = new ArrayList<>();

                    //For category = History
                    final ArrayList<DataCategoryWise> history = new ArrayList<>();

                    //For category = How It Works
                    final ArrayList<DataCategoryWise> howItWorks = new ArrayList<>();

                    //For category = Other
                    final ArrayList<DataCategoryWise> other = new ArrayList<>();

                    for (int i = 0; i < eventsArray.length(); i++) {
                        //Creating a json object of the current index
                        JSONObject obj = null;
                        try {
                            //getting json object from current index
                            obj = eventsArray.getJSONObject(i);
                            String tempCategory = obj.getString(TAG_category);
                            if (tempCategory.equalsIgnoreCase("Art")){
                                DataCategoryWise art_obj = new DataCategoryWise();
                                art_obj.bookId = obj.getString(TAG_bookId);
                                art_obj.bookName=obj.getString(TAG_bookName);
                                art_obj.authorName=obj.getString(TAG_authorName);
                                art_obj.publication=obj.getString(TAG_publication);
                                art_obj.category=obj.getString(TAG_category);
                                art_obj.price=obj.getString(TAG_price);
                                art_obj.description=obj.getString(TAG_description);
                                art_obj.bookImagePath=obj.getString(TAG_bookImagePath);
                                art.add(art_obj);
                            } else if (tempCategory.equalsIgnoreCase("Fiction")){
                                DataCategoryWise fiction_obj = new DataCategoryWise();
                                fiction_obj.bookId = obj.getString(TAG_bookId);
                                fiction_obj.bookName=obj.getString(TAG_bookName);
                                fiction_obj.authorName=obj.getString(TAG_authorName);
                                fiction_obj.publication=obj.getString(TAG_publication);
                                fiction_obj.category=obj.getString(TAG_category);
                                fiction_obj.price=obj.getString(TAG_price);
                                fiction_obj.description=obj.getString(TAG_description);
                                fiction_obj.bookImagePath=obj.getString(TAG_bookImagePath);
                                fiction.add(fiction_obj);
                            } else if (tempCategory.equalsIgnoreCase("History")){
                                DataCategoryWise history_obj = new DataCategoryWise();
                                history_obj.bookId = obj.getString(TAG_bookId);
                                history_obj.bookName=obj.getString(TAG_bookName);
                                history_obj.authorName=obj.getString(TAG_authorName);
                                history_obj.publication=obj.getString(TAG_publication);
                                history_obj.category=obj.getString(TAG_category);
                                history_obj.price=obj.getString(TAG_price);
                                history_obj.description=obj.getString(TAG_description);
                                history_obj.bookImagePath=obj.getString(TAG_bookImagePath);
                                history.add(history_obj);
                            } else if (tempCategory.equalsIgnoreCase("How It Works")){
                                DataCategoryWise hiw_obj = new DataCategoryWise();
                                hiw_obj.bookId = obj.getString(TAG_bookId);
                                hiw_obj.bookName=obj.getString(TAG_bookName);
                                hiw_obj.authorName=obj.getString(TAG_authorName);
                                hiw_obj.publication=obj.getString(TAG_publication);
                                hiw_obj.category=obj.getString(TAG_category);
                                hiw_obj.price=obj.getString(TAG_price);
                                hiw_obj.description=obj.getString(TAG_description);
                                hiw_obj.bookImagePath=obj.getString(TAG_bookImagePath);
                                howItWorks.add(hiw_obj);
                            } else if (tempCategory.equalsIgnoreCase("Other")){
                                DataCategoryWise other_obj = new DataCategoryWise();
                                other_obj.bookId = obj.getString(TAG_bookId);
                                other_obj.bookName=obj.getString(TAG_bookName);
                                other_obj.authorName=obj.getString(TAG_authorName);
                                other_obj.publication=obj.getString(TAG_publication);
                                other_obj.category=obj.getString(TAG_category);
                                other_obj.price=obj.getString(TAG_price);
                                other_obj.description=obj.getString(TAG_description);
                                other_obj.bookImagePath=obj.getString(TAG_bookImagePath);
                                other.add(other_obj);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    //For Art
                    ArrayList<String> bookName = new ArrayList<>();
                    ArrayList<String> authorName = new ArrayList<>();
                    ArrayList<String> price = new ArrayList<>();
                    ArrayList<String> bookImagePath = new ArrayList<>();

                    for (int i=0;i<art.size();i++){
                        bookName.add(art.get(i).getBookName());
                        authorName.add(art.get(i).getAuthorName());
                        price.add(art.get(i).getPrice());
                        bookImagePath.add(art.get(i).getBookImagePath());
                    }

                    grid.setNumColumns(art.size());
                    gridViewSetting(grid,art.size());
                    grid.setAdapter(new CategoriesFragment.gridAdapter(getContext(),bookName,authorName,price,bookImagePath));

                    //For Fiction
                    bookName.clear();
                    authorName.clear();
                    price.clear();
                    ArrayList<String> bookImagePath1 = new ArrayList<>();
                    for (int i=0;i<fiction.size();i++){
                        bookName.add(fiction.get(i).getBookName());
                        authorName.add(fiction.get(i).getAuthorName());
                        price.add(fiction.get(i).getPrice());
                        bookImagePath1.add(fiction.get(i).getBookImagePath());
                    }

                    grid2.setNumColumns(fiction.size());
                    gridViewSetting(grid2,fiction.size());
                    grid2.setAdapter(new CategoriesFragment.gridAdapter2(getContext(),bookName,authorName,price,bookImagePath1));

                    //For History
                    bookName.clear();
                    authorName.clear();
                    price.clear();
                    ArrayList<String> bookImagePath2 = new ArrayList<>();
                    for (int i=0;i<history.size();i++){
                        bookName.add(history.get(i).getBookName());
                        authorName.add(history.get(i).getAuthorName());
                        price.add(history.get(i).getPrice());
                        bookImagePath2.add(history.get(i).getBookImagePath());
                    }

                    grid3.setNumColumns(history.size());
                    gridViewSetting(grid3,history.size());
                    grid3.setAdapter(new CategoriesFragment.gridAdapter3(getContext(),bookName,authorName,price,bookImagePath2));

                    //For How It Works
                    bookName.clear();
                    authorName.clear();
                    price.clear();
                    ArrayList<String> bookImagePath3 = new ArrayList<>();
                    for (int i=0;i<howItWorks.size();i++){
                        bookName.add(howItWorks.get(i).getBookName());
                        authorName.add(howItWorks.get(i).getAuthorName());
                        price.add(howItWorks.get(i).getPrice());
                        bookImagePath3.add(howItWorks.get(i).getBookImagePath());
                    }

                    grid4.setNumColumns(howItWorks.size());
                    gridViewSetting(grid4,howItWorks.size());
                    grid4.setAdapter(new CategoriesFragment.gridAdapter4(getContext(),bookName,authorName,price,bookImagePath3));

                    //For Other
                    bookName.clear();
                    authorName.clear();
                    price.clear();
                    ArrayList<String> bookImagePath4 = new ArrayList<>();
                    for (int i=0;i<other.size();i++){
                        bookName.add(other.get(i).getBookName());
                        authorName.add(other.get(i).getAuthorName());
                        price.add(other.get(i).getPrice());
                        bookImagePath4.add(other.get(i).getBookImagePath());
                    }

                    grid5.setNumColumns(other.size());
                    gridViewSetting(grid5,other.size());
                    grid5.setAdapter(new CategoriesFragment.gridAdapter5(getContext(),bookName,authorName,price,bookImagePath4));

                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            UserHomeActivity.book_id = art.get(i).getBookId();
                            UserHomeActivity.book_name=art.get(i).getBookName();
                            UserHomeActivity.author_name=art.get(i).getAuthorName();
                            UserHomeActivity.description=art.get(i).getDescription();
                            UserHomeActivity.price=art.get(i).getPrice();
                            UserHomeActivity.imagePath=art.get(i).getBookImagePath();
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.xUserFrame,new ViewBookFragment()).addToBackStack(null).commit();
                        }
                    });

                    grid2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            UserHomeActivity.book_id = fiction.get(i).getBookId();
                            UserHomeActivity.book_name=fiction.get(i).getBookName();
                            UserHomeActivity.author_name=fiction.get(i).getAuthorName();
                            UserHomeActivity.description=fiction.get(i).getDescription();
                            UserHomeActivity.price=fiction.get(i).getPrice();
                            UserHomeActivity.imagePath=fiction.get(i).getBookImagePath();
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.xUserFrame,new ViewBookFragment()).addToBackStack(null).commit();
                        }
                    });

                    grid3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            UserHomeActivity.book_id = history.get(i).getBookId();
                            UserHomeActivity.book_name=history.get(i).getBookName();
                            UserHomeActivity.author_name=history.get(i).getAuthorName();
                            UserHomeActivity.description=history.get(i).getDescription();
                            UserHomeActivity.price=history.get(i).getPrice();
                            UserHomeActivity.imagePath=history.get(i).getBookImagePath();
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.xUserFrame,new ViewBookFragment()).addToBackStack(null).commit();
                        }
                    });

                    grid4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            UserHomeActivity.book_id = howItWorks.get(i).getBookId();
                            UserHomeActivity.book_name=howItWorks.get(i).getBookName();
                            UserHomeActivity.author_name=howItWorks.get(i).getAuthorName();
                            UserHomeActivity.description=howItWorks.get(i).getDescription();
                            UserHomeActivity.price=howItWorks.get(i).getPrice();
                            UserHomeActivity.imagePath=howItWorks.get(i).getBookImagePath();
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.xUserFrame,new ViewBookFragment()).addToBackStack(null).commit();
                        }
                    });

                    grid5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            UserHomeActivity.book_id = other.get(i).getBookId();
                            UserHomeActivity.book_name=other.get(i).getBookName();
                            UserHomeActivity.author_name=other.get(i).getAuthorName();
                            UserHomeActivity.description=other.get(i).getDescription();
                            UserHomeActivity.price=other.get(i).getPrice();
                            UserHomeActivity.imagePath=other.get(i).getBookImagePath();
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.xUserFrame,new ViewBookFragment()).addToBackStack(null).commit();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        DataRequest dataRequest = new DataRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(dataRequest);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = searchet.getText().toString();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.xUserFrame,new SearchFragment()).addToBackStack(null).commit();
            }
        });

        return mainView;
    }

    class DataCategoryWise{
        String bookId,bookName,authorName,publication,category,price,description,bookImagePath;

        public String getBookId() {
            return bookId;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getPublication() {
            return publication;
        }

        public void setPublication(String publication) {
            this.publication = publication;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBookImagePath() {
            return bookImagePath;
        }

        public void setBookImagePath(String bookImagePath) {
            this.bookImagePath = bookImagePath;
        }
    }

    private void gridViewSetting(GridView gridview,int sizes) {

        int size=sizes;
        // Calculated single Item Layout Width for each grid element ....
        int width = 300;

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;

        int totalWidth = (int) (width * size);
        int singleItemWidth = (int) (width * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (totalWidth+100), LinearLayout.LayoutParams.MATCH_PARENT);

        gridview.setLayoutParams(params);
        gridview.setColumnWidth(width);
        gridview.setStretchMode(GridView.STRETCH_SPACING);
        gridview.setNumColumns(size);
    }

    class DashboardItem {
        String dashboardIconName;
        String authorName;
        String price;
        DashboardItem(String dashboardIconName,String authorName,String price){
            this.dashboardIconName=dashboardIconName;
            this.authorName=authorName;
            this.price=price;
        }
    }

    class gridAdapter2 extends BaseAdapter {
        ArrayList<CategoriesFragment.DashboardItem> list;
        Context context;
        ArrayList<String> bookName = new ArrayList<>();
        ArrayList<String> authorName = new ArrayList<>();
        ArrayList<String> price = new ArrayList<>();
        ArrayList<String> imagePath = new ArrayList<>();

        gridAdapter2(Context context, ArrayList<String> bookName, ArrayList<String> authorName, ArrayList<String> price, ArrayList<String> imagePath) {
            this.context = context;
            this.bookName = bookName;
            this.authorName = authorName;
            this.price = price;
            this.imagePath = imagePath;

            list = new ArrayList<CategoriesFragment.DashboardItem>();

            for (int i = 0; i < bookName.size() ; i++) {
                CategoriesFragment.DashboardItem tempDashboardItem = new CategoriesFragment.DashboardItem(
                        bookName.get(i),
                        authorName.get(i),price.get(i));
                list.add(tempDashboardItem);
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        //this one is used for the databases related activities
        public long getItemId(int i) {
            return i;
        }

        class ViewHolder {
            ImageView myDashboardIcon;
            TextView myDashboardIconName,myAuthorName,myPrice;
            RelativeLayout relative;

            ViewHolder(View v) {
                myDashboardIcon = (ImageView) v.findViewById(R.id.singleItem);
                myDashboardIconName=(TextView)v.findViewById(R.id.singleItemName);
                myAuthorName = (TextView)v.findViewById(R.id.xAuthorName);
                myPrice = (TextView)v.findViewById(R.id.xPrice);
                relative=(RelativeLayout)v.findViewById(R.id.xRelative);
            }
        }

        CategoriesFragment.gridAdapter2.ViewHolder holder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View row = view;
            holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_item, viewGroup, false);
                holder = new CategoriesFragment.gridAdapter2.ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (CategoriesFragment.gridAdapter2.ViewHolder) row.getTag();
            }
            CategoriesFragment.DashboardItem temp = list.get(i);
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imagePath.get(i)).getContent());
                        holder.myDashboardIcon.setImageBitmap(bitmap);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            holder.myDashboardIconName.setText(temp.dashboardIconName);
            holder.myAuthorName.setText(temp.authorName);
            holder.myPrice.setText(temp.price);
            return row;
        }
    }

    class gridAdapter extends BaseAdapter {
        ArrayList<CategoriesFragment.DashboardItem> list;
        Context context;
        ArrayList<String> bookName = new ArrayList<>();
        ArrayList<String> authorName = new ArrayList<>();
        ArrayList<String> price = new ArrayList<>();
        ArrayList<String> imagePath = new ArrayList<>();

        gridAdapter(Context context, ArrayList<String> bookName, ArrayList<String> authorName, ArrayList<String> price, ArrayList<String> imagePath) {
            this.context = context;
            this.bookName = bookName;
            this.authorName = authorName;
            this.price = price;
            this.imagePath = imagePath;

            list = new ArrayList<CategoriesFragment.DashboardItem>();

            for (int i = 0; i < bookName.size() ; i++) {
                CategoriesFragment.DashboardItem tempDashboardItem = new CategoriesFragment.DashboardItem(
                        bookName.get(i),
                        authorName.get(i),price.get(i));
                list.add(tempDashboardItem);
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        //this one is used for the databases related activities
        public long getItemId(int i) {
            return i;
        }

        class ViewHolder {
            ImageView myDashboardIcon;
            TextView myDashboardIconName,myAuthorName,myPrice;
            RelativeLayout relative;

            ViewHolder(View v) {
                myDashboardIcon = (ImageView) v.findViewById(R.id.singleItem);
                myDashboardIconName=(TextView)v.findViewById(R.id.singleItemName);
                myAuthorName = (TextView)v.findViewById(R.id.xAuthorName);
                myPrice = (TextView)v.findViewById(R.id.xPrice);
                relative=(RelativeLayout)v.findViewById(R.id.xRelative);
            }
        }

        CategoriesFragment.gridAdapter.ViewHolder holder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View row = view;
            holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_item, viewGroup, false);
                holder = new CategoriesFragment.gridAdapter.ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (CategoriesFragment.gridAdapter.ViewHolder) row.getTag();
            }
            CategoriesFragment.DashboardItem temp = list.get(i);

            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imagePath.get(i)).getContent());
                        holder.myDashboardIcon.setImageBitmap(bitmap);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            holder.myDashboardIconName.setText(temp.dashboardIconName);
            holder.myAuthorName.setText(temp.authorName);
            holder.myPrice.setText(temp.price);
            return row;
        }
    }

    class gridAdapter3 extends BaseAdapter {
        ArrayList<CategoriesFragment.DashboardItem> list;
        Context context;
        ArrayList<String> bookName = new ArrayList<>();
        ArrayList<String> authorName = new ArrayList<>();
        ArrayList<String> price = new ArrayList<>();
        ArrayList<String> imagePath = new ArrayList<>();

        gridAdapter3(Context context, ArrayList<String> bookName, ArrayList<String> authorName, ArrayList<String> price, ArrayList<String> imagePath) {
            this.context = context;
            this.bookName = bookName;
            this.authorName = authorName;
            this.price = price;
            this.imagePath = imagePath;

            list = new ArrayList<CategoriesFragment.DashboardItem>();

            for (int i = 0; i < bookName.size() ; i++) {
                CategoriesFragment.DashboardItem tempDashboardItem = new CategoriesFragment.DashboardItem(
                        bookName.get(i),
                        authorName.get(i),price.get(i));
                list.add(tempDashboardItem);
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        //this one is used for the databases related activities
        public long getItemId(int i) {
            return i;
        }

        class ViewHolder {
            ImageView myDashboardIcon;
            TextView myDashboardIconName,myAuthorName,myPrice;
            RelativeLayout relative;

            ViewHolder(View v) {
                myDashboardIcon = (ImageView) v.findViewById(R.id.singleItem);
                myDashboardIconName=(TextView)v.findViewById(R.id.singleItemName);
                myAuthorName = (TextView)v.findViewById(R.id.xAuthorName);
                myPrice = (TextView)v.findViewById(R.id.xPrice);
                relative=(RelativeLayout)v.findViewById(R.id.xRelative);
            }
        }

        CategoriesFragment.gridAdapter3.ViewHolder holder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View row = view;
            holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_item, viewGroup, false);
                holder = new CategoriesFragment.gridAdapter3.ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (CategoriesFragment.gridAdapter3.ViewHolder) row.getTag();
            }
            CategoriesFragment.DashboardItem temp = list.get(i);
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imagePath.get(i)).getContent());
                        holder.myDashboardIcon.setImageBitmap(bitmap);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            holder.myDashboardIconName.setText(temp.dashboardIconName);
            holder.myAuthorName.setText(temp.authorName);
            holder.myPrice.setText(temp.price);
            return row;
        }
    }

    class gridAdapter4 extends BaseAdapter {
        ArrayList<CategoriesFragment.DashboardItem> list;
        Context context;
        ArrayList<String> bookName = new ArrayList<>();
        ArrayList<String> authorName = new ArrayList<>();
        ArrayList<String> price = new ArrayList<>();
        ArrayList<String> imagePath = new ArrayList<>();

        gridAdapter4(Context context, ArrayList<String> bookName, ArrayList<String> authorName, ArrayList<String> price, ArrayList<String> imagePath) {
            this.context = context;
            this.bookName = bookName;
            this.authorName = authorName;
            this.price = price;
            this.imagePath = imagePath;

            list = new ArrayList<CategoriesFragment.DashboardItem>();

            for (int i = 0; i < bookName.size() ; i++) {
                CategoriesFragment.DashboardItem tempDashboardItem = new CategoriesFragment.DashboardItem(
                        bookName.get(i),
                        authorName.get(i),price.get(i));
                list.add(tempDashboardItem);
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        //this one is used for the databases related activities
        public long getItemId(int i) {
            return i;
        }

        class ViewHolder {
            ImageView myDashboardIcon;
            TextView myDashboardIconName,myAuthorName,myPrice;
            RelativeLayout relative;

            ViewHolder(View v) {
                myDashboardIcon = (ImageView) v.findViewById(R.id.singleItem);
                myDashboardIconName=(TextView)v.findViewById(R.id.singleItemName);
                myAuthorName = (TextView)v.findViewById(R.id.xAuthorName);
                myPrice = (TextView)v.findViewById(R.id.xPrice);
                relative=(RelativeLayout)v.findViewById(R.id.xRelative);
            }
        }

        CategoriesFragment.gridAdapter4.ViewHolder holder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View row = view;
            holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_item, viewGroup, false);
                holder = new CategoriesFragment.gridAdapter4.ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (CategoriesFragment.gridAdapter4.ViewHolder) row.getTag();
            }
            CategoriesFragment.DashboardItem temp = list.get(i);
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imagePath.get(i)).getContent());
                        holder.myDashboardIcon.setImageBitmap(bitmap);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            holder.myDashboardIconName.setText(temp.dashboardIconName);
            holder.myAuthorName.setText(temp.authorName);
            holder.myPrice.setText(temp.price);
            return row;
        }
    }

    class gridAdapter5 extends BaseAdapter {
        ArrayList<CategoriesFragment.DashboardItem> list;
        Context context;
        ArrayList<String> bookName = new ArrayList<>();
        ArrayList<String> authorName = new ArrayList<>();
        ArrayList<String> price = new ArrayList<>();
        ArrayList<String> imagePath = new ArrayList<>();

        gridAdapter5(Context context, ArrayList<String> bookName, ArrayList<String> authorName, ArrayList<String> price, ArrayList<String> imagePath) {
            this.context = context;
            this.bookName = bookName;
            this.authorName = authorName;
            this.price = price;
            this.imagePath = imagePath;

            list = new ArrayList<CategoriesFragment.DashboardItem>();

            for (int i = 0; i < bookName.size(); i++) {
                CategoriesFragment.DashboardItem tempDashboardItem = new CategoriesFragment.DashboardItem(
                        bookName.get(i),
                        authorName.get(i),price.get(i));
                list.add(tempDashboardItem);
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        //this one is used for the databases related activities
        public long getItemId(int i) {
            return i;
        }

        class ViewHolder {
            ImageView myDashboardIcon;
            TextView myDashboardIconName,myAuthorName,myPrice;
            RelativeLayout relative;

            ViewHolder(View v) {
                myDashboardIcon = (ImageView) v.findViewById(R.id.singleItem);
                myDashboardIconName=(TextView)v.findViewById(R.id.singleItemName);
                myAuthorName = (TextView)v.findViewById(R.id.xAuthorName);
                myPrice = (TextView)v.findViewById(R.id.xPrice);
                relative=(RelativeLayout)v.findViewById(R.id.xRelative);
            }
        }

        CategoriesFragment.gridAdapter5.ViewHolder holder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View row = view;
            holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_item, viewGroup, false);
                holder = new CategoriesFragment.gridAdapter5.ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (CategoriesFragment.gridAdapter5.ViewHolder) row.getTag();
            }
            CategoriesFragment.DashboardItem temp = list.get(i);
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imagePath.get(i)).getContent());
                        holder.myDashboardIcon.setImageBitmap(bitmap);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            holder.myDashboardIconName.setText(temp.dashboardIconName);
            holder.myAuthorName.setText(temp.authorName);
            holder.myPrice.setText(temp.price);
            return row;
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    // handle back button

                    return true;

                }

                return false;
            }
        });
    }

}