package com.example.aishwarya.onlinebookstore;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aishwarya on 04-Apr-17.
 */

public class MakePaymentRequest extends StringRequest {
    // login url
    private static final String LOGIN_REQUEST_URL = "http://lambelltech.com/OnlineBookStore/makepayment.php";
    private Map<String,String> params;

    public MakePaymentRequest(String userid, String cardno, Response.Listener<String> listener){

        super(Method.POST,LOGIN_REQUEST_URL,listener,null);

        params = new HashMap<>();
        params.put("userid", userid);
        params.put("cardno", cardno);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

}

