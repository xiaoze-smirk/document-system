package edu.fjnu.utils;

import edu.fjnu.entity.Department;

import java.util.List;

public class EntityUtil {

    public String str(String[] s , List<Department> deptList){

        StringBuilder builder = new StringBuilder();

        for (int i=0;i<s.length;i++){
            if(i!=0)
                builder.append("、");
            for(Department dept:deptList) {
                if(dept.getDeptId().equals(s[i])){
                    builder.append(dept.getDeptName());
                }

            }
        }
        return builder.toString();

    }

}
