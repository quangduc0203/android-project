package com.example.project.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.db.DBHelper;
import com.example.project.model.Account;
import com.example.project.ui.ChangeInfo;
import com.example.project.ui.InforAccount;
import com.example.project.ui.ListProductUser;
import com.example.project.ui.LoginActivity;
import com.example.project.ui.NewPhone;
import com.example.project.ui.Thongbao;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAccount extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txtName;
    private Button btnInfo, btnLike, btnThongbao, btnlogout,btnChange;

    public FragmentAccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAccount newInstance(String param1, String param2) {
        FragmentAccount fragment = new FragmentAccount();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtName = getActivity().findViewById(R.id.tvNameProfile);
        btnInfo = getActivity().findViewById(R.id.accountInfo);
        btnLike = getActivity().findViewById(R.id.btn_like);
        btnlogout = getActivity().findViewById(R.id.btnLogout);
        btnThongbao = getActivity().findViewById(R.id.btn_thongbao);
        btnChange=getActivity().findViewById(R.id.btnEdit);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyReferences", getActivity().MODE_PRIVATE);
        int id = sharedPreferences.getInt("id",0);
        DBHelper dbHelper = new DBHelper(getContext());
        final Account account = dbHelper.findAcc(id);
        txtName.setText(account.getName());


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InforAccount.class);
                intent.putExtra("Account", account);
                getActivity().startActivity(intent);
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeInfo.class);
                intent.putExtra("Account", account);
                getActivity().startActivity(intent);
            }
        });

        btnThongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Thongbao.class);
                getActivity().startActivity(intent);
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListProductUser.class);
                getActivity().startActivity(intent);
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();

            }
        });


    }
    public static class AppState {
        private static AppState singleInstance;

        private boolean isLoggingOut;

        private AppState() {
        }

        public static AppState getSingleInstance() {
            if (singleInstance == null) {
                singleInstance = new AppState();
            }
            return singleInstance;
        }

        public boolean isLoggingOut() {
            return isLoggingOut;
        }

        public void setLoggingOut(boolean isLoggingOut) {
            this.isLoggingOut = isLoggingOut;
        }
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Còn lâu!!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Không thoát được", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences myPrefs = getActivity().getSharedPreferences("MyReferences",
                        getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                AppState.getSingleInstance().setLoggingOut(true);
                Log.d(TAG, "Now log out and start the activity login");
                Intent intent = new Intent(getActivity(),
                        LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}