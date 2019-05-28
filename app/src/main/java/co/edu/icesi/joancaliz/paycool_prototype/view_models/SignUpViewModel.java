package co.edu.icesi.joancaliz.paycool_prototype.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

import co.edu.icesi.joancaliz.paycool_prototype.User;

public class SignUpViewModel extends ViewModel {
    private ArrayList<String> userData = new ArrayList<>();
    private MutableLiveData<User> user = new MutableLiveData<>();

    public String getStringData(int index) {
        return userData.get(index);
    }

    public void setStringData(String data) {
        this.userData.add(data);
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }
}
