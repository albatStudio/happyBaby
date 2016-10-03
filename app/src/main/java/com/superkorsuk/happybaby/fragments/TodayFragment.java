package com.superkorsuk.happybaby.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.adapters.ExpandableListViewAdapter;
import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.models.Baby;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoType;
import com.superkorsuk.happybaby.util.DateAndTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


        initUITimeline(view);


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

    private void initUITimeline(View view) {
        List<ExpandableListViewAdapter.Item> selectedDayData = new ArrayList<>();
        BabyDoRepository repo = new BabyDoRepository(view.getContext());

        //TODO :: 실 배포시 아래 날짜 절대값을 변수로 변경 필요
        List<BabyDo> babyDoList = repo.getBabyDoListAt(babyId, 2016, 6, 20);

        selectedDayData.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.HEADER, "오늘"));

        if(babyDoList.size() < 1) {
            selectedDayData.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "기록 없음"));
        }

        for(BabyDo babyDo : babyDoList) {
            int amount = babyDo.getAmount();
            String note = babyDo.getNote();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String time = sdf.format(babyDo.getIssueDate());

            String icon = "기본";
            String text = "";

            if (babyDo.getBabyDoType() == BabyDoType.FORMULA) {
                icon = "분유";
                text = babyDo.getAmount() + "ml";
            } else if (babyDo.getBabyDoType() == BabyDoType.POOP) {
                icon = "대변";
                text = "양 : " + babyDo.getAmountPoop().toString() + ", 색 : " + babyDo.getColor().toString();
            } else if (babyDo.getBabyDoType() == BabyDoType.BREAST_MILK) {
                icon = "모유";
                if (babyDo.getAmount() > 0) {
                    text = babyDo.getAmount() + "ml";
                } else {
                    text = "좌 : " + babyDo.getBreastfeedingLeft() + "분, 우 : " + babyDo.getBreastfeedingRight() + "분";
                }
            } else if (babyDo.getBabyDoType() == BabyDoType.SLEEP) {
                icon = "수면";
            }

            String labelText = icon + " " + text;

            selectedDayData.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, labelText));
        }
        RecyclerView recyclerview = (RecyclerView) view.findViewById(R.id.today_babydo_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(new ExpandableListViewAdapter(selectedDayData));
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
}
