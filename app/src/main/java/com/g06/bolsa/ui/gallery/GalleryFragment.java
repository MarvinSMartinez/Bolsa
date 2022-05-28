package com.g06.bolsa.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.View.OnClickListener;

import com.g06.bolsa.R;
import com.g06.bolsa.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button bperfil = (Button) root.findViewById(R.id.button_dato_personal);
        bperfil.setOnClickListener((View view) -> {
            try {
                Class<?> clase = Class.forName("com.g06.bolsa.datosPerfil.DatosPerMenuActivity");
                Intent intent = new Intent(getActivity(), clase);
                this.startActivity(intent);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        Button b1 = (Button) root.findViewById(R.id.id_button_experiencia);
        b1.setOnClickListener((View view) -> {
            try {
                Class<?> clase = Class.forName("com.g06.bolsa.detalle_experiencia.DetalleExperienciaMenuActivity");
                Intent intent = new Intent(getActivity(), clase);
                this.startActivity(intent);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        Button b2 = (Button) root.findViewById(R.id.id_button_estudio);
        b2.setOnClickListener((View view) -> {
            try {
                Class<?> clase = Class.forName("com.g06.bolsa.dato_estudio.DatoEstudioMenuActivity");
                Intent intent = new Intent(getActivity(), clase);
                this.startActivity(intent);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        Button b3 = (Button) root.findViewById(R.id.id_button_contactos);
        b3.setOnClickListener((View view) -> {
            try {
                Class<?> clase = Class.forName("com.g06.bolsa.contacto_aspirante.ContactoAspiranteMenuActivity");
                Intent intent = new Intent(getActivity(), clase);
                this.startActivity(intent);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


        Button b4 = (Button) root.findViewById(R.id.id_button_aplicacion);
        b4.setOnClickListener((View view) -> {
            try {
                Class<?> clase = Class.forName("com.g06.bolsa.detalle_aplicacion.DetalleAplicacionMenuActivity");
                Intent intent = new Intent(getActivity(), clase);
                this.startActivity(intent);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


        //final TextView textView = binding.textGallery;
       // galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}