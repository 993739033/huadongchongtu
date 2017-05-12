package com.scode.huadongchongtu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by 知らないのセカイ on 2017/5/12.
 */

public class TestListView extends ListView {
    public TestListView(Context context) {
        super(context);
    }

    public TestListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TestListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private String[] content = {"shdfsdhf", "sdfijasodfj0", "sdfsajdfkjs","sdfsdfasdf","ASDFSDFasd","FSDFSDFS","FDVXV","sdfasdfasdf"
            ,"xzcvgasdgdfasdf","SDFSADFASDFSD","XZVCASDFSDFSDFSD","shdfsdhf", "sdfijasodfj0", "sdfsajdfkjs","sdfsdfasdf","ASDFSDFasd","FSDFSDFS","FDVXV","sdfasdfasdf"
            ,"xzcvgasdgdfasdf","SDFSADFASDFSD","XZVCASDFSDFSDFSD"};
    private ArrayAdapter<String> arrayAdapter=null;
    private void initView(){
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, content);
        this.setAdapter(arrayAdapter);
    }

}
