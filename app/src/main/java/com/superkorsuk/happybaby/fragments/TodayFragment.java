package com.superkorsuk.happybaby.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.adapters.TodayDoListViewAdapter;
import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.models.Baby;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.util.DateAndTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TodayFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    private int babyId;

    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            initUI(view);
        }

        Log.d("Today", "onStart()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    private void initUI(View view) {
        initUISummary(view);
        initUITodayDoList(view);
    }

    private void initUITodayDoList(View view) {
        BabyDoRepository repo = new BabyDoRepository(view.getContext());
        List<BabyDo> babyDoList = repo.getBabyDoListAt(babyId, 2016, 6, 20);

        // ListView Set
        ListView listView = (ListView) view.findViewById(R.id.today_babydo_list);
        TodayDoListViewAdapter adapter = new TodayDoListViewAdapter();
        listView.setAdapter(adapter);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());


        for(BabyDo babyDo : babyDoList) {
            Log.d("doList : " , babyDo.getBabyDoType() + " , " + babyDo.getIssueDate());

            String time = sdf.format(babyDo.getIssueDate());

            Drawable icon = ContextCompat.getDrawable(getActivity(), R.drawable.ic_info_black_24dp);
            String label = "";

            switch (babyDo.getBabyDoType()) {
                case FORMULA:
                    icon = ContextCompat.getDrawable(getActivity(), R.drawable.today_bottle);
                    label = babyDo.getAmount() + "ml";
                    break;
                case BREAST_MILK:
                    icon = ContextCompat.getDrawable(getActivity(), R.drawable.today_bottle);
                    if (babyDo.getAmount() > 0) {
                        label = babyDo.getAmount() + "ml";
                    } else {
                        label = "좌 : " + babyDo.getBreastfeedingLeft() + "분, 우 : " + babyDo.getBreastfeedingRight() + "분";
                    }
                    break;
                case POOP:
                    icon = ContextCompat.getDrawable(getActivity(), R.drawable.today_dong);
                    label = "양 : " + babyDo.getAmountPoop().toString() + ", 색 : " + babyDo.getColor().toString();
                    break;
                case SLEEP:
                    icon = ContextCompat.getDrawable(getActivity(), R.drawable.today_sleep);
                    label = sdf.format(babyDo.getStartTime()) + " ~ " + sdf.format(babyDo.getEndTime());
                    break;
                default:
                    break;

            }

            adapter.addItem(icon, label, time);
        }
    }

    private void initUISummary(View view) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault());

        TextView textToday = (TextView) view.findViewById(R.id.today_text_today);
        textToday.setText(sdf.format(today));

        TextView textDDay = (TextView) view.findViewById(R.id.today_text_dday);
        BabyRepository babyRepo = new BabyRepository(view.getContext());

        Baby baby = babyRepo.find(babyId);
        Calendar birthday = baby.getBirthDayToCalendar();
        long dday = DateAndTime.getDDay(birthday);

        textDDay.setText("D +" + dday);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setBabyId(int babyId) {
        this.babyId = babyId;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_today:
                break;
            default:
                break;
        }
    }
}
