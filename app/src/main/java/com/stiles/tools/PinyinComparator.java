package com.stiles.tools;

import com.stiles.model.ContactBean;

import java.util.Comparator;

/**
 * Created by stiles on 16/5/10.
 */
public class PinyinComparator implements Comparator<ContactBean> {
    @Override
    public int compare(ContactBean lhs, ContactBean rhs) {
        return sort(lhs, rhs);
    }

    private int sort(ContactBean lhs, ContactBean rhs) {
        // 获取ascii值
        int lhs_ascii = lhs.getFirstPinYin().toUpperCase().charAt(0);
        int rhs_ascii = rhs.getFirstPinYin().toUpperCase().charAt(0);
        // 判断若不是字母，则排在字母之后
        if (lhs_ascii < 65 || lhs_ascii > 90)
            return 1;
        else if (rhs_ascii < 65 || rhs_ascii > 90)
            return -1;
        else
            return lhs.getPinYin().compareTo(rhs.getPinYin());
    }
}
