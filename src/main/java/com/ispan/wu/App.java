package com.ispan.wu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.naming.NamingException;

import com.ispan.wu.bean.AtmBean;
import com.ispan.wu.dao.AtmDao;
import com.ispan.wu.dao.AtmDaoImpl;

public class App {
	private static final String HOST = "127.0.0.1";
	private static final String user = "sa";
	private static final String password = "P@ssw0rd";

	public static String dbURL = "jdbc:sqlserver://" + HOST + ":1433;databaseName=midtern;encrypt=false";

	public static String getDbURL() {
		return dbURL;
	}

	public static String getUser() {
		return user;
	}

	public static String getPassword() {
		return password;
	}

	public static void main(String[] args) {

		String work = "insert";

		AtmDao atmDao = null;

		// ===============新增================
		if (work.equals("insert")) {
			try (BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("data/post_atm.csv"), "UTF-8"))) {
				atmDao = new AtmDaoImpl();
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
					atmDao.insert(atmBean);
					System.out.println(atmBean);
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
			// =======================刪除===========================
		} else if (work.equals("delete")) {
			try {
				atmDao = new AtmDaoImpl();
				atmDao.deleteById(11);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			// =======================查詢全部===========================
		} else if (work.equals("findAll")) {
			atmDao = new AtmDaoImpl();
			List<AtmBean> result = null;
			try {
				result = atmDao.queryAll();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			for (AtmBean bean : result) {
				System.out.println(bean);
			}
			// =======================查詢byID===========================
		} else if (work.equals("findById")) {
			try {
				atmDao = new AtmDaoImpl();
				AtmBean atm = atmDao.queryById(999999);
				System.out.println(atm);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			// =======================修改===========================
		} else if (work.equals("update")) {

			int key = 120000;

			atmDao = new AtmDaoImpl();

			try {
				if (atmDao.isExist(key)) {

					AtmBean data = new AtmBean();
					data.setKey(key);
					data.setCity("123");
					data.setDistrict("123");
					data.setPostNo("123");
					data.setPostName("123");
					data.setPhone("123");
					data.setAddress("123");
					data.setLongitude(789.456);
					data.setLatitude(123.456);
					data.setHasAtm(true);
					data.setHasDeposit(true);
					data.setHasPassbookUpdate(true);
					data.setHasPassbookUpdateMachine(true);
					data.setHas200cash(true);
					data.setHasVoiceAssistant(true);
					data.setOutside(true);
					data.setCityNo(100);
					data.setInsertTime(Timestamp.valueOf(LocalDateTime.now()));
					int n = atmDao.update(data);
					System.out.println(n);
				} else {
					System.out.println("????");
				}
			} catch (SQLException e) {
				e.printStackTrace();

			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

}
