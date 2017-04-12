package com.edgarxie.bindingtest.aty;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.edgarxie.bindingtest.R;
import com.edgarxie.bindingtest.databinding.ActivityMainBinding;
import com.edgarxie.bindingtest.model.Employee;

public class MainActivity extends AppCompatActivity {
    private Employee employee=new Employee("Edgar","Xie",true);
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setEmployee(employee);
        binding.setPresenter(new Presenter());
    }


    public class Presenter{
        public void onClick(View view){
            Toast.makeText(MainActivity.this,"ddd",Toast.LENGTH_SHORT).show();
        }

        public void onClickListenerBinding(Employee employee){
            Toast.makeText(MainActivity.this,employee.getLastName(),Toast.LENGTH_LONG)
                    .show();
        }
    }
}
