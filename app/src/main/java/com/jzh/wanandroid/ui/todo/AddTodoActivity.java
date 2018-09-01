package com.jzh.wanandroid.ui.todo;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jzh.wanandroid.R;
import com.jzh.wanandroid.ui.base.BaseActivity;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:jzh
 * desc:
 * Date:2018/09/01 09:35
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class AddTodoActivity extends BaseActivity implements AddTodoMvpView {
    @BindView(R.id.ac_todo_add_title)
    EditText mTitle;
    @BindView(R.id.ac_todo_add_content)
    EditText mContent;
    @BindView(R.id.ac_todo_add_time)
    Button mTime;
    @Inject
    AddTodoPresenter<AddTodoMvpView> mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_add_todo;
    }

    @Override
    protected void initWidget() {
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
        setUnBinder(ButterKnife.bind(this));
        setTitle(R.string.todo_add_title, true, false, false);
        Calendar calendar = Calendar.getInstance();
        mTime.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @OnClick({R.id.ac_todo_add_time, R.id.ac_todo_add_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_todo_add_time:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        mTime.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();
                break;
            case R.id.ac_todo_add_btn:
                if (TextUtils.isEmpty(getmTitle())) {
                    onToastWarn("标题不能为空");
                    return;
                }
                if (TextUtils.isEmpty(getTime())) {
                    onToastWarn("日期不能为空");
                    return;
                }
                showProgressLoadingDialog("添加中，请稍后...");
                mPresenter.doAddTodoCall(getmTitle(), getContent(), getTime(), 0);
                break;
        }
    }

    private String getmTitle() {
        return mTitle.getText().toString().trim();
    }

    private String getContent() {
        return mContent.getText().toString().trim();
    }

    private String getTime() {
        return mTime.getText().toString();
    }

    @Override
    public void onSucc(String msg) {
        onToastSucc(msg);
        setResult(RESULT_OK);
        finish();
    }
}
