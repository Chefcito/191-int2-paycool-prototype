package co.edu.icesi.joancaliz.paycool_prototype.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import co.edu.icesi.joancaliz.paycool_prototype.User;

public class SignUpViewModel extends ViewModel {
    private MutableLiveData<User> user = new MutableLiveData<>();

    public LiveData<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }

    public String getName() {
        return user.getValue().getName();
    }

    public void setUserName(String name) {
        this.user.getValue().setName(name);
    }

    public void setUserSurname(String surname) {
        this.user.getValue().setName(surname);
    }

    public void setUserDni(String Dni) {
        this.user.getValue().setDni(Dni);
    }

    public void setUserDocumentExpeditionDate(String documentExpeditionDate) {
        this.user.getValue().setDocumentExpeditionDate(documentExpeditionDate);
    }

    public void setUserPhoneNumber(String phoneNumber) {
        this.user.getValue().setPhoneNumber(phoneNumber);
    }

    public void setUserEmail(String email) {
        this.user.getValue().setEmail(email);
    }

    public void setUserPassword(String password) {
        this.user.getValue().setPassword(password);
    }
}
