package com.fino.projekaplikasimobile;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText fullname, username, email, password, confirmPassword, phone, address;
    private DatePicker birthDate;
    private Spinner gender;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Pastikan layout yang benar digunakan

        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        birthDate = findViewById(R.id.birth_date);
        gender = findViewById(R.id.gender);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    // Create success message
                    String successMessage = "Registration successful!\n" +
                            "Name: " + fullname.getText().toString().trim() + "\n" +
                            "Email: " + email.getText().toString().trim() + "\n" +
                            "Birth Date: " + getFormattedBirthDate();

                    // Display success message in a Toast
                    Toast.makeText(MainActivity.this, successMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validateInputs() {
        String fullnameText = fullname.getText().toString().trim();
        String usernameText = username.getText().toString().trim();
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        String confirmPasswordText = confirmPassword.getText().toString().trim();
        String genderText = gender.getSelectedItem().toString();
        String phoneText = phone.getText().toString().trim();
        String addressText = address.getText().toString().trim();

        int day = birthDate.getDayOfMonth();
        int month = birthDate.getMonth();
        int year = birthDate.getYear();
        String birthDateText = day + "/" + (month + 1) + "/" + year;

        if (TextUtils.isEmpty(fullnameText)) {
            fullname.setError("Full name is required");
            return false;
        }

        if (TextUtils.isEmpty(usernameText)) {
            username.setError("Username is required");
            return false;
        }

        if (TextUtils.isEmpty(emailText) || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError("Valid email is required");
            return false;
        }

        if (TextUtils.isEmpty(passwordText)) {
            password.setError("Password is required");
            return false;
        }

        if (TextUtils.isEmpty(confirmPasswordText) || !passwordText.equals(confirmPasswordText)) {
            confirmPassword.setError("Passwords do not match");
            return false;
        }

        if (TextUtils.isEmpty(phoneText)) {
            phone.setError("Phone number is required");
            return false;
        }

        if (TextUtils.isEmpty(addressText)) {
            address.setError("Address is required");
            return false;
        }

        // Validasi gender
        if (gender.getSelectedItemPosition() == 0) {  // Menganggap item pertama adalah prompt atau placeholder
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validasi umur, misalnya memastikan pengguna berusia setidaknya 13 tahun
        Calendar today = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        if (age < 5) {
            Toast.makeText(this, "You must be at least 13 years old", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String getFormattedBirthDate() {
        int day = birthDate.getDayOfMonth();
        int month = birthDate.getMonth() + 1;
        int year = birthDate.getYear();
        return day + "/" + month + "/" + year;
    }
}
