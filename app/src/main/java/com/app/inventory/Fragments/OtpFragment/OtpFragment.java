package com.app.inventory.Fragments.OtpFragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.app.inventory.Fragments.OtpFragment.Presenter.OtpVerificationPresenter;
import com.app.inventory.Fragments.OtpFragment.View.OtpVerificationView;
import com.app.inventory.Utlis.Utils;
import com.app.inventory.Utlis.GenericTextWatcher;
import com.app.inventory.databinding.FragmentOtpBinding;

public class OtpFragment extends Fragment implements View.OnClickListener, OtpVerificationView {
    private FragmentOtpBinding binding;
    private OtpVerificationPresenter otpVerificationPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        otpVerificationPresenter = new OtpVerificationPresenter(getActivity(),this,this);


        EditText[] edit = {binding.otpET1, binding.otpET2, binding.otpET3, binding.otpET4};

        binding.otpET1.addTextChangedListener(new GenericTextWatcher(binding.otpET1, edit));
        binding.otpET2.addTextChangedListener(new GenericTextWatcher(binding.otpET2, edit));
        binding.otpET3.addTextChangedListener(new GenericTextWatcher(binding.otpET3, edit));
        binding.otpET4.addTextChangedListener(new GenericTextWatcher(binding.otpET4, edit));
        listeners();
        return view;
    }

    private void listeners() {
        binding.imgforward.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view==binding.imgforward){
//            Navigation.findNavController(getActivity(), R.id.fragmentContainerView3).navigate(R.id.action_forgotPassFragment_to_homeActivity);
            otpVerificationPresenter.forgotpassMethod(binding.otpET1.getText().toString()+binding.otpET2.getText().toString()+binding.otpET3.getText().toString()
            +binding.otpET4.getText().toString());

        }
    }

    @Override
    public void showMessage(@Nullable Activity activity, @Nullable String msg) {
        Utils.Companion.showMessage(getActivity(),msg);

    }

    @Override
    public void showDialog(@Nullable Activity activity) {
        Utils.Companion.showDialogMethod(activity,activity.getFragmentManager());

    }

    @Override
    public void hideDialog() {
        Utils.Companion.hideDialog();

    }
}