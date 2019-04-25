package za.co.moxomo.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import io.reactivex.disposables.CompositeDisposable;
import za.co.moxomo.MoxomoApplication;
import za.co.moxomo.R;
import za.co.moxomo.databinding.FragmentCreateAlertBinding;
import za.co.moxomo.model.Alert;
import za.co.moxomo.model.ApiResponse;
import za.co.moxomo.service.Status;
import za.co.moxomo.viewmodel.AlertActivityViewModel;
import za.co.moxomo.viewmodel.ViewModelFactory;


public class CreateAlertFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    Gson gson;

    private FragmentCreateAlertBinding binding;
    private AlertActivityViewModel alertActivityViewModel;
    private NavController navController;

    public CreateAlertFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MoxomoApplication.moxomoApplication().injectionComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_alert, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        alertActivityViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(AlertActivityViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.navHostFragment);
        binding.registerBtn.setOnClickListener(btn -> {
          //  createAlert();
        });
        alertActivityViewModel.getAlertCreationResponse().observe(getActivity(), result -> {
            processAlerCreationResponse(result);
        });
    }


    private void createAlert() {
        Alert.AlertBuilder alertDTOBuilder = Alert.builder();
        if (null != binding.jobTitle.getText() || binding.jobTitle.getText().length() > 0) {
            alertDTOBuilder.tags(binding.jobTitle.getText().toString());
        }
        if (null != binding.company.getText() || binding.company.getText().length() > 0) {
            alertDTOBuilder.tags(binding.company.getText().toString());
        }
        if (null != binding.location.getText() || binding.location.getText().length() > 0) {
            alertDTOBuilder.tags(binding.location.getText().toString());
        }
        if (null != binding.mobileNumber.getText() || binding.mobileNumber.getText().length() > 0) {
            alertDTOBuilder.tags(binding.mobileNumber.getText().toString());
        }
        alertDTOBuilder.sms(binding.push.getText()
                .toString())
                .sms(binding.sms.getText().toString());
        alertActivityViewModel.createAlert(alertDTOBuilder.build());





    }

    private void processAlerCreationResponse(ApiResponse apiResponse) {
        if (apiResponse.status.equals(Status.SUCCESS)) {
           Alert alert = gson.fromJson(apiResponse.data, Alert.class);
        }

    }

}
