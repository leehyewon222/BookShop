package com.bookshop01.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;


//@Controller("mainController"): �빐�떦 �겢�옒�뒪瑜� 而⑦듃濡ㅻ윭濡� 吏��젙�븯硫�, 鍮덉쓽 �씠由꾩쓣 "mainController"濡� �꽕�젙�븳�떎.
//@EnableAspectJAutoProxy: AOP瑜� �궗�슜�븯湲� �쐞�븳 �뼱�끂�뀒�씠�뀡�쑝濡�, �씠瑜� �꽕�젙�븯硫� �옄�룞�쑝濡� AOP �봽濡앹떆瑜� �깮�꽦�븯�뿬 �쟻�슜�븳�떎.
@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {
	
	//GoodsServiceImpl.java�뙆�씪�뿉 �옉�꽦 �빐 �넃�� 
	//public class GoodsServiceImpl implements GoodsService {} �쓽
	//<bean>�쓣 �옄�룞 二쇱엯 �빀�땲�떎.
	@Autowired
	private GoodsService goodsService;

	
	//수정 확인을 위한 주석
	// http://localhost:8090/bookShop01/main/main.do �엯�젰�븯�뿬 硫붿씤�솕硫� �슂泥��떆
	// 硫붿씤�솕硫� 以묒븰�뿉 蹂댁뿬以� 踰좎뒪�듃���윭, �떊媛�, �뒪�뀒�뵒 ���윭瑜� 議고쉶�븳�썑  Map�뿉 ���옣�븯�뿬  JSP濡� �쟾�떖�빀�땲�떎.
	@RequestMapping(value= "/main/main.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//�꽭�뀡�쁺�뿭�뿉 side_menu�냽�꽦媛� user瑜� ���옣 �빐 �넃�쑝硫�
		//泥� 硫붿씤 �솕硫� main.jsp�쓽 �쇊履얠궗�씠�뱶硫붾돱 紐⑥뒿�� 鍮꾨줈洹몄씤�맂 �긽�깭濡� �젒�냽�븳 �궗�씠�뱶 �쇊履얜찓�돱瑜� �굹���궡湲� �쐞�빐 �벐�엯�땲�떎.
		//李멸퀬. 洹몃컰�뿉 �꽭�뿰�쁺�뿭�뿉 ���옣�릺�뒗 媛� �솗�씤�쓣 �쐞�빐 common�뤃�뜑�뿉 留뚮뱾�뼱�졇 �엳�뒗 side.jsp瑜� �뿴�뼱 �솗�씤 �빐蹂댁꽭�슂
		HttpSession session;
		session=request.getSession();
		session.setAttribute("side_menu", "user");
				
		ModelAndView mav=new ModelAndView();
		
		//ViewNameIntercetor�겢�옒�뒪�쓽 preHandle硫붿냼�뱶 �궡遺��뿉�꽌 request�뿉 諛붿씤�뵫�븳 酉� 二쇱냼 /main/main �쓣!!!!!!!
		//request.getAttribute("viewName"); �쑝濡� �뼸�뼱 
		//views�뤃�뜑/main�뤃�뜑/main.jsp硫붿씤 �럹�씠吏�濡� 議고쉶�맂 踰좎뒪�듃���윭 , �떊媛�, �뒪�뀒�뵒���윭 �룄�꽌 �젙蹂대�� 蹂대궡湲� �쐞�빐  ModelAndView媛앹껜�뿉 ���옣 (諛붿씤�뵫)
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		//踰좎뒪�듃 ���윭, �떊媛�, �뒪�뀒�뵒 ���윭  �룄�꽌�젙蹂대�� 議고쉶 �빐 Map�뿉 ���옣諛쏄린 �쐞�빐  �꽌鍮꾩뒪�쓽 硫붿냼�뱶 �샇異�!
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods();
		mav.addObject("goodsMap", goodsMap); //ModelAndView�뿉 main.jsp以묒븰�솕硫댁뿉 蹂댁뿬以� 議고쉶�맂 �룄�꽌 �젙蹂닿� ���옣�맂 Map�쓣 ���옣 (諛붿씤�뵫)
		
		  System.out.println("-------------------------------------------------------------------");
	      System.out.println("-------------------------------------------------------------------");
	      System.out.println("�쓲由�2.");
	      System.out.println("MainController而⑦듃濡ㅻ윭 �겢�옒�뒪 �궡遺��쓽 main硫붿냼�뱶 �샇異쒕릺�뿀�쑝硫�");
	      System.out.println("main硫붿냼�뱶 �궡遺��뿉�꽌 ViewNameInterceptor�겢�옒�뒪�뿉�꽌 request�뿉 諛붿씤�뵫�븳 ");
	      System.out.println("酉� 二쇱냼 " + viewName + " �쓣 request.getAttribute('viewName'); �쑝濡� �뼸�쓬");
	      System.out.println("ModelAndView�뿉 " + viewName + " 酉� 二쇱냼 ���옣 �븿.");
	      System.out.println(" �꽭�뀡 硫붾え由� �쁺�뿭�뿉 session.setAttribute('side_menu',user); 濡� ���옣 �븿.");
	      System.out.println(" 'side_menu'�냽�꽦 媛믪뿉 �뵲�씪 �솕硫� �쇊履쎌뿉 �몴�떆�릺�뒗 硫붾돱 �빆紐⑹쓣 �떎瑜닿쾶 �븯湲� �쐞�빐 ���옣 �꽭�뀡�뿉 ���옣 �븯���쓬");
	      System.out.println(" Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods(); �씠肄붾뱶�뿉 �쓽�빐 踰좎뒪�뱶���윭 �떊媛�, �뒪�뀒�뵒���윭 �젙蹂대�� 議고쉶�빐 Map�뿉 ���옣 �썑 諛섑솚 諛쏆뒿�땲�떎.");
	      System.out.println(" 留덉�留됱쑝濡� ModelAndView�뿉 異붽�濡�~ mav.addObject('goodsMap', goodsMap); 肄붾뱶�뿉 �쓽�빐 議고쉶�맂 Map�쓣 ���옣�떆�궢�땲�떎.");
	      System.out.println(" return mav; 援щЦ�뿉 �쓽�빐 ViewNameInterceptor�겢�옒�뒪�쓽 postHandle 硫붿냼�뱶媛� �샇異쒕맗�땲�떎.");
	      
	      
	      System.out.println("-------------------------------------------------------------------");
	      System.out.println("-------------------------------------------------------------------");
	      
	      System.out.println("�쓲由�3. 洹� �썑 tiles_main.xml �뙆�씪�뿉 �옉�꽦�븳 <definition name=/main/main ...> �깭洹몄쓽 name�냽�꽦媛� /main/main�씠 ModelAndView�뿉 ���옣 �뻽�뜕 /main/main怨� �씪移섑븯硫� �빐�떦 酉고뀥�뵆由우쓣 硫붿씤�솕硫댁쑝濡� 蹂댁뿬以띾땲�떎.");
	
		
		return mav;
		
		
		
		
	}
}





