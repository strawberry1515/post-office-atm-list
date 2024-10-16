package com.ispan.wu.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ispan.wu.Constants;
import com.ispan.wu.Utils;
import com.ispan.wu.bean.AtmBean;
import com.ispan.wu.dao.result.Result;
import com.ispan.wu.dao.result.ResultImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.el.ImplicitObjectELResolver;

@WebServlet("/AtmDaoImpl_vF")
public class AtmDaoImpl_vF extends HttpServlet implements AtmDao {
	private static final long serialVersionUID = 1L;
	private static final String INSERT = "INSERT post_atm VALUES (? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ) ";
	private static final String DELETE_BY_ID = "DELETE post_atm WHERE [key] = ? ";
	private static final String DELETE_ALL = "DELETE post_atm ";
	private static final String RESET_IDENT = "DBCC CHECKIDENT (post_atm, RESEED, 0) ";
	private static final String UPDATE = "UPDATE post_atm SET " + "city = ?, " + "district = ?, " + "postNo = ?, "
			+ "postName = ?, " + "phone = ?, " + "address = ?, " + "longitude = ?, " + "latitude = ?, " + "hasAtm = ?, "
			+ "hasDeposit = ?, " + "hasPassbookUpdate = ?, " + "hasPassbookUpdateMachine = ?, " + "has200cash = ?, "
			+ "hasVoiceAssistant = ?, " + "isOutside = ?, " + "cityNo = ?, " + "insertTime = ? " + "WHERE [key] = ? ";
	private static final String QUERY_BY_ID = "SELECT * FROM post_atm WHERE [key] = ? ";
	private static final String QUERY_OBSCURE_BY_NAME = "SELECT * FROM post_atm WHERE postName LIKE ? ";
	private static final String QUERY_CITY_BY_CITYNO = "SELECT DISTINCT city, cityNo FROM post_atm WHERE cityNo = ? ";
	private static final String QUERY_ALL = "SELECT * FROM post_atm ORDER BY cityNo, [key] ";
	private static final String IS_EXIST = "SELECT COUNT(*) AS 'counts' FROM post_atm WHERE [key] = ? ";

	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String work = (String) request.getAttribute(Constants.getWorkString());
		String id = request.getParameter("id");//
		int key = 0;// same as above
		String postName = "";
		List<AtmBean> atms = null;
		AtmBean atm = null;
		Result result = new ResultImpl();
		request.setAttribute("result", result);

		// ===============新增from default file================
		if (work.equals(Constants.getInsertFromDefaultString())) {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					getServletConfig().getServletContext().getResourceAsStream("/data/post_atm.csv"), "UTF-8"))) {

				String line = null;
				boolean isFirstLine = true;
				while ((line = br.readLine()) != null) {
					if (isFirstLine) {
						isFirstLine = false;
						continue;
					}
					String[] sa = line.split(",");
					AtmBean atmBean = new AtmBean();
					atmBean.setCity(sa[0].trim());
					atmBean.setDistrict(sa[1].trim());
					atmBean.setPostNo(sa[2].trim());
					atmBean.setPostName(sa[3].trim());
					atmBean.setPhone(sa[4].trim());
					atmBean.setAddress(sa[5].trim());
					atmBean.setLongitude(Double.parseDouble(sa[6].trim()));
					atmBean.setLatitude(Double.parseDouble(sa[7].trim()));
					atmBean.setHasAtm(Utils.fromOToTrue(sa[8].trim()));
					atmBean.setHasDeposit(Utils.fromOToTrue(sa[9].trim()));
					atmBean.setHasPassbookUpdate(Utils.fromOToTrue(sa[10].trim()));
					atmBean.setHasPassbookUpdateMachine(Utils.fromOToTrue(sa[11].trim()));
					atmBean.setHas200cash(Utils.fromOToTrue(sa[12].trim()));
					atmBean.setHasVoiceAssistant(Utils.fromOToTrue(sa[13].trim()));
					atmBean.setOutside(Utils.fromOutsideToTrue(sa[14].trim()));
					atmBean.setCityNo(Integer.parseInt(sa[15].trim()));
					atmBean.setInsertTime(Timestamp.valueOf(LocalDateTime.now()));
					insert(atmBean);
//					System.out.println(atmBean);

					result.setMsg("突然多了好多資料, magic~");
					result.setIsSuccess(true);

					atms = queryAll();
					request.setAttribute("atms", atms);
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}

			// ===============新增from file================

		} else if (work.equals(Constants.getInsertFromFileString())) {
			String fileName = (String) request.getAttribute("fileName");
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					getServletConfig().getServletContext().getResourceAsStream("uploads/" + fileName), "UTF-8"))) {

				String line = null;
				boolean isFirstLine = true;
				while ((line = br.readLine()) != null) {
					if (isFirstLine) {
						isFirstLine = false;
						continue;
					}
					String[] sa = line.split(",");
					AtmBean atmBean = new AtmBean();
					atmBean.setCity(sa[0].trim());
					atmBean.setDistrict(sa[1].trim());
					atmBean.setPostNo(sa[2].trim());
					atmBean.setPostName(sa[3].trim());
					atmBean.setPhone(sa[4].trim());
					atmBean.setAddress(sa[5].trim());
					atmBean.setLongitude(Double.parseDouble(sa[6].trim()));
					atmBean.setLatitude(Double.parseDouble(sa[7].trim()));
					atmBean.setHasAtm(Utils.fromOToTrue(sa[8].trim()));
					atmBean.setHasDeposit(Utils.fromOToTrue(sa[9].trim()));
					atmBean.setHasPassbookUpdate(Utils.fromOToTrue(sa[10].trim()));
					atmBean.setHasPassbookUpdateMachine(Utils.fromOToTrue(sa[11].trim()));
					atmBean.setHas200cash(Utils.fromOToTrue(sa[12].trim()));
					atmBean.setHasVoiceAssistant(Utils.fromOToTrue(sa[13].trim()));
					atmBean.setOutside(Utils.fromOutsideToTrue(sa[14].trim()));
					atmBean.setCityNo(Integer.parseInt(sa[15].trim()));
					atmBean.setInsertTime(Timestamp.valueOf(LocalDateTime.now()));
					insert(atmBean);
//					System.out.println(atmBean);

					result.setMsg("新增檔案資料完成, 檔案名稱: " + fileName);
					result.setIsSuccess(true);

					atms = queryAll();
					request.setAttribute("atms", atms);
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			// =======================新增單筆from web===========================
		} else if (work.equals(Constants.getInsertString())) {

			try {
				
//				if (request.getParameter("key") != null) {
//					key = Integer.parseInt(request.getParameter("key"));
//				}
				postName = request.getParameter("postName");

				AtmBean atmBean = new AtmBean();

				atmBean.setCity(request.getParameter("city").split(",")[1]);
				atmBean.setDistrict(request.getParameter("district"));
				atmBean.setPostNo(request.getParameter("postNo"));
				atmBean.setPostName(postName);
				atmBean.setPhone(request.getParameter("phone"));
				atmBean.setAddress(request.getParameter("address"));
				atmBean.setLongitude(Double.parseDouble(request.getParameter("longitude")));
				atmBean.setLatitude(Double.parseDouble(request.getParameter("latitude")));
				atmBean.setHasAtm(Utils.fromOToTrue(request.getParameter("hasAtm")));
				atmBean.setHasDeposit(Utils.fromOToTrue(request.getParameter("hasDeposit")));
				atmBean.setHasPassbookUpdate(Utils.fromOToTrue(request.getParameter("hasPassbookUpdate")));
				atmBean.setHasPassbookUpdateMachine(
						Utils.fromOToTrue(request.getParameter("hasPassbookUpdateMachine")));
				atmBean.setHas200cash(Utils.fromOToTrue(request.getParameter("has200cash")));
				atmBean.setHasVoiceAssistant(Utils.fromOToTrue(request.getParameter("hasVoiceAssistant")));
				atmBean.setOutside(Utils.fromOutsideToTrue(request.getParameter("isOutside")));
				atmBean.setCityNo(Integer.parseInt(request.getParameter("cityNo")));
				atmBean.setInsertTime(Timestamp.valueOf(LocalDateTime.now()));
				
				if (!isExist(key)) {
					insert(atmBean);
					
					atm = getLastKey();
					atm = queryById(atm.getKey());
					request.setAttribute("atm", atm);
					result.setMsg("新增成功: 名稱[" + postName + "]的資料已新增, 資料編號[" + atm.getKey() + "]");
					result.setIsSuccess(true);
				} else {
					result.setMsg("!新增失敗!: 名稱[" + postName + "]的資料已存在");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}

			// =======================刪除===========================
		} else if (work.equals(Constants.getDeleteByIdString())) {
			try {
				int n = deleteById(Integer.parseInt(id));
				atms = queryAll();
				request.setAttribute("atms", atms);
				if (n != 0) {
					result.setMsg("刪除成功, 編號[" + id + "]的資料已刪除");
					result.setIsSuccess(true);
				} else {
					result.setMsg("!!刪除失敗!!, 編號[" + id + "]的資料不存在");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			// =======================刪除全部===========================
		} else if (work.equals(Constants.getDeleteAllString())) {
			try {
				int n = deleteAll();

				if (n != 0) {
					result.setMsg("小精靈已經把資料吃光光, yum yum");
					result.setIsSuccess(true);
				} else {
					result.setMsg("喔歐! 已經沒東西可以刪了!");
				}
				atms = queryAll();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			// =======================查詢全部===========================
		} else if (work.equals(Constants.getQueryAllString())) {
			atms = null;
			try {
				atms = queryAll();
				request.setAttribute("atms", atms);
				if (atms != null) {
					result.setMsg("查詢成功, 共" + atms.size() + "筆資料");
					result.setIsSuccess(true);
				} else {
					result.setMsg("!!無資料!!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}

			// =======================查詢byID===========================
		} else if (work.equals(Constants.getQueryByIdString())) {
			try {
				atm = queryById(Integer.parseInt(id));
				request.setAttribute("atm", atm);
				if (atm != null) {
					result.setMsg("查詢成功!");
					result.setIsSuccess(true);
				} else {
					result.setMsg("!!查無編號[" + id + "]的資料!!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}

			// =======================模糊查詢, 傳回List============================
		} else if (work.equals(Constants.getQueryObscureByNameString())) {
			try {
				postName = request.getParameter("postName");
				int cityNo = (Integer) request.getAttribute("cityNo");
				atms = queryObscureByName(postName, cityNo);
				AtmBean cityBean = queryCityByCityNo(cityNo);
				String msgCity = "";
				String msgPostName = "";
				if (!"".equals(postName)) {
					msgPostName = "名稱包含[" + postName + "]";
				}
				if (cityBean != null) {
					msgCity = "[" + cityBean.getCity() + "]中";
				}
				if("".equals(msgCity)&&"".equals(msgPostName)) {
					msgPostName="全部";
				}
				if (atms.size() != 0) {
					request.setAttribute("atms", atms);
					result.setMsg("查詢成功: " + msgCity + " " + msgPostName + "的資料共" + atms.size() + "筆");
					result.setIsSuccess(true);
				} else {
					result.setMsg("!!查無條件符合" + msgCity + " " + msgPostName + "的資料!!");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			//======================依boolean資料查詢================
		}else if (work.equals("queryObscureByBooleanData")) {
			try {
				AtmBean atmBean = new AtmBean();
				atmBean.setHasAtm(Utils.fromOToTrue(request.getParameter("hasAtm")));
				atmBean.setHasDeposit(Utils.fromOToTrue(request.getParameter("hasDeposit")));
				atmBean.setHasPassbookUpdate(Utils.fromOToTrue(request.getParameter("hasPassbookUpdate")));
				atmBean.setHasPassbookUpdateMachine(
						Utils.fromOToTrue(request.getParameter("hasPassbookUpdateMachine")));
				atmBean.setHas200cash(Utils.fromOToTrue(request.getParameter("has200cash")));
				atmBean.setHasVoiceAssistant(Utils.fromOToTrue(request.getParameter("hasVoiceAssistant")));
				atmBean.setOutside(Utils.fromOutsideToTrue(request.getParameter("isOutside")));
				
				atms = queryObscureByBooleanData(atmBean);
				request.setAttribute("atms", atms);
				
				if (atms != null) {
					result.setMsg("查詢成功, 共" + atms.size() + "筆資料");
					result.setIsSuccess(true);
				} else {
					result.setMsg("!!查無符合條件之資料!!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
//			// =======================修改===========================
		} else if (work.equals(Constants.getUpdateString())) {

			key = Integer.parseInt(request.getParameter("key"));
			postName = request.getParameter("postName");

			try {
				if (isExist(key)) {

					AtmBean data = new AtmBean();
					data.setKey(key);
					data.setCity(request.getParameter("city").split(",")[1]);
					data.setDistrict(request.getParameter("district"));
					data.setPostNo(request.getParameter("postNo"));
					data.setPostName(postName);
					data.setPhone(request.getParameter("phone"));
					data.setAddress(request.getParameter("address"));
					data.setLongitude(Double.parseDouble(request.getParameter("longitude")));
					data.setLatitude(Double.parseDouble(request.getParameter("latitude")));
					data.setHasAtm(Utils.fromOToTrue(request.getParameter("hasAtm")));
					data.setHasDeposit(Utils.fromOToTrue(request.getParameter("hasDeposit")));
					data.setHasPassbookUpdate(Utils.fromOToTrue(request.getParameter("hasPassbookUpdate")));
					data.setHasPassbookUpdateMachine(
							Utils.fromOToTrue(request.getParameter("hasPassbookUpdateMachine")));
					data.setHas200cash(Utils.fromOToTrue(request.getParameter("has200cash")));
					data.setHasVoiceAssistant(Utils.fromOToTrue(request.getParameter("hasVoiceAssistant")));
					data.setOutside(Utils.fromOutsideToTrue(request.getParameter("isOutside")));
					data.setCityNo(Integer.parseInt(request.getParameter("cityNo")));
					data.setInsertTime(Timestamp.valueOf(LocalDateTime.now()));

					update(data);
					request.setAttribute("atm", data);
					result.setMsg("修改成功:編號[" + key + "], 名稱[" + postName + "]的資料已修改");
					result.setIsSuccess(true);
				} else {
					result.setMsg("!修改失敗!:編號[" + key + "], 名稱[" + postName + "]的資料不存在");
				}
			} catch (SQLException e) {
				e.printStackTrace();

			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void insert(AtmBean atmBean) throws SQLException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt = conn.prepareStatement(INSERT);
		stmt.setString(1, atmBean.getCity());
		stmt.setString(2, atmBean.getDistrict());
		stmt.setString(3, atmBean.getPostNo());
		stmt.setString(4, atmBean.getPostName());
		stmt.setString(5, atmBean.getPhone());
		stmt.setString(6, atmBean.getAddress());
		stmt.setDouble(7, atmBean.getLongitude());
		stmt.setDouble(8, atmBean.getLatitude());
		stmt.setBoolean(9, atmBean.isHasAtm());
		stmt.setBoolean(10, atmBean.isHasDeposit());
		stmt.setBoolean(11, atmBean.isHasPassbookUpdate());
		stmt.setBoolean(12, atmBean.isHasPassbookUpdateMachine());
		stmt.setBoolean(13, atmBean.isHas200cash());
		stmt.setBoolean(14, atmBean.isHasVoiceAssistant());
		stmt.setBoolean(15, atmBean.isOutside());
		stmt.setInt(16, atmBean.getCityNo());
		stmt.setTimestamp(17, atmBean.getInsertTime());
		stmt.executeUpdate();
		conn.close();
	}

	@Override
	public int deleteById(Integer id) throws SQLException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt = conn.prepareStatement(DELETE_BY_ID);
		stmt.setInt(1, id);

		int n = stmt.executeUpdate();

		stmt.close();
		conn.close();
		return n;
	}

	@Override
	public int deleteAll() throws SQLException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt = conn.prepareStatement(DELETE_ALL);

		int n = stmt.executeUpdate();

		stmt = conn.prepareStatement(RESET_IDENT);
		stmt.executeUpdate();

		stmt.close();
		conn.close();
		return n;
	}

	@Override
	public int update(AtmBean atmBean) throws SQLException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt = conn.prepareStatement(UPDATE);
		stmt.setString(1, atmBean.getCity());
		stmt.setString(2, atmBean.getDistrict());
		stmt.setString(3, atmBean.getPostNo());
		stmt.setString(4, atmBean.getPostName());
		stmt.setString(5, atmBean.getPhone());
		stmt.setString(6, atmBean.getAddress());
		stmt.setDouble(7, atmBean.getLongitude());
		stmt.setDouble(8, atmBean.getLatitude());
		stmt.setBoolean(9, atmBean.isHasAtm());
		stmt.setBoolean(10, atmBean.isHasDeposit());
		stmt.setBoolean(11, atmBean.isHasPassbookUpdate());
		stmt.setBoolean(12, atmBean.isHasPassbookUpdateMachine());
		stmt.setBoolean(13, atmBean.isHas200cash());
		stmt.setBoolean(14, atmBean.isHasVoiceAssistant());
		stmt.setBoolean(15, atmBean.isOutside());
		stmt.setInt(16, atmBean.getCityNo());
		stmt.setTimestamp(17, atmBean.getInsertTime());
		stmt.setInt(18, atmBean.getKey());

		int n = stmt.executeUpdate();

		stmt.close();
		conn.close();
		return n;
	}

	@Override
	public AtmBean queryById(Integer id) throws SQLException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt = conn.prepareStatement(QUERY_BY_ID);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		AtmBean atm = null;
		if (rs.next()) {
			atm = new AtmBean();
			atm.setKey(id);
			atm.setCity(rs.getString("city"));
			atm.setDistrict(rs.getString("district"));
			atm.setPostNo(rs.getString("postNo"));
			atm.setPostName(rs.getString("postName"));
			atm.setPhone(rs.getString("phone"));
			atm.setAddress(rs.getString("address"));
			atm.setLongitude(rs.getDouble("longitude"));
			atm.setLatitude(rs.getDouble("latitude"));
			atm.setHasAtm(rs.getBoolean("hasAtm"));
			atm.setHasDeposit(rs.getBoolean("hasDeposit"));
			atm.setHasPassbookUpdate(rs.getBoolean("hasPassbookUpdate"));
			atm.setHasPassbookUpdateMachine(rs.getBoolean("hasPassbookUpdateMachine"));
			atm.setHas200cash(rs.getBoolean("has200cash"));
			atm.setHasVoiceAssistant(rs.getBoolean("hasVoiceAssistant"));
			atm.setOutside(rs.getBoolean("isOutside"));
			atm.setCityNo(rs.getInt("cityNo"));
			atm.setInsertTime(rs.getTimestamp("insertTime"));
		}

		stmt.close();
		conn.close();
		return atm;
	}

	@Override
	public List<AtmBean> queryObscureByName(String postName) throws SQLException, NamingException {
		return queryObscureByName(postName, -1);
	}

	@Override
	public List<AtmBean> queryObscureByName(String postName, int cityNo) throws SQLException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt;

		if (cityNo == -1) {
			stmt = conn.prepareStatement(QUERY_OBSCURE_BY_NAME);
		} else {
			stmt = conn.prepareStatement(QUERY_OBSCURE_BY_NAME + "AND cityNo = " + cityNo);
		}

		stmt.setString(1, "%" + postName + "%");
		ResultSet rs = stmt.executeQuery();
		List<AtmBean> atms = new ArrayList<AtmBean>();

		AtmBean atm = null;
		while (rs.next()) {
			atm = new AtmBean();
			atm.setKey(rs.getInt("key"));
			atm.setCity(rs.getString("city"));
			atm.setDistrict(rs.getString("district"));
			atm.setPostNo(rs.getString("postNo"));
			atm.setPostName(rs.getString("postName"));
			atm.setPhone(rs.getString("phone"));
			atm.setAddress(rs.getString("address"));
			atm.setLongitude(rs.getDouble("longitude"));
			atm.setLatitude(rs.getDouble("latitude"));
			atm.setHasAtm(rs.getBoolean("hasAtm"));
			atm.setHasDeposit(rs.getBoolean("hasDeposit"));
			atm.setHasPassbookUpdate(rs.getBoolean("hasPassbookUpdate"));
			atm.setHasPassbookUpdateMachine(rs.getBoolean("hasPassbookUpdateMachine"));
			atm.setHas200cash(rs.getBoolean("has200cash"));
			atm.setHasVoiceAssistant(rs.getBoolean("hasVoiceAssistant"));
			atm.setOutside(rs.getBoolean("isOutside"));
			atm.setCityNo(rs.getInt("cityNo"));
			atm.setInsertTime(rs.getTimestamp("insertTime"));
			atms.add(atm);
		}

		stmt.close();
		conn.close();
		return atms;
	}

	@Override
	public List<AtmBean> queryObscureByBooleanData(AtmBean atmBean) throws SQLException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt;
		String SQL="SELECT * FROM post_atm ";
		HashMap<String, Boolean> conditionValues = new HashMap<String, Boolean>(); 
			conditionValues.put("hasATM = 1 ",atmBean.isHasAtm());
			conditionValues.put("hasDeposit = 1 ",atmBean.isHasDeposit());
			conditionValues.put("hasPassbookUpdate = 1 ",atmBean.isHasPassbookUpdate());
			conditionValues.put("hasPassbookUpdateMachine = 1 ",atmBean.isHasPassbookUpdateMachine());
			conditionValues.put("has200cash = 1 ",atmBean.isHas200cash());
			conditionValues.put("hasVoiceAssistant = 1 ",atmBean.isHasVoiceAssistant());
			conditionValues.put("isOutside = 1 ",atmBean.isOutside());
			StringBuffer conditionSQL = new StringBuffer(SQL);
			Set<String> keySet = conditionValues.keySet();
			int itemWithTrue = 0;
			for (String condition : keySet) {
				boolean currentBoolean = conditionValues.get(condition);
				
				if(currentBoolean) {
					if(itemWithTrue==0) {
						conditionSQL.append("WHERE ");
					}else {
						conditionSQL.append("AND ");
					}
					conditionSQL.append(condition);
					itemWithTrue++;
				}
			}
			stmt=conn.prepareStatement(conditionSQL.toString());
			ResultSet rs = stmt.executeQuery();
			List<AtmBean> atms = new ArrayList<AtmBean>();
			while (rs.next()) {
				AtmBean atm = new AtmBean();
				atm.setKey(rs.getInt("key"));
				atm.setCity(rs.getString("city"));
				atm.setDistrict(rs.getString("district"));
				atm.setPostNo(rs.getString("postNo"));
				atm.setPostName(rs.getString("postName"));
				atm.setPhone(rs.getString("phone"));
				atm.setAddress(rs.getString("address"));
				atm.setLongitude(rs.getDouble("longitude"));
				atm.setLatitude(rs.getDouble("latitude"));
				atm.setHasAtm(rs.getBoolean("hasAtm"));
				atm.setHasDeposit(rs.getBoolean("hasDeposit"));
				atm.setHasPassbookUpdate(rs.getBoolean("hasPassbookUpdate"));
				atm.setHasPassbookUpdateMachine(rs.getBoolean("hasPassbookUpdateMachine"));
				atm.setHas200cash(rs.getBoolean("has200cash"));
				atm.setHasVoiceAssistant(rs.getBoolean("hasVoiceAssistant"));
				atm.setOutside(rs.getBoolean("isOutside"));
				atm.setCityNo(rs.getInt("cityNo"));
				atm.setInsertTime(rs.getTimestamp("insertTime"));
				atms.add(atm);
			}

			stmt.close();
			conn.close();
			return atms;
		
	}

	@Override
	public List<AtmBean> queryAll() throws SQLException, NamingException {
		List<AtmBean> atms = new ArrayList<AtmBean>();
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt = conn.prepareStatement(QUERY_ALL);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			AtmBean atm = new AtmBean();

			atm.setKey(rs.getInt("key"));
			atm.setCity(rs.getString("city"));
			atm.setDistrict(rs.getString("district"));
			atm.setPostNo(rs.getString("postNo"));
			atm.setPostName(rs.getString("postName"));
			atm.setPhone(rs.getString("phone"));
			atm.setAddress(rs.getString("address"));
			atm.setLongitude(rs.getDouble("longitude"));
			atm.setLatitude(rs.getDouble("latitude"));
			atm.setHasAtm(rs.getBoolean("hasAtm"));
			atm.setHasDeposit(rs.getBoolean("hasDeposit"));
			atm.setHasPassbookUpdate(rs.getBoolean("hasPassbookUpdate"));
			atm.setHasPassbookUpdateMachine(rs.getBoolean("hasPassbookUpdateMachine"));
			atm.setHas200cash(rs.getBoolean("has200cash"));
			atm.setHasVoiceAssistant(rs.getBoolean("hasVoiceAssistant"));
			atm.setOutside(rs.getBoolean("isOutside"));
			atm.setCityNo(rs.getInt("cityNo"));
			atm.setInsertTime(rs.getTimestamp("insertTime"));
			atms.add(atm);
		}

		stmt.close();
		conn.close();
		return atms;
	}

	@Override
	public boolean isExist(Integer id) throws SQLException, NamingException {
		if (id == 0) {
			return false;
		}
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt = conn.prepareStatement(IS_EXIST);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		String counts = "";
		if (rs.next()) {
			counts = rs.getString("counts");
		}

		stmt.close();
		conn.close();

		if ("0".equals(counts)) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public AtmBean queryCityByCityNo(int cityNo) throws SQLException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt = conn.prepareStatement(QUERY_CITY_BY_CITYNO);

		stmt.setInt(1, cityNo);
		ResultSet rs = stmt.executeQuery();

		AtmBean atm = null;
		if (rs.next()) {
			atm = new AtmBean();
			atm.setCity(rs.getString("city"));
			atm.setCityNo(rs.getInt("cityNo"));
		}

		stmt.close();
		conn.close();
		return atm;
	}

	@Override
	public AtmBean getLastKey() throws SQLException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
		conn = ds.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT TOP 1 [key] FROM post_atm ORDER BY [key] DESC ");
		ResultSet rs = stmt.executeQuery();
		
		AtmBean atm = null;
		if (rs.next()) {
			atm = new AtmBean();
			atm.setKey(rs.getInt("key"));
		}

		
			
		stmt.close();
		conn.close();
		return atm;
	}

}
