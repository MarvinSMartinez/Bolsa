package com.g06.bolsa.ui.home;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.g06.bolsa.clasesCardAreas.Cconstruccion;
import com.g06.bolsa.R;
import com.g06.bolsa.clasesCardAreas.cFinanzas;
import com.g06.bolsa.clasesCardAreas.cInformatica;
import com.g06.bolsa.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private FragmentHomeBinding binding;

    CardView cardConstruccion,cardInformatica,cardFinanzas; //SEGUN EL ID QUE SE PUSO EN LAYOUT HOME
    View vista;
    Activity actividad;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    vista=inflater.inflate(R.layout.fragment_home,container,false);
    //aCONSTRUCCION
    cardConstruccion=vista.findViewById(R.id.cardConstruccion);
    cardConstruccion.setOnClickListener(this);
    cardConstruccion.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
                Intent intent = new Intent(v.getContext(),Cconstruccion.class);
                startActivity(intent);
        }
    });
    //FINANZAS
     cardFinanzas=vista.findViewById(R.id.cardFinanzas);
     cardFinanzas.setOnClickListener(this);
     cardFinanzas.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(view.getContext(), cFinanzas.class);
             startActivity(intent);
         }
     });
    //INFORMATICA
    cardInformatica=vista.findViewById(R.id.cardInformatica);
    cardInformatica.setOnClickListener(this);
    cardInformatica.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), cInformatica.class);
            startActivity(intent);
        }
    });



        //final TextView textView = binding.textHome;
      //  homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
       // return vista;
        //final TextView textView = binding.textHome;
      //  homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return vista;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onClick(View view) {

    }
}