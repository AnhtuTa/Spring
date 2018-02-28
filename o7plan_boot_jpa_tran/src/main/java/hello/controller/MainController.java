package hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.dao.BankAccountDAO;
import hello.exception.BankTransactionException;
import hello.form.SendMoneyForm;
import hello.entity.BankAccount;

@Controller
public class MainController {
	@Autowired
	BankAccountDAO bad;
	
	@RequestMapping(value= {"/", "account-page"}, method=RequestMethod.GET)
	public String showBankAccounts(Model model) {
		List<BankAccount> accountInfos = bad.getBankAccounts();
		model.addAttribute("accountInfos", accountInfos);
		return "account_page";
	}
	
	@RequestMapping(value="/send-money", method=RequestMethod.GET)
	public String sendMoney(Model model) {
		model.addAttribute("sendMoneyForm", new SendMoneyForm());
		return "send_money_page";
	}
	
	@RequestMapping(value="/send-money", method=RequestMethod.POST)
	public String doSendMoney(Model model,
			@ModelAttribute("sendMoneyForm") SendMoneyForm sendMoneyForm) {
		try {
			bad.sendMoney(sendMoneyForm.getFromAccountId(), 
					sendMoneyForm.getToAccountId(), sendMoneyForm.getAmount());
			return "redirect:/account-page";
		} catch (BankTransactionException e) {
			String errorMessage = e.getMessage();
			model.addAttribute("errorMessage", errorMessage);
			e.printStackTrace();
			return "send_money_page";
			// CHÚ Ý: Không cần phải thiết lập:
			// model.addAttribute("sendMoneyForm", new SendMoneyForm());
			// vì trang trước đã làm rồi!
		}
	}
}
