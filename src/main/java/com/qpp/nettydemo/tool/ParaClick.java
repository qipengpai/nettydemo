package com.qpp.nettydemo.tool;

import java.util.List;


public class ParaClick {

	public static boolean clickString(String str){
		if(str==null){
			return true;
		}else if("".equals(str)){
			return true;
		}
		return false;
	}
	@SuppressWarnings("rawtypes")
	public static boolean clickList(List list){
		if(list!=null&&list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	public static boolean clickObj(Object obj){
		if(obj==null){
			return false;
		}else{
			return true;
		}
	}
}
