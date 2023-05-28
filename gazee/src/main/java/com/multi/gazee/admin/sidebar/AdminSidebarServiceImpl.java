package com.multi.gazee.admin.sidebar;

import com.multi.gazee.charge.ChargeDAO;
import com.multi.gazee.customerService.CustomerServiceDAO;
import com.multi.gazee.customerService.CustomerServiceVO;
import com.multi.gazee.member.MemberDAO;
import com.multi.gazee.member.MemberVO;
import com.multi.gazee.order.OrderDAO;
import com.multi.gazee.order.OrderVO;
import com.multi.gazee.product.ProductDAO;
import com.multi.gazee.product.ProductVO;
import com.multi.gazee.report.ReportDAO;
import com.multi.gazee.report.ReportVO;
import com.multi.gazee.reportCount.ReportCountDAO;
import com.multi.gazee.reportCount.ReportCountVO;
import com.multi.gazee.set.SetDAO;
import com.multi.gazee.set.SetVO;
import com.multi.gazee.transactionHistory.TransactionHistoryDAO;
import com.multi.gazee.transactionHistory.TransactionHistoryVO;
import com.multi.gazee.withdraw.WithdrawDAO;
import com.multi.gazee.withdraw.WithdrawVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminSidebarServiceImpl implements AdminSidebarService {
    @Autowired
    MemberDAO Mdao;
    @Autowired
    ProductDAO Pdao;
    @Autowired
    CustomerServiceDAO CSdao;
    @Autowired
    ChargeDAO Cdao;
    @Autowired
    ReportDAO Rdao;
    @Autowired
    ReportCountDAO RCdao;
    @Autowired
    OrderDAO Odao;
    @Autowired
    WithdrawDAO Wdao;
    @Autowired
    SetDAO Sdao;
    @Autowired
    TransactionHistoryDAO Tdao;
    
    public String loadDashboard(Model model) {
        List<MemberVO> memberList = Mdao.list();
        List<ProductVO> productList = Pdao.list();
        List<ReportVO> nonPagedReportList = Rdao.nonPagedList();
        List<OrderVO> orderNeedToSetList = Odao.listOrderNeedToSet();
        List<OrderVO> orderList = Odao.listOrder();
        int sum = Wdao.sumCommission();
        model.addAttribute("memberList", memberList);
        model.addAttribute("productList", productList);
        model.addAttribute("reportList", nonPagedReportList);
        model.addAttribute("orderNeedToSetList", orderNeedToSetList);
        model.addAttribute("orderList", orderList);
        model.addAttribute("sum", sum);
        return "../admin/adminDashboard";
    }
    
    public String loadInfo(Model model) throws Exception {
        MemberVO memberVo = Mdao.readAdmin();
        model.addAttribute("adminOne", memberVo);
        return "../admin/adminInfo";
    }
    
    public String loadinfoEdit(Model model) throws Exception {
        MemberVO memberVo = Mdao.readAdmin();
        model.addAttribute("adminOne", memberVo);
        return "../admin/adminInfoEdit";
    }
    
    public String loadMember(Model model) {
        List<MemberVO> memberList = Mdao.list();
        List<MemberVO> newMemberThisWeekList = Mdao.newMemberThisWeek();
        List<MemberVO> memberOfPastThirtyDaysList = Mdao.memberOfPastThirtyDays();
        List<MemberVO> suspendedList = Mdao.suspendedList();
        List<ReportVO> nonPagedReportList = Rdao.nonPagedList();
        
        model.addAttribute("memberList", memberList);
        model.addAttribute("newMemberThisWeekList", newMemberThisWeekList);
        model.addAttribute("memberOfPastThirtyDaysList", memberOfPastThirtyDaysList);
        model.addAttribute("suspendedList", suspendedList);
        model.addAttribute("reportList", nonPagedReportList);
        return "../admin/adminMember";
    }
    
    public String loadOrder(Model model) {
        List<ProductVO> productList = Pdao.list();
        List<OrderVO> orderList = Odao.listOrder();
        List<OrderVO> orderNeedToSetList = Odao.listOrderNeedToSet();
        List<OrderVO> orderFinishedList = Odao.listOrderFinished();
        int orderInProgress = orderList.size() - orderFinishedList.size();
        int sum = Wdao.sumCommission();
        List<SetVO> setList = Sdao.listSet();
        
        model.addAttribute("productList", productList);
        model.addAttribute("orderList", orderList);
        model.addAttribute("orderNeedToSetList", orderNeedToSetList);
        model.addAttribute("orderFinishedList", orderFinishedList);
        model.addAttribute("orderInProgress", orderInProgress);
        model.addAttribute("sum", sum);
        model.addAttribute("setList", setList);
        return "../admin/adminOrder";
    }
    
    public String loadProduct(Model model) {
        List<ProductVO> productList = Pdao.list();
        List<ProductVO> productTodayList = Pdao.listProductToday();
        List<OrderVO> orderList = Odao.listOrder();
        List<OrderVO> orderFinishedList = Odao.listOrderFinished();
        int sum = Odao.sumTotalTrading();
        
        model.addAttribute("productList", productList);
        model.addAttribute("productTodayList", productTodayList);
        model.addAttribute("orderList", orderList);
        model.addAttribute("orderFinishedList", orderFinishedList);
        model.addAttribute("sum", sum);
        return "../admin/adminProduct";
    }
    
    public String loadPay(Model model) {
        List<TransactionHistoryVO> transactionList = Tdao.listTransactionHistory();
        List<WithdrawVO> withdrawList = Wdao.listWithdraw();
        List<MemberVO> bankAccountList = new ArrayList<>(); // 사용자 계좌 목록 담을 리스트
        // withdrawList에서 user 값을 하나씩 꺼내서 Adao.listBankAccount()의 파라미터로 사용
        List<MemberVO> memberList = Mdao.list();
        int sum = Wdao.sumCommission();
        
        for (WithdrawVO withdraw : withdrawList) {
            String user = withdraw.getMemberId();
            List<MemberVO> userBankAccounts = Mdao.listBankAccount(user);
            bankAccountList.addAll(userBankAccounts);
        }
        
        model.addAttribute("seedHistoryList", transactionList);
        model.addAttribute("withdrawList", withdrawList);
        model.addAttribute("memberList", memberList);
        model.addAttribute("bankAccountList", bankAccountList); // 사용자 계좌 목록
        model.addAttribute("sum", sum);
        
        return "../admin/adminPay";
    }
    
    public String loadCs(Model model) {
        List<CustomerServiceVO> nonPagedCsList = CSdao.nonPagedList();
        List<CustomerServiceVO> nonPagedNeedReplyList = CSdao.nonPagedNeedReply();
        model.addAttribute("csList", nonPagedCsList);
        model.addAttribute("nonPagedNeedReplyList", nonPagedNeedReplyList);
        return "../admin/adminCs";
    }
    
    public String loadReport(Model model) {
        List<ReportVO> nonPagedReportList = Rdao.nonPagedList();
        List<ReportVO> nonPagedNeedReplyList = Rdao.nonPagedNeedReply();
        List<MemberVO> needPenaltyList = Mdao.needPenaltyList();
        List<Integer> countList = new ArrayList<>();
        
        
        for (MemberVO member : needPenaltyList) {
            String memberId = member.getId();
            ReportCountVO RCvo = RCdao.one(memberId);
            int count = 0;
            
            if (RCvo != null) {
                count = RCvo.getCount();
            }
            countList.add(count);
        }
        model.addAttribute("reportList", nonPagedReportList);
        model.addAttribute("nonPagedNeedReplyList", nonPagedNeedReplyList);
        model.addAttribute("needPenaltyList", needPenaltyList);
        model.addAttribute("countList", countList);
        
        return "../admin/adminReport";
    }
}
