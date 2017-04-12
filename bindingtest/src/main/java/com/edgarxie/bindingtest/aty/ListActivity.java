package com.edgarxie.bindingtest.aty;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.edgarxie.bindingtest.R;
import com.edgarxie.bindingtest.databinding.ActivityListBinding;
import com.edgarxie.bindingtest.model.Employee;
import com.edgarxie.bindingtest.model.EmployeeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 17-4-6.
 */

public class ListActivity extends AppCompatActivity {

    private ActivityListBinding mBinding;
    private EmployeeAdapter mAdapter;

    public class Presenter{
        public void onClickAddItem(View view){
            mAdapter.add(new Employee("123","456",false));
        }

        public void onClickRemoveItem(View view){
            mAdapter.remove();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_list);
        mBinding.setPresenter(new Presenter());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new EmployeeAdapter(this);
        mBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.setListener(new EmployeeAdapter.onItemClickListener() {
            @Override
            public void onClick(Employee employee) {
                Toast.makeText(ListActivity.this,employee.getFirstName(),Toast.LENGTH_SHORT).show();

            }
        });

        List<Employee> demoList=new ArrayList<>();
        demoList.add(new Employee("edgar","xie",true));
        demoList.add(new Employee("edg","xie",false));
        demoList.add(new Employee("edar","xe",true));
        demoList.add(new Employee("edgar","xie",true));
        demoList.add(new Employee("edddar","xie",false));
        demoList.add(new Employee("d","dd",true));
        demoList.add(new Employee("edgar","ee",true));
        demoList.add(new Employee("dd的的的","dd",false));
        mAdapter.addAll(demoList);
    }
}
