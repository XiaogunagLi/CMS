package com.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("daoServer")
@Scope("singleton")
public class BaseDao {
	
	private HibernateTemplate hibernateTemplate;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
    @Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
    /**
     * 保存对象实例
     * @param obj
     */
    public  int  saveObject(Object obj){
    	Serializable id =  hibernateTemplate.save(obj);
    	int ID = Integer.parseInt(id.toString());
    	return ID;
    }
    /**
     * 更新对象实例
     * @param obj
     */
    public synchronized void updateObject(Object obj){
    	hibernateTemplate.update(obj);
    }
	/**
	 * 获取对象列表
	 * hql String  完整的hql语句
	 * @param <T>
	 */
    @SuppressWarnings("unchecked")
	public <T> List<T> getList(String hql){
    	List list = hibernateTemplate.find(hql);
    	return list;
    }
    
    public List<Object> getListBySql(String sql, int max){
    	Session session = hibernateTemplate.getSessionFactory().openSession();
    	Query query = session.createQuery(sql);
    	//query.setFirstResult(first);
    	query.setMaxResults(max);
    	List<Object> list = query.list();
    	session.close();
    	
    	return list;
    }
    
    public int getCount(String hql){
    	Session session = hibernateTemplate.getSessionFactory().openSession();
    	Long count = (Long)session.createQuery(hql).uniqueResult();
    	session.close();
    	
    	return Integer.parseInt(String.valueOf(count));
    }
    
    public List<Object> getList(String hql, int first, int max){
    	Session session = hibernateTemplate.getSessionFactory().openSession();
    	Query query = session.createQuery(hql);
    	query.setFirstResult(first);
    	query.setMaxResults(max);
    	List<Object> list = query.list();
    	session.close();
    	
    	return list;
    }
    
    //----------------------start---------------------
    public List getObjectList(String table,int dirId, int first, int max, String sort){
    	StringBuffer sb = new StringBuffer("from ");
    	sb.append(table);
    	sb.append(" where ifShow = 1 and dirId=");
    	sb.append(String.valueOf(dirId));
    	if(sort != null){
    		sb.append("order by id ");
    		sb.append(sort);
    	}
    	String hql = sb.toString();
    	Session session = hibernateTemplate.getSessionFactory().openSession();
    	Query query = session.createQuery(hql);
    	query.setFirstResult(first);
    	query.setMaxResults(max);
    	List list = query.list();
    	
    	session.close();
    	return list;
    }
    public List getObjectList(String table,int dirId, String sort){
    	StringBuffer sb = new StringBuffer("from ");
    	sb.append(table);
    	sb.append(" where ifShow = 1 and dirId=");
    	sb.append(String.valueOf(dirId));
    	if(sort != null){
    		sb.append("order by id ");
    		sb.append(sort);
    	}
    	String hql = sb.toString();
    	
    	List list = hibernateTemplate.find(hql);

    	return list;
    }
    //--------------------------end-----------------------------------
    /**
     * 根据ID获取对象实例
     */
    public Object getObjectById(Class entityClass, int id){
    	Object obj = hibernateTemplate.get(entityClass, id);
    	return obj;
    }
    public void delObject(Object obj){
    	hibernateTemplate.delete(obj);
    }
    /*//连表查询
    public List<Object> getDetailsList(int id){
    	Session session = hibernateTemplate.getSessionFactory().openSession();
    	 SQLQuery query = session.createSQLQuery("SELECT details.label0,details.content,details.detailsId,details.id FROM model_deseno deseno,model_desen_details details WHERE deseno.id=details.id AND deseno.id="+id);
    	 List<Object> list = query.list();
    	 session.close();
    	return list;
    }*/
    
}
