package com.common;

import java.io.Serializable;
import java.util.List;


public class PageDao<T, PK extends Serializable> extends BaseDao<T, PK> {

	// 每页五条记录
	private static final int PER_PAGE_NUM = 5;

	public List<T> findListByCondition(int currentPage, int direction,String con1,String con2,Page page,int userId) {
		List<T> list = null;
		int start = 0;      //默认从第一条开始
		int totalNum = 0;   //默认数量为 0
		String hql=null;
		
		start = this.getStartIndex(currentPage, direction);
		hql = this.getHql(con1, con2,userId);
		//获取总条数，带有条件的
		totalNum = super.getCount(hql);
		//开始查询大于总条数  开始是从0开始，不是从1开始，所以减去1
		if(start>totalNum-1){
			//如果超出，则还是显示当前的页面
			start = (currentPage - 1) * PageDao.PER_PAGE_NUM ;
		}
		list = this.find(hql, start, PageDao.PER_PAGE_NUM);
		this.setPage(page, start, totalNum);
		
		System.out.println("输出："+hql);  //测试的时候用的
		System.out.println(start+"    "+totalNum);   //测试的时候用的
		return list;
	}
	
	//获取当前实体
	public String getEntity(){
		String [] s =this.entityClass.toString().split("\\.");
		return s[s.length-1];
	}
	
	//获取当前表的总条数
	public int getTotalNum(){
		String hql = "from "+this.getEntity();
		return super.getCount(hql);
	}
	
	//获取将要从数据库提取数据的开始下标
	private int getStartIndex(int currentPage,int direction){
		int start = 0;
		if (currentPage >= 1) {
			if (direction == 1) {
				start = currentPage * PageDao.PER_PAGE_NUM ;
			} else if (currentPage>1 && direction == -1) {
				start = (currentPage - 2) * PageDao.PER_PAGE_NUM ;
			} else if (direction == 0){
				start = 0;
			}
		} else {
			start = 0;
		}
		return start;
	}
	
	//获取整理完后的hql
	private String getHql(String con1,String con2,int userId){
		//获取当前实体
		String hql = "from "+this.getEntity();
		hql += " where user_id="+userId+" ";
		//附加查询条件
		if(con1!=null && con2!=null){
			hql += " and "+con1+" and "+con2;
		}else if(con1!=null && con2==null){
			hql += " and "+con1;
		}else if(con2!=null && con1==null){
			hql += " and "+con2;
		}
		//添加排序方法  Wallet 没有  id column
		if(!"Wallet".equalsIgnoreCase(this.getEntity()))
			hql += " order by id";
		
		return hql;
	}
	
	private void setPage(Page page,int start,int totalNum){
		page.setCurrentPage(start/PageDao.PER_PAGE_NUM + 1);
		int totalPage = totalNum%5==0?totalNum/5:totalNum/5+1;
		page.setTotalPage(totalPage);
	}
	
}
