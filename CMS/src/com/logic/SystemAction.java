package com.logic;
//package gm.logic;
//
//import gm.servers.SystemServer;
//import gm.tool.FormTag;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.apache.struts2.ServletActionContext;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.opensymphony.xwork2.ModelDriven;
//
//@Component("system")
//@Scope("prototype")
//public class SystemAction  implements ModelDriven<Object> {
//	public SystemServer sys;
//	private  int pageSize = 10;
//	public SystemServer getSys() {
//		return sys;
//	}
//	@Resource(name="systemServer")
//	public void setU(SystemServer sys) {
//		this.sys = sys;
//	}
//	public Object getModel() {
//		return null ;
//	}
//	
//	public String exit(){
//		HttpServletRequest request=ServletActionContext.getRequest();
//		HttpSession session=request.getSession();
//		session.invalidate();
//		return "exit";
//	}
//	/**
//	 * 用户管理入口
//	 * @return
//	 */
//	public String sysManage(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("leftAction", "sysLeft.action");
//		request.setAttribute("mainAction", "sysList_countList.action");
//		return "sysManage";
//	}
//	/**
//	 * 左侧目录
//	 * @return
//	 */
//	public String sysLeft(){
//		return "sysLeft";
//	}
//	/**
//	 * 获取用户列表
//	 * @return
//	 */
//	public String countList(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		Map<String, String> map = new HashMap<String, String>();
//		
//		//条件查询
//		String field = request.getParameter("field");
//		String fieldValue = request.getParameter("fieldValue");
//		if(field != null && fieldValue != null && !"".equals(fieldValue) && !"".equals(field)){
//			map.put(field, fieldValue);
//		}
//		//分页
//		int nextPageN = 1;
//		String nextPage = request.getParameter("next");
//		if(nextPage != null){
//			nextPageN = Integer.parseInt(nextPage);
//		}
//		int allCount = sys.getCount("ReqestCount", map);
//		int allPage = (allCount + pageSize - 1)/pageSize;
//		String partPage = FormTag.getPartPage("searchSys.action",0, nextPageN, allPage,allCount, field, fieldValue);
//		
//		//获取列表
//		List<Object> conList = sys.getSysList("ReqestCount", map, nextPageN, pageSize);
//		
//		request.setAttribute("conList", conList);
//		request.setAttribute("partPage", partPage);
//		try {
//			request.setAttribute("search", FormTag.getSearchTag(Class.forName("lxg.model.ReqestCount"), field, fieldValue));
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		return "sysList";
//	}
//	
//	public String search(){
//		countList();
//		return "search";
//	}
//	
//	/**
//	 * 获取整形数据
//	 * @param request
//	 * @return
//	 */
//	public int getInt(HttpServletRequest request, String param){
//		String num = (String)request.getParameter(param);
//		int NUM;
//		//客户端发送数据有误处理--默认访问根目录
//		try{
//			NUM = Integer.parseInt(num);
//		}catch(Exception e){
//			//异常处理,默认返回管理管理员列表
//			return 1;
//		}
//		return NUM;
//	}
//}
