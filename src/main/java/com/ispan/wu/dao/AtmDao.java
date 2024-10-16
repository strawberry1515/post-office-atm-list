package com.ispan.wu.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.ispan.wu.bean.AtmBean;

public interface AtmDao {
	void insert(AtmBean atmBean) throws SQLException, NamingException;
	int deleteById(Integer id) throws SQLException, NamingException;
	int deleteAll() throws SQLException, NamingException;
	int update(AtmBean atmBean) throws SQLException, NamingException;
	AtmBean queryById(Integer id) throws SQLException, NamingException;
	AtmBean queryCityByCityNo(int cityNo) throws SQLException, NamingException;
	List<AtmBean> queryObscureByName(String postName) throws SQLException, NamingException;
	List<AtmBean> queryObscureByName(String postName, int cityNo) throws SQLException, NamingException;
	List<AtmBean> queryObscureByBooleanData(AtmBean atmBean) throws SQLException, NamingException;
	List<AtmBean> queryAll() throws SQLException, NamingException;
	boolean isExist(Integer id) throws SQLException, NamingException;
	AtmBean getLastKey() throws SQLException, NamingException;
	
}
