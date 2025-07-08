package com.example.btvn_recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListProductFragment extends Fragment {

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    ArrayList<Category> categoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        // Nhận dữ liệu từ MainActivity
        categoryList = new ArrayList<>();
        if (getArguments() != null) {
            ArrayList<Category> passedList = (ArrayList<Category>) getArguments().getSerializable("categoryList");
            if (passedList != null) {
                categoryList.addAll(passedList);
            }
        }

        adapter = new CategoryAdapter(categoryList, getContext(), this::navigateToDetail);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void navigateToDetail(Category category) {
        Bundle bundle = new Bundle();
        bundle.putInt("imageResId", category.getImageResId());
        bundle.putString("name", category.getName());

        DetailedProductFragment fragment = new DetailedProductFragment();
        fragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
