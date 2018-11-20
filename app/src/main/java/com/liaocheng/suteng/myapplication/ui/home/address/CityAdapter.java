package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.util.City;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyue on 2016/12/26.
 */

public class CityAdapter extends BaseAdapter {
    public interface AdapterListener{
        void startPosition();
        void hotCityClick(String city);
    }
    private AdapterListener listener;
    private static final int VIEW_TYPE_COUNT = 1;
    private Context context;
    private List<City> cities;
    private TextView currCity;
    /*记录每个字母开始的位置*/
    private Map<String,Integer> letterPositionMap = new HashMap<>();

    public CityAdapter(Context context, List<City> cityList){
        this.context = context;
        this.cities = cityList;
        String perLetter = "";
        for(int n = 0;n<cities.size();n++){
            String currentLetter = cities.get(n).getPinyin().substring(0,1);
            if (n==0){
                letterPositionMap.put(currentLetter,n);
            }else {
                 perLetter = cities.get(n-1).getPinyin().substring(0,1);
                if(!currentLetter.equals(perLetter)){
                    letterPositionMap.put(currentLetter,n);
                }
            }

        }
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return  position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            ViewHoldre holdre = null;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.citylist_item,null);
                holdre = new ViewHoldre();
                holdre.pinyin = (TextView)convertView.findViewById(R.id.city_py);
                holdre.name = (TextView)convertView.findViewById(R.id.city_name);
                convertView.setTag(holdre);
            }
            City city = cities.get(position);
            holdre = (ViewHoldre)convertView.getTag();
            holdre.pinyin.setVisibility(View.GONE);

            String curPY = city.getPinyin().substring(0,1);
            if(position == 0){
                holdre.pinyin.setText(curPY.toUpperCase());
                holdre.pinyin.setVisibility(View.VISIBLE);
            }else {
                String perPY = cities.get(position-1).getPinyin().substring(0,1);
                if(!curPY.equals(perPY)){
                    holdre.pinyin.setText(curPY.toUpperCase());
                    holdre.pinyin.setVisibility(View.VISIBLE);
                }
            }
            holdre.name.setText(city.getName());


        return convertView;
    }

    private class ViewHoldre{
        TextView pinyin;
        TextView name;
    }

    public void setAdapterListener(AdapterListener listener){
        this.listener = listener;
    }

    public void setCurrentPosition(String city){
        if(currCity!=null){
            currCity.setText(city);
        }
    }

    public int getLetterPosition(String letter){
        Integer position = letterPositionMap.get(letter);
        return position != null ? position : -1;
    }
}
