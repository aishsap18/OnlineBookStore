package com.example.aishwarya.onlinebookstore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aishwarya on 04-Apr-17.
 */

public class MakePaymentFragment extends Fragment {

    EditText cardNo;
    Button pay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.make_payment_fragment, container, false);
        UserHomeActivity.title.setText("Make Payment");

        cardNo = (EditText)mainView.findViewById(R.id.xCardNum);
        pay = (Button)mainView.findViewById(R.id.xPayButton);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = cardNo.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonResponse = null;
                        try {
                            jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        //CartViewFragment.totalPrice = 0;
                                        //Toast.makeText(getActivity(), "Books purchased.", Toast.LENGTH_SHORT).show();
                                        CartViewFragment.totalPrice = 0;
                                        Toast.makeText(getActivity(), "Books purchased.", Toast.LENGTH_SHORT).show();
                                    }
                                };
                                String userid = UserHomeActivity.userid + "";
                                ConfirmOrderRequest confirmCartRequest = new ConfirmOrderRequest(userid, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(confirmCartRequest);

                            } else{
                                Toast.makeText(getActivity(), "Check your card number.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                String userid = UserHomeActivity.userid + "";
                MakePaymentRequest confirmCartRequest = new MakePaymentRequest(userid, str, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(confirmCartRequest);
            }
        });

        return  mainView;
    }
}
