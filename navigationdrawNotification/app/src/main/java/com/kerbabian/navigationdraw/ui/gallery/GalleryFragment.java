package com.kerbabian.navigationdraw.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kerbabian.navigationdraw.GalleryListener;
import com.kerbabian.navigationdraw.R;
import com.kerbabian.navigationdraw.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private NotificationManagerCompat notificationManager;
    private EditText edittextTitile;
    private EditText edittextMessage;
    private Button buttChannel1;
    private Button buttChannel2;
    private GalleryListener mListener;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);


        edittextTitile = rootView.findViewById(R.id.edit_text_itle);
        edittextMessage = rootView.findViewById(R.id.edit_text_message);
        buttChannel1 = rootView.findViewById(R.id.buttoncha1);
        buttChannel2 = rootView.findViewById(R.id.buttoncha2);


        buttChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.Channel1(edittextTitile.getText().toString(), edittextTitile.getText().toString());

            }
        });




        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }








    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (GalleryListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}