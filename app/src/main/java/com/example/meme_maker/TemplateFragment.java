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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_list, container, false);
        memeList = new ArrayList<>();

        memeList.add(new Item(R.drawable.memetemplate_3, "Allstars"));
        memeList.add(new Item(R.drawable.memetemplate_5, "Face"));
        memeList.add(new Item(R.drawable.memetemplate_6, "Gundog"));
        memeList.add(new Item(R.drawable.memetemplate_7, "Finger"));
        memeList.add(new Item(R.drawable.memetemplate_8, "Pingui"));
        memeList.add(new Item(R.drawable.memetemplate_9, "Bunny"));
        memeList.add(new Item(R.drawable.memetemplate_11, "Fist"));
        memeList.add(new Item(R.drawable.memetemplate_12, "Gunda"));
        memeList.add(new Item(R.drawable.memetemplate_13, "Sponge Pant"));
        memeList.add(new Item(R.drawable.memetemplate_14, "Angry Bob"));
        memeList.add(new Item(R.drawable.memetemplate_15, "Giga Bob"));
        memeList.add(new Item(R.drawable.memetemplate_16, "Angrystein"));
        memeList.add(new Item(R.drawable.memetemplate_17, "Shake"));
        memeList.add(new Item(R.drawable.memetemplate_18, "Reload Cat"));
        memeList.add(new Item(R.drawable.memetemplate_19, "Dog"));
        memeList.add(new Item(R.drawable.memetemplate_20, "Dawg"));
        memeList.add(new Item(R.drawable.memetemplate_21, "Kife Dog"));


        templateRecyclerView = view.findViewById(R.id.templatesRecyclerView);

        // Kattintás kezelése manuálisan (lambda helyett)
        TemplateItemRecyclerViewAdapter.OnTemplateClickListener listener = new TemplateItemRecyclerViewAdapter.OnTemplateClickListener() {
            @Override
            public void onTemplateClick(int imageResId) {
                // Kiválasztott sablon megnyitása az editorban
                openEditorFragment(imageResId);
            }
        };

        // Adapter inicializálása
        adapter = new TemplateItemRecyclerViewAdapter(memeList, listener);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        templateRecyclerView.setLayoutManager(layoutManager);
        templateRecyclerView.setAdapter(adapter);

        return view;
    }

    private void openEditorFragment(int imageResId) {
        MemeEditorFragment editorFragment = new MemeEditorFragment();
        Bundle args = new Bundle();
        args.putInt("TEMPLATE_IMAGE", imageResId); // A kiválasztott sablonkép ID-t átadjuk
        editorFragment.setArguments(args);

        ((MainActivity)getActivity()).changeTitle("edit");

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, editorFragment) // Editor fragmentbe cseréljük
                .addToBackStack(null)
                .commit();
    }
}