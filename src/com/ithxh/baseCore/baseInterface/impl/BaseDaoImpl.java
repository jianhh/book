package com.ithxh.baseCore.baseInterface.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.xhh.constant.StaticConst.AndOrOr;
import com.ithxh.xhh.tools.SqlBuilder;

public class BaseDaoImpl<T,V> implements BaseDao<T,V>{
	
	Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);
	
	@Autowired
	protected JdbcTemplate jt;
	
	protected Class<T> entityClass;
	protected Class<V> voClass;
	//sql语句构建器
	protected SqlBuilder sb;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected BaseDaoImpl() {
		this.entityClass = null;
		this.voClass = null;
		Class c = this.getClass();
		//获取父类
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			//获取泛型参数类型
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			//获取实体类型
			this.entityClass = (Class<T>) parameterizedType[0];
			//获取vo类型
			this.voClass = (Class<V>) parameterizedType[1];
			//构造对应 sql语句生成器
			sb = new SqlBuilder(entityClass);
		}
	}

	@Override
	public int insert(T obj) throws SQLException {
		String sql = sb.getInsertSql();
		Object[] params = sb.getAllParameter(obj);
		return jt.update(sql, params);
	}
	
	@Override
	public int insertList(List<T> list) throws SQLException {
		int insert = 0;
		for(T t:list){
			insert += insert(t);
		}
		return insert;
	}

	@Override
	public int delete(String id) throws SQLException {
		String sql = sb.getDeleteSql();
		return jt.update(sql, id);
	}

	@Override
	public int delete(String[] ids) throws SQLException {
		int delete = 0;
		for(String id:ids){
			delete += delete(id);
		}
		return delete;
	}

	@Override
	public int updateTable(T obj) throws SQLException {
		String sql = sb.getUpdateSql(obj);
		Object[] params = sb.getNotNullAllParameter(obj);
		
		return jt.update(sql, params);
	}

	@Override
	public V findById(String id) throws SQLException {
		String sql = sb.getFindSql();
		List<V> list = jt.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(voClass),id);
		if(ListUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}

	@Override
	public V findByFieldName(String fieldName, String fieldValue) throws SQLException {
		StringBuilder sbu = new StringBuilder();
		sbu.append(" select * from ").append(sb.getTableName()).append(" where ").append(fieldName).append(" = ? ");
		List<V> list = jt.query(sbu.toString(), ParameterizedBeanPropertyRowMapper.newInstance(voClass),fieldValue);
		if(ListUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<V> findListByIds(String[] ids) throws SQLException {
		String sql = sb.getFindListSql(ids);
		return jt.query(sql, ids, ParameterizedBeanPropertyRowMapper.newInstance(voClass));
	}

	@Override
	public List<V> findListByFieldName(String fieldName, String fieldValue,
			boolean isSpecific) throws SQLException {
		StringBuilder sbu = new StringBuilder();
		sbu.append(" select * from ").append(sb.getTableName()).append(" where ").append(fieldName).append(" = ? ");
		return jt.query(sbu.toString(), new String[]{fieldValue},ParameterizedBeanPropertyRowMapper.newInstance(voClass));
	}

	@Override
	public int updateTableOneField(Object table, String fieldName,
			Object fieldValue, Object id) throws SQLException {
		StringBuilder sbu = new StringBuilder();
		sbu.append(" update ").append(sb.getTableName()).append(" set ").append(fieldName).append(" = ? ").append(" where ").append(sb.getPrimaryKey()).append(" = ? ");
		return jt.update(sbu.toString(), new Object[]{fieldValue,id});
	}
	
	@Override
	public int updateTableOneField(String[] fieldName,
			Object[] fieldValue, Object id) throws SQLException {
		StringBuilder sbu = new StringBuilder();
		
		sbu.append(" update ").append(sb.getTableName()).append(" set ");
		for (int i = 0; i < fieldName.length; i++) {
			sbu.append(fieldName[i].toString()).append(" = ? ");
			if(fieldName.length>1 && i!=fieldName.length-1){
				sbu.append(" , ");
			}
		}
		
		sbu.append(" where ").append(sb.getPrimaryKey()).append(" = ? ");
		String[] params = new String[fieldValue.length+1];
		for (int i = 0; i < fieldValue.length; i++) {
			params[i] = fieldValue[i].toString();
		}
		params[params.length-1] = id.toString();
		return jt.update(sbu.toString(), params);
	}

	@Override
	public int updateTableOneFieldAddValue(Object table, String fieldName,
			String id, Integer value) throws SQLException {
		StringBuilder sbu = new StringBuilder();
		String val = "";
		if(value>=0){
			val = "+"+Math.abs(value);
		}else{
			val = value+"";
		}
		sbu.append(" update ").append(sb.getTableName()).append(" set ").append(fieldName).append(" = IFNULL(").append(fieldName).append(",0)").append(val).append(" where ").append(sb.getPrimaryKey()).append(" = ? ");
		return jt.update(sbu.toString(), new Object[]{id});
	}

	@Override
	public int updateTableOneFieldAddOne(Object table, String fieldName, String id)
			throws SQLException {
		StringBuilder sbu = new StringBuilder();
		sbu.append(" update ").append(sb.getTableName()).append(" set ").append(fieldName).append(" = IFNULL(").append(fieldName).append(",0)+1").append(" where ").append(sb.getPrimaryKey()).append(" = ? ");
		return jt.update(sbu.toString(), new Object[]{id});
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public Pager findForPager(Pager pager,AndOrOr andOrOr) {
		if (pager == null) {
			pager = new Pager();
		}
		Object[] keywords = com.ithxh.baseCore.baseUtils.SqlBuilder.BuildKeyWord(pager);

		// 1) 组建 select 后面的语句
		String strSelectSql = "";
		try {
			strSelectSql = com.ithxh.baseCore.baseUtils.SqlBuilder.BuildSelectPropSql(entityClass.newInstance());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		// 2) 组建where 后面的语句(包含 order by limit 等 )
		String whereSql = "";
		if(andOrOr!=null)
		{
			 whereSql = com.ithxh.baseCore.baseUtils.SqlBuilder.BuildSelectWhereSql(pager, andOrOr.getCode());
		}
		// 3) 组建count 记录条数的语句
		String whereCountSql = com.ithxh.baseCore.baseUtils.SqlBuilder.BuildCountWhereSql(pager, andOrOr.getCode());

		String resultSql = null;
		if(whereSql.equals(""))
		{
			resultSql = " select " + strSelectSql + " from " + sb.getTableName();
			
		}else if (whereSql.trim().startsWith("limit") || whereSql.trim().startsWith("order")) {
			resultSql = " select " + strSelectSql + " from " + sb.getTableName() + whereSql;

		} else {
			resultSql = " select " + strSelectSql + " from " + sb.getTableName() + " where " + whereSql;
		}
		String countSql = null;
		if (whereCountSql.trim().equals("") || whereSql.trim().startsWith("order")) {
			countSql = " select count(1) from " + sb.getTableName();
		} else {
			countSql = " select count(1) from " + sb.getTableName() + " where " + whereCountSql;
		}
		System.out.println("查询语句是：" + resultSql);
		System.out.println("统计语句是：" + countSql);
		List resultList = jt.query(resultSql, ParameterizedBeanPropertyRowMapper.newInstance(voClass), keywords);
		int total = jt.queryForInt(countSql, keywords);
		pager.setList(resultList);
		pager.setTotalCount(total);
		return pager;
	}
	
	/**
	 * @Description: 查询list  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午2:47:55
	 * @param @param sql
	 * @param @param args
	 * @param @return
	 */
	public List<V> findList(String sql, Object[] args) {

		if (args != null) {
			return jt.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(voClass), args);
		} else {
			return jt.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(voClass));
		}
	}
	
	
	public int deleteByOneField(String fieldName,String value){
		String sql = "delete from "+sb.getTableName()+" where "+fieldName+" =?";
		return jt.update(sql, value);
	}
	
	public int deleteByFields(String[] fieldName,String[] values){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from ").append(sb.getTableName()).append(" where ");
		for(String f : fieldName){
			stringBuilder.append(f).append(" = ").append("? and ");
		}
		stringBuilder.append(" 1=1 ");
		return jt.update(stringBuilder.toString(), values);
	}

	@Override
	public List<V> findAll() {
		String sql = "select * from "+sb.getTableName();
		return jt.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(voClass));
	}
	
	
	public List<V> findByOrderAndLength(String order,String start,String end){
		String sql = "select * from "+sb.getTableName()+" order by "+order+" limit "+start+","+end;
		return jt.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(voClass));
	}
	
	
	public List<V> findByFields(String[] fields,Object[] values,AndOrOr andOrOr){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from ").append(sb.getTableName()).append(" where ");
		for(String f : fields){
			stringBuilder.append(f).append(" = ").append("? ").append(andOrOr.getCode());
		}
		stringBuilder.append(" 1=1 ");
		return jt.query(stringBuilder.toString(),values, ParameterizedBeanPropertyRowMapper.newInstance(voClass));
	}
}
