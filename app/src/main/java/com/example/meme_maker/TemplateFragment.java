package com.example.meme_maker;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


public class TemplateFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private List<Item> memeList;
    private RecyclerView templateRecyclerView;
    private TemplateItemRecyclerViewAdapter adapter;

    public TemplateFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TemplateFragment newInstance(int columnCount) {
        TemplateFragment fragment = new TemplateFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_list, container, false);
        memeList = new ArrayList<>();

        memeList.add(new Item( R.drawable.memetemplate_1, "Dog and Dawg" ));
        memeList.add(new Item( R.drawable.memetemplate_1, "Dog and Dawg" ));
        memeList.add(new Item( R.drawable.memetemplate_1, "Dog and Dawg" ));

        templateRecyclerView = view.findViewById(R.id.templatesRecyclerView);
        adapter = new TemplateItemRecyclerViewAdapter(memeList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        templateRecyclerView.setLayoutManager(layoutManager);
        templateRecyclerView.setAdapter(adapter);


      
        return view;
    }
}