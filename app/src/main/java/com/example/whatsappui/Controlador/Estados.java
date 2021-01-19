package com.example.whatsappui.Controlador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whatsappui.Adapter.EstadosAdapter;
import com.example.whatsappui.Adapter.UsuarioAdapter;
import com.example.whatsappui.Model.Usuario;
import com.example.whatsappui.R;
import com.example.whatsappui.WebService.Asynchtask;
import com.example.whatsappui.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Estados#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Estados extends Fragment implements Asynchtask {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recVEstado;

    public Estados() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Estados.
     */
    // TODO: Rename and change types and number of parameters
    public static Estados newInstance(String param1, String param2) {
        Estados fragment = new Estados();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_estados, container, false);
        recVEstado = (RecyclerView)rootView.findViewById(R.id.recVEstado);
        recVEstado.setHasFixedSize(true);
        recVEstado.setLayoutManager(new LinearLayoutManager(getContext()));

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, getContext(), this);
        ws.execute("GET");
        return rootView;
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Usuario> lstUsuarios = new ArrayList<Usuario> ();

        try {

            JSONObject JSONlista =  new JSONObject(result);
            JSONArray JSONlistaUsuarios=  JSONlista.getJSONArray("data");

            lstUsuarios = Usuario.JsonObjectsBuild(JSONlistaUsuarios);

            EstadosAdapter adapatorUsuario = new EstadosAdapter(getContext(), lstUsuarios);

            //int resId = R.anim.layout_animation_down_to_up;
            // LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
            //       resId);
            //recVChat.setLayoutAnimation(animation);

            recVEstado.setAdapter(adapatorUsuario);



        }catch (JSONException e)
        {
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }
}