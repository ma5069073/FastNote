package com.makk.fastnote.fragment;

import com.makk.fastnote.DividerItemDecoration;
import com.makk.fastnote.R;
import com.makk.fastnote.adapter.RecyclerViewAdapter;
import com.makk.fastnote.base.BaseFragment;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin on 2015/7/2.
 */
public class NotesFragment extends BaseFragment {

	@Override
	protected View initView(LayoutInflater inflater) {
		View root = inflater.inflate(R.layout.fragment_recyclerview, null);

		RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

		RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), getResources().getStringArray(R.array.countries));
		recyclerView.setAdapter(adapter);

		FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
		
		fab.attachToRecyclerView(recyclerView);
		

		return root;
	}

	@Override
	protected void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}
	
	

}
